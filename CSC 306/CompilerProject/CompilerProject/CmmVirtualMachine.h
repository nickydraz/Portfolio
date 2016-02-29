/* 
 * File:   CmmVirtualMachine.h
 * Author: Administrator
 *
 * Created on May 9, 2015, 9:56 PM
 */

//Modified by Nicholas Drazenovic
//Modifications made wereto initialization lists, 
//as their previous implementations did not allow VS to compile.

#ifndef CMMVIRTUALMACHINE_H
#define	CMMVIRTUALMACHINE_H
#include <vector>
#include <string>
#include <sstream>
#include <iostream>
#include <stack>
using namespace std;



/**
 * A variable entry contains everything you need to know about a variable. 
 */
class variable_entry
{
private:
  int index;  // index into variable table
  string id;  // variable identifier
  int value;  // runtime value of variable
public:
  int get_index() { return index; }
  string get_id() { return id; }
  int get_value() {return value; }
  void set_value(int value) { this->value = value; }
  string to_string() {return id;}
  // constructor
  variable_entry(int index, string id, int value = 0)
  { 
	  this->index = index;
	  this->id = id;
  }
};

/**
 * A vector of pointers to variable_entry objects.
 */
class cmm_variable_table : public vector<variable_entry *>
{
public:
  void add(string id)
  {
      int index = this->size();
      this->push_back(new variable_entry(index, id));
  }
  // Return the index of an identifier in the variable table, or -1 
  // if not found
  int index_of(string id);
};

/**
 * Base class of all cmm statements
 */
class cmm_stmt
{
public:
  virtual void execute() {throw "Unsupported operation!";}
  virtual string to_string() = 0;
};

/**
 * Declarations of the string table, variable table, and instruction table,
 * and program counter variable
 */
extern vector<string> string_table;
extern cmm_variable_table  variable_table;
extern vector<cmm_stmt *> instruction_table;

extern int cmm_pc;  // program counter
extern int non_fatal_error_count;

class break_stmt;
extern stack<vector<break_stmt *>> break_stmts_stack;
extern stack<int> loop_top_stack;

extern void cmm_execute();



// declarations of functions to print the above tables
void string_table_print(ostream &);
void variable_table_print(ostream &);
void instruction_table_print(ostream &);

/**
 * cmm_super_expr is the base class for all expressions.  
 * String expressions and Arithmetic expressions derive from this class.
 */
class cmm_super_expr
{
public:
    virtual string to_string() = 0;
    virtual int get_value(){ return 0; }
};

// String expressions
class cmm_string_expr : public cmm_super_expr
{    
private:
    int index;  // index into string table
public: 
    // Constructor
    cmm_string_expr(int index) : index{index}{}
    // Member functions
    virtual int get_value() { return index; }
    virtual string to_string() 
    {
        ostringstream s{};
        s << "string: " <<  index;
        return s.str();
    }
};

// Base class of all arithmetic expressions
class cmm_expr: public cmm_super_expr
{
 public:
   virtual  int get_value() { return 0;}
};

// Expression that is a single number. (Represents a  numeric literal)
class number_expr : public cmm_expr
{
  int number;
public:
  // Constructor
  number_expr(int number) : number{number}{}
  // Member functions
  virtual int get_value() { return number; }
  virtual string to_string() 
  {
      ostringstream s{};
      s << number;
      return s.str();
  } 
};

// Expression that is a variable. (All variables are simple identifiers)
class id_expr : public cmm_expr
{
  int index;
public:
  // Constructor
  id_expr(int index) : index{index}{}
  // Member functions  
  virtual int get_value() 
  {
    return variable_table[index]->get_value();
  }
  virtual string to_string() 
  {
      ostringstream s{};
      s << variable_table[index]->get_id();
      return s.str();
  }
};

// Expression built from a unary operator. 
enum class unary_op { minus_op, not_op };
class unary_op_expr : public cmm_expr
{
   unary_op op;
   cmm_expr * p_expr;
public:
     // Constructor
     unary_op_expr(unary_op op, cmm_expr *p_expr)
     : op{op}, p_expr{p_expr}
      {               
      }
     virtual int get_value();
     virtual string to_string();    
};

// Expression built from a binary operator.
enum class binary_op
{     
    // relational operators
    lt_op, le_op, gt_op, ge_op, eq_op, ne_op,  
    // additive operators
    plus_op, minus_op, 
    // multiplicative operators
    mult_op, div_op, mod_op
};

class binary_op_expr : public cmm_expr
{
    binary_op op;
    cmm_expr * p_expr1;
    cmm_expr * p_expr2;
public:
    // Constructor
    binary_op_expr(binary_op op, cmm_expr *p_expr1, cmm_expr *p_expr2)
      : op{op}, p_expr1{p_expr1}, p_expr2{p_expr2}
      {
          
      }
    // Member functions  
    virtual string to_string();
    virtual int get_value();
};

// Different types of statements

// goto 
class goto_stmt : public cmm_stmt
{
protected:
    int target;
    string tag = "";
public:
    // Constructor
	goto_stmt(int target)
	{
		this->target = target;
	}
    // Member functions    
    virtual string to_string() 
    {
        ostringstream s{};
        s << tag << "goto " << target;
        return s.str();
    } 
    void patch(int target){this->target = target; };
    void execute();
    
    int get_target() { return target; }
};

// break statement
class break_stmt : public goto_stmt
{
public:
    // Constructor
    break_stmt(int target): goto_stmt{target} { tag = "break: ";};
    
};

// Continue statement
class continue_stmt : public goto_stmt
{
public:
    // Constructor
    continue_stmt(int target): goto_stmt{target} { tag = "continue: ";};

};


// Assignment statement
class assign_stmt : public cmm_stmt
{
    string id;
    cmm_expr * p_expr;
public:
    // Constructor
    assign_stmt(string id, cmm_expr * p_expr)
    {
		this->id = id;
		this->p_expr = p_expr;
    }
    // Member functions
    string to_string() 
    {
        ostringstream s{};
        s <<  "assign " << id << ": " << p_expr->to_string();
        return s.str();
    }
    
    void execute();
    
};

// Output statement
class output_stmt : public cmm_stmt
{    
    vector <cmm_super_expr *> output_exprs;
public:
    // Default constructor is fine
    // Member functions
    void add_expr(cmm_super_expr *p_super_expr) 
    {
        output_exprs.push_back(p_super_expr);
    }
    virtual string to_string(); 
    
    void execute();
         
};

// Input statement
class input_stmt : public cmm_stmt
{
    vector <string> var_ids;
public:
    // Default constructor is fine
    // Member functions
    void add_var_id(string id) 
    {
        var_ids.push_back(id);
    }
    virtual string to_string(); 
    
    void execute();
    
};

// Conditional Transfer
class if_false_goto_stmt : public goto_stmt
{    
    cmm_expr * p_expr;
public:
    // Constructor
    if_false_goto_stmt(string label, cmm_expr* p_expr) 
    : goto_stmt{-1}, p_expr{p_expr}
    {
        tag = label + ": ";
    } 
    virtual string to_string()
    {
        ostringstream s{};
        s << target; 
        return tag + " " + p_expr->to_string() + " target: " +  s.str();
    }
    
    void execute();
    
    cmm_expr * get_expr()
    {
      return p_expr;
    }
};

// Conditional transfer at top of while loop
class top_while_stmt : public if_false_goto_stmt
{
public:
    top_while_stmt(cmm_expr *p_expr) : if_false_goto_stmt("while", p_expr){}
    
   
};

// Conditional transfer at top of if statement
class top_if_stmt : public if_false_goto_stmt
{
public:
    top_if_stmt(cmm_expr *p_expr) : if_false_goto_stmt("if", p_expr){}
     
};

// Use for loop, end loop, end while, and else
class marker_stmt : public cmm_stmt
{
    string marker;
public:
    // Constructor
	marker_stmt(string marker)
	{
		this->marker = marker;
	}
    // Member functions
    virtual string to_string() { return marker; }    
    
    void execute() { }
};

// Use for exit
class exit_stmt : public cmm_stmt
{
  public:
    virtual string to_string() { return "exit"; }
    void execute();
};

// Use new newline
class newline_stmt : public cmm_stmt
{
  public:
    virtual string to_string() { return "newline"; }
    void execute();
};

#endif	/* CMMVIRTUALMACHINE_H */


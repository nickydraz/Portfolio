//Modified by Nicholas Drazenovic

#include "CmmVirtualMachine.h"
#include <map>
#include <iostream>
#include <stack>
#include <cstdlib>


// Global variables
vector<string> string_table;
vector<cmm_stmt *> instruction_table;
cmm_variable_table  variable_table;
int cmm_pc = 0;

// positions of top of loop in the instruction table
stack<int> loop_top_stack;

//stack of lists of break statements that appear in a loop
stack<vector<break_stmt *>> break_stmts_stack;

//Counter for number of non fatal errors found during compilation
int non_fatal_error_count = 0;

void cmm_execute()
{
    if (non_fatal_error_count > 0)
    {
         throw "CMM program has errors.\n";
    }
    while (true)
    {
       cmm_stmt * p_current_stmt = instruction_table[cmm_pc];
       //cerr << "executing statement at " << cmm_pc << endl;
       cmm_pc ++;
       p_current_stmt->execute();
    }
}

void string_table_print(ostream &out)
{
   out << "String Table"  << endl;
   for (int k = 0; k < string_table.size(); k++)
   {
     out << k << " : " << string_table[k] << endl;
   }
}
void variable_table_print(ostream &out)
{
    out << "Variable Table" << endl;
    for (int k = 0; k < variable_table.size(); k++)
    {
        out << k << ": " << variable_table[k]->get_id() << endl;
    }
}
void instruction_table_print(ostream &out)
{
    out << "Instruction Table" << endl;
    for (int k = 0; k < instruction_table.size(); k++)
    {
		out << k << ": " << instruction_table[k]->to_string() << endl;
    }
}



/**
 * Searches a variable table for an entry for a given id
 * and returns the index of the id within the table, or -1.
 * @param id
 * @return 
 */
int cmm_variable_table::index_of(string id)
{
    int index = size()-1;
    for  ( ; index >= 0; index--)
    {
        if ((*this)[index]->get_id() == id)
            return index;
    }
    return index;
}

// to_string member function
string unary_op_expr::to_string()
{
    ostringstream s{};
    switch(op)
    {
        case unary_op::minus_op : 
            s << "-"; break;
        case unary_op::not_op :
            s << "!"; break;            
    }
    s << this->p_expr->to_string();
    return s.str();     
}

// get_value member function
int unary_op_expr::get_value()
{
    switch(op)
    {
        case unary_op::minus_op : 
            return - this->p_expr->get_value();
        case unary_op::not_op : 
            return ! this->p_expr->get_value();            
    }
}

// to_string member function
map<binary_op, string> binary_op_to_string_map
{
    {binary_op::div_op, "/"},
    {binary_op::mult_op, "*"},
    {binary_op::mod_op, "%"},
    {binary_op::plus_op, "+"},
    {binary_op::minus_op, "-"},
    {binary_op::lt_op, "<"},
    {binary_op::le_op, "<="},
    {binary_op::gt_op, ">"},
    {binary_op::ge_op, ">="},
    {binary_op::ne_op, "!="},
    {binary_op::eq_op, "=="},    
};

// to_string member function
string binary_op_expr::to_string()
{
    ostringstream s{};    
    s << "(" << this->p_expr1->to_string() << ")"
      << binary_op_to_string_map[this->op] 
      << "(" << this->p_expr2->to_string() << ")";
    return s.str();     
}

//get_value for binary expression
int binary_op_expr::get_value()
{
    switch (op)
    {
        case binary_op::div_op:
            return this->p_expr1->get_value() / this->p_expr2->get_value();
        case binary_op::mult_op:
            return this->p_expr1->get_value() * this->p_expr2->get_value();
    	case binary_op::mod_op:
            return this->p_expr1->get_value() % this->p_expr2->get_value();
        case binary_op::plus_op:
            return this->p_expr1->get_value() + this->p_expr2->get_value();
        case binary_op::minus_op:
            return this->p_expr1->get_value() - this->p_expr2->get_value();
        case binary_op::lt_op:
            return this->p_expr1->get_value() < this->p_expr2->get_value();
        case binary_op::le_op:
            return this->p_expr1->get_value() <= this->p_expr2->get_value();
        case binary_op::gt_op:
            return this->p_expr1->get_value() > this->p_expr2->get_value();
        case binary_op::ge_op:
            return this->p_expr1->get_value() >= this->p_expr2->get_value();
        case binary_op::ne_op:
            return this->p_expr1->get_value() != this->p_expr2->get_value();
        case binary_op::eq_op:
            return this->p_expr1->get_value() == this->p_expr2->get_value();
    }//end switch
}//end binary_op_expr::get_value

// to_string member function
string output_stmt::to_string()
{
    ostringstream s{"output: "};
    s << "cout  ";
    for (auto p : this->output_exprs)
    {
        s << "[" << p->to_string()<< "] ";
    }
    return s.str();
}

// to_string member function
string input_stmt::to_string()
{
    ostringstream s{};
    s << "cin ";
    for (auto id : this->var_ids)
    {
        s << "[" <<  id << "] ";
    }
    return s.str();
}

/* Execute implementations for 
   subclasses of cmm_stmt */ 
            
void goto_stmt::execute()
{
    //Adjust the pc to the proper goto target
    cmm_pc = this->target;
}

void assign_stmt::execute()
{
    //Access the variable in the table,
    //then set its value to the expr passed to the assign_stmt
    variable_table[variable_table.index_of(id)]->set_value(p_expr->get_value());
}

void output_stmt::execute()
{
    for (int k = 0; k < output_exprs.size(); k++)
    {
        //Runtime Type Identification
        cmm_string_expr * p_str_expr = dynamic_cast<cmm_string_expr *>(output_exprs[k]);
        
        //If a string, get from the string table
        //else, get the value of the expression
        if (p_str_expr != NULL)
            cout << string_table[output_exprs[k]->get_value()];
        else
            cout << output_exprs[k]->get_value();
    }//end for
}

void input_stmt::execute()
{
	for (int k = 0; k < var_ids.size(); k++)
    {
        //Get the input from the user
        //Store in int because int is the only
        //data type in cmm
        int number;
        cin >> number;
        
        //Assign the user's input to the next value in var_ids
        variable_table[variable_table.index_of(var_ids[k])]->set_value(number);
    }   
}

void if_false_goto_stmt::execute()
{
    //Determine if the expression is
    //true or false.
    //If false, then set the pc 
    //to the 'if_false_goto' target
    if (p_expr->get_value() == 0)
        cmm_pc = this->target;        
}

void exit_stmt::execute()
{
    //Create a newline in the console,
    //for readability purposes.
    cout << endl;
    
    exit(0);
}

void newline_stmt::execute()
{
    cout << endl;
}
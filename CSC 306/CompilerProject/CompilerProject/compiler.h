//modified by Nicholas Drazenovic
#include <cstdlib>
#include <cassert>
#include "lex.h"
#include "CmmVirtualMachine.h"
#include <set>



// member variables for token classification
const set<Token_Type> stmt_begin // start symbols for STMT
{
	Token_Type::t_id, Token_Type::t_cout,
	Token_Type::t_cin, Token_Type::t_if,
	Token_Type::t_while, Token_Type::t_loop,
	Token_Type::t_break, Token_Type::t_continue,
	Token_Type::t_exit, Token_Type::t_newline

};
const set<Token_Type> expr_begin // start symbols for expressions
{
	Token_Type::t_number, Token_Type::t_id,
	Token_Type::t_lparen, Token_Type::t_rparen,
	Token_Type::t_not, Token_Type::t_minus
};
const set<Token_Type> relops
{
	Token_Type::t_lt, Token_Type::t_le, Token_Type::t_gt,
	Token_Type::t_ge, Token_Type::t_ne, Token_Type::t_eq
};
const set<Token_Type> addops
{
	Token_Type::t_plus, Token_Type::t_minus
};
const set<Token_Type> multops
{
	Token_Type::t_mult, Token_Type::t_div, Token_Type::t_mod
};

class Compiler
{
public:
	Compiler(ifstream *source_filename, ofstream* listing_filename)
		:
		lex{ source_filename, listing_filename }
	{
		// get the lookahead token
		token = lex.get_token();
	}

	void compile()
	{
		program();
	}
	

private:
	// Parsing methods for non-terminals
	void vars(); void vardeclist();
	void type_id(); void stmtlist(); void stmt();
	void assignstmt(); void outputstmt(); void inputstmt();
	void ifstmt(); void whilestmt(); void loopstmt();
	void output_expr(cmm_super_expr *&); void expr(cmm_expr *&); void comp_expr(cmm_expr *&);
	void simple_expr(cmm_expr *&); void factor(cmm_expr *&);

	// Various utilities for token classification
	bool is_relop(Token_Type t)
	{
		return relops.find(t) != relops.end();
	}
	bool is_multop(Token_Type t)
	{
		return multops.find(t) != multops.end();
	}
	bool is_addop(Token_Type t)
	{
		return addops.find(t) != addops.end();
	}
	bool is_stmt_begin(Token_Type t)
	{
		return stmt_begin.find(t) != stmt_begin.end();
	}
	bool is_expr_begin(Token_Type t)
	{
		return expr_begin.find(t) != expr_begin.end();
	}

	// Recognize an expected token
	void accept(Token_Type t);

	// Various member variables
	Lex lex; // lexical analyzer

	Token_Type token; // lookahead token

	// Interface to lexical analyzer
	inline Token_Type get_token() { return lex.get_token(); }
	inline string get_lexeme() { return lex.get_lexeme(); }

	void compiler_fatal_error(const string &message)
	{
		lex.error(message);
		exit(1);
	}

	// Error message for when an unexpected token is found.
	void syntax_error(Token_Type found,
		initializer_list<Token_Type> expected);

	// Generic error message
	void syntax_error(const string &message)
	{
		compiler_fatal_error(message);
	}

	//Non fatal error message
	void error(const string &message)
	{
		lex.error(message);
		non_fatal_error_count++;
	}
	//Method for processing entire program
	void program();


};

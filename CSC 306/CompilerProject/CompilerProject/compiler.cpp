//Written by Nicholas Drazenovic

#include "compiler.h"
#include <sstream>
#include <string>


//Method for the start of a program/source file
//PROGRAM ::= VARS begin STMTLIST end
void Compiler::program()
{

	vars();						 //Accept any vars
	accept(Token_Type::t_begin); //Accept the beginning of the program
	stmtlist();					 //Work through any statements
	accept(Token_Type::t_end);	 //Accept the end of the program
	
	//And an exit line to the instruction table
	instruction_table.push_back(new exit_stmt());

}//end program

//Method for accepting any variable declarations
//VARS ::= { VARDECLIST }
void Compiler::vars()
{
	//While the token is of type int
	//Works for C-- since there are no other 
	//variable types
	while (token == Token_Type::t_int)
	{
		vardeclist();
	}
}//end vars

//method that cycles through any variable 
//declarations 
//VARDECLIST ::= TYPEID id { , id }
void Compiler::vardeclist()
{
	//Check the type of the variable
	//should only ever be int in C--
	type_id();
	string id = get_lexeme();

	//Add to variable table
	if (variable_table.index_of(id) != -1)
	{
		error("Variable " + id + " already in table");
	}
	else
	{
		variable_table.add(id);
	}

	accept(Token_Type::t_id);

	//If a comma is found, then cycle through the list of
	//variables
	while (token == Token_Type::t_comma)
	{
		token = get_token();
		id = get_lexeme();
		//Add to variable table
		if (variable_table.index_of(id) != -1)
		{
			error("Variable " + id + " already in table");
		}
		else
		{
			variable_table.add(id);
		}

		accept(Token_Type::t_id);

	}//end while

	//semicolon marks the end of the variable declarations
	accept(Token_Type::t_semi);

}//end vardeclist

//Method to verify that 
//the type of a variable is an int
//TYPEID ::= int
void Compiler::type_id()
{
	accept(Token_Type::t_int);
}

//Method to cycle through
//and accept statements
//STMTLIST ::= { STMT; }
void Compiler::stmtlist()
{
	while (is_stmt_begin(token))
	{
		stmt();
		accept(Token_Type::t_semi);  //each statement is followed by a semicolon

	}//end while

}//end stmtlist

//Check for what kind of statement is currently being processed
//STMT ::= ASSIGNSTMT | OUTPUTSTMT | INPUTSTMT
//STMT ::= IFSTMT | WHILESTMT | LOOPSTMT
//STMT ::= break | continue
//STMT ::= exit | newline
void Compiler::stmt()
{
	switch (token)
	{
	case Token_Type::t_id:
	{
		assignstmt();
		break;
	}
	case Token_Type::t_cout:
	{
		outputstmt();
		return;
	}
	case Token_Type::t_cin:
	{	inputstmt();
		break;
	}
	case Token_Type::t_if:
	{
		ifstmt();
		break;
	}
	case Token_Type::t_while:
	{	whilestmt();
		break;
	}
	case Token_Type::t_loop:
	{	loopstmt();
		break;
	}
	case Token_Type::t_break:
	{	if (loop_top_stack.empty())
			{
				error("Break statment outside of a loop/while loop.");
			}
		else
			{
				break_stmt * brk = new break_stmt(-1); 
				instruction_table.push_back(brk);
				break_stmts_stack.top().push_back(brk);
			}
		
		token = get_token();
		break;
	}
	case Token_Type::t_continue:
	{
		if (loop_top_stack.empty())
			{
				error("Continue statment outside of a loop/while loop.");
			}
		else
			instruction_table.push_back(new continue_stmt(loop_top_stack.top()));
		
		token = get_token();
		break;
	}
	case Token_Type::t_exit:
	{
		instruction_table.push_back(new exit_stmt());
		token = get_token();
		break;
	}
	case Token_Type::t_newline:
	{
		instruction_table.push_back(new newline_stmt());
		token = get_token();
		break;
	}
	default:
	{
		syntax_error(token, { Token_Type::t_id, Token_Type::t_cout, Token_Type::t_cin,
			Token_Type::t_if, Token_Type::t_while, Token_Type::t_loop,
			Token_Type::t_break, Token_Type::t_continue });
	}
	}//end switch
}//end stmt

//Method for processing an assignment statement
//ASSIGNSTMT ::= id = EXPR
void  Compiler::assignstmt()
{
	string id = get_lexeme();
	cmm_expr * p_expr;

	//assignments must begin with an id
	accept(Token_Type::t_id);
	
	int index = variable_table.index_of(id);
	if (index == -1)
	{
		error("Variable " + id + " not declared");
		variable_table.add(id);
	}
		
	accept(Token_Type::t_assign);	//Verify that the assign token followed the id
	expr(p_expr);							//Process the right hand side of the assignment

	instruction_table.push_back(new assign_stmt(id, p_expr));
}//end assignstmt

//Method for processing output statements
//OUTPUTSTMT ::= cout << OUTPUTEXPR { << OUTPUTEXPR }
void Compiler::outputstmt()
{
	//All outputs begin with cout
	accept(Token_Type::t_cout);
	accept(Token_Type::t_insertion);			//Verify that an insertion symbol follows the cout token


	output_stmt * output = new output_stmt();	//pointer to hold the entire output expression
	cmm_super_expr * p_output_expr;				
	output_expr(p_output_expr);					//Process the rest of the output expression
	output->add_expr(p_output_expr);			//Add expression to the output statement


	//While more is being chained onto the insertion,
	//continue to process the output statement
	while (token == Token_Type::t_insertion)
	{
		token = get_token();
		output_expr(p_output_expr);
		output->add_expr(p_output_expr);
	}//end while

	//Add to instruction table
	instruction_table.push_back(output);

}//end outputstmt

//Method for processing an input statement
//INPUTSTMT ::= cin >> id { >> id }
void  Compiler::inputstmt()
{
	input_stmt * input = new input_stmt();

	//All input statements begin with cin
	accept(Token_Type::t_cin);
	accept(Token_Type::t_extraction);	//Verify that an extraction symbol follows the cin token
	//accept(Token_Type::t_id);			//Verify that an id is being extracted from
	string id = get_lexeme();
	if (variable_table.index_of(id) == -1)
	{
		error("Variable " + id + " not declared");
		variable_table.add(id);
	}

	//Add to input table
	input->add_var_id(id);

	accept(Token_Type::t_id);

	//While more is being chained onto the extraction,
	//continue to process the input statement
	while (token == Token_Type::t_extraction)
	{
		token = get_token();

		string id = get_lexeme();
		if (variable_table.index_of(id) == -1)
		{
			error("Variable " + id + " not declared");
			variable_table.add(id);
		}

		//add to table
		input->add_var_id(id);

		accept(Token_Type::t_id);

	}//end while

	//Add to instruction table
	instruction_table.push_back(input);
}//end inputstmt

//Method to process if statements
//IFSTMT ::= if EXPR then STMTLIST end if
//IFSTMT ::= if EXPR then STMTLIST else STMTLIST end if
void  Compiler::ifstmt()
{
	cmm_expr * p_expr;

	//All if statements must begin with if token
	if (token == Token_Type::t_if)
	{
		token = get_token();		//Get the next token
		expr(p_expr);						//Process the expression within the if statement

		//create top of the if statement
		top_if_stmt * if_stmt = new top_if_stmt(p_expr);
		instruction_table.push_back(if_stmt);

		accept(Token_Type::t_then);	//Verfy that a then token follows the if expression
		stmtlist();					//Process the statements that will execute within the if statement
		
		goto_stmt * go_to = new goto_stmt(-1);
		
		instruction_table.push_back(go_to);
		
		if (token == Token_Type::t_else)
		{
			token = get_token();
			instruction_table.push_back(new marker_stmt("else"));
			if_stmt->patch(instruction_table.size() - 1);
			stmtlist();
		}
			
		accept(Token_Type::t_end);	
		accept(Token_Type::t_if);
		instruction_table.push_back(new marker_stmt("end if"));
		
		//If there was an else statement, the if_stmt
			//will have already been patched
		if (if_stmt->get_target() == -1)
			if_stmt->patch(instruction_table.size() - 1);
			
		go_to->patch(instruction_table.size() - 1);
	}
	else
		syntax_error(token, { Token_Type::t_if });
}//end ifstmt

//Method to process while statements
//WHILESTMT ::= while EXPR do STMTLIST end while
void  Compiler::whilestmt()
{
	int top_of_loop_index;
	cmm_expr * p_expr;

	//All while statements being with the while token
	accept(Token_Type::t_while);
	expr(p_expr);					//Process the expression for the while

	//Add top of while to instruction table
	top_while_stmt * while_stmt = new top_while_stmt(p_expr);
	instruction_table.push_back(while_stmt);

	break_stmts_stack.emplace();
	
	//set the index for the goto statement
	top_of_loop_index = (instruction_table.size() - 1);
	
	loop_top_stack.push(top_of_loop_index);

	accept(Token_Type::t_do);		//Verify that a do token follows the expression
	stmtlist();						//Process the statement(s) that will execute within the while statement

	//Add goto statement to top of while
	instruction_table.push_back(new goto_stmt(top_of_loop_index));
	accept(Token_Type::t_end);		//Verify that an end token follows the statements, signalling the end of the loop
	accept(Token_Type::t_while);	//while token must follow end token to signal what is ending
		
	//Add marker for end of the while
	instruction_table.push_back(new marker_stmt("end while"));
	while_stmt->patch(instruction_table.size() - 1);
	
	//Patch the break statements
	if (!break_stmts_stack.empty())
	{
		vector<break_stmt *> vt = break_stmts_stack.top();
		for (int j = 0; j < vt.size(); j++)
		{
			vt[j]->patch(instruction_table.size());
		}
		
		break_stmts_stack.pop();
	}//end if

	loop_top_stack.pop();
}//end whilestmt

//Method to process loop statements
//LOOPSTMT ::= loop STMTLIST else STMTLIST end if
void  Compiler::loopstmt()
{
	//All loop statements begin with loop
	accept(Token_Type::t_loop);

	//Add marker for start of the loop
	instruction_table.push_back(new marker_stmt("loop"));

	break_stmts_stack.emplace();
	
	//Index for target of goto at end of loop
	int top_of_loop_index = instruction_table.size() - 1;

	loop_top_stack.push(top_of_loop_index);
	
	stmtlist();					//Process statement(s) that will execute within the loop

	//Add marker for the goto, with the target back to top of loop
	instruction_table.push_back(new goto_stmt(top_of_loop_index));
	accept(Token_Type::t_end);	//Verify that an end token follows, signalling the end of the loop
	accept(Token_Type::t_loop);	//loop token must follow end token to signal what is ending

	//Add marker for the end of the loop
	instruction_table.push_back(new marker_stmt("end loop"));
	
	if (!break_stmts_stack.empty())
	{
		vector<break_stmt *> vt = break_stmts_stack.top();
		for (int j = 0; j < vt.size(); j++)
		{
			vt[j]->patch(instruction_table.size());
		}
		
		break_stmts_stack.pop();
	}//end if
	
	loop_top_stack.pop();
}//end loopstmt

//Method to process output expressions
//OUTPUTEXPR ::= EXPR | string
void  Compiler::output_expr(cmm_super_expr *& p_output_Expr)
{

	//Output expression must begin with a token that is found 
	//in the expr_begin set
	if (is_expr_begin(token))
	{
		cmm_expr * p_expr;
		expr(p_expr); //Process the expression
		p_output_Expr = p_expr;
	}
	else if (token == Token_Type::t_string) //May also be a string, if not found in the set
	{
		//Add to string table
		string_table.push_back(get_lexeme());
		p_output_Expr = new cmm_string_expr(string_table.size() - 1);

		token = get_token();
	}
	else
		syntax_error(token, { Token_Type::t_number, Token_Type::t_id,
		Token_Type::t_lparen, Token_Type::t_rparen,
		Token_Type::t_not, Token_Type::t_minus });
}//end output_expr

//Method to process expressions
//EXPR ::= COMPEXPR [ RELOP COMPEXPR ]
void  Compiler::expr(cmm_expr *& p_expr)
{
	cmm_expr * p_expr2;

	//cmm_expr * p_new_expr;
	comp_expr(p_expr); //process the complex expression

	//If token is found in relation operations set
	if (is_relop(token))
	{
		binary_op current_op;

		//Check which binary operator is being used
		switch (token)
		{
		case Token_Type::t_lt:
			current_op = binary_op::lt_op;
			break;
		case Token_Type::t_le:
			current_op = binary_op::le_op;
			break;
		case Token_Type::t_gt:
			current_op = binary_op::gt_op;
			break;
		case Token_Type::t_ge:
			current_op = binary_op::ge_op;
			break;
		case Token_Type::t_ne:
			current_op = binary_op::ne_op;
			break;
		case Token_Type::t_eq:
			current_op = binary_op::eq_op;
			break;
		}//end switch

		token = get_token(); 	 //Then get the next token
		comp_expr(p_expr2);		 //And process the complex expression

		p_expr = new binary_op_expr(current_op, p_expr, p_expr2);
	}
}//end expr

//Method to process complex expressions
//COMPEXPR ::= SIMPLEEXPR { ADDOP SIMPLEEXPR }
void  Compiler::comp_expr(cmm_expr *& p_expr)
{
	cmm_expr * p_expr2;

	//Process simple expression
	simple_expr(p_expr);

	//While the token is found in the addop set
	while (is_addop(token))
	{
		binary_op current_op;
		
		//Check which binary operator is being used
		if (token == Token_Type::t_plus)
			current_op = binary_op::plus_op;
		else
			current_op = binary_op::minus_op;

		token = get_token();
		simple_expr(p_expr2);

		p_expr = new binary_op_expr(current_op, p_expr, p_expr2);
	}//end while

}//end comp_expr

//Method to process simple expressions
//SIMPLEEXPR ::= FACTOR { MULTOP FACTOR }
void  Compiler::simple_expr(cmm_expr *& p_expr)
{
	cmm_expr * p_expr2;

	//Call to factor 
	factor(p_expr);

	//While token is found in multop set
	while (is_multop(token))
	{
		binary_op current_op;

		//Check which binary operator is being used
		if (token == Token_Type::t_mult)
			current_op = binary_op::mult_op;
		else if (token == Token_Type::t_mod)
			current_op = binary_op::mod_op;
		else
			current_op = binary_op::div_op;

		token = get_token();
		factor(p_expr2);

		p_expr = new binary_op_expr(current_op, p_expr, p_expr2);
	}//end while

}//end simple_expr

//Method to process factor symbols
//FACTOR ::= number | id | (EXPR) | !FACTOR | -FACTOR
void  Compiler::factor(cmm_expr *& p_expr)
{
	string id;
	int index;

	switch (token)
	{
		cmm_expr * ptrFactor;

	case Token_Type::t_number:
		p_expr = new number_expr(atoi(get_lexeme().c_str()));
		token = get_token();
		break;
	case Token_Type::t_id:
		id = get_lexeme();
		index = variable_table.index_of(id);
		if (index == -1)
		{
			error("Variable " + id + " not declared");
			variable_table.add(id);
		}
		p_expr = new id_expr(variable_table.index_of(get_lexeme()));
		token = get_token();
		break;
	case Token_Type::t_lparen:
		token = get_token();
		expr(p_expr);
		accept(Token_Type::t_rparen);
		break;
	case Token_Type::t_not:
		token = get_token();
		factor(ptrFactor);
		p_expr = new unary_op_expr(unary_op::not_op, ptrFactor);
		break;
	case Token_Type::t_minus:
		token = get_token();
		factor(ptrFactor);
		p_expr = new unary_op_expr(unary_op::minus_op, ptrFactor);
		break;
	default:
		syntax_error(token, { Token_Type::t_number, Token_Type::t_id,
			Token_Type::t_lparen, Token_Type::t_not, Token_Type::t_minus });
	}//end switch
}//end factor

//Method for verifying that the token beign examined 
//matches the token that is expected
void Compiler::accept(Token_Type t)
{
	if (token == t)
	{
		token = get_token();
	}
	else
	{
		syntax_error(token, { t });
	}
}//end accept

//Method for printing an error to listing file
void Compiler::syntax_error(Token_Type found, initializer_list<Token_Type> expected)
{
	assert(expected.size() != 0);
	ostringstream message{};
	message << "Expected ";
	if (expected.size() > 1)
	{
		message << " one of : ";
	}
	for (auto p = expected.begin();;)
	{
		message << lex.token_stringfy(*p);
		p++;
		// Is it the last one ?
		if (p != expected.end())
		{
			message << ", ";
			continue;
		}
		break;
	}
	message << " but found " << lex.token_stringfy(found);
	compiler_fatal_error(message.str());
}
//Modified by Nicholas Drazenovic

#include "lex.h"
#include <fstream>
#include <iostream>
#include <cctype>
#include <cstdlib>

using namespace std;

Token_Type Lex::get_token()
{
	static char ch = ' ';
	lexeme = "";

	//Check for white spaces
	while (isspace(ch))
	{
		ch = get_char();
	}

	//Non-white space
	if (ch == '_' || isalpha(ch))
	{
		lexeme += ch;
		ch = get_char();

		while (isalpha(ch) || ch == '_')
		{
			lexeme += ch;
			ch = get_char();
		}

		//Look up lexeme in keyword map
		if (keywords_map.count(lexeme) == 0)
			return Token_Type::t_id;

		return keywords_map.find(lexeme)->second;
	}

	if (isdigit(ch))
	{
		while (isdigit(ch))
		{
			lexeme += ch;
			ch = get_char();
		}
		return Token_Type::t_number;
	}//end is digit

	switch (ch)
	{

	case '+':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_plus;
	case '-':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_minus;
	case '*':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_mult;
	case '/':
		lexeme += ch;
		ch = get_char();

		if (ch == '*')
		{
			while (ch != EOF)
			{
				ch = get_char();
				if (ch == '*')
				{
					ch = get_char();
					if (ch == '/')
					{
						ch = get_char();
						return get_token();
					}
				}
			}//end while
			fatal_error("Program ended while scanning multi-line comment.");
		}//end if '*'
		else if (ch == '/')
		{
			ch = get_char();

			while (ch != '\n')
			{
				ch = get_char();
			}

			ch = get_char();
			return get_token();

		}//end if '/'
		else
		{
			return Token_Type::t_div;
		}
	case '%':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_mod;
	case '=':
		lexeme += ch;
		ch = get_char();
		if (ch == '=')
		{
			lexeme += ch;
			ch = get_char();
			return Token_Type::t_eq;
		}
		else
			return Token_Type::t_assign;
	case '!':
		lexeme += ch;
		ch = get_char();

		if (ch != '=')
		{
			return Token_Type::t_not;
		}
		else
		{
			lexeme += ch;
			ch = get_char();
			return Token_Type::t_ne;
		}
	case '<':
		lexeme += ch;
		ch = get_char();
		if (ch == '=')
		{
			lexeme += ch;
			ch = get_char();
			return Token_Type::t_le;
		}
		else if (ch == '<')
		{
			lexeme += ch;
			ch = get_char();
			return Token_Type::t_insertion;
		}
		return Token_Type::t_lt;
	case '>':
		lexeme += ch;
		ch = get_char();
		if (ch == '=')
		{
			lexeme += ch;
			ch = get_char();
			return Token_Type::t_ge;
		}
		else if (ch == '>')
		{
			lexeme += ch;
			ch = get_char();
			return Token_Type::t_extraction;
		}
		return Token_Type::t_gt;
	case ',':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_comma;
	case ';':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_semi;
	case '(':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_lparen;
	case ')':
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_rparen;
	case '"':
		ch = get_char();

		while (ch != '"')
		{
			lexeme += ch;
			
			if (ch == EOF)
			{
				fatal_error("Program ended while scanning string.");
			}
			
			ch = get_char();
			
		}

		ch = get_char();
		return Token_Type::t_string;
	case EOF:
		return Token_Type::t_eof;
	default:
		lexeme += ch;
		ch = get_char();
		return Token_Type::t_unknown;
	}//end switch

}
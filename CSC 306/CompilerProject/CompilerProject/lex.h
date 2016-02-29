//Modified by Nicholas Drazenovic
#include <string>
#include <vector>
#include <fstream>
#include <iostream>
#include <map>
#include <time.h>
#include <cstdlib>

using namespace std;

enum class Token_Type
{
	// keywords
	t_begin, t_break, t_cin, t_continue, t_cout, t_do, t_else, t_end, t_if,
	t_int, t_loop, t_then, t_while, t_newline, t_exit,
	// identifier, number, and string tokens
	t_id, t_number, t_string,
	// various operators
	t_plus, t_minus, t_mult, t_div, t_mod, t_assign, t_not,
	// relational operators
	t_lt, t_le, t_gt, t_ge, t_ne, t_eq,
	//io operators
	t_insertion, t_extraction,
	// various punctuation symbols
	t_comma, t_semi, t_lparen, t_rparen,
	// unknown and eof tokens
	t_unknown, t_eof
};

// These establish the correspondence between tokens and 
// the stringfyed versions of those tokens, for example 
// the t_begin token corresponds to the string "t_begin"
// Note: order of the vector entries is tied to order of Token_Type constants!
const vector < string > token_tostring
{
	"t_begin", "t_break", "t_cin", "t_continue", "t_cout",
	"t_do", "t_else", "t_end", "t_if", "t_int", "t_loop", "t_then", "t_while",
	"t_id", "t_number", "t_string", "t_plus", "t_minus", "t_mult",
	"t_div", "t_mod", "t_assign", "t_not",
	"t_lt", "t_le", "t_gt", "t_ge", "t_ne", "t_eq",
	"t_insertion", "t_extraction",
	"t_comma", "t_semi", "t_lparen", "t_rparen",
	"t_unknown", "t_eof",
};

const map<string, Token_Type> keywords_map
{
	{ "begin", Token_Type::t_begin },
	{ "break", Token_Type::t_break },
	{ "cin", Token_Type::t_cin },
	{ "continue", Token_Type::t_continue },
	{ "cout", Token_Type::t_cout },
	{ "do", Token_Type::t_do },
	{ "else", Token_Type::t_else },
	{ "end", Token_Type::t_end },
	{ "if", Token_Type::t_if },
	{ "int", Token_Type::t_int },
	{ "loop", Token_Type::t_loop },
	{ "then", Token_Type::t_then },
	{ "while", Token_Type::t_while },
	{ "newline", Token_Type::t_newline },
	{ "exit", Token_Type::t_exit }
};



class Lex
{
public:

	//Constructor
	

	//clock for tracking run time
	clock_t time = clock();

	Token_Type get_token();

	string get_lexeme()
	{
		return lexeme;
	}
	string token_stringfy(Token_Type t)
	{
		return token_tostring[static_cast<int> (t)];
	}


	void error(const string &message)
	{
		*listing_file << "*** Error *** " << message << endl;
	}

	void fatal_error(const string &message)
	{
		cout << "\nRUN FAILED (exit value 1, total time: " << (clock() - time) << "ms).\n\n";
		*listing_file << "*** Error *** " << message << endl;
		exit(1);
	}
	// Constructor
	Lex(istream *source, ostream *listing)
	{
		source_file = source;
		listing_file = listing;
	}
	// Destructor
	//~Lex();

private:
	// Get next character from file and echo to listing file
	char get_char()
	{
		char ch = source_file->get();
		if (ch != -1)
		{
			listing_file->put(ch);
		}
		return ch;
	}
	// various member variables
	string lexeme;
	istream* source_file;
	ostream* listing_file;
	// string source_filename, listing_filename;
};
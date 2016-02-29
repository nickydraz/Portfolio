//Program for checking if
//parens are balanced
//Coded by Nicholas Drazenovic

public class ParensChecker {
	
	//Create the stack
	static MyGenericStack<Character> stack;

	public static void main(String[] args) {
		
		//Array to hold the Strings to be tested
		String[] strings = new String[5];
		
		//Create Strings to be tested
		strings[0] = "( a + b ) / 9";
		strings[1] = ") a + b (";
		strings[2] = "((x-y)/(9-z)";
		strings[3] = "noparens";
		strings[4] = "(((x-y)/(9-z)))";
		
		//Check each String
		for (int i = 0; i < strings.length; i++)
		{
			if (parensChecker(strings[i]))
				System.out.println(strings[i] + " is balanced.");
			else
				System.out.println(strings[i] + " is NOT balanced.");
		}
		
	}//end main
	
	//============================================//
	//				parensChecker
	// Method that will take a String as input,
	// which will then be scanned for any parens.
	// The parens will then be check for balance,
	// and return true if balanced, false otherwise
	//============================================//
	static boolean parensChecker(String par)
	{
		
		//Create the queue
		//Give extra room, just in case
		stack = new MyGenericStack<Character>(20);
		
		//Scan the String
		for (int i = 0; i < par.length(); i++)
		{
			//if open paren, push to stack
			if (par.charAt(i) == '(')
				stack.push('(');
			else if (par.charAt(i) == ')') //if close parens, pop from stack
			{
				//if the stack is already empty, parens not balanced
				if (stack.empty())
					return false;
				
				//If not empty, will pop
				stack.pop();
			}
				
		}//end for
		
		if (stack.empty())
			return true;
		else
			return false;
	}//end parensChecker

}

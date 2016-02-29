//Coded by Nicholas Drazenovic

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class mainProg {

	public static void main(String[] args) throws IOException {
	
		//Read in the text file
		//Open the file
		//Create file reader
		FileReader freader = new FileReader("palindromes.txt");
		BufferedReader inFile = new BufferedReader(freader);
		
		//Read in the first line
		String line = inFile.readLine();
		
		//while there is a next line
		while (line != null)
		{
			//Store the original String for printing
			String original = line;
			
			//Ignore the case
			line = line.toLowerCase();
			
			//Remove any punctuation
			line = line.replaceAll("[^a-z]+",  "");
			
			//check if the word is a palindrome
			if (palCheck(line))
			{
				System.out.println(original + " is a palindrome!");
			}
			else
			{
				System.out.println(original + " is not a palindrome.");
			}
			
			//read in the next line
			line = inFile.readLine();
			
		}//end while
		
		//Clean up
		inFile.close();
		freader.close();
		
	}//end main
	
	//Method to check if a word is a palindrome
	public static boolean palCheck(String word)
	{
		
		//Create the stack
		Stack<Character> stack = new Stack<Character>();
		
		//For loop to traverse the String
		//Will push it to the stack
		for (int i = 0; i < word.length(); i++)
		{
			stack.push(word.charAt(i));
		}//end for
		
		//Now pop the stack into a String
		String checked = "";
	
		while (!stack.empty())
		{
			checked += stack.pop();
		}//end while
		
		//Now compare!
		if (word.equals(checked))
			return true;
		else
			return false;
	}//end palCheck
	
	
}//end class

//Coded by Nicholas Drazenovic

import java.util.Scanner;

public class palMain {

	public static void main(String[] args) {
		//create scanner
		Scanner kb = new Scanner(System.in);
		
		//create String to hold user input
		String pal;
		
		//int to hold validity
		int valid = 1;
		
		//Explain to user
		System.out.println("This program will test if input is a palindrome or not.");
		System.out.println("The program will disregard any numbers or other non-alphabetic characters.");
		System.out.println("If you wish to exit the program, simply enter 'done' without the quotation marks.");
		
		//Prompt user for sequence
		System.out.println("Please enter a sequence of characters to be tested: ");
		pal = kb.nextLine();
		
		//exit program if user types in 'done'
		while (!(pal.equals("done")))
		{
			valid = 0;
			//remove all non-alphabetic characters
			pal = pal.toLowerCase().replaceAll("\\W",  "");
			pal = pal.replaceAll("[^a-z]+", "");
			valid = checker.palCheck(pal, 0,  pal.length() - 1, valid);
			
			if (valid == -1)
			{
				System.out.println("The sequence '" + pal + "' is not a palindrome.");
			}
			else
				System.out.println("The sequence '" + pal + "' is a palindrome.");
			
			System.out.println("Please enter another sequence to check or 'done' to exit: ");
			pal = kb.nextLine();
		}//end if
		
		if (pal.equals("done"))
			System.out.println("Goodbye!");
		
		//clean up
		kb.close();
	}//end main

}//end class

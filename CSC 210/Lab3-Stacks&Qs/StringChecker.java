//Program for checking if two Strings
//are the same
//Coded by Nicholas Drazenovic

import java.util.Scanner;

public class StringChecker {

	public static void main(String[] args) {
		
		//Create scanner
		Scanner kb = new Scanner(System.in);
		
		//Greet the user
		System.out.println("\t\t\tWelcome to the String Checker!\n");
		//Prompt the user
		System.out.println("Please enter all Strings in the "
				+ "following format: String1:String2");
		System.out.println("Please enter your String now, "
				+ "or enter \"done\" to exit: ");
		String check = kb.next();
		
		while (!check.equalsIgnoreCase("Done"))
		{
			//Input validation
			if (!check.contains(":"))
			{
				System.out.println("\nApologies, please try again with the "
						+ "following format: "
						+ "\n\n\t\tString1:String2"
						+ "\n\nEnter the String now or \"done\" to exit: ");
				check = kb.next();
				
			}//end if
			else
			{
			//Call the method and check the Strings
			stringCheck(check);
			
			//Prompt user for next input
			System.out.println("\nPlease enter the next String "
					+ "or \"done\" to exit: ");
			check = kb.next();
			}
		}//end while
		
		
		//Close the keyboard
		kb.close();
		
	}//end main

	//================= stringChecker ===============
	//
	// Method that takes a single String as input,
	// and splits it using a delimiter (:).
	// It then compares the separated Strings
	// to tell if they are the same or not.
	//
	//===============================================
	static boolean stringCheck(String s)
	{
		
		//Create the queues
		//One to hold the String from each side of the colon
		MyGenericQueue<Character> queue = new MyGenericQueue<Character>(20);
		MyGenericQueue<Character> queue2 = new MyGenericQueue<Character>(20);
		
		//Counter used when adding to the queues
		int i = 0;
		
		//Deposit the first String
		while (s.charAt(i) != ':' || s.equals(""))
		{
			//Add character to the queue
			queue.enqueue(s.charAt(i));
			
			//Increment counter
			i++;
		}//end while
	
		//Be sure to skip that mean ol' colon!
		//Increment again to move past the delimiter
		i++;
		
		//Deposit the second String
		while (i != s.length())
		{
			//Add character to the queue
			queue2.enqueue(s.charAt(i));
		
			//increment the counter
			i++;
		}//end while
		
		//Holds size of queue to be used when using the for loops.
		int size = queue2.size();
		
		//If different lengths, don't both checking the Strings.
		if (size != queue.size())
		{
			System.out.println("Different lengths.");
			return false;
		}//end if
		
		//Compare the Strings against each other
		for (int n = 0; n < size; n++)
		{
			if (queue.empty() || queue2.empty() || queue.dequeue() != queue2.dequeue())
			{
				//If the same length, but you find a discrepancy,
				//Tell the user, and return false
				System.out.println("Same length, but different substrings.");
				return false;
			}//end if
			
		}//end for
		
		//If the Strings matched, the queues should both be empty
		//Return true if so
		if(queue.empty() && queue2.empty())
		{
			System.out.println("Same substrings.");
			return true;	
		}//end if
		else
		{
			//Should never hit this return statement. 
			//If here, something went wrong. 
			//Have the user try again and return false.
			System.out.println("Something went wrong with comparing your Strings. "
					+ " Please try another String.");
			return false;
		}//end else
		
	}//end stringCheck
	
}//end class

/**Sample Output:
 * 
 * 
				Welcome to the String Checker!

Please enter all Strings in the following format: String1:String2
Please enter your String now, or enter "done" to exit: 
first:second
Different lengths.

Please enter the next String or "done" to exit: 
need:food
Same length, but different substrings.

Please enter the next String or "done" to exit: 
hello:
Different lengths.

Please enter the next String or "done" to exit: 
:
Same substrings.

Please enter the next String or "done" to exit: 
ha:ha
Same substrings.

Please enter the next String or "done" to exit: 
validationTest

Apologies, please try again with the following format: 

		String1:String2

Enter the String now or "done" to exit: 
done

 * 
 */



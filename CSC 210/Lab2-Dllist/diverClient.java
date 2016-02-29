//Main client for diver record program
//Coded by Nicholas Drazenovic

import java.util.Scanner;

public class diverClient {

	//Create a Scanner
	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//Create the list
		Dllist diveRecords = new Dllist();
		
		//Greet the user & get their selection
		System.out.println("Welcome to the Diving Record Keeper.");
		System.out.println("Please make a choice on what you would like to do.");
		System.out.println("Enter 0 to enter a diver's record, \n"
				+ "or 1 to query the data; \n"
				+ "alternatively, enter 99 to exit the program: ");
		int selection = kb.nextInt();
		
		//loop until user exits
		while (selection != 99)
		{
			//use switch statement to create a main menu
			switch(selection)
			{
				//Case 0 = user wants to unput data into the list
				case 0: 
				{
					inputData(diveRecords);
					break;
				}
				//case 1 = user wants to retrieve information about the list
				case 1:
				{
					queryData(diveRecords);
					break;
				}
			}//end switch
			
			//Get the user's next choice
			System.out.println("What would you like to do now?");
			System.out.println("Enter 0 to enter a new record, "
					+ "1 to query the current records, "
					+ "or 99 to exit the program: ");
			
			selection = kb.nextInt();
			
		}//end while
		
	}//end main

	public static void inputData(Dllist diveRecords)
	{
	
		//Get user input
		System.out.println("Enter 0 to enter a new record or 99 to return to the main menu: ");
		int choice = kb.nextInt();
		
		//loop this menu until the user enters 99
		while (choice !=99)
		{
			//Ensures the user doesn't enter any numbers besides 0 and 99
			if (choice != 0 && choice != 99)
			{
				System.out.println("Invalid option.");
				System.out.println("Please enter either 0, to enter a new record, or 99, to return to the main menu: ");
				choice = kb.nextInt();	
			}
			else //if they enter 0, have them enter a record in
			{
				//Get the diver's name
				System.out.println("Please enter the name of the diver: ");
				String name = kb.next();
				
				//Get the diver's score
				System.out.println("Please enter their score: ");
				double score = kb.nextDouble();
				
				//Show the user, to confirm
				System.out.println("You have just entered " + name + ", with a score of " + score + ".");
						
				//Create the new node and add it to the list
				diveRecords.add(score, name);
			}
			
			//Get the next choice from the user
			System.out.println("What would you like to do next?");
			System.out.println("Enter 0 to enter a new record or 99 to return to the main menu: ");
			choice = kb.nextInt();
			
		}//end while
		
	}//end inputData
	
	public static void queryData(Dllist diveRecords)
	{
		
		//Get user input/display what they can do
		System.out.println("\n\nWhat would you like to do with the records?");
		System.out.println("0: Display the list in ascending score order");
		System.out.println("1: Display the list in descending score order");
		System.out.println("2: Display the score for a certain diver");
		System.out.println("3: Display the name and score of the diver with the lowest score");
		System.out.println("4: Display the name and score of the diver with the highest score");
		System.out.println("99: Return to the main menu");
		
		System.out.println("Menu selection: ");
		int choice = kb.nextInt();
		
		//loop the menu until user enters 99
		while (choice != 99)
		{
			switch(choice)
			{
				//case 0 = user wants list in ascending order
				case 0:
				{
					diveRecords.displayListAscending();
					break;
				}
				//case 1 = user wants list in descending order
				case 1:
				{
					diveRecords.displayListDescending(diveRecords.getHead());
					break;
				}
				//Case 2 = user wants to get score of a specific diver
				case 2:
				{
					//Get the name of the diver wanted
					System.out.println("Enter the name of the diver whose score you would like to see: ");
					String name = kb.next();
					
					//Look through the list
					double score = diveRecords.getScore(name);
					if (score == -1)
						System.out.println("Record not found.");
					else
						System.out.println(name + "'s score is " + score);
					
					break;
				}
				//Case 3 = user wants to see the lowest score/name
				case 3: 
				{
					diveRecords.displayLowest();
					break;
				}
				//Case 4 = user wants to see the highest score/name
				case 4:
				{
					diveRecords.displayHighest();
					break;
				}
			
			}//end switch
			
			//Get the next choice/display their options again
			System.out.println("\n\nWhat would you like to do with the records?");
			System.out.println("0: Display the list in ascending score order");
			System.out.println("1: Display the list in descending score order");
			System.out.println("2: Display the score for a certain diver");
			System.out.println("3: Display the name and score of the diver with the lowest score");
			System.out.println("4: Display the name and score of the diver with the highest score");
			System.out.println("99: Return to the main menu");
			
			System.out.println("Menu selection: ");
			choice = kb.nextInt();
			
		}//end while
	
	}//end queryData
	
}//end  class

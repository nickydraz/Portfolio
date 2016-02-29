//Coded by Nicholas Drazenovic
//Program to read user data from file, 
//Prompt user to log in
//If log in data matches data from file, 
//Print out greeting, else indicate so

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class prog1 {

	public static void main(String[] args) throws IOException {

		//create scanner
		Scanner kb = new Scanner(System.in);
		
		//variables to hold user input
		String idInput;
		String pwInput;
		
		//read user data from file
		prog1 prog = new prog1();
		prog.readFile();
		
		//prompt user for userid
		System.out.println("Please enter your userID followed by #: ");
		idInput = kb.next();
		idInput = idInput.replaceAll("#", "");
		
		System.out.println("Please enter your password followed by #: ");
		pwInput = kb.next();
		pwInput = pwInput.replaceAll("#", "");
		
		//validate input
		findID(idInput, custList, pwInput);
		
		//cleanup
		kb.close();

	}//end main
	
	//create list to hold each customer object
	private final static List<custAccount> custList = new ArrayList<custAccount>();
	
	//begin Methods
	//Method to read the user data in from the file
		private void readFile() throws IOException
		{
			//String to hold line being read
			String line;
			
			//Creates reader to open input file
			FileReader freader = new FileReader("accountData.txt");
			BufferedReader inFile = new BufferedReader(freader);
			
			//Read first line		
			line = inFile.readLine();


			
			//While loop to read until out of lines
			while (line != null)
			{
				//create scanner
				Scanner s = new Scanner(line);
			
				//read in data from file
				String firstName = s.next();
				String lastName = s.next();				
				String title = s.next();
				String userid = s.next();
				String password = s.next();
						
				//create customer object to hold each section of data
				custAccount customer = new custAccount(firstName, lastName, title, userid, password);
				//add object to list
				custList.add(customer);
				
				//read next line
				line = inFile.readLine();
				
				//clean up
				s.close();
				
			}//end while loop
			
			//clean up
			inFile.close();
			
		}//end readFile
		
		//method for converting data to numeric sequences
		public static String let2num(String original)
		{
			//String to hold converted data
			String converted = "";
			//Convert the data to all uppercase so that case-sensitivity is not an issue
			original = original.toUpperCase();
			
			//For loop to go through the original String, checking each character
			for (int i = 0; i < original.length(); i++)
			{
				//Nested if-statements will be used to convert each letter in String to its T9 equivalence
				//Will then return the converted String to allow for easier matching with user input.
				
				/**Table for matching T9 to Alphabetic input:
				 * 1 --> N/A
				 * 2 --> A, B, C
				 * 3 --> D, E, F
				 * 4 --> G, H, I
				 * 5 --> J, K, L
				 * 6 --> M, N, O
				 * 7 --> P, Q, R, S
				 * 8 --> T, U, V
				 * 9 --> W, X, Y, Z
				 * 0 --> N/A
				**/
				
				if ((original.charAt(i) == 'A') || (original.charAt(i) == 'B') || (original.charAt(i) == 'C'))
				{
					converted = converted + '2';
				}
				else
					if ((original.charAt(i) == 'D') || (original.charAt(i) == 'E') || (original.charAt(i) == 'F'))
					{
						converted = converted + '3';
					}
					else
						if ((original.charAt(i) == 'G') || (original.charAt(i) == 'H') || (original.charAt(i) == 'I'))
						{
							converted = converted + '4';
						}
						else
							if ((original.charAt(i) == 'J') || (original.charAt(i) == 'K') || (original.charAt(i) == 'L'))
							{
								converted = converted + '5';
							}
							else
								if ((original.charAt(i) == 'M') || (original.charAt(i) == 'N') || (original.charAt(i) == 'O'))
								{
									converted = converted + '6';
								}
								else
									if ((original.charAt(i) == 'P') || (original.charAt(i) == 'Q') || (original.charAt(i) == 'R') || (original.charAt(i) == 'S'))
									{
										converted = converted + '7';
									}
									else
										if ((original.charAt(i) == 'T') || (original.charAt(i) == 'U') || (original.charAt(i) == 'V'))
										{
											converted = converted + '8';
										}
										else
											if ((original.charAt(i) == 'W') || (original.charAt(i) == 'X') || (original.charAt(i) == 'Y') || (original.charAt(i) == 'Z'))
											{
												converted = converted + '9';
											}
											else
											{
												//final else clause will activate if not a letter, but a number
												//Will then simply place the number into the data sequence and continue on to the next character in the String
												converted = converted + original.charAt(i);
											}//End Nested If-Else statements
			}//end for loop
			
			return converted;
		}//end let2num
		
		
		//method for searching the array for the matching userid
		//will keep separate from password method for search
		public static void findID(String id, List<custAccount> custList, String pw)
		{
			//if valid is -1, no match
			//if valid is anything else, match found
			int valid = -1;
			
			//use for loop to search for matching userid
			//if match is found, call findPW method, passing in the value of the index that the match was found. 
			for (int i = 0; i < custList.size(); i++)
			{
				//convert ID from index to numeric sequence
				String convID = let2num(custList.get(i).getID());
				if (convID.contains(id))
				{
					valid = findPW(i, pw, custList);
				}
			}//end for
			
			//if match is found, print greeting, else say so
			if (valid != -1)
			{
				//get time of day
				Calendar cal = new GregorianCalendar();
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int min = cal.get(Calendar.MINUTE);
				
				if (hour < 12)
				{
					//print greeting
					System.out.println("Good morning, " + custAccount.getTitle(custList, valid) + " " + custAccount.getLast(custList, valid) + ".");

					//formatting
					if (min < 10)
					{
						System.out.println("It is currently " + hour + ":0" + min);
					}
					else
						System.out.println("It is currently " + hour + ":" + min);
				}//end if hour
				else
				{
					System.out.println("Good evening, " + custAccount.getTitle(custList, valid) + " " + custAccount.getLast(custList, valid) + ".");
					
					//formatting
					if (min < 10)
					{
						System.out.println("It is currently " + hour + ":0" + min);
					}
					else
						System.out.println("It is currently " + hour + ":" + min);
				}
			}//end if
			else
				System.out.println("Invalid userID or password.");

		}//end findID
		
		
		
		//method to search array for matching password
		public static int findPW(int i, String pw, List<custAccount> custList)
		{
			//if valid is -1, no match
			//if valid is anything else, match found
			int valid = -1;
			
			//use index from for loop in findID method to search specific index of list
			//This will determine if the password in that index belongs to the id
			//convert PW from index to numeric sequence
			String convPW = let2num(custList.get(i).getPass());
			if (convPW.contains(pw))
			{
				valid = i;
			}
			
			return valid;
		}//end findPW

}//end prog1

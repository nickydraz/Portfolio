//Coded by Nicholas Drazneovic
//This file is to hold methods

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;


public class methodCentral {

	//Strings to hold user input data
	static String input = "";
	static String userID = "";
	static String userPW = "";
	
	//boolean variable to indicate if user is finished entering in data
	//Used to tell window to close
	static boolean done = false;
	
	//create list to hold each customer object from file
	static ArrayList<custAccount> custList = new ArrayList<custAccount>();

	//Begin get/set methods 
	//Set the boolean to show if user is finished entering in data
	public static void setDone(boolean d)
	{
		done = d;
	}
	
	//method to set the user's input
	public static void setInput(char in)
	{
		//if user indicated end of input
		if (in == '#')
		{
			//Do not add anything to input String 
		}
		else //add the user's input to input String
			input += in;
	}
	
	
	//method to clear user's input
	//will be used after getting the first input data, so that program can get second input data
	public static void clearInput()
	{
		input = "";
	}
	
	//method to retrieve user's input
	public static String getInput()
	{
		return input;
	}
	
	//method to get the custList
	public static ArrayList<custAccount> getList()
	{
		return custList;
	}
	
	//Method to set userID
	public static void setID(String id)
	{
		userID = id;
	}
	
	//method to set userPW
	public static void setPW(String pw)
	{
		userPW = pw;
	}
	
	//get methods for userID and PW
	public static String getID()
	{
		return userID;
	}
	
	public static String getPW()
	{
		return userPW;
	}
	
	public static boolean getDone()
	{
		return done;
	}
	
	//end get/set methods
	
	
	//Method to read the user data in from the file
		public static void readFile() throws IOException
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
	public static String findID(String id, List<custAccount> custList, String pw)
	{
		//String to hold output message
		String output = "";
		
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
				output += "<html><center><font size=+5>Good morning, " + custAccount.getTitle(custList, valid) + " " + custAccount.getLast(custList, valid) + ".";

				//formatting based on time
				if (min < 10)
				{
					output += "<br>It is currently " + hour + ":0" + min + "</font></center></html>";
				}
				else
					output+= "<br>It is currently " + hour + ":" + min + "</font></center></html>";
			}//end if hour
			else
			{
				output += "<html><center><font size=+5>Good evening, " + custAccount.getTitle(custList, valid) + " " + custAccount.getLast(custList, valid) + ".";
				
				//formatting based on time
				if (min < 10)
				{
					output += "<br>It is currently " + hour + ":0" + min + "</font></center></html>";
				}
				else
					output += "<br>It is currently " + hour + ":" + min + "</font></center></html>";
			}

		}//end if
		else //show dialog indicating user was not found
			output = "<html><center><font size=+5>Invalid userID or password." + "<br><br>Exiting application.</font></center></html>";
			
		//return output String
		return output;

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
	
}//end class

//coded by Nicholas Drazenovic

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class prog2 {
	static Scanner kb = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		//declare variables
		boolean done = false; //flag for menu program
		char Choice; //holds user's menu choice
		
		//create scanner
		Scanner kb = new Scanner(System.in);
		
		//read file data
		readFile();
		
		//main loop
		
		while (!done)
		{
			displayMenu();
			Choice = getChoice();
			
			//switch to determine action to take based on user choice
			switch(Choice)
			{
			case 'A':
				displaySecondMenu();
				Choice = getSecondChoice();
				switch(Choice)
				{
				case 'X':
					done = true;
					break;
				case '1':
					findAllHourly();
					break;
				case '2':
					findAllSalaried();
					break;
				case '3':
					findAllSupervisor();
					break;
				default:
					done = true;
				}//end mini switch
				break;
			case 'B':
				System.out.println("Enter the ID of the employee: ");
				String input = kb.nextLine();
				findByID(input);
				break;
			case 'X':
				done = true;
			default:
				done = true;
				
			}//end switch
		}//end while

		//cleanup
		kb.close();
	}//end main

	
	//list to hold all employee objects
	private final static ArrayList<Employee> workerList = new ArrayList<Employee>();
	
	//method to display menu 
	public static void displayMenu()
	{
		System.out.print("\n\n\t\tEmployee Lookup Program\n\n\n");
		System.out.print("\tA) Find all employees with a given title\n\n");
		System.out.print("\tB) Find a single employee\n\n");
		System.out.print("\tX) Exit the program\n\n");
		
		System.out.print("\t\tEnter your choice: ");
	}//end displayMenu
	
	//method to display secondary menu
	public static void displaySecondMenu()
	{
		
		System.out.print("\t1) Hourly Employees\n\n");
		System.out.print("\t2) Salaried Employees\n\n");
		System.out.print("\t3) Supervisory Employees\n\n");
		
		System.out.print("\t\tEnter 1, 2, or 3: ");
		
	}//end displaySecondMenu
		
	//method to get user's menu choice
	public static char getChoice()
	{
		final String err = "Please enter a valid menu option";
		byte errcount = 1; //count number of invalid choices
		String input; //keyboard input
		char pick; //user's choice

		input = kb.next().toUpperCase();
		pick = input.charAt(0);
		
		//while loop to ensure proper input
		while (((pick != 'A') && (pick != 'B') && (pick != 'X')) && (errcount < 3))
		{
			errcount++;
			System.out.println(err);
			System.out.print("\t\tEnter your choice: ");
			input = kb.next().toUpperCase();
			pick = input.charAt(0);
		}//end while
		
		//program will exit if invalid option is entered 3 times
		if (errcount == 3)
			pick = 'X';
		System.out.println("");
		
		return pick;
	}//end getChoice
	
	public static char getSecondChoice()
	{
		final String err = "Please enter a valid menu option";
		byte errcount = 1; //count number of invalid choices
		String input; //keyboard input
		char pick; //user's choice
		

		input = kb.next();
		pick = input.charAt(0);
		
		//while loop to ensure proper input
		while (((pick < '1') || (pick > '3')) && (errcount < 3))
		{
			errcount++;
			System.out.println(err);
			System.out.print("\t\tEnter your choice: ");
			input = kb.next();
			pick = input.charAt(0);
		}//end while
		
		//program will exit if invalid option is entered 3 times
		if (errcount == 3)
			pick = 'X';
		System.out.println("");
		
		return pick;	
	}
	
	//method to read in file
	public static void readFile() throws IOException
	{
		//create String to hold line
		String line;
		
		//create file reader
		FileReader freader = new FileReader("employeeData.txt");
		BufferedReader inFile = new BufferedReader(freader);
		
		//read in first line
		line = inFile.readLine();
		
		//while loop to read until end of file is reached
		while (line != null)
		{
			Scanner s = new Scanner(line);
			//tokens separated by ;
			s.useDelimiter(";");
			
			//read each piece and store in variable
			String title = s.next();
			String name = s.next();
			String address = s.next();
			String id = s.next();
			
			
			if (title.equals("Hourly"))
			{
				//Hourly has Employee traits and hourly rate and hours worked
				String hourlyRate = s.next();
				double hr = Double.parseDouble(hourlyRate);
				String hoursWorked = s.next();
				double hw = Double.parseDouble(hoursWorked);
				
				Employee worker = new Hourly(title, name, address, id, hr, hw);
				workerList.add(worker);
			}
			else if (title.equals("Salaried"))
			{
				//Salaried has Employee traits and annual pay
				String annualPay = s.next();
				double annPay = Double.parseDouble(annualPay);
			
				Employee worker = new Salaried(title, name, address, id, annPay);
				workerList.add(worker);
			}
			else if (title.equals("Supervisor"))
			{
				//Supervisor has Employee traits, with annual pay, bonus, 
				//and a list of ID numbers of workers that report to them
				String annualPay = s.next();
				double annPay = Double.parseDouble(annualPay);
				String bonus = s.next();
				double bon = Double.parseDouble(bonus);
				
				//Create list to hold ID numbers of employees who report to Supervisor
				ArrayList<String> IDList = new ArrayList<String>();
				//String to hold each ID number
				String workerID;
				
				//ID numbers separated by space
				s.useDelimiter(" ");
				
				
				//while loop to read next token until end of line
				while (s.hasNext())
				{
				workerID = s.next();
				workerID = workerID.replaceAll(";", "");
				IDList.add(workerID);
				
				}//end while for ID numbers
				
				Employee worker = new Supervisor(title, name, address, id, annPay, bon, IDList);
				workerList.add(worker);
			}//end else/if
			
			//clean up
			s.close();
			//read in next line
			line = inFile.readLine();
		}//end while for file
		
		//clean up
		inFile.close();
		
	}//end readFile
	
	//method to find all hourly employees
	public static void findAllHourly()
	{
		System.out.println("  \tName\t\t  ID\t   Gross\n\t\t\t\t Weekly Pay");
		System.out.println("\t-----------------------------------");
		for (int i = 0; i < workerList.size(); i++)
		{
			String T = workerList.get(i).getTitle();
			if (T.equals("Hourly"))
			{
				System.out.printf("%20s\t%6s\t$%8.2f", workerList.get(i).getName(), workerList.get(i).getID(), workerList.get(i).getGrossWeeklyPay());
				System.out.println("");
			}
		}//end for
		
	}//end finadAllHourly
		
	//method to find all salaried employees
	public static void findAllSalaried()
	{
		System.out.println("  \tName\t\t  ID\t   Gross\n\t\t\t\t Weekly Pay");
		System.out.println("\t-----------------------------------");
		for (int i = 0; i < workerList.size(); i++)
		{
			String T = workerList.get(i).getTitle();
			if (T.equals("Salaried"))
			{
				System.out.printf("%20s\t%6s\t$%8.2f", workerList.get(i).getName(), workerList.get(i).getID(), workerList.get(i).getGrossWeeklyPay());
				System.out.println("");
			}
		}//end for
	
	}//end findAllSalaried
	
	//method to find all supervisor employees
	public static void findAllSupervisor()
	{
		System.out.println("  \tName\t\t  ID\t   Gross   \tDirect Reports"
				+ "\n\t\t\t\t Weekly Pay");
		System.out.println("  ---------------------------------------------------------------------------");
		for (int i = 0; i < workerList.size(); i++)
		{
			String T = workerList.get(i).getTitle();
			if (T.equals("Supervisor"))
			{
				System.out.printf("%20s\t%6s\t$%8.2f", workerList.get(i).getName(), workerList.get(i).getID(), workerList.get(i).getGrossWeeklyPay());
				System.out.printf("\t%s", workerList.get(i).getIDList());
				System.out.println("");
			}
		}//end for
		
	}//end findAllSupervisor
	
	//method to find employee by ID#
	public static void findByID(String input)
	{
		//string to hold id from list
		String id;
		
		//search the list
		int i = 0;
		id = workerList.get(i).getID();
		
		while (!(id.equals(input)) && (i < workerList.size()))
		{
		i++;
		if (i < workerList.size())
			id = workerList.get(i).getID();
		}
		
		if (i != workerList.size())
		{
			System.out.printf("%20s\t%6s\t%10s Employee", workerList.get(i).getName(), workerList.get(i).getID(), workerList.get(i).getTitle());
		}
		else
			System.out.println("Employee not found.");
	}//end findByID
	
}//end class

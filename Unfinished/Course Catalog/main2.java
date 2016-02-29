/**This version of the main program will
 * read in a text file of the course catalog,
 * Then will print out lines to a second file
 * if they are lines containing either 
 * a) Department titles, or
 * b) Individual course headers (number, name, credits)
 * 
 * Will reconfigure after the text file has been made. 
 * 
 */


import java.io.*;
import java.util.ArrayList;

public class main2 {

	public static void main(String[] args) throws IOException {


	}//end main

	
	//method to read in original course catalog text file
	//Will convert to cleaner txt file
	public void readFile() throws IOException
	{
		//Creates reader to open input file
		FileReader freader = new FileReader("UndergraduateCatalog2013.txt");
		BufferedReader inFile = new BufferedReader(freader);
		
		//Create writer to write to output file
		FileWriter fwriter = new FileWriter("CleanCatalogVersion.txt");
		PrintWriter outFile = new PrintWriter(fwriter);

		//Read in first line
		String line = inFile.readLine();
		
		//String dept = "";
		
		//Read in each line of the file, based on number of lines in file
		for (int i = 0; i <= 4675; i++)
		{
			if (line == null)
			{
				line = inFile.readLine();
			}
			else if (line.matches("[a-zA-Z]*"))
			{
				line = inFile.readLine();
			}
			else if (line.charAt(0) == '*')
			{
				//dept = line;
				outFile.println("============================================================");
				outFile.println(line + "\n\n");
				line = inFile.readLine();
			}
			else if (Character.isDigit(line.charAt(0)))
			{
				outFile.println(line + "\n");
				line = inFile.readLine();
			}
			else
			{
				line = inFile.readLine();
			}
		}//end for
		
		outFile.println("\n\n\n\n\n\nEND OF CATALOG\n\n\n\n\n");
		
		//Clean up
		inFile.close();
		freader.close();
		fwriter.close();
		outFile.close();
	}
	
	//Method to read in cleaner version of txt file
	//Will store in series of Array List
	public void createMaster() throws IOException
	{
		//Creates reader to open input file
		FileReader freader = new FileReader("UndergraduateCatalog2013.txt");
		BufferedReader inFile = new BufferedReader(freader);
		
		
	}
}//end class

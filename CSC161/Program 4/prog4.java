//Coded by Nicholas Drazenovic

import java.io.*;
import java.util.Scanner;

public class prog4 {

	public static void main(String[] args) throws IOException {
	
		//Create the Binary Tree
		BinaryTree BT = new BinaryTree();
		
		
		/**
		 * 
		 * Read in the list of words
		 * 
		 */
		//Open the file
		//Create file reader
		FileReader freader = new FileReader("wordlist.txt");
		BufferedReader inFile = new BufferedReader(freader);
		
		//Read in first line
		String line = inFile.readLine();
		
		//While there are more lines
		while (line != null)
		{
			//Ignore case of words
			line = line.toLowerCase();
			
				Scanner s = new Scanner(line);
				
				//While there are more words in the line
				while (s.hasNext())
				{
					
				String word = s.next();
				
				//Get rid of any punctuation
				word = word.replaceAll("[^a-z]+",  "");
				
				//Insert into the tree
				BT.insert(word);
				
				}//End while
				
				//Clean up
				s.close();
				
				//Read next line
				line = inFile.readLine();

		}//End while
		
		//Clean up
		inFile.close();
		freader.close();
		
		/**
		 * Done reading the list of words
		 */
		

		/**
		 * Read in the text file to be checked
		 */
		
		//Open the file
		//Create file reader
		freader = new FileReader("Chapter1OfLifeOnTheMississippiByMarkTwain.txt");
		inFile = new BufferedReader(freader);
		
		//Read in the first line
		line = inFile.readLine();
		
		//There's always a pesky chapter title
			//Let's check for that and skip it if necessary
		if (line.toLowerCase().contains("chapter"))
				line = inFile.readLine();

		//While there are more lines
		while (line != null)
		{
			//ignore case of words
			line = line.toLowerCase();
			
			//Remove any punctuation
			//The em dash is being replaced with a full space 
				//so that the words are not smashed together, but checked on their own
			//Hyphens are OK, because its purpose is essentially to combine two or more words
				//So let's just remove it and smash the words together
			line = line.replaceAll("--", " ");
			line = line.replaceAll("\\p{Punct}+", "");
			
			//Create the scanner that will traverse the line
			Scanner s = new Scanner(line);
			
			//While there are more words in the line
			while (s.hasNext())
			{
				
				//Create the string that will hold the current word
				String word = s.next();
				

				
				//Check if the word is in the list
				//If not, print it out
				if ((word.length() > 0) && (!BT.lookup(word)))
				{
					//Check if word is possibly plural
					//If it is, remove final 's' and recheck within the tree
					if (word.charAt(word.length() - 1) == 's')
					{
						word = word.substring(0, word.length()-1);
						if((word.length() > 0) && (!BT.lookup(word)))
							System.out.println("'" + word + "' is not in the tree.");
					}
					else
						System.out.println("'" + word + "' is not in the tree.");
				}//End monster if
			
			}//End while
			
			
			//Clean up
			s.close();
			
			//Read next line
			line = inFile.readLine();
		}//End while
		
		//Clean up
		freader.close();
		inFile.close();

		//Hey look at that, we're done!
		
	}//End main

}//End class

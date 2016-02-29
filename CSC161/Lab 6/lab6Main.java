import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Coded by Nicholas Drazenovic.

public class lab6Main {

	
	
	public static void main(String[] args) throws IOException {

		//Create ArrayList to hold words
		ArrayList<keyData> wordList = new ArrayList<keyData>();
		
		
		//String to hold current line
		String line;
		
		//create file reader
		FileReader freader = new FileReader("Chapter1OfLifeOnTheMississippiByMarkTwain.txt");
		BufferedReader inFile = new BufferedReader(freader);
		
		//read in the first line
		line = inFile.readLine();
		
		
	//while there are more lines
		while (line != null)
		{
			//ignore case of words
			line = line.toLowerCase();
			
				Scanner s = new Scanner(line);
				
				//while there are more words in the line
				while (s.hasNext())
				{
					
				String word = s.next();
				
				//get rid of any punctuation
				word = word.replaceAll("[^a-z]+",  "");
				
				//if the word is already in the list, simply update the count
				if (exists(wordList, word))
					update(wordList, word);
				else //else add it to the list
				{
					keyData key = new keyData(word);
					wordList.add(key);
				}
				
				}//end while
				
				
				//clean up
				s.close();
				
				//read next line
				line = inFile.readLine();
		}
		//clean up
		inFile.close();
		freader.close();
		
		
	
		//sort the list
		Collections.sort(wordList);
		
		//List will show items from least to greatest, 
		//We want it the other way around!
		Collections.reverse(wordList);

		System.out.println(" Word\t   #\n===============");
		
		//Traverse the array
		for (int i = 0; i < 5; i++)
		{
			System.out.printf("%5s\t%5d\n", wordList.get(i).getWord(), wordList.get(i).getData());
			//System.out.println(wordList.get(i).getWord() + "\t\t" + wordList.get(i).getData());
		}//end for
		
	}//end main
	
	public static boolean exists(ArrayList<keyData> kk, String w)
	{
		boolean retVal = false;
		
		for (int i = 0; i < kk.size(); i ++)
		{
			if (kk.get(i).getWord().equals(w))
				retVal = true;
		}
		return retVal;
	}//end exists
	
	public static void update(ArrayList<keyData> kk, String w)
	{
		int i = 0;
		while (i < kk.size())
		{
			if (kk.get(i).getWord().equals(w))
				kk.get(i).increment();
			i++;
		}
	}//end update

}//end class

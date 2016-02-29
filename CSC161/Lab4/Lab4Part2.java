//Coded by Nicholas Drazenovic

import java.io.*;

public class Lab4Part2 {

	public static void main(String[] args) throws IOException {
	

		//create file reading utilities
		FileInputStream freader = new FileInputStream("Lab4-6.data");
		DataInputStream inFile = new DataInputStream(freader);
		
		String greeting = inFile.readUTF();
		int num = inFile.readInt();
		double dub = inFile.readDouble();
		long neg = inFile.readLong();
		
		boolean eof = false;
		
		//create filewriter utilities
		FileWriter fwriter = new FileWriter("Lab4-6.txt");
		PrintWriter outFile = new PrintWriter(fwriter);
		
		
		outFile.println("Written by Nicholas Drazenovic using Lab4-6.data");
		outFile.println(greeting);
		outFile.println(num);
		outFile.println(dub);
		outFile.println(neg);
		
		while (!eof)
		{
		try {
			outFile.print(inFile.readInt() + " ");
		}
		catch (EOFException e)
		{
			eof = true;
		}
		}//end while
		//cleanup
		inFile.close();
		freader.close();
		outFile.close();
		fwriter.close();
		
	}

}

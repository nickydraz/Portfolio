//Coded by Nicholas Drazenovic

import java.io.*;

public class Lab4Part1 {

	public static void main(String[] args) throws IOException {
		String greeting = "Hi, this file is from Nicholas Drazenovic.";
		int num = 5;
		double dub = 8.5;
		long neg = -22;
		
		int[] numArr = { 5, 99, -5, -8, 3, -2};
		
		//create file handlers
		FileOutputStream fwriter = new FileOutputStream("Lab4-8.data");
		DataOutputStream outFile = new DataOutputStream(fwriter);
		
		outFile.writeUTF(greeting);
		outFile.writeInt(num);
		outFile.writeDouble(dub);
		outFile.writeLong(neg);
		
		for (int i = 0; i < numArr.length; i++)
		{
			outFile.writeInt(numArr[i]);
		}
		
		//clean up
		outFile.close();
		fwriter.close();
	}

}

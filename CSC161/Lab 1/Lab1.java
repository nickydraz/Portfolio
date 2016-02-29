//Lab for converting between number formats
//Coded by Nicholas Drazenovic

import java.util.Scanner;
public class Lab1 {

	public static void main(String[] args) {

		//create scanner
		Scanner kb = new Scanner(System.in);
	
		//create variable to hold the number to be converted
		int decimal;
		
		//create a String to hold the converted value
		String res;
		
		//create a string to hold binary value
		String bin;
		
		//int to hold result of bin to dec conversion
		int result;
		
		
		//ask user what they would like to do
		String choice;
		System.out.println("What would you like to do?");
		System.out.println("Enter 'A' for decimal to binary");
		System.out.println("Or 'B' for binary to decimal: ");
		choice = kb.next();
		choice = choice.toUpperCase();
		
		//while ((!choice.equals("A")) || (!choice.equals("B")))
		//{
		//	System.out.println("Not a valid choice, please try again: ");
		//	choice = kb.next();
		//	choice = choice.toUpperCase();
		//}//end while
		
		if (choice.equals("A"))
		{
		System.out.println("Please input a number in decimal form: ");
		decimal = kb.nextInt();
		System.out.println("You entered: " + decimal);
		
		res = BinConvert.int2bin(decimal);
		
		System.out.println("The converted value is: " + res);
		}//end if
		else
		{
			System.out.println("Please input the number in binary form: ");
			bin = kb.next();
			System.out.println("You entered: " + bin);
			
			result = BinConvert.bin2int(bin);
			
			System.out.println("The converted value is: " + result);
			
		}//end else
		
		

		

	}//end main

}//end program

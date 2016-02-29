//Class for converting numbers
//coded by Nicholas Drazenovic

public class BinConvert {

	//Method to convert from decimal to binary
	public static String int2bin(int num)
	{
		//String to hold result
		String result = "";
		
		//conversion
		//while the decimal number is not 0
		while (num != 0)
		{
			//if num is odd
			if (num % 2 != 0)
			{
				//diagnosis line
				//System.out.println(num + " odd");
				//System.out.println(num%2 + " remainder");
				
				//then place a 1 to the left
				result = "1" + result;
				//then subtract 1 to make even
				num = num - 1;
			}//end if
			else
			{
				//diagnosis line
				//System.out.println(num + " even");
				
				//if num is even, place 0 to the left
				result = "0" + result;
			}//end else
			
			//divide number by two, placing the result back in num
			num = num/2;
				
		}//end while
		
		return result;
	}//end int2bin
	
	//method to convert from binary to decimal
	public static int bin2int(String bin)
	{
		//variable to hold the sum or decimal value
		int sum = 0;
		int n = bin.length();
		
		
		
		for (int i = 0; i < bin.length(); i++)
		{
			
			//variable to hold value of leftmost, unprocessed digit
			//Since the ASCII code for 0 equates to 48
			//And 1 equates to 49, subtract 48 from value to get what is needed
			int d = (bin.charAt(i) - 48);

			sum = (int) (sum + (d * Math.pow( 2, (n - 1))));
			n = n - 1;

		}//end for
		
		return sum;	
	}//end bin2int

}

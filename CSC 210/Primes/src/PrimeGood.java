//Improved Prime Checking algorithm
//Coded by Nicholas Drazenovic
public class PrimeGood
{
	static int outerLoop = 0;
	static int loopCount = 0;
	static int numPrimes = 0; 
	static int numsOnLine = 0; 
	
	public static void main(String[] args)
	{
		//Set the maximum for the range to be checked
		int max = 1000;
		
		
	
		System.out.println( "The prime numbers between 2 and " +
		                    max + " are: \n");
		
		primeCheck(max);

		System.out.println("\n\nNumber of primes     : " + numPrimes);
		System.out.println("Outer loop iterations: " + outerLoop);
		System.out.println("Inner loop iterations: " + loopCount);
	
	}// end main
	
	//method to check the prime numbers between the range provided
	public static void primeCheck(int max)
	{
		
		//create boolean, default to true
		//Will change if needed
		boolean isPrime = true;
		
		//Create increment variable
		//Set to 1 to allow to be increased to 2 after checking if 2 is prime
		int increment = 1;
		
		for (int n = 2; n <= max; n += increment)
		{  
			
			//If statement to increase the incrementer, since after 2, no even numbers are primes.
			if (n == 3)
				increment++;
			
			outerLoop++;
			
			//This resets the boolean if needed
			isPrime = true;
			
			//For loop starts at 3 and skips all even divisors
			//only need to go as high as the square root of the number
	    	for (int divisor = 3; divisor <= Math.sqrt(n); divisor += 2)
			{  loopCount++;
			
				if (n % divisor == 0)
					isPrime = false;

		    	
			}//end inner for	

			
			if (isPrime)
			   { 	numPrimes++;
					System.out.print(n + "  ");
					numsOnLine++;
					if (numsOnLine == 15)
					{  System.out.println();
						numsOnLine = 0;
					}
				}
			
			
		}// end outer for

	}//end primeCheck
	
	//method for checking if even
	//Did not end up in final prime checker
	public static boolean isEven(int x)
	{
		if (x%2 == 0)
			return true;
		else
			return false;
		
	}//end isEven
	
}// end PrimeBad class

/*
	Number of primes     : 168
	Outer loop iterations: 500
	Inner loop iterations: 4780

*/
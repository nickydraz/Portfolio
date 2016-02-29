/********************************
*   Slow bubble sort in java    *
*   written by Prof. Walters    *
*********************************/
import java.util.Random;
//import java.util.Date;
public class SlowSort
{
	static final int SIZE = 25000;  // Change to 50K, then 125K,
	                                // then 250K and record the results
	static int[]A;
	                       
	/**
    * This is the main function for the sorting program.
    * @param args
    */
    public static void main(String[] args)
    {
	     Random rand  = new Random();
		  //Date myTimer = new Date();
		  A = new int[SIZE];

		  // Write a line here to print your name << endl << endl;
	     for (int  i = 0; i < SIZE; i++)
		  {	A[i] = rand.nextInt(32766);
        }
        System.out.println("\nExecuting Bubble Sort with " + SIZE + " elements.");
        //double start = myTimer.getTime()/ 1000;
        double start = System.currentTimeMillis() / 1000.0;
        //System.out.println("Start time = " + start);
        
        bubbleSort();
        
        // double end = myTimer.getTime()/ 1000;
        double end = System.currentTimeMillis() / 1000.0;
        //System.out.println("End time = " + end);
        
        double diff = roundIt(end - start);
        System.out.println("Elapsed time = " + diff + " seconds.");
		  
        System.out.println("\nFirst 20 sorted numbers are:");
        for (int i = 0; i < 20; i++)
        {	if (i % 10 == 0)    // Start a new line
					System.out.printf("\n");  
				System.out.printf("%7d",A[i]);  
		  }
		  System.out.println("\n\nLast 20 sorted numbers are:");
		  for (int i = SIZE - 20; i < SIZE; i++)
		  {	if (i % 10 == 0)    // Start a new line
					System.out.printf("\n");  
				System.out.printf("%7d",A[i]);  
			}
    }// end main

	
	// ******************  bubbleSort  ****************
	// * This function performs a bubble sort on the 
	// * elements of array A to illustrate a slow sort.
	// ************************************************
	public static void bubbleSort()
	{
	   int lastToCheck = SIZE-2,
		    temp;		
		   
		for (int pass = 1; pass < SIZE; pass++, lastToCheck--)
	   {
			for (int i = 0; i <= lastToCheck; i++)
			{   if (A[i] > A[i+1])
			    {    temp = A[i];       //swap them
					 	A[i] = A[i+1];
					 	A[i+1] = temp;
			    }
			}
		}
	}//end bubbleSort
	
	// *****************  roundIt  ****************
	// * This function returns the double value 
	// * passed to it, rounded to 2 decimal places.
	// ********************************************
	public static double roundIt(double val)
	{
		double rounded = ((long)(val*100 + .5)) / 100.0;
		return rounded;
	}
	
 }// end class declaration

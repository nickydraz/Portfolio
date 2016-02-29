/*******************************************
*  Selection Sort in java                  *
*  written by Prof. Walters                *
*  This sorts values in DESCENDING order.  *
*  Modify it to sort in ASCENDING order.   *
********************************************/
import java.util.Random;

public class SelectionSortDown
{
	static final int SIZE = 25000;  // Change to 50K, then 125K,
	                                // then 250K and record the results
	/**
    * This is the main function for the sorting program.
    * @param args
    */
    public static void main(String[] args)
    {
	     Random rand  = new Random();
		  int[] A = new int[SIZE];

		  // Write a line here to print your name << endl << endl;
		  // Fill the array with random non-negative integers
	     for (int  i = 0; i < SIZE; i++)
		  {	A[i] = rand.nextInt(32766);
		  }
		  
		  System.out.println("\nExecuting Selection Sort with " + SIZE + " elements.");
		  selectionSort(A);
		  
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
	 }// End main

	/*******************
	*  selectionSort   *
   ********************/
	static void selectionSort(int array[])
	{
	   int numElts = array.length;
	   int seek;                         // Array position currently being put in order
	   int posOfMax;                     // Array location of largest value found
	   int maxValue;                     // Holds the largest value found
	
	   for (seek = 0; seek < (numElts-1); seek++) // Outer loop performs the swap
	   {                                          // and then increments seek
	      posOfMax = seek;
	      maxValue = array[seek];
	      for(int index = seek + 1; index < numElts; index++)
	      {
	         if(array[index] > maxValue)  // Inner loop searches through the array 
	         {                            // starting at array[seek+1], searching for
	            maxValue = array[index];  // the largest value. When the value is
	                                      // found, it is stored in maxValue and its
	            posOfMax = index;         // subscript is stored in posOfMax. 
	         }
	      }
	      array[posOfMax] = array[seek];     
	      array[seek] = maxValue; 
	   }
	}// End selectionSort  
	
}//End of SelectionSort class declaration
	
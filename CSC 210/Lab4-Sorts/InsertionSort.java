/*******************************************
*  Insertion Sort in java                  *
*  written by Prof. Walters                *
*  This sorts values in ascending order.   *
********************************************/
import java.util.Random;

public class InsertionSort
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
		  
		  System.out.println("\nExecuting Insertion Sort with " + SIZE + " elements.");
		  insertionSort(A);
		  
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

	/************************************************
	*                    insertionSort              *
	* Moves 1 element at a time from the UNSORTED   *
	* region to its correct position in the SORTED  *
	* region. Initially only array[0] is SORTED and *
	* array[1] through array[length-1] is UNSORTED. *
   *************************************************/
	static void insertionSort(int[] array)
	{
	   int nextUnsortedIndex;   // FIRST index of the still UNSORTED region
	   int index;               // index of insertion in the SORTED region, once found
	   int nextItem;            // Next VALUE in the UNSORTED region
		
	   for (nextUnsortedIndex = 1; nextUnsortedIndex < array.length; nextUnsortedIndex++)
	   {   	
	      nextItem = array[nextUnsortedIndex];
	      	
	      // Shift all sorted section values > nextItem to the right to make room for it. 
	      for (index = nextUnsortedIndex; index > 0 && array[index-1] > nextItem; index--)
	         array[index] = array[index-1];  	     	      	
	     
		   // insert nextItem into Sorted region at position index
	      array[index] = nextItem;
	   } 
   } // end insertionSort
	
} // End InsertionSort class declaration

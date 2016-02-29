/*******************************************
*  Merge Sort in java                  *
*  written by Prof. Walters                *
*  This sorts values in ascending order.   *
********************************************/
import java.util.Random;

public class MergeSort
{
	static final int SIZE = 250000;  // Change to 50K, then 125K,
	                                // then 250K and record the results
	/**
    * This is the main function for the sorting program.
    * @param args
    */
    public static void main(String[] args)
    {
	     Random rand  = new Random();
		  int[] A = new int[SIZE];    // The actual array to be sorted
		  int[] B = new int[SIZE];    // A temporary array to merge subarrays into

		  // Write a line here to print your name << endl << endl;
		  // Fill the array with random non-negative integers
	     for (int  i = 0; i < SIZE; i++)
		  {	A[i] = rand.nextInt(32766);
		  }
		  
		  System.out.println("\nExecuting Merge Sort with " + SIZE + " elements.");
		  mergeSort(A, B, 0, SIZE-1);
		  
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

	/**************************************************
	*                    mergeSort                    *
	* Divides the list in half repeatedly by making   *
	* reursive calls until there are n subarrays of   *
	* size 1. Then, on the way back up from the       *
	* recursive calls it merges the lists, placing    *
	* everything in order. No values are ever swapped.*
	***************************************************/
	static void mergeSort(int[] A, int[]B, int start, int end)
	{
		if (start < end)
		{
			int mid = (start + end) / 2;
			mergeSort(A, B, start, mid);
			mergeSort(A, B, mid+1, end);
			merge(A, B, start, mid, mid+1, end);
			copyValues(A, B, start, end);
		}
		// else just return without doing anything
		
   } // end mergeSort
	
	/*********************************************************
	*                           merge                        *
	* Merges the two ordered "lists" of values(i.e. sections * 
	* of array A passed to it into a single ordered set of   *
	* values in array B.                                     *
	**********************************************************/
	static void merge( int[]A, int[]B, int start1, int end1, int start2, int end2)
	{
		int pos1 = start1;
		int pos2 = start2;   
		int index = start1;  // Note index can also be started at 0.
                           // In that case, copyValues copies values
									// beginning at B[0] into A[start] thru A[end]
									  		
		// while both "lists" still have values, merge the 2 "lists"
		while(pos1 <= end1 && pos2 <= end2)
		{
			if (A[pos1] < A[pos2])
			{	B[index] = A[pos1];
				pos1++;
			}
			else
			{	B[index] = A[pos2];
				pos2++;
			}
			index++;
		}
		// Now copy the rest of whichever list 
		// still has values into array B.
		if (pos1 > end1)  // There are still values in list 2 to transfer
		{
			while (pos2 <= end2)
			{	B[index] = A[pos2];
			   pos2++;
				index++;
			}
		}
		else              // There are still values in list 1 to transfer 	
		{
			while (pos1 <= end1)
			{	B[index] = A[pos1];
			   pos1++;
				index++;
			}
		}
	}//end merge

	/*******************************************
	*                  copyValues              *
	* Copies the ordered merged subarray from  *
	* temp array B back into array A.	       *
	********************************************/
	static void copyValues(int[] A, int[] B, int start, int end)
	{
		for (int pos = start; pos <= end; pos++)
			A[pos] = B[pos];
			
	}//end copyValues	
	
} // End InsertionSort class declaration

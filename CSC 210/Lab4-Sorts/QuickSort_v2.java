/*******************************
*   NEW Quicksort v2  Java     *
*   written by Prof. Walters   *
********************************/
import java.util.Random;

public class QuickSort_v2
{
	static final int SIZE = 25000; 
	                                // Change to 50K, 125K, then keep doubling
                                   // 250K, 500K, 1 MIL, 2 MIL, 4 MIL, 8 MIL, 
                                   // 16 MIL, 32 MIL, and finally 64 MIL
                                   // and record the results in a table.

	// *****************  main  *****************
   public static void main(String[] args)
   {
  		Random rand = new Random();
		int[]A = new int[SIZE];

     	// Write a line here to print your name << endl << endl;
     	for (int  i = 0; i < SIZE; i++)
    	{	A[i] = rand.nextInt(32766);
    	}
     	System.out.println("\nExecuting QuickSort with " + SIZE + " elements.");
     	double start = System.currentTimeMillis() / 1000.0;
      
     	quickSort(A, 0, SIZE-1);
     
     	double end = System.currentTimeMillis() / 1000.0;
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
		System.out.println();
	}// end main

	
	// **********************  quicksort  **********************
	// This function performs a quicksort to illustrate a fast sort.
	// *********************************************************
	public static void quickSort(int[] A, int start, int end)
	{	int pivotPoint;

		if (start < end)
		{
			// Get the pivot point
			pivotPoint = partition(A, start, end);
			 
			// Make recursive calls to sort the 
			// left sublist, then the right sublist.
			quickSort(A, start, pivotPoint - 1);
			quickSort(A, pivotPoint + 1, end);
		}
		// Else do nothing and just return because
		// This part of the array is already sorted
		
	}// end quickSort

	// **********************  partition  **********************
	// This function partitions the part of the array between
	// subscripts "start" and "end" in such a way that all values
	// less than a selected "pivot" value are to its "left" and 
	// all values greater than the "pivot" value are to its "right". 
	// The subscript of this pivot value is then returned.
	// *******************************************************  
	public static int partition(int[] A, int start, int end)
	{
		int pivotValue = A[start];
		int pivotIndex = start;
	
		for (int pos = start + 1; pos <= end; pos++)
		{
			// If the "current" value being examined is less than the pivot
			// value, we need to move it to the left of the pivot position.
			if (A[pos] < pivotValue)
			{	// Swap the "current" value with the value to the right of
				// the pivot index. To do this we call swap and pass it
				// the indexes of the two values to be swapped.
				swap(A, pivotIndex+1, pos);
				
				// Swap the "current" value (now sitting in A[pivotIndex+1])
				// with the pivot value sitting in A[pivotIndex].
				swap(A, pivotIndex, pivotIndex+1);
				
				// Adjust the pivot index so it stays with the pivot value.
				pivotIndex++;
			}
		}
		return pivotIndex;  // Every value less than it is now to its left
							     // Every value greater than it is now to its right
	}// end partition
	
	// *****************  roundIt  ****************
	// * This function returns the double value 
	// * passed to it, rounded to 2 decimal places.
	// ********************************************
	public static double roundIt(double val)
	{
		double rounded = ((long)(val*100 + .5)) / 100.0;
		return rounded;
	}

	// ********************  swap  ********************
	// This function swaps the array A values having 
	// indices A[index1] and A[index2]
	// ************************************************
	public static void swap(int[] A, int index1, int index2)
	{
	 	int temp = A[index1];
		
		A[index1] = A[index2];
		A[index2] = temp;
	}
	 
}// end class declaration

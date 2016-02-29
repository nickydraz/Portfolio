//Coded by Nicholas Drazenovic
// Java program to simulate the arrival and processing of people at a drive-up teller
// at a bank between 10:00 and 11:00 am
// 
// Modified by:  <Nicholas_Drazenovic_,_No_Really_It's_Me!>

import java.util.Random;  // for # of cars arriving and and being served

public class Lab10 
{

	// The program works as follows:
	// 1) Storage for a queue is created, and an int variable that will hold the
	//    numeric value of each car is declared and initialized.
	// 2) A for loop that represents the 5 minute marks in an hour is used.
	//    For each 5 minute increment,
	//    a) A random number is generated that represents the number of cars
	//       that arrive in that time slot.
	//    b) If the random number is positive, then that many cars are added
	//       to the queue.  Messages are printed for each car added.
	//    c) Next, a random number representing the number of customers who
	//       are served is generated.  If this is not zero, and if the queue
	//       is not empty, then the car numbers of the customers who are served
	//       is displayed and the cars are removed from the queue.
	
	    public static void main(String [] args)
	    {    
		   
			// Create initial queue to hold 100 ints representing cars    
	       IntArrayQueue queue = new IntArrayQueue(100);// <-- COMPLETE THIS LINE
	       
	       // carNum is the number of the next car to arrive in the queue
	       int nextCar = 1;  // initialize to be the first car in line
	       
	       // Determine the cars in the queue for one hour of the day, 
	       // measured every 5 minutes
	       
	       for (int i = 0; i < 60; i += 5)
	       {
	    	   // Print a message for this  5 minute timeslot
	    	   System.out.printf("Time is 10:%02d:\n", i);
	    	   
	    	   // Generate a random number indicating the number of cars that arrive
	    	   // in a 5 minute span.  The number is between 0 and 10.
	    	   
	    	   int carsIn = new Random () .nextInt(10) + 1;// <-- COMPLETE THIS LINE
	    	   
	    	   // put that many cars in the queue.  Use the variable 'nextCar' as the
	    	   // value to put in the queue.  Increment nextCar after each car is put in
	    	   // the queue
	    	   
	    		   System.out.print("Adding " + carsIn + " cars to the queue: ");
	    	   
	    		   for (int j = 1; j <= carsIn; j++)
	    		   {
	    			   // <-- PUT nextCar IN THE QUEUE
	    	           //     PRINT THE NUMBER OF THE CAR THAT WAS ADDED TO THE QUEUE
					   //     INCREMENT nextCar
	    			   queue.enqueue(nextCar);
	    			   System.out.print(nextCar + " ");
	    			   nextCar++;    			 
	    		   }
	    	 
	    	   // Generate a random number indicating the number of cars that are
	    	   // served at the teller window in a 5 minute span.  The number
	    	   // is in the same range, 0 to 10.
	    	   
	    	   int carsOut = new Random () .nextInt(10) + 1; // <-- COMPLETE THIS LINE
	    	   
    		   System.out.print("\nAttempting to remove " + carsOut + " cars from the queue: ");
	    	   if ((carsOut > 0) && (!queue.empty()))
	    	   {
	    	   
	    		   for (int j = 1; j <= carsOut; j++)
	    		   {
	    			   // <-- IF THE QUEUE IS NOT EMPTY,
					   //       REMOVE THE NEXT CAR FROM THE QUEUE
					   //       PRINT THE CAR NUMBER
					   //     END IF
					   
	    			   if (!queue.empty())
	    			   {
	    				   int x = queue.dequeue();
	    				   System.out.print(x + " ");
	    			   }//end if
	    		   }//end for
	    	   }//end if
	    	   System.out.println("\n");
	    	   
	       }
	       
	   
	    }    // end main
	

}

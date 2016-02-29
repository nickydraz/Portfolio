// **************************************************************
// Modified by Nicholas Drazenovic
//
// Java Linked List Lab: UseDll
// This program tests some of the functions of a linked list.
// It uses the floating-point Dllist class you are developing.
// It will later be modified to test all your Dllist functions.
//
// The currently available class functions that may be called are:
// addToFront  -- adds a new node to the front of the list
// addToRear   -- adds a new node to the end of the list
// add         -- inserts a new node in (ascending) data value order  
// displayList -- displays the data value of each node
// remove      -- deletes the node holding the specified key value
// **************************************************************

public class UseDll
{
	public static void main(String[] args)
   {
	    Dllist numbers = new Dllist();
	
	    System.out.println("CSC 210 Dllist Lab -- Nicholas Drazenovic");
	   	System.out.println("\nFour name/score combinations will initially be " +
			                   "inserted in score order into the list.");
	   	System.out.println("They are: John (8.1), Mary (4.1), Ashley (9.9), Kevin (6.3)"); 
	
	   	numbers.add(8.1, "John");
	   	numbers.add(4.1, "Mary");
	   	numbers.add(9.9, "Ashley");
	   	numbers.add(6.3, "Kevin");
	     
	   	System.out.println("\nThe values in the list are:");
	   	numbers.displayListAscending();
	   	System.out.println("\nNotice how the add method put them\n" +
	                         "into the list in ascending order.");
	
	   	System.out.println("\nNow we will append Jake (5.0) to the end of the list.");
	   	numbers.addToRear(5.0, "Jake");
	
	   	System.out.println("\nThe values in the list are now:");
	   	numbers.displayListAscending();
	   	System.out.println("\nNotice that the addToRear method added the number\n" +
	                         "5.0 to the end of the list, not in order.");
									 
		System.out.println("\nNow we will add Larry (5.5) front of the list.");
	   	numbers.addToFront(5.5, "Larry");
	
	   	System.out.println("\nNow we will add Kelly (6.0) to the list, twice. ");
	   	System.out.println("\nThis will be used to ensure the "
	   			+ "\nhowMany method is working properly.");
	   	
	   	numbers.add(6.0, "Kelly");
	   	numbers.add(6.0, "Kelly");
	   	
	   	
	   	System.out.println("\nThe values in the list are now:");
	   	numbers.displayListAscending();
	   	System.out.println("\nNotice that the addToFront method added the number\n" +
	                         "5.5 to the front of the list, not in order.\n");
						
	   	//Test howMany
	   	System.out.println("The value 6.0 appears " + numbers.howMany(6.0) + " times in the list.");
	   	
	   	//Display the list again, but in descending order.
	   	System.out.println("\nThe values in the list, in descending order: ");
	   	numbers.displayListDescending(numbers.getHead());
	   	
	  	System.out.println("\n\nWe will now remove:\n" + 
		                   "the number at the front of the list (5.5)\n" + 
      	                "the number at the end of the list (5.0), and \n" +
      	                "the number in the middle of the list (8.1).");
	  	
	   	numbers.remove(5.5);
	   	numbers.remove(5.0);
	   	numbers.remove(8.1);
	   
	   	System.out.println("\nThe values remaining in the list are: ");
	   	numbers.displayListAscending();
	 
	   	System.out.println("\n\nIs Kevin still in the list? ");
	   	numbers.getScore("Kevin");
	   	
	   	System.out.println("\nThe predecessor of Mary is: " + numbers.getPredecessor("Mary"));
	   	
	   	System.out.println("\nThe successor of Kevin is: " + numbers.getSuccessor("Kevin"));

  	}// End main

}// End useLL class declaration

/* Sample output from final test class, after all modifications
 * See below for original test output
 CSC 210 Dllist Lab -- Nicholas Drazenovic

Four name/score combinations will initially be inserted in score order into the list.
They are: John (8.1), Mary (4.1), Ashley (9.9), Kevin (6.3)

The values in the list are:
4.1: Mary     6.3: Kevin     8.1: John     9.9: Ashley     
Notice how the add method put them
into the list in ascending order.

Now we will append Jake (5.0) to the end of the list.

The values in the list are now:
4.1: Mary     6.3: Kevin     8.1: John     9.9: Ashley     5.0: Jake     
Notice that the addToRear method added the number
5.0 to the end of the list, not in order.

Now we will add Larry (5.5) front of the list.

Now we will add Kelly (6.0) to the list, twice. 

This will be used to ensure the 
howMany method is working properly.

The values in the list are now:
5.5: Larry     4.1: Mary     6.3: Kevin     8.1: John     9.9: Ashley     5.0: Jake     6.0: Kelly     6.0: Kelly     
Notice that the addToFront method added the number
5.5 to the front of the list, not in order.

The value 6.0 appears 2 times in the list.

The values in the list, in descending order: 
| 6.0: Kelly 	| 6.0: Kelly 	| 5.0: Jake 	| 9.9: Ashley 	| 8.1: John 	| 6.3: Kevin 	| 4.1: Mary 	| 5.5: Larry 	

We will now remove:
the number at the front of the list (5.5)
the number at the end of the list (5.0), and 
the number in the middle of the list (8.1).

The values remaining in the list are: 
4.1: Mary     6.3: Kevin     9.9: Ashley     6.0: Kelly     6.0: Kelly     

Is Kevin still in the list? 
Kevin's score is 6.3

The predecessor of Mary is: There is no predecessor to this record.

The successor of Kevin is: Ashley

 */


/* Sample run output form original test class
    CSC 210 Dllist Lab -- Nicholas Drazenovic
    
    Four numbers will initially be inserted in value order into the list.
    They are: 8.1, 4.1, 9.9, 6.3
    
    The values in the list are:
    4.1    6.3    8.1    9.9    
    Notice how the add method put them
    into the list in ascending order.
    
    Now we will append 5.0 to the end of the list.
    
    The values in the list are now:
    4.1    6.3    8.1    9.9    5.0    
    Notice that the addToRear method added the number
    5.0 to the end of the list, not in order.
    
    Now we will add 5.5 front of the list.
    
    The values in the list are now:
    5.5    4.1    6.3    8.1    9.9    5.0    
    Notice that the addToFront method added the number
    5.5 to the front of the list, not in order.
    
    We will now remove:
    the number at the front of the list (5.5)
    the number at the end of the list (5.0), and 
    the number in the middle of the list (8.1).
    
    The values remaining in the list are: 
    4.1    6.3    9.9    
	 
	 */ 
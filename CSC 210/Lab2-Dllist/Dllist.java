// ***************************************
// Dllist Class modified by Nicholas Drazenovic
// Dllist Class begun by Prof. Walters
// Now it is just a regular LList. You will
// transform it into a doubly linked list, 
// as well as add additional methods. 
// ***************************************

public class Dllist
{
	
	private Node head;	//Start of the list
	private Node tail; //End of the list
	private int size; //Size of the list
					 
	// Constructor
	public Dllist()
	{  
		startNewList();
	}//End constructor
	
	/****************************
	 * 
	 * startNewList
	 * 
	 * Does what it says, 
	 * call to create a new list
	 * 
	 ****************************/
	private void startNewList()
	{   
		head = null;
	  	size = 0;
	 	tail = null;
	}//end startNewList

	
	/******************************
	            isEmpty
	            
	    Checks if list is empty
	 ******************************/
	public boolean isEmpty()
	{
		return (head == null);
		// or return(size == 0);
	}//end isEmpty
	
	/******************************
	            size
	 Returns the length of the list
	 ******************************/
	public int size() 
	{
		return size;
	}//end size
	
	/******************************
	            addToFront
	 Adds a node containing the new
	 value to the front of the list.
	 ******************************/
	public void addToFront(double val)
	{
		Node newNode = new Node(val, head);
		
		//if the list is empty, the front is also the tail
		if (isEmpty())
			tail = newNode;
		
		//If the next node isn't null
		//then set its previous pointer to the new Node
		if (newNode.getNext() != null)
			newNode.getNext().setPrev(newNode);
		
		//The front is the head
		head = newNode;
		//Increase the size of the list
		size++;
	}//end addToFront(double)

	/******************************
	            addToFront
	 Adds a node containing the new
	 String name to the front of the list.
	 ******************************/	
	public void addToFront(String name)
	{
		Node newNode = new Node(name, head);

		//If the list is empty, the front is also the tail
		if (isEmpty())
			tail = newNode;

		//If the next node isn't null
		//then set its precious pointer to the new Node
		if (newNode.getNext() != null)
			newNode.getNext().setPrev(newNode);
		
		//The front is the head
		head = newNode;
		//Increase the size of the list
		size++;
	}//end addToFront(String)

	/******************************
	            addToFront
	 Adds a node containing the new
	 value and name to the front of the list.
	 ******************************/
	public void addToFront(double val, String name)
	{
		Node newNode = new Node(val, name, head);

		//if the list is empty, the front is also the tail
		if (isEmpty())
			tail = newNode;
		
		//If the next node isn't null
		//then set its previous pointer to the new Node
		if (newNode.getNext() != null)
			newNode.getNext().setPrev(newNode);

		//The front is the head
		head = newNode;
		//Increase the size of the list
		size++;
	}//end addToFront(double, String)
	
	/******************************
	            addToRear
	 Adds a node containing the new
	 value to the end of the list.
	 ******************************/
	public void addToRear(double val)
	{
		//If the list is empty, an addToFront will suffice
		if (isEmpty())
			addToFront(val);
		else //if list isn't empty, then add to the rear
		{
			Node newNode = new Node(val);
			Node ptr = head;
			
			while(ptr.getNext()!= null)   // While there IS a next node
			{	
				ptr = ptr.getNext();       // walk down the list.
			}//end while
			                              // When loop is exited, ptr points to the last node
			ptr.setNext(newNode);         // Make it point to the new node.
			newNode.setPrev(ptr);
			tail = newNode;
			size++;
		}
	}//end addToRear(double)

	/******************************
	            addToRear
	 Adds a node containing the new
	 name to the end of the list.
	 ******************************/
	public void addToRear(String name)
	{
		//If list is empty, an addToFront will suffice
		if (isEmpty())
			addToFront(name);
		else //If list isn't empty, then add to the rear
		{
			Node newNode = new Node(name);
			Node ptr = head;
		
			while(ptr.getNext()!= null)   // While there IS a next node
			{	
				ptr = ptr.getNext();       // walk down the list.
			}//end while
			                              // When loop is exited, ptr points to the last node
			ptr.setNext(newNode);         // Make it point to the new node.
			newNode.setPrev(ptr);
			tail = newNode;
			size++;
		}
	}//end addToRear(String)

	/******************************
	            addToRear
	 Adds a node containing the new
	 value and name to the end of the list.
	 ******************************/
	public void addToRear(double val, String name)
	{
		//If list is empty, then an addToFront will suffice
		if (isEmpty())
			addToFront(val, name);
		else //If list is not empty, then addToRear
		{
			Node newNode = new Node(val, name);
			Node ptr = head;
			
			while(ptr.getNext()!= null)   // While there IS a next node
			{	
				ptr = ptr.getNext();       // walk down the list.
			}//end while
			                              // When loop is exited, ptr points to the last node
			ptr.setNext(newNode); // Make it point to the new node. 
			newNode.setPrev(ptr);
			tail = newNode;
			size++;
		}
	}//end addToRear(double, String)


	/******************************
	               add
	 Adds a node containing the new
	 value in order by value.
	 ******************************/
	public void add(double val)  
	{
		//Node pointers for traversing the list
		Node curr = head,
		     prev = null;
			  
		//If list is empty, add to the front of the list
		if (isEmpty() || head.getValue() >= val)
			addToFront(val);
		else if (val >= tail.getValue()) //If score is greater than the tail, make it the new tail
			addToRear(val);
		else //Otherwise, walk through the list until spot is found
		{	
			Node newNode = new Node(val);                // Set up the node
			
			while(curr != null && curr.getValue() < val) // Walk down the list
			{	
				prev = curr;                              // until insertion point
				curr = curr.getNext();                    // for new node is found.
			}//end While
			
			prev.setNext(newNode);                       // Link it in.
			newNode.setNext(curr);
			newNode.setPrev(prev);
			newNode.getNext().setPrev(newNode);
			
			//Increase the size of the list
			size++;
		}
	}//end add(double)
	
	public void add(double val, String n)
	{
		//Node pointers for traversing the list
		Node curr = head,
			 prev = null;
		
		//If list is empty, add to the front of the list
		if (isEmpty() || head.getValue() >= val)
			addToFront(val, n);
		else if (val >= tail.getValue()) //If score is greater than the tail, make it the new tail
			addToRear(val, n);
		else //Otherwise, walk through the list until a spot is found
		{	
			Node newNode = new Node(val, n);                // Set up the node
			
			while(curr != null && curr.getValue() < val) // Walk down the list
			{	
				prev = curr;                              // until insertion point
				curr = curr.getNext();                    // for new node is found.
			}//end While
			
			prev.setNext(newNode);                       // Link it in.
			newNode.setNext(curr);
			newNode.setPrev(prev);
			newNode.getNext().setPrev(newNode);
			
			//Increase size of the list
			size++;
		}		 
	}//end add(double, String)
	
	/******************************
	            isInList
	            
	    Checks if score is in list
	 ******************************/	
	public boolean isInList(double val)
	{
	   Node ptr = head;
			
	   //If list is empty, it's clearly not in the list
	   if (isEmpty())
		   return false; 
	   // else walk down list looking for val
	   while (ptr != null && ptr.getValue() != val)
	   {	
		   ptr = ptr.getNext();
	   }//end While
	   
	   if (ptr == null) // End of list was reached w/o finding val
		   return false;
	   else
		   return true;  // val was found
	}//end isInList
	
	/**********************************
	 * 			isInList
	 * 		String version
	 * 
	 *  Checks if a name is in the list
	 **********************************/
	public boolean isInList(String n)
	{
		Node ptr = head;

		//If list is empty, it's clearly not in the list
		if (isEmpty())
			return false;
		//else walk down list, looking for the String
		while (ptr != null && !ptr.getName().equalsIgnoreCase(n))
		{
			ptr = ptr.getNext();
		}//end while
		
		if (ptr == null)
			return false; //Name not found
		else
			return true; //Name found
	}
	
	/************************************
	              displayList
	 Prints the value fields of each node
	 in ascending score order
	 ************************************/
	public void displayListAscending()  
	{
		Node ptr = head;
		
		//If it's empty, print out appropriate message
		if (isEmpty())
			System.out.println("The list is empty.\n");
		else
		{	while (ptr != null)
			{	
				System.out.print(ptr.getValue() + ": " + ptr.getName() + "     "); // Print this node's value
				ptr = ptr.getNext();                       // Move ptr to the next node.
			}//end while
		}
	}//end displayListAscending
	
	
	/**************************************
	 * 
	 * 			displayListDescending
	 * 
	 * Displays the list in descending score order
	 * 
	 **************************************/
	
	public void displayListDescending(Node ptr)
	{
		
		//If list is empty, print appropriate message
		if (isEmpty())
			System.out.println("The list is empty.\n");
		
		if (ptr == null)
			return;
	
		displayListDescending(ptr.getNext());
		System.out.print("| " + ptr.getValue() + ": " + ptr.getName() + " 	"); //Print the Node
		
		
	}//end displayListDescending


	/************************************
	              howMany
	 Counts how many times val appears
	 in the list
	 ************************************/
	//First method used to retrieve the head of the list
	//This method calls another version, which will use recursion
	//to get the number of times the value appears
	public int howMany(double val)
	{
		return howMany(val, head);
	}
	
	private int howMany(double val, Node ptr)
	{
		//counter variable
		int numFound = 0;
		
		//if at the end of list, return 0 and begin exit
		if (ptr == null)
			return 0;
		
		//if the value matches, increment numFound
		if (ptr.getValue() == val)
			numFound++;
		
		//Make recursive call to get the next Node in the list
		return numFound + howMany(val, ptr.getNext());
	
	}//end recursive howMany


	/***************************************
	              remove
	 Removes 1st node containing val and
	 returns val. If not found, return -1.0
	 ***************************************/
	public double remove(double val) 
	{                        
		//Node pointers for traversing the list
		Node current = head;
		Node previous = null;

		if (!isInList(val))          // Case 1: val is not in list
			return -1.0;
		//else	
		if (head.getValue() == val)  // Case 2: val is in first node  
			return removeFront();
	   //else 				
		if (tail.getValue() == val)	//Case 3: val is in the last node
			return removeRear();
	   									// Case 4: val is further down the list
		                             	// so walk down the list to find it.
		while (current.getNext() != null &&  current.getValue() != val)
		{
			previous = current;
			current = current.getNext();	
		}//end while
		
	   previous.setNext(current.getNext()); // Unlink the node.
	   current.setPrev(previous);
	   
	   //Set the tail, if at the end of the list
	   if(current.getNext() == null)
		   tail = current;

	   //decrease size
		size--;
		//Return the value
		return val;
	}//end remove
	
	/************************************
	             removefront
	 Removes the 1st node and returns the
	 value that was stored in it. If list
	 is empty, return -1.0.
	 ************************************/
	public double removeFront() 
	{
		//If list is empty, return -1
		if (isEmpty())
			return -1.0;
		// else
		double val = head.getValue();    // Save value in first node.
		head = head.getNext();           // Unlink first node.
		head.setPrev(null);
		//decrease the size
		size--;
		
		//Sets the tail to null if the list is now empty
		if (size == 0)
			tail = null;
		
		//Return value
		return val;                      
	}//end removeFront

	/**
	 * removeRear
	 * 
	 * Method to remove the tail from the list
	 * 
	 */
	public double removeRear()
	{
		if (isEmpty())
			return -1.0;
		// else
		double val = tail.getValue();
		
		//Set the tail to the previous node
		tail = tail.getPrev(); //Unlink previous tail
		tail.setNext(null);
		//Decrease the size of the list
		size --;
		
		//Return value
		return val;
		
	}//end removeRear
	
	
	/***************************************
	 * 			getScore
	 * 
	 * Finds the node with the name matching
	 * the name passed to it, and returns 
	 * the score
	 ***************************************/
	public double getScore(String n)
	{
		//If not in the list, return -1
		if (!isInList(n))
		{
			return -1;
		}
		//else check if it's the head
		if (head.getName().equalsIgnoreCase(n))
		{
			System.out.println(n + "'s score is " + head.getValue());
			return head.getValue();
		} 
		//else check if it's the tail
		else if (tail.getName().equalsIgnoreCase(n))
		{
			System.out.println(n + "'s score is " + tail.getValue());
			return tail.getValue();	
		}	
		else //travers the list 
		{
			Node ptr = head;
			while (ptr.getNext() != null)
			{
				
				if (ptr.getName().equalsIgnoreCase(n))
				{
					System.out.println(n + "'s score is " + ptr.getValue());
					return ptr.getValue();
				}
				
				ptr = ptr.getNext();
				
			}//end while
		
			//Should the value still be unfound, return -1
			return -1;
			
		}//end else
		
	}//end getScore
	
	/***************************************
	              destroyList
	 Sets head to null, effectively removing 
	 all nodes in the list. Java garbage 
	 collector will now free the memory of  
	 all nodes previously in the list.
	 ***************************************/
	public void destroyList()
	{
		startNewList();
	}//end destroyList
	
	
	/************************
	 * 		getHead
	 * 
	 * Gets head pointer
	 *
	 ************************/
	public Node getHead()
	{
		return head;
	}//end getHead
	
	/**************************
	 * 		getPredecessor
	 * 
	 * Returns the previous node
	 * from the name passed in
	 **************************/
	public String getPredecessor(String n)
	{
		Node ptr = head;
		
		//If in the list, continue
		if (isInList(n))
		{
			//Check if it's the head
			if (head.getName().equalsIgnoreCase(n))
			{
				return "There is no predecessor to this record.";
			}
			//Else check if it's the tail
			else if (tail.getName().equalsIgnoreCase(n))
				return tail.getPrev().getName();
			else //Else traverse the list
			{
				while (ptr.getNext() != null)
				{
					if (ptr.getName().equalsIgnoreCase(n))
						return ptr.getPrev().getName();
					
					ptr = ptr.getNext();
				}//end while
				
			}//end else
			
		}//end if
		
		//If not found, return appropriate String
		return "Not in records.";
		
	}//end getPredecessor
	
	/**************************
	 * 		getSuccessor
	 * 
	 * Returns the following node
	 * from the name passed in
	 **************************/
	public String getSuccessor(String n)
	{
		Node ptr = head;
		
		//If in the list, continue
		if (isInList(n))
		{
			//Check if it's the head
			if (head.getName().equalsIgnoreCase(n))
				return head.getNext().getName();
			//Else check if it's the tail
			else if (tail.getName().equalsIgnoreCase(n))
			{
				return "There is no successor to this record.";
			}
			else //traverse the list
			{
				while (ptr.getNext() != null)
				{
					if (ptr.getName().equalsIgnoreCase(n))
						return ptr.getNext().getName();
					
					ptr = ptr.getNext();
				}//end while
				
			}//end else
			
		}//end if
		
		//If the name is not anywhere in the list
		return "Not in records.";
		
	}//end getSuccessor
	
	
	/**************************************
	 * 			displayLowest
	 * 
	 * Returns the record of the competitor
	 * with the lowest score
	 *************************************/
	public Node displayLowest()
	{
		System.out.println("The lowest score is " + head.getValue() + ", belonging to " + head.getName() + ".");
		return head;
	}//end displayLowest
	
	
	/**************************************
	 * 			displayHighest
	 * 
	 * Returns the record of the competitor
	 * with the highest score
	 *************************************/
	public Node displayHighest()
	{
		System.out.println("The highest score is " + tail.getValue() + ", belonging to " + tail.getName() + ".");
		return tail;
	}//end displayHighest
	
}// End Dllist class declaration
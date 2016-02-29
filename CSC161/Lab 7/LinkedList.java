//Coded by Nicholas Drazenovic

// a Java class to represent a linked list of Strings

public class LinkedList 
{
	// data member - a variable that holds the address of a node
	private Node head;
	
	// constructor that takes no arguments - create an empty list
	public LinkedList()
	{
		
		head = null;
	}
	
	// constructor that takes one argument, a Node object.  Use
	// it to initialize the linked list.
	public LinkedList(Node n)
	{
		head = n;
	}
	
	// method to add a node to the end of the list
	public void append(String i)
	{
		// create a new node
		Node n = new Node(i);
		
		// if the list is empty, then put the new node 
		// at the front of the list
		
		if (head == null)
			head = n;
		else // the list is not empty
		{
			// create a Node variable to use to
			// 'walk' to the end of the list, initialized
			// to the beginning of the list.  We want to 
			// stop when the node we are looking at has
			// a null next field.
			
			Node p = head;
			// understand why the condition below is written 
			// this way and not as (p != null).  Drawing a 
			// picture may help.
			while (p.getNext() != null)  
				
				p = p.getNext();
			
			// at this point, p has the location of
			// the last node in the list.  Set its next
			// field to point to the node we created above
			
			p.setNode(n);
			
			// that's it
		}  // end else
	}  // end method insert
	
	// method to insert a node in order (in this case,
	// increasing numeric order) into the list
	
	public void insert(String i)
	{
		//If list is empty, head will be null
			//Then just add it in front
		if (!exists(i))
		{
			if (head != null)
			{
				//Else list is not empty, so check the rest
				//If the first one is greater, 
				//Then replace the head
				if (head.getData().compareToIgnoreCase(i) > 0)
				{
					head = new Node(i, head);
				}//end if
				else
				{
					//traverse the list until you find the appropriate spot
					//create nodes to hold current and previous positions
					Node p, q = null;
					p = head;
					
					//While there is a next node, and the current node is 'greater' (alphabetically)
					while ((p != null) && (p.getData().compareToIgnoreCase(i) < 0))
					{
						//Set the previous node to the current one, then read in the next node
						q = p;
						p = p.getNext();
		
					}//end while
	
					Node r = new Node(i, p);
					q.setNode(r);
	
				}//end else
				
			}//end if
			else
			{
				head = new Node(i);
			}
		}//end main if
		
	}//end Insert
	
	
	//method to check if the data exists
	public boolean exists(String i)
	{
		//check to see if the list is empty
		if (head != null)
		{
			//if not empty, proceed, else nothing exists. Duh
			//use while loop to check list
			Node p = head;
			while (p.getNext() != null && !(p.getData().equals(i)))
			{
				p = p.getNext();
			}
			
			if (p.getData().equals(i))
				return true;
			else
				return false;
		}
		else
			return false;

	}//end exists
	
	//method to check if list is empty
	public boolean isEmpty()
	{
		return (head == null);
	}
	
	
	// method to remove a node from the list
	public void delete (String i)
	{
		// do nothing if the list is empty
		if (head != null)
		{
			// is the one to be deleted at the head of 
			// the linked list?
			
			if (head.getData().equalsIgnoreCase(i))
			{
				// head.setNode(null);
				head = head.getNext();
			}
			else
			{
				Node p, q = null;
				p = head;
				while((p.getNext() != null) && !(p.getData().equalsIgnoreCase(i)))
				{
					q = p;
					p = p.getNext();
				} // end while
				
				// At this point, p points to the node to be deleted,
				// and q points to the node before the one to be deleted
				if (p != null)
					q.setNode(p.getNext());
				
			}
		}
	}
	
	// method to display the contents of the list
	public void display()
	{
		Node p = head;
		while (p != null)
		{
			System.out.print(p.getData() + " ");
			p = p.getNext();
		}
	}  // end display

	// simple main method to demonstrate the LinkedList class
	
	public static void main(String[] args)
	{
		// create a LinkedList object
		LinkedList ll = new LinkedList();
		
		// add some Strings to it
		ll.insert("A");
		ll.insert("Z");
		ll.insert("B");
		
		ll.insert("Q");	
		ll.insert("L");
		ll.insert("E");
		ll.insert("A");
		
		// now display what's in the list
		
		System.out.println("The list contains: ");
		ll.display();

		// delete the node with 5
		ll.delete("b");
		
		
		// now display what's in the list
		System.out.println("\n\nAfter deleting the node that contains B, the list contains: ");
		ll.display();
		
		//blank lines
		System.out.println("\n");
		
		//check if exists
		if (ll.exists("Q"))
			System.out.println("Yo man, the letter 'Q' exists!");
		else
			System.out.println("Nah, no 'Q' here, dawg.");
		
		//blank lines
		System.out.println("");
		
		if (ll.exists("TT"))
			System.out.println("Bro, 'TT' does in fact exist.");
		else
			System.out.println("Sorry man, 'TT' is nowhere to be found.");
		
		//blank lines
		System.out.println("");
		
		System.out.println("But is the list empty?");
		if (ll.isEmpty())
			System.out.println("Yes sir.");
		else
			System.out.println("Nah ah.");
	}
}  // end class LinkedList

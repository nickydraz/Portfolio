//Node Class
//Modified by Nicholas Drazenovic

// ************************************************************************
// This class defines a node that can hold 2 data values: a string and a
// double, as well as a reference (i.e. pointer, address) to another Node.
// You will need to modify the class to work with a Dllist.
//
// Llist and Dllist classes, will create instances of this class to build
// their lists. The client program,(i.e. user program), will not use it. 
// ************************************************************************

public class Node
{
	//Create global variables for class
    private String name;	//String to hold diver's name
	private double value;	//double to hold diver's score
	private Node next;		//Node pointer to hold next node in list
	private Node previous;	//Node pointer to hold the previous node in list
	
	/*******************
	 * 
	 * 	CONSTRUCTORS
	 * 
	 *******************/
	public Node()
	{   next = null;
		previous = null;
	}
	
	public Node (String n)
   {   name = n;
	    next = null;
	    previous = null;
	}
	
	public Node (double val)
   {   value = val;
	    next = null;
	    previous = null;
	}

	public Node (double val, Node ptr)
	{
		value = val;
		next = ptr;
	}

	public Node (double val, String n)
	{   name = n;
	    value = val;
	    next = null;
	    previous = null;
	}
	
	public Node (double val, String n, Node ptr)
	{
		name = n;
		value = val;
		next = ptr;
	}
	public Node (String n, Node ptr)
   {   name = n;
	    next = ptr;
	}

	public Node (String n, Node ptr, Node prev)
	{
		name = n;
		next = ptr;
		previous = prev;
	}
	
	public Node (double val, Node ptr, Node prev)
   {   
		value = val;
	    next = ptr;
	    previous = prev;
	}

	public Node (String n, double val, Node ptr, Node prev)
	{   name = n;
	    value = val;
	    next = ptr;
	    previous = prev;
	}
	//End constructors
	
	/**
	 * 
	 * ACESSOR METHODS
	 * 
	 */
	public String getName()
	{   
		//String aName = name;
	    return name;
	}
	
	public double getValue()
	{   
		return value;
	}

	public Node getNext()
	{   
		return next;
	}

	public Node getPrev()
	{
		return previous;
	}
	
	//End accessor methods
	
	/**
	 * 
	 * MUTATOR METHODS
	 * 
	 */
	public void setName(String n) 
	{   name = n;
	}

	public void setValue(double val) 
	{   value = val;
	}
	
	public void setNext(Node ptr)
	{   next = ptr;
	} 

	public void setPrev(Node prev)
	{
		previous = prev;
	}
	//End mutator methods
	
}//end class
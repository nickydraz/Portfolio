//Coded by Nicholas Drazenovic

// a class to represent a node in a linked list
public class Node 
{
	// the data field holds the node's data
	// the next field holds the reference to the 
	// next node in the linked list
	
	private String data;
	private Node next;
	
	// Constructor that takes two arguments
	public Node(String i, Node n)
	{
		data = i; 
		next = n;
	}
	
	
	// Constructor that takes one argument, for the data
	public Node(String i)
	{
		data = i;
		next = null;
	}
	
	// set method for the next pointer
	public void setNode(Node n)
	{
		next = n;
	}
	
	// get methods
	
	public String getData()
	{
		return data;
	}
	
	public Node getNext()
	{
		return next;
	}
}

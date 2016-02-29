import java.util.*;
/********************************************
  This class creates an array-based generic
  stack and provides all needed methods to 
  use it.  Written by prof. Walters
 *********************************************/
 
public class MyGenericStack<T>
// T will be designated whenever tne class is instantiated.
// (That is, whenever a class object is created.)
{
	private T[ ] stack;     // Stack will hold elements of type T
	                     
	private int top = -1;   // Stack empty signal
	    
	/************ myStack **************
	 Constructor that creates the array
	 of the user specified size to hold
	 the stack elements of type T.
	***********************************/
	public MyGenericStack(int capacity)
	{
		// Because Java does not allow arrays of generic type to be
		// instantiated, we can't just say stack = new T[capacity];
		// So, we create an array of type Object, then cast it to the 
		// desired generic type. The Java compiler will give us a
		// warning message, but that's OK. We know what we're doing.
		stack = (T[ ]) new Object [capacity];
	}
	
	/**************** pop ****************
	 "Pops" off and returns the top stack
	 element to the user, if there is one.
	 ************************************/
	 T pop()
	 {
	    if (empty())
		 	throw new RuntimeException("Stack Empty");
		 // else	
		 top--;
		 return stack[top+1];
	 }
	 
	/**************** push ****************
	 "Pushes" the element passed to it onto
	 the top of the stack, if the stack is
	 not already full.
	 *************************************/
	 void push(T element)
	 {
	 	 if (top == stack.length-1)
		   throw new RuntimeException("Stack Overflow");
	    top++;
		 stack[top] = element;
	 }
	 
	 /******************* size *******************
	  Returns the number of elements in the stack.	
	  ********************************************/
	  int size()
	  {
	  	 return (top + 1);
	  }
	
	/*************** empty ****************
	 Returns true if the stack is empty, or
	 false if it contains any elements.
	 *************************************/
	 boolean empty()
	 {
	 	return (top == -1);   // return (size() == 0);
	 }
	 
	  
}// end MyStack class declaration

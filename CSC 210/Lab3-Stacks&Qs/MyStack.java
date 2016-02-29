import java.util.*; 
/********************************************
  This class creates an array-based stack
  and provides all needed methods to use it.
  Tony Gaddis code modified by Prof. Walters
 *********************************************/

public class MyStack
{
	  private int[ ] stack;
	  private int top = -1;   // Stack empty signal
	    
	 /************ myStack **************
	  Constructor that creates the array
	  of the user specified size to hold
	  the stack elements.
	  ***********************************/
	  public MyStack(int capacity)
	  {
	    stack = new int[capacity];
	  }
	  
	 /**************** pop ****************
	  "Pops" off and returns the top stack 
	  element to the user, if there is one.
	  *************************************/
	  int pop()
	  {
	    if (empty())
		   throw new RuntimeException("Stack Empty");
		 // else	
		 top--;
		 return stack[top+1];
	  }
	  
	 /*************** push ****************
	  "Pushes" the element passed to it onto
	  the top of the stack, if the stack is
	  not already full. 
	  *************************************/
	  void push(int x)
	  {
	    if (top == stack.length-1)
		   throw new RuntimeException("Stack Overflow");
	    top++;
		 stack[top] = x;
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
	    return (top == -1);  // or return (size() == 0);
	  }
}// end MyStack class declaration

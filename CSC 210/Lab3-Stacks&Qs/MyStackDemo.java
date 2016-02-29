import java.util.*;
//   Demo to illustrate a basic stack class
//   Written by Prof. Walters

public class MyStackDemo
{

 public static void main(String [ ] arg)
 {
   MyStack stack = new MyStack(5);
	
	for (int num = 1; num <= 5; num++)
	   stack.push(num);	
		
	System.out.println("The stack now contains " + stack.size() +
	                   " elements.\n");
			
	while (!stack.empty())
	{
	  System.out.println(stack.pop());
	} 
	
	System.out.println("\nThe stack now contains " + stack.size() +
	                   " elements.\n");

	// UNcomment the following code to see the stack
	// what happens when a stack overflow occurs.
	// for (int num = 1; num < 10; num++)
	//   stack.push(num);	
	   
 }// end main
}// end MyStackDemo class declaration

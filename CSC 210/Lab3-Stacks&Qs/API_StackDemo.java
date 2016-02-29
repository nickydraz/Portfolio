import java.util.*;
// Demo using the API Stack class.
// Written by Prof. Walters

public class API_StackDemo
{
	public static void main(String [ ] arg)
	{
		Stack <Integer>   stack1 = new Stack <Integer>();
		Stack <Character> stack2 = new Stack <Character>();
	   Stack <String>    stack3 = new Stack <String>();
		
		for (int num = 1; num <= 3; num++)
		{
			stack1.push(num);               // Push 1, 2, 3
			stack2.push((char)(num + 'A')); // Push B, C, D
			stack3.push("Hello" + num);     // Push Hello1, Hello2, Hello3
		}
			
		System.out.println("stack1  stack2  stack3");
		System.out.println("======================");

		while (!stack1.empty())
		{
			System.out.print("  "      + stack1.pop() +
			                 "       " + stack2.pop() +
								  "     "   + stack3.pop() + "\n");
		} 
 	   
 }// end main
}// end MyGenericStackDemo class declaration

/*       RUN OUTPUT

    stack1  stack2  stack3
    ======================
      3       D     Hello3
      2       C     Hello2
      1       B     Hello1
*/

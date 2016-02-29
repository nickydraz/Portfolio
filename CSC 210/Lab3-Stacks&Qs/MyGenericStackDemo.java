import java.util.*;
// Demo of using a generic stack.
// Written by Prof. Walters

public class MyGenericStackDemo
{
	public static void main(String [ ] arg)
	{
		MyGenericStack <Integer>   stack1 = new MyGenericStack <Integer>(5);
		MyGenericStack <Character> stack2 = new MyGenericStack <Character>(5);
	   MyGenericStack <String>    stack3 = new MyGenericStack <String>(5);
		
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

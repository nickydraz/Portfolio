// Demo of using a generic stack.
//Modified by Nicholas Drazenovic
// Written by Prof. Walters

public class MyGenericQueueDemo
{
	public static void main(String [ ] arg)
	{
		MyGenericQueue <Integer>   queue1 = new MyGenericQueue <Integer>(5);
		MyGenericQueue <Character> queue2 = new MyGenericQueue <Character>(5);
	   MyGenericQueue <String>    queue3 = new MyGenericQueue <String>(5);
		
		for (int num = 1; num <= 3; num++)
		{
			queue1.enqueue(num);               // Push 1, 2, 3
			queue2.enqueue((char)(num + 'A')); // Push B, C, D
			queue3.enqueue("Hello" + num);     // Push Hello1, Hello2, Hello3
		}
			
		System.out.println("queue1  queue2  queue3");
		System.out.println("======================");

		while (!queue1.empty())
		{
			System.out.print("  "      + queue1.dequeue() +
			                 "       " + queue2.dequeue() +
								  "     "   + queue3.dequeue() + "\n");
		} 
 	   
 }// end main
}// end MyGenericQueueDemo class declaration

/*       RUN OUTPUT

    stack1  stack2  stack3
    ======================
      3       D     Hello3
      2       C     Hello2
      1       B     Hello1
*/

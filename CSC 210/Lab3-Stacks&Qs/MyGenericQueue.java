//Generic Queue Class
//Modified by Nicholas Drazenovic
//Original code from Generic Stack Class
	//Written by Judy Walters
 
public class MyGenericQueue<T>
// T will be designated whenever the class is instantiated.
// (That is, whenever a class object is created.)
{
	private T[ ] queue;     // queue will hold elements of type T
	                     
	private int front = 0;  
	private int rear = -1;	 // queue empty signal
	
	    
	/************ myQueue **************
	 Constructor that creates the array
	 of the user specified size to hold
	 the queue elements of type T.
	***********************************/
	@SuppressWarnings("unchecked") //Suppression gets rid of IDE worries
	public MyGenericQueue(int cap)
	{
		queue = (T[ ]) new Object [cap];	
	}//end constructor
	
	/**************** dequeue ****************
	 dequeues off and returns the front of the
	 queue element to the user, if there is one.
	 ************************************/
	 T dequeue()
	 {
	    if (empty())
		 	throw new RuntimeException("queue Empty");
		//else 
	    
        //Remove from front, store in variable
        T element = queue[front];
        queue[front] = null;     
			  
        // Update front
        front = (front + 1)%queue.length;
			         
        return element;  
	    
	 }//end dequeue
	 
	/**************** enqueue ****************
	 Enqueues the element passed to it onto
	 the top of the queue, if the queue is
	 not already full.
	 *************************************/
	 void enqueue(T element)
	 {
	 	 if (isFull())
		   throw new RuntimeException("queue Overflow");
	 	 //else
	
	      //Update rear and add to it
	      rear = (rear + 1)%queue.length;
	      queue[rear] = element;
	     
	 	 
	 }//end dequeue
	 
	 /******************* size *******************
	  Returns the number of elements in the queue.	
	  ********************************************/
	  int size()
	  {
		 return (((rear - front) + 1)%queue.length);
	  }//end size
	  
	
	/*************** empty ****************
	 Returns true if the queue is empty, or
	 false if it contains any elements.
	 *************************************/
	 boolean empty()
	 {
	 	return (front == (rear + 1)%queue.length);
	 }//end empty
	 
	 /**************isFull*****************
	  Returns true if the queue is full
	  *************************************/
	 boolean isFull()
	 {
		 return (front == (rear + 2)%queue.length);
	 }//end isFull
	 
}// end myGenericQueue class declaration

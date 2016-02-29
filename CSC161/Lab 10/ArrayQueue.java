//Coded by Nicholas Drazenovic



/**
   The ArrayQueue class uses an 
	array to implement a queue.
*/

class IntArrayQueue
{
    private int [ ] q; // Holds queue elements
    private int front;    // Next item to be removed
    private int rear;     // Next slot to fill
    private int size;     // Number of items in queue   
    
    /**
       Constructor.
       @param capacity  The capacity of the queue.
    */
    
    IntArrayQueue(int capacity)
    {
        q = new int[capacity];
        front = 0; 
        rear = 0;
        size = 0;
    }
    
    /**
       The capacity method returns the length of 
       the array used to implement the queue.
       @return The capacity of the queue.
    */
    
    public int capacity()
    {
        return q.length;
    }
    
    /**
       The enqueue method adds an element to the queue.
       @param s The element to be added to the queue.
		 @exception QueueOverFlowException When there
		 is no more room in the queue.
	 */
    
    public void enqueue(int s)
    {
       if (size == q.length)
           throw new QueueOverFlowException();
       else
       {
           // Add to rear
           size ++;
           q[rear] = s;
           rear ++;
           if (rear == q.length) rear = 0;           
       }
    }
    
    /**
       The peek method returns the item 
       at the front of the queue.
       @return element at front of queue.
		 @exception EmptyQueueException When 
		 the queue is empty.
    */
    
    public int peek()
    {
        if (empty())
             throw new EmptyQueueException();
        else
             return q[front];
    }
    
    /**
       The dequeue method removes and returns 
       the element at the front of the queue.
       @return The element at the front of the queue.
		 @exception EmptyQueueException When 
		 the queue is empty.
    */
    
    public int dequeue()
    {
        if (empty())
            throw new EmptyQueueException();
        else
        {
            size --;
           // Remove from front
           int value = q[front];
			  
			  // Facilitate garbage collection  
           q[front] = 0;     
			  
           // Update front
           front++;
           if (front == q.length) front = 0;
			         
           return value;        
        }
    }
    
    /**
       The empty method checks to see if 
		 this queue is empty.
       @return true if the queue is empty and 
		 false otherwise.
    */
    
    public boolean empty()
    {
        return size == 0;
    }
    
    /**
       The toString method returns a 
		 readable representation of the 
		 contents of the queue.
       @return  The string representation
		 of the contents of the queue.
     */
    
    public String toString()
    {
      StringBuilder sBuilder = new StringBuilder();
      sBuilder.append("front = " + front + "; ");
      sBuilder.append("rear = " + rear + "\n");
      for (int k = 0; k < q.length; k++)
      {
          if (q[k] != 0)
             sBuilder.append(k + " " + q[k]);
          else 
             sBuilder.append(k + " ?");
          if (k != q.length - 1)
			    sBuilder.append("\n");
      }
      return sBuilder.toString();        
    }    
    
	public String qString()
	{
		String qContents = "";
		if (!this.empty())
		{
			for (int i = 1, indx = front; i <= size; i++)
			{
				qContents += i + ": " + q[indx] + "\n";
				indx++;
				if (indx == q.length)
					indx = 0;
			}
		}
		return qContents;
	}
}
/** The StringListType class is a concrete class
    for storing an unordered list of String objects.
*/

public class StringListType implements StringList
{
   // Constants for the default capacity and
   // the resizing factor.
   private final int DEFAULT_CAPACITY = 10;
   private final int RESIZE_FACTOR = 2;
   
   // Private Fields
   private String[] list;  // The list
   private int elements;   // Number of elements stored
   
   /** This constructor creates an empty list of the
       default capacity.
   */
   public StringListType()
   {
      list = new String[DEFAULT_CAPACITY];
      elements = 0;
   }

   /** This constructor creates an empty list of the
       specified capacity.
       @param capacity The initial capacity.
       @exception IllegalArgumentException if the
                  specified capacity is less than one.
   */
   public StringListType(int capacity)
   {
      if (capacity < 1)
         throw new IllegalArgumentException();
      
      list = new String[capacity];
      elements = 0;
   }
   
   /** Add a string to the end of the list.
       @param str The string to add. 
   */
   public void add(String str)
   {
      // If the list is full, resize it.
      if (elements == list.length)
         resize();
      
      // Add str to the end of the list.
      list[elements] = str;
      
      // Adjust the number of elements.
      elements++;
   }

   /** Add a string at a specific index.
       @param index The added string's position.
       @param str The string to add.
       @exception IndexOutOtBoundsException When index
                  is out of bounds.
   */   
   public void add(int index, String str)
   {
      // First make sure the index is valid.
      if (index >= elements || index < 0)
         throw new IndexOutOfBoundsException();

      // If the list is full, resize it.
      if (elements == list.length)
         resize();
         
      // Shift the elements starting at index
      // to the right one position.
      for (int index2 = elements; index2 > index; index2--)
         list[index2] = list[index2 - 1];
      
      // Add the new element at index.
      list[index] = str;
      
      // Adjust the number of elements.
      elements++;
   }
   
   /** Clear the list. */
   public void clear()
   {
      for (int index = 0; index < list.length; index++)
         list[index] = null;
      elements = 0;
   }
   
   /** Search the list for a specified string.
       @param str The string to search for.
       @return true if the list contains the string;
               false otherwise.
   */
   public boolean contains(String str)
   {
      int index = 0;          // Index counter
      boolean found = false;  // Search flag
      
      // Step through the list. When the string
      // is found, set found to true and stop.
      while (!found && index < elements)
      {
         if (list[index].equals(str))
            found = true;
         index++;
      }
      
      // Return the status of the search.
      return found;
   }
   
   /** Get an element at a specific position.
       @param index The specified index.
       @return The element at index.
       @exception IndexOutOtBoundsException When index
                  is out of bounds.
   */
   public String get(int index)
   {
      if (index >= elements || index < 0)
         throw new IndexOutOfBoundsException();
      return list[index];
   }

   /** Gets the index of the first occurrence of the
       specified string.
       @param str The string to search for.
       @return The index of the first occurrence of str
               if it exists; -1 if str is not in the list.
   */
   public int indexOf(String str)
   {
      int index = 0;          // Index counter
      boolean found = false;  // Search flag
      
      // Step through the list. When the string
      // is found, set found to true and stop.
      while (!found && index < elements)
      {
         if (list[index].equals(str))
            found = true;
         else
            index++;
      }
      
      // Return the index of str or -1.
      if (!found)
         index = -1;
      return index;
   }
   
   /** Determines whether the list is empty.
       @return true if the list is emptly; false otherwise.
   */
   public boolean isEmpty()
   {
      return (elements == 0);
   }
   
   /** Remove a specific string from the list.
       @param str The string to remove.
       @return true if the string was found; false otherwise.
   */
   public boolean remove(String str)
   {
      int index = 0;          // Index counter
      boolean found = false;  // Search flag
      
      // Perform a sequential search for str. When it is
      // found, remove it and stop searching.
      while (!found && index < elements)
      {
         if (list[index].equals(str))
         {
            list[index] = null;
            found = true;
         }
         index++;
      }
      
      // If the value was found, shift all subsequent
      // elements toward the front of the list.
      if (found)
      {
         while(index < elements)
         {
            list[index - 1] = list[index];
            index++;
         }
         
         // Adjust the number of elements.
         elements--;
         
         //shrink the array
         desize();
         
      }
      
      // Return the status of the search.
      return found;
   }
   
   /** Remove a string at a specific index.
       @param index The index of the string to remove.
       @return The string that was removed.
       @exception IndexOutOtBoundsException When index
                  is out of bounds.
   */ 
   public String remove(int index)
   {
      if (index >= elements || index < 0)
         throw new IndexOutOfBoundsException();

      // Save the string, but remove it from the list.
      String temp = list[index];
      list[index] = null;
      index++;

      // Shift all subsequent elements toward
      // the front of the list.
      while(index < elements)
      {
         list[index - 1] = list[index];
         index++;
      }
      
      // Adjust the number of elements.
      elements--;
      
      //shrink the array
      desize();
      
      // Return the string that was removed.
      return temp;
   }
   
   /** Resizes the list to twice its current length. */
   private void resize()
   {
      // Calculate the new length, which is the current
      // length multiplied by the resizing factor.
      int newLength = list.length * RESIZE_FACTOR;
      System.out.println("Resizing from " + list.length + " to " + newLength);
      // Create a new list.
      String[] tempList = new String[newLength];
      
      // Copy the existing elements to the new list.
      for (int index = 0; index < elements; index++)
         tempList[index] = list[index];
      
      // Replace the existing list with the new one.
      list = tempList;
   }
  
   /** Replace the string at a specified index with
       another string.
       @param index The index of the string to replace.
       @param str The string to replace it with.
       @return The string previously stored at the index.
       @exception IndexOutOtBoundsException When index
                  is out of bounds.     
   */
   public String set(int index, String str)
   {
      if (index >= elements || index < 0)
         throw new IndexOutOfBoundsException();

      // Save the existing string at that index.
      String temp = list[index];
      
      // Replace the string with str.
      list[index] = str;
      
      // Return the previously stored string.
      return temp;
   }
   
   /** Get the number of elements in the list.
       @return The number of elements in the list.
   */
   public int size()
   {
      return elements;
   }
   
   /** Convert the list to a String array.
       @return A String array with the same elements as the list.
   */
   public String[] toStringArray()
   {
      // Create a String array large enough to hold
      // all the elements of the list.
      String[] stringArray = new String[elements];
      
      // Store the elements in the array.
      for (int index = 0; index < elements; index++)
         stringArray[index] = list[index];
      
      // Return the String array.
      return stringArray;
   }
   
   //Method to SHRINK the list
   private void desize()
   {
	   //Compare the amount of space available
	   if (elements <= list.length/2)
	   {
	      // Calculate the new length, which is the current
	      // length multiplied by the resizing factor.
	      int newLength = list.length / RESIZE_FACTOR;
	      System.out.println("Resizing from " + list.length + " to " + newLength);
	      // Create a new list.
	      String[] tempList = new String[newLength];
	      
	      // Copy the existing elements to the new list.
	      for (int index = 0; index < elements; index++)
	         tempList[index] = list[index];
	      
	      // Replace the existing list with the new one.
	      list = tempList;
	   
	   }
   }//End desize
   
   public String[] getList()
   {
	   return list;
   }
}

import java.util.Scanner;

/** This program gives a simple demonstration of some
    of the StringListType methods.
*/

public class StringListDemo
{
   public static void main(String[] args)
   {
      // Create a StringListType object.
      StringListType myList = new StringListType();

      //Display the size and capacity
      System.out.println("\nYou are using " + myList.size() + " of the " + myList.getList().length + " spaces available.");
      
      // Add to the list
      myList.add("Bam");
      myList.add("Pow");
      myList.add("Shoop");
      myList.add("Boop");
      myList.add("Pewpew");
      myList.add("meow");
      myList.add("Kersplat");
      myList.add("Shazam");
      myList.add("Hegegerger");
      myList.add("End");
      myList.add("Boo");
    
      // Display the list.
      System.out.println("\nHere are the words you entered:");
      displayList(myList);
      
      //Display the size and capacity
      System.out.println("\nYou are using " + myList.size() + " of the " + myList.getList().length + " spaces available.");
      
      // Remove the first and last elements.
      System.out.println("\nWe are now removing the first and last words from the list.");
      myList.remove(0);
      myList.remove(myList.size() - 1);
      
      // Display the list.
      System.out.println("\nNow with the first and last removed:");
      displayList(myList);
      
      //Display the size and capactiy again
      System.out.println("\nYou are now using " + myList.size() + " of the " + myList.getList().length + " spaces available");
      
   }
   
   /** The displayList method displays the elements in
       a StringList object.
       @param list The list to display.
   */
   public static void displayList(StringList list)
   {
      for (int index = 0; index < list.size(); index++)
      {
         System.out.println("Index " + index + ": " +
                            list.get(index));
      }
   }
}
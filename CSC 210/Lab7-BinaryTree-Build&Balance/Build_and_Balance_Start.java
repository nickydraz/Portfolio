 /* ********************************************************
                Build_and_Balance_Start
 
 * Starting with a set of unordered keys stored in an array
 * this program builds, then balances, a binary search tree
 * stored in dynamic memory. The array holds the values to  
 * be placed in the tree. It is NOT an array representation 
 * of a binary tree.
 *
 * It also contains function, and calls to them, to save 
 * the tree to a file and later restore it from the file. 
 * THESE functions may be completed for extra credit. 
 *
 * This program was begun by Prof. Walters and completed by
 *               Nicholas Drazenovic.
 * Try to complete the program in lab today. If you cannot,
 * then hand it in on Thursday, Feb. 26, 2015 
 ************************************************************/
  
import java.util.Scanner;
import java.io.*;

public class Build_and_Balance_Start
{
	
	//************ Node Class **************
	public static class Node
	{
		public Node left = null;
		public int  value;
		public Node right = null;
		
		// Constructor
		public Node (int val)
		{
			value = val;
		}
	}// end Node class declaration
   
	//Scanner to read in backup file
   private static Scanner inputFile;
   
   //Printer to write to backup file
   private static PrintWriter outputFile;
   
   // ******************************* main ********************************
   public static void main (String[] args) throws IOException
	{
	   //Array of ints to be put into the tree
		int[] keys = {16, 1, 47, 10, 5, 7, 38, 11, 12, 2, 25, 40, 3, 42, 4};
		
		// Build a binary search tree, print its values in order, and find its height. 
		Node root = buildTree(keys);
		printInOrder(root); 
		System.out.println("\n The tree height is " + getTreeHeight(root) + "\n");
		
		// Now replace the old unsorted keys array values, with the
		// sorted values obtained by traversing the tree in-order.
		reOrderKeys(keys, 0, root);
       
		// Next, rebuild the tree using the ordered keys, verify the
		// new tree is still in order, and see what the tree height is now. 
		// AWFUL isn't it.
		root = buildTree(keys);              
		printInOrder(root);                        
		System.out.println("\n The tree height is " + getTreeHeight(root) + "\n");
		 
		// Finally, call the recursive balanceTree method to build a BALANCED tree
		// from the ordered data. Verify that it is still in order, and
		// see what the tree height is now. 
		root = buildBalancedTree(keys, 0, keys.length-1);
		printInOrder(root);                        
		System.out.println("\n The tree height is " + getTreeHeight(root) + "\n");
      
      // For EXTRA CREDIT, also create a saveTree function that writes the tree 
      // data to a file in pre-order and a restoreTree function that reads the data
      // back in and rebuilds the tree. Test it with the following statements.
      
		startTreeSave(root, "savedTree.dat");
      	root = null;
      	root = startTreeRestore("savedTree.dat");
      	System.out.println( "\nHere is the tree after saving it to a file \n" +
                           "and then reading it back in and restoring the tree.");
      	printInOrder(root);                        
      	System.out.println("\n The height of the rebuilt tree is " + 
      			getTreeHeight(root) + "\n");
       
	}// end main method
 

	// ********************* buildTree **********************
	// Builds a new tree and returns a pointer to its root.
   // ******************************************************
   public static Node buildTree (int [] array)
	{
	 
		Node root = null;         // Start a new tree
		 
		//Traverse the array, inserting each index into the tree
		for (int item = 0; item < array.length; item++) 
		   root = insertNode(root, array[item]);
		          
		return root;              // Root will have been changed by insertNode
		                          // to point to the root of the new tree.
	} // end buildTree method
	 
	  
	// **************************** insertNode **************************
	// Inserts a new node in a tree. Assumes it is NOT a duplicate value.
   // ******************************************************************
	public static Node insertNode(Node myNode, int val)
	{
	 
		//If null, place in the root
		if (myNode == null)
		{  
			myNode = new Node(val);    // Create the first node with root referencing it.
		}
		else                        // Create and link in the other nodes.
		{  
			if (val > myNode.value)
				myNode.right = insertNode(myNode.right, val);
			else
				myNode.left = insertNode(myNode.left, val);
		}
		
		return myNode;  
		
	} // end insertNode method
                                                               
    
	// ********************** printInOrder **********************
	public static void printInOrder(Node myNode)
 
	{
	
		//Null check
		if (myNode == null)
			return;
		
		//Print the tree's elements in order
		printInOrder(myNode.left);
		System.out.print(myNode.value + " ");
		printInOrder(myNode.right);
		
	}// end printInOrder method
   
	
	// ******************* getTreeHeight ***********************
	public static int getTreeHeight(Node myNode) 
	{
	    
		if (myNode == null)
			return 0;    // I don't exist and thus have no height.
		       
		//If a leaf, do not add to the height
		if (isLeaf(myNode))
			return 0;
		else //add 1 and the greater value of either the left or right recursive call
			return 1 + Math.max(getTreeHeight(myNode.left), getTreeHeight(myNode.right));
		
	}//end getTreeHeight method 
   
	// ******************** isLeaf ****************************
	public static boolean isLeaf(Node myNode)
	{
		//If there are no children, the node is a leaf node
		if (myNode.left == null && myNode.right == null )
			return true;
		
		return false;
	}
	    
	// ******************** reOrderKeys *********************
	// Place the values in order in the keys array by reading
	// them from the tree while doing an in-order traversal.
	// pos is the next array location into which a value must 
	// be placed. 
	// ******************************************************
	public static int reOrderKeys(int [] keys, int pos, Node myNode)
	{
    
       if (myNode == null)
          return pos;         // pos was not changed because I didn't
                              // add anything to the array.
       // else
       pos = reOrderKeys(keys, pos, myNode.left);   //Build the left tree
				 
       keys[pos] = myNode.value; //add value to the tree
       pos++; //increment the position index
		 
       pos = reOrderKeys(keys, pos, myNode.right);   //Build the right tree
       
       //Return the position index
       return pos;
       
	}// End reOrderKeys method
   
	    
	// ************************buildBalancedTree ************************
	public static Node buildBalancedTree (int[] array, int start, int end) 
	{
	
		if (start > end)
			return null;
		
	
		// else
		int mid = (end + start) / 2; //Calculate the middle index of the array
	
		//Create the new node based on the middle index 
		Node newGuy = new Node(array[mid]);
		
		//Recursive calls to set the left and right pointers of the new node
		newGuy.left  =  buildBalancedTree(array, start, mid - 1);
		newGuy.right =  buildBalancedTree(array, mid + 1, end);
		    
		//Return the node to its parent
		return newGuy;
	    
	} // end buildBalancedTree method
   
   
  	// ********************** startTreeSave **********************
	// Makes the initial call to the recursive saveTree function
   // ***********************************************************
   public static void startTreeSave(Node root, String fname) throws IOException
   {  
      outputFile = new PrintWriter(fname);
      saveTree(root, outputFile);
      outputFile.close();
      
   } // end startTreeSave 


  	// *********************** save Tree **********************
	// Recursive function to write the tree values to a file
   // in pre-order. When a null pointer is encountered, a -1 
   // should be written to the file before returning.
   // ********************************************************
   public static void saveTree(Node myNode, PrintWriter outputFile)throws IOException
   {
   
	//If the node is empty, print a -1
	if (myNode == null)
	{
		outputFile.println(-1);
		return;
	}
	
	//Else, print to the file in pre-order
    outputFile.println(myNode.value);
    saveTree(myNode.left, outputFile);
    saveTree(myNode.right, outputFile);
   
   }// end saveTree

   
   // ************************ startTreeRestore ********************
   // Makes the initial call to the recursive restoreTree function
   // **************************************************************
   public static Node startTreeRestore(String fname) throws IOException
   {  
      File f = new File(fname);
      inputFile = new Scanner(f);
      
      Node newRoot = restoreTree();
      
      inputFile.close();
      return newRoot;
      
   } // end startTreeRestore   
   
   // ************************ restoreTree ************************
	// Recursive function to write the tree values to a file
   // in pre-order. 
   // **************************************************************
   public static Node restoreTree() throws IOException
   {
	   
	   //Read in the next line of the file
	   String line = inputFile.next();
	   
	   //If the line is null, end out
	   if (line == null)
		   return null;
	   
	   //If the current line is -1, part of a leaf node.
	   //Return without adding to tree
	   if (Integer.parseInt(line) == -1)
		   return null;
	   
	   //Create the new node with the value from the file
	   Node newNode = new Node(Integer.parseInt(line));
	   
	   //Recursive calls to set the left and right pointers of the node
	   newNode.left = restoreTree();
	   newNode.right = restoreTree();
	  
	   //Return the node to its parent
	   return newNode;
     
     
   }// end restoreTree
    
} // end BalanceTree class
  
  /* ************************  ORIGINAL DATA  **************************** 
   int[] keys = {16, 1, 47, 10, 5, 7, 38, 11, 12, 2, 25, 40, 3, 42, 4 };
 
    **************************  RUN OUTPUT  *************************
1 2 3 4 5 7 10 11 12 16 25 38 40 42 47 
 The tree height is 6

1 2 3 4 5 7 10 11 12 16 25 38 40 42 47 
 The tree height is 14

1 2 3 4 5 7 10 11 12 16 25 38 40 42 47 
 The tree height is 3


Here is the tree after saving it to a file 
and then reading it back in and restoring the tree.
1 2 3 4 5 7 10 11 12 16 25 38 40 42 47 
 The height of the rebuilt tree is 3
 */
//Modified by Nicholas Drazenovic 
//BinaryTree.java 

/****************************************************
 * Title: Binary Trees
 * Author: Parlante, N
 * Date: 2000
 * Code Version: Unknown
 * Availability: http://cslibrary.stanford.edu/110/BinaryTrees.html#java
 * 
 * 
 * This is article #110 in the Stanford CS Education Library. 
 * This and other free CS materials are available at the library 
 * (http://cslibrary.stanford.edu/). 
 * That people seeking education should have the opportunity to find it.
 * This article may be used, reproduced, excerpted, or sold so long 
 * as this paragraph is clearly reproduced. 
 * Copyright 2000-2001, Nick Parlante, 
 * nick.parlante@cs.stanford.edu.
 * 
 * 
 * 
 *******************************************************************************/
public class BinaryTree { 
  // Root node pointer. Will be null for an empty tree. 
  private Node root; 
 

  /* 
   --Node-- 
   The binary tree is built using this nested node class. 
   Each node stores one data element, and has left and right 
   sub-tree pointer which may be null. 
   The node is a "dumb" nested class -- we just use it for 
   storage; it does not have any methods. 
  */ 
  private static class Node { 
    Node left; 
    Node right; 
    String data;

    Node(String word) { 
      left = null; 
      right = null; 
      data = word; 
    } 
  }

  /** 
   Creates an empty binary tree -- a null root pointer. 
  */ 
  public BinaryTree() { 
    root = null; 
  } 
 

  /** 
   Returns true if the given target is in the binary tree. 
   Uses a recursive helper. 
  */ 
  public boolean lookup(String data) { 
    return(lookup(root, data)); 
  } 
 

  /** 
   Recursive lookup  -- given a node, recur 
   down searching for the given data. 
  */ 
  private boolean lookup(Node node, String data) { 
    if (node==null) { 
      return(false); 
    }

    if (data.equals(node.data)) { 
      return(true); 
    } 
    else if (data.compareToIgnoreCase(node.data) > 0) { 
      return(lookup(node.left, data)); 
    } 
    else { 
      return(lookup(node.right, data)); 
    } 
  } 
 

  /** 
   Inserts the given data into the binary tree. 
   Uses a recursive helper. 
  */ 
  public void insert(String word) { 
    root = insert(root, word); 
  } 
 

  /** 
   Recursive insert -- given a node pointer, recur down and 
   insert the given data into the tree. Returns the new 
   node pointer (the standard way to communicate 
   a changed pointer back to the caller). 
  */ 
  private Node insert(Node node, String word) { 
    if (node==null) { 
      node = new Node(word); 
    } 
    else { 
      if (word.compareToIgnoreCase(node.data) > 0) { 
        node.left = insert(node.left, word); 
      } 
      else { 
        node.right = insert(node.right, word); 
      } 
    }

    return(node); // in any case, return the new pointer to the caller 
  } 
  
  //Method to display tree
  //Only here for testing to ensure that the nodes are being stored properly
	public void printTree() { 
		 printTree(root);  
		}
	
	private void printTree(Node node) { 
		 if (node == null) return;

		 // left, node itself, right 
		 printTree(node.left); 
		 System.out.println("<-- " + node.data); 
		 printTree(node.right);
		 System.out.println("--> " + node.data);
		} 

}
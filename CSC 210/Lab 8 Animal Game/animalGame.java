/*Animal Game Class
 * Written by Nicholas Drazenovic
 * and Emily Huizenga
 * This class defines an animalGame object. It also defines a node object
 */
import java.io.*;
import java.util.Scanner;
		
public class animalGame {
	
	//Node class 
	public class Node
	{
		//Lchild is the 'No' answer field
		//Rchild is the 'Yes' answer field
		Node Lchild, Rchild;
		String data;
		
		//=====CONSTRUCTORS=======
		
		public Node(String value)
		{
			data = value;
			Lchild = null;
			Rchild = null;
			
		}//end constructor
		
		public Node(String value, Node left, Node right)
		{
			data = value;
			Lchild = left;
			Rchild = right;
		}//end constructor
		
	}//end Node class
	
	//Scanner to read in saved game file
	private static Scanner inputFile;
	   
	//Scanner to read user input
	private static Scanner keyboard = new Scanner(System.in);
	
	//Printer to write to game file
	private static PrintWriter outputFile;
	
	Node root = null;
	
	/******************************************
	 * 				startNewGame
	 * This method creates a new tree
	 * with an initial question and answer set
	 ******************************************/
	public void startNewGame()
	{
		root = new Node ("Does it have legs?", 
				new Node("SNAKE"), new Node("CAT"));
	}//end startNewGame
	
	/******************************************
	 * 				resumeOldGame
	 * This method takes in a file name and makes
	 * the initial call to the rebuildTree method
	 ******************************************/
	public void resumeOldGame(String fname) throws IOException
	{
		  File f;
			
		  if (fname.contains(".")) //if they used a file extension, 
			  					   //don't add .txt
		  {
			  f = new File(fname);
		  }
		  else
		  {
			  f = new File(fname + ".txt"); //if they did not, add .txt
		  }
		  
		  inputFile = new Scanner(f);
		  
		  root = rebuildTree(); 
		  
		  inputFile.close();
	  
	}//end resumeGame
	
	/******************************************
	 * 				rebuildTree
	 * This method recreates the game tree from
	 * the given file.
	 ******************************************/
	private Node rebuildTree() throws IOException
	{
	   //Read in the next line of the file
	   String line = inputFile.nextLine();
	   
	   
	   //return null if we have reached the end of the file
	   if (line == null)
		   return null;
	   
	   //if a -1 is read in, it indicates that the 
	   //previous node had null pointers
	   if (line.equals("-1"))
		   return null;
	   
	 //Create the new node with the value from the file
	   Node newNode = new Node(line);
	
	   //Recursive calls to set the left and 
	   //right pointers of the node
	   newNode.Lchild = rebuildTree();
	   newNode.Rchild = rebuildTree();
	
	   //Return the node to its parent
	   return newNode;
	}//end rebuildTree
	
	/**************************************************
	 * 					playGame
	 * This method traverses the decision tree,
	 * prompting the user for answers to its questions.
	 **************************************************/
	public void playGame()
	{
		Node previous = null; //points to the previous node in the tree
		Node current = root;  //points to the current node, initially the root
		
		char answer; 		  //holds the user's input
		
		//while there are still more questions to ask
		while (!isLeaf(current))
		{
			System.out.println(current.data); //display the question
			answer = getAndValidateInput();   //get the user's answer
			
			previous = current;				  //move to the next node
			
			if (answer == 'Y')
			{
				//if the answer is yes, move down the right branch
				current = current.Rchild;
			}
			else
			{   //otherwise move down the left branch
				current = current.Lchild;
			}
		}//end while
		
		//Now we have reached an animal. 
		//Determine whether it was what they had in mind
		System.out.println("Were you thinking of " + current.data + "?");
		answer = getAndValidateInput(); //get the user's answer
		
		if (answer == 'Y') 
			//if you guessed correctly, display win dialog and return to main
			System.out.println("Fantastic, told you I would win!");
		else
			insertNewQuestion(previous, current); //if you did not guess 
												  //correctly, add their
												  //animal to the tree
	
	}//end playGame
	
	/******************************************
	 * 				getAndValidateInput
	 * This method gets input from the user and
	 * makes sure that it is yes or no
	 ******************************************/
	public char getAndValidateInput()
	{
		String input = keyboard.nextLine();
		char answer = input.toUpperCase().charAt(0); //convert to a capital 
													 //character
		
		//if there is an error, prompt for another input
		while (!validateInput(answer))
		{
			System.out.println("Entry invalid, please try again: ");
			input = keyboard.nextLine();
			answer = input.toUpperCase().charAt(0);
		}//end while
		
		return answer;
	}//end getAndValidate
	
	/******************************************
	 * 				validateInput
	 * This method validates the character sent
	 * to it
	 ******************************************/
	public boolean validateInput(char input)
	{
		return (input == 'Y' || input == 'N');
	}//end valudateInput
	
	/******************************************
	 * 				insertNewQuestion
	 * This method inserts a new question and 
	 * answer into the game tree.
	 ******************************************/
	public void insertNewQuestion(Node oldQuestion, Node oldAnswer)
	{
		//get the animal the user was thinking of
		System.out.println("What were you thinking of?");
		String animal = keyboard.nextLine();
		
		Node newAnimal = new Node(animal); //create a new node containing 
										   //the animal
		
		//prompt for a question that corresponds to the animal
		System.out.println("Please enter a question that would lead to your "
				+ "animal: ");
		String newQuestion = keyboard.nextLine();
	
		//Create the node containing the new question
		//Link it in with the guess the computer made,
		//and the new animal from the user.
		Node newQ = new Node(newQuestion, oldAnswer, newAnimal);
		
		/*
		 * Determine which subtree to put the new question in.
		 * 
		 * If the computer guessed the right subtree's animal,
		 * place the new question in the right subtree.
		 * Otherwise, the animal was in the left subtree,
		 * so that's where the new question goes as well. 
		 */
		if (oldQuestion.Rchild.data == oldAnswer.data)
			oldQuestion.Rchild = newQ;
		else
			oldQuestion.Lchild = newQ;
		
	}//end insertNewQuestion
	
	/******************************************
	 * 				isEmpty
	 * Determines if the tree is empty by 
	 * checking the root. 
	 * Returns true if empty, false otherwise 
	 ******************************************/
	public boolean isEmpty()
	{
		return (root == null);
	}//end isEmpty
	
	/******************************************
	 * 				isLeaf
	 * 
	 * Determines if the current node is a 
	 * leaf (answer).
	 ******************************************/
	public boolean isLeaf(Node myNode)
	{
		return (myNode.Lchild == null && myNode.Rchild == null);
	}//end isLeaf
		
	/******************************************
	 * 				startGameSave
	 * Makes initial call to saveGame to
	 * save the current game into a file.
	 ******************************************/
	public void startGameSave(String fname) throws IOException
	{  
		//Perform a check if the file name entered
		//already has an extension
	   if (fname.contains("."))
		   outputFile = new PrintWriter(fname);
	   else //If there is no extension, add '.txt' 
		   outputFile = new PrintWriter(fname + ".txt");
	   
	  saveGame(root, outputFile);
	  outputFile.close();
	      
	} // end startTreeSave 
	
	/******************************************
	 * 				saveGame
	 * Saves the game to a file.
	 ******************************************/
	public void saveGame(Node myNode, PrintWriter outputFile) throws IOException
	{
	    
	 	//If the node is empty, print a -1
	 	if (myNode == null)
	 	{
	 		outputFile.println(-1);
	 		return;
	 	}
	 	
	 	//Else, print to the file in pre-order
	     outputFile.println(myNode.data);
	     
	     //Recursive calls for left and right subtrees
	     saveGame(myNode.Lchild, outputFile);
	     saveGame(myNode.Rchild, outputFile);
	
	}//end saveGame
			
}//end animalGame
	
	

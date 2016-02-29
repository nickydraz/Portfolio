//AntWalk Game
//Modified by Nicholas Drazenovic
//BONUS #2
//Main method primarily written by J. Walters

import javax.swing.*;			//Needed for GUI

public class AntGame_Start
{
	static private int size = 50; //Size of the board
				       //Direction is as follows: North = 0, East = 1, South = 2, West = 3
		
	//=============================================================
	// Main Method
	// Creates Scanner, gets number of moves from user
	// Then passes that to the ant object to play the game
	// Prints out results and loops around until user enters '0'
	// ============================================================
	public static void main(String[] args)
	{

		int numMoves;		
	
		//Create Ant object
		Ant ant = new Ant(size);
		
		//Although GUI is used, continue to print dialogs out in the console
		System.out.println("\t\t    Langston's Ant\n");
		
		//Get number of Moves from the User
		numMoves = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of moves (or 0 to quit): "));

	  	// Play the game
		while (numMoves > 0)            
	   {	
			//Reset the board, setting all the tiles to RED,
			//as well resetting the ant's position and direction
			ant.board.resetBoard();
			ant.row = ant.col = size/2;
			ant.currentDir = 0;
		
			ant.makeMoves(numMoves);
			System.out.println("After " + numMoves + " moves the ant is on tile ("
			                              + ant.getRow() + "," + ant.getCol() + ")\n");
			
			JOptionPane.showMessageDialog(null, "After " + numMoves + " moves the ant is on tile (" 
										  + ant.getRow() + "," + ant.getCol() + ")");
			//System.out.print("Enter the number of moves (or 0 to quit): ");
			//numMoves = keyboard.nextInt();
			numMoves = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of moves (or 0 to quit): "));
		
			//Delay before resetting the board
			ant.board.sleep(1000);
			
      }//end while loop
		
		System.exit(0);
	
	}// End main

}// End AntWalk1 class


//=====================================================================
//
// Please note, my console output looks slightly different from before,
// as I use JOptionPane to provide a GUI for prompting
// the user for numMoves, rather than having it appear in the console.
// 
// The numbers that appear below are still the numbers given by the user, 
// and not hard coded into the program.
//=====================================================================
/**
	   		    Langston's Ant

After 1 moves the ant is on tile (24,25)

After 100 moves the ant is on tile (23,25)

After 500 moves the ant is on tile (19,19)

After 5000 moves the ant is on tile (35,23)


**/
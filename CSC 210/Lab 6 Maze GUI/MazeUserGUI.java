/******************************************
 * Client code written by Prof. Walters   *
 * to test the maze solving program.      *
 * Modified by Nicholas Drazenovic for GUI*
 *****************************************/
import java.util.Scanner;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MazeUserGUI extends JFrame 
{
 	//**********************  main  *************************
 	public static void main(String[] args) throws IOException
	{
		final int numMazes = 4;
		
		Scanner keyboard = new Scanner(System.in);

		// Create the array of Maze objects
		Maze[] aMaze = new Maze[numMazes];
		aMaze[0] = new Maze (8, "maze1.txt");
		aMaze[1] = new Maze(15, "maze2.txt");
		aMaze[2] = new Maze(20, "maze3.txt");
		aMaze[3] = new Maze( 8, "maze4.txt");
		

		
		//Loop once for each Maze object
		for (int num = 0; num < numMazes; num++)
		{
		
			//Display the board
			displayBoard(aMaze[num]);
			
			//Attempt to find a path
			doMaze(aMaze[num]);
			
			//Close the window, so the next maze may open
			aMaze[num].board.dispose();
			
		}
	}// end main

	/**********************************************
	 *                  doMaze                    *
	 * Calls on Maze object to traverse the maze, *
	 * finding a solution path if one exists. Also*
	 * displays the maze before and again after   *
	 * it is traversed.                           *
	 **********************************************/
	public static void doMaze(Maze myMaze)
	{ 		
	   // Solve the maze and display the result
	   if (myMaze.solveMaze())   // if there was a solution
		   JOptionPane.showMessageDialog(null, "Maze complete!");
		else
			JOptionPane.showMessageDialog(null, "No solution to this maze.");
	   
	}// end doMaze
	
	public static void displayBoard(Maze myMaze)
	{
		myMaze.displayBoard();
	}
}// end MazeUser class declaration

/*****************************************
 * Client code written by Prof. Walters  *
 * to test the maze solving program.     *
 *****************************************/
import java.util.Scanner;
import java.io.*;

public class MazeUser 
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
			System.out.println("\n\nMaze " + (num + 1) + "\n");
			doMaze(aMaze[num]);
			System.out.print("Press Enter to continue.");
		   keyboard.nextLine();
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
		// Print original maze
		System.out.println(myMaze.getVisualMaze());
	
	   // Solve the maze and print the result
	   if (myMaze.solveMaze())   // if there was a solution
			System.out.println("\nHere is the solution:\n");
		else
			System.out.println("\nThere is no solution:\n");
			
	  	System.out.println(myMaze.getVisualMaze());
		
	}// end doMaze
}// end MazeUser class declaration

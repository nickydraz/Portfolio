//Maze class used by MazeUser Class
//Coded by Nicholas Drazenovic
//Creates the maze that is traversed 
//by the client program.

//Needed for file input
import java.io.*;
//Scanner needed for reading the file
import java.util.Scanner;

public class Maze {
	
	
	int size; //Size (dimensions) of the array
	String fileName; //file name of the txt file to be used
	char[][] mazeArray; //The array for the maze.
						//Will initialize at a later point.
	//The GUI of the maze
	MazeGUI board;
	
	/*******************************
	 * 			Maze Constructor
	 * @throws IOException
	 * @param s, fName
	 * 
	 * Takes two arguments: size of the maze (x times y),
	 * and the file name of the txt file to be used.
	 * 
	 * Reads in the txt file of the maze, 
	 * then translates it to an array of 
	 * chars.
	 *******************************/
	public Maze(int s, String fName) throws IOException
	{
		size = s;
		fileName = fName;
		
		//Initialize the Maze
		setupMaze(size);
		//String to hold current line being read
		String line;
		
		//create file reader
		FileReader freader = new FileReader(fileName);
		BufferedReader inFile = new BufferedReader(freader);
		
		line = inFile.readLine();
		
		//Scanner to read in each character of the line
		//Scanner charReader = new Scanner(line);
		
		//For loop for traversing each line
		for (int row = 0; line != null; row++)
		{
			
			//For loop for reading each character in the line
			for (int col = 0; col < line.length(); col++)
			{
				
				//Read in the next character in the line
				char currentChar = line.charAt(col); //charReader.next();
				
				
				//Based on the currentChar,
				//fill the index of the maze
				//accordingly.
				//X is a wall
				//0 is an open space
				//$ is the exit point
				//Any other characters are converted to @
				if (currentChar == 'X')
					mazeArray[row][col] = 'X';
				else if (currentChar == '0')
					mazeArray[row][col] = '0';
				else if (currentChar == '$')
					mazeArray[row][col] = '$';
				else 
					mazeArray[row][col] = '@';
				
			}//end col for
		
			//Read the next line in the file
			line = inFile.readLine();
		}//end row for
		
	
		//Close the streams
		//charReader.close();
		inFile.close();
		freader.close();
		
	}//end Maze constructor (2 args)
	
	/*******************************
	 * 			Maze Constructor
	 * @throws IOException
	 * 
	 * Takes no arguments.
	 * Reads in the txt file of the maze, 
	 * then translates it to an array of 
	 * chars.
	 *******************************/
	public Maze() throws IOException
	{
		
		size = 8;
		fileName = "maze1.txt";
		
		
		//Initialize the Maze
		setupMaze(size);
		//String to hold current line being read
		String line;
		
		//create file reader
		FileReader freader = new FileReader(fileName);
		BufferedReader inFile = new BufferedReader(freader);
		
		line = inFile.readLine();
		
		//Scanner to read in each character of the line
		Scanner charReader = new Scanner(line);
		
		//For loop for traversing each line
		for (int row = 0; line != null; row++)
		{
			
			//For loop for reading each character in the line
			for (int col = 0; charReader.hasNext(); col++)
			{
				
				//Read in the next character in the line
				char currentChar = line.charAt(col);
				
				
				//Based on the currentChar,
				//fill the index of the maze
				//accordingly.
				//X is a wall
				//0 is an open space
				//$ is the exit point
				//Any other characters are converted to @
				if (currentChar == 'X')
					mazeArray[row][col] = 'X';
				else if (currentChar == '0')
					mazeArray[row][col] = '0';
				else if (currentChar == '$')
					mazeArray[row][col] = '$';
				else
					mazeArray[row][col] = '@';
					
				
			}//end col for
		
			//Read the next line in the file
			line = inFile.readLine();
		}//end row for
		
	
		//Close the streams
		charReader.close();
		inFile.close();
		freader.close();
	}//end default constructor
	
	/*******************************
	 * 				setupMaze
	 * @param size
	 * 
	 * Initializes the maze array
	 * to be traversed.
	 * 
	 *******************************/
	private void setupMaze(int size)
	{
		
		mazeArray = new char[size][size];
	
	}//end setupSize

	/*************************************************
	 * 			getVisualMaze
	 * @return
	 * 
	 * Method that returns a String containing the 
	 * visual contents of the maze.
	 **************************************************/
	public String getVisualMaze() 
	{
	
		String visualMaze = "";
	
		for (int row = 0; row < size; row++)
		{
			for (int col = 0; col < size; col++)
			{
				if (mazeArray[row][col] == 'X')
					visualMaze += "X";
				else if (mazeArray[row][col] == '0')
					visualMaze += " ";
				else if (mazeArray[row][col] == '.')
					visualMaze += ".";
				else if (mazeArray[row][col] == '*')
					visualMaze += "*";
				else if (mazeArray[row][col] == '$')
					visualMaze += "$";
				else
					visualMaze += "@";
				
			}//end col for
			
			//Break for the next row
			visualMaze += "\n";
			
			}//end row for
		
		//Return the String with the maze in visual form.
		return visualMaze;
		
	}//end getVisualMaze
	
	/*******************************************
	 * 				solveMaze
	 * 
	 * No parameters.
	 * 
	 * Makes the initial call to Move. 
	 * If, eventually, there is a path found, 
	 * solveMaze returns true to the client.
	 * If no path was found, false is returned.
	 * 
	 *******************************************/
	public boolean solveMaze() 
	{
		
		//Pass the move method the starting point of the maze
		if (moveGUI(1, 0))
			return true;
		else //If ultimately no path was found, return false.
			return false;
		
	}//end solveMaze
	
	
	/***************************************************
	 * 					move
	 * @param myRow
	 * @param myCol
	 * @return
	 * 
	 * This method controls the movement 
	 * through the maze.
	 * Does multiple checks to see if the move is
	 * legal. If not legal, returns false to previous call.
	 * If move is legal, this method changes the char in the 
	 * mazeArray index to '*'.
	 * 
	 * Once changed to a '*', begin checking
	 * the positions to the North, East, South, and West.
	 * 
	 * If a path is found in any of those directions, 
	 * return true to the previous call.
	 * 
	 * If no path is found in any of those directions, change
	 * the index to '.' to indicate backtracking has occurred, 
	 * then return false to the previous call.
	 * 
	 * 
	 */
	public boolean moveGUI(int myRow, int myCol)
	{
			
		//Check if the space is the exit point.
		//Change index character and return true
		//if so.
		if (mazeArray[myRow][myCol] == '$')
		{
			mazeArray[myRow][myCol] = '*';
			changeTile(myRow, myCol, 0);
			return true;
		}
		
		mazeArray[myRow][myCol] = '*';
		changeTile(myRow, myCol, 0);
		
		
		//Begin checking adjacent spaces
		
		//North
		if (freeSpace(myRow - 1, myCol) && moveGUI(myRow - 1, myCol))
			return true;
		
		//East
		if (freeSpace(myRow, myCol + 1) && moveGUI(myRow, myCol + 1))
			return true;
		
		//South
		if (freeSpace(myRow + 1, myCol) && moveGUI(myRow + 1, myCol))
			return true;
		
		//West
		if (myCol != 0 && freeSpace(myRow, myCol - 1) && moveGUI(myRow, myCol - 1))
			return true;
		
		/*This assignment occurs if and only if 
		 * backtracking needs to occur.
		 * Otherwise, a traversed space
		 * remains '*'.
		*/
		
		mazeArray[myRow][myCol] = '.';
		changeTile(myRow, myCol, 1);
		
		//If no path found, return false.
		return false;
	}//end moveGUI
	
	/*********************************************
	 * 				freeSpace
	 * 
	 * @param myRow
	 * @param myCol
	 * @return
	 * 
	 * Determines if the position is free.
	 * If a wall or invalid position, return false.
	 * 
	 **********************************************/
	public boolean freeSpace(int myRow, int myCol)
	{
		//If the space is not a wall or
		//invalid character, return true.
		if (mazeArray[myRow][myCol] == '0' || mazeArray[myRow][myCol] == '$')
			return true;
		else
			return false;
	}//end freeSpace
	
	/*********** displayBoard *********
	 * 
	 * Method to initialize and display
	 * the maze in a GUI 
	 * 
	 **********************************/
	public void displayBoard()
	{
		board = new MazeGUI(size, mazeArray);
	}//end displayBoard
	
	/******************************************
	 * 			changeTile
	 * 
	 * @param row
	 * @param col
	 * @param type
	 * 
	 * Initial call that calls the board's
	 * changeTile method, which will change
	 * the color of the tile in the maze,
	 * based on the type that is passed to it.
	 * 
	 ******************************************/
	public void changeTile(int row, int col, int type)
	{	
		board.changeTile(row, col, type);
	}//end changeTile
}//end class

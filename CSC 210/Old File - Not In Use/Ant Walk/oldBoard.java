//Board object for AntWalk game
//Coded by Nicholas Drazenovic


public class oldBoard {

	//Size has default value of 102
	int size = 102;
	int[][] board = new int[size] [size];
	
	//========================
	// Constructors
	//========================

	//Constructor for no arguments
	public oldBoard()
	{

	}//end constructor()


	//Constructor for passing in custom size
	public Board(int s)
	{
		s = size;
	}//end constructor(s)
	
	//=====================
	// setColor Method
	//=====================
	public void setColor(int row, int col)
	{
		//invert the color on a given space with XOR function
		board [row] [col] ^= 1;
	}//end setColor
	

	//====================
	// getColor Method
	//====================
	public int getColor(int row, int col)
	{
		return board[row][col];
	}//end getColor
	

	//==============================================
	// resetBoard
	// Method to reset the game board between games
	//==============================================
	public void resetBoard()
	{
		//Reset the board for the next game with for loop
		//Traverse the board, changing each tile to red for start of game
		//Red = 0, Blue = 1
		for (int i = 0; i < size; i++)
		{
			for (int n = 0; n < size; n++)
			{
				
				//Set color value to 0/Red
				board[i][n] = 0;
				
			}//end inner for
			
		}//end outer for
		
	}//end resetBoard
}//end Board class

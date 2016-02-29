//Ant object for AntWalk Game
//Coded by Nicholas Drazenovic

public class Ant {

	//currentDirection of Ant
		//Default to 0
		//NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3
	int currentDir = 0;

	//Initial position of the ant
		//Start in center of board
	int row = 25, col = 25;
	
	//Create board object
	Board board;
	
	
	//======================
	// Constructors
	//======================

	//Constructor with size argument for custom game board size
	public Ant(int size)
	{
		//Initialize Board object
		board = new Board(size);	
	

	}//end constructor(size)
	
	//=============================================
	// makeMoves
	// Method for moving the ant around the board
	// ============================================
	public void makeMoves(int numMoves)
	{
		//Loop to cycle through all the moves
		for (int i = 1; i <= numMoves; i++)
		{
			//Formulas for changing the position of the ant based on its current direction
			//If currentDir = 0 or 2, it will adjust the col value, but negate the row formula
			//Likewise, if the value of currentDir = 1 or 3, it will negate the col formula, while adjusting the row value
			//The negation occurs as a result of the statements at the end of the formula involving the modulus, which will result in either 
				//a 1 or a 0 value for the formula result
			col += ((((currentDir + 2) - 4) * (-1)) * (currentDir % 2));			
			row += ((currentDir - 1)) * ((currentDir + 1) % 2);

			//Change direction using the formula
			currentDir = (currentDir + (2 * board.getColor(row, col) + 3)%4)%4;
			
			//Change color of tile
			board.chgColor(row, col);
			
			//Delay before next move
			board.sleep(100);
			
		}//End for loop	

	}//end makeMoves
	

	//==================
	// getRow Method
	//==================
	public int getRow()
	{
		return row;
	}//end getRow
			
	//=================
	// getCol Method
	//=================
	public int getCol()
	{
		return col;
	}//end getCol
	
}//end Ant Class

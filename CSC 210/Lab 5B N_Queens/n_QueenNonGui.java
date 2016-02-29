//n-Queens
//Written by Nicholas Drazenovic

public class n_QueenNonGui {

	public static void main(String[] args) {
		
		//Number of Queens
		int n = 8;
		
		System.out.println("======================================"
						+ "\n|        Welcome to n-Queens!\t     |"
						+ "\n|    Coded by Nicholas Drazenovic    |"
						+ "\n======================================"
				+ "\n\nThere are currently " + n + " Queens in play.\n");
		
		
		//Create the board
		char[][] M = new char[n][n];
		
		if (placeQueen(M, n - 1))
			displayBoard(M, n);
		else
			System.out.println("There is no solution when N = " + n + ".");
		
	}//end main

	/****************************************
	 *			placeQueen
	 *
	 * Finds a place on the board
	 * to place the Queen.
	 * 
	 *  If positions for all Queens
	 *  is found, return true
	 *  Else, return false.
	 *
	 *****************************************/
	static boolean placeQueen(char[][] M, int myRow)
	{
	
		//if at row = -1, all places found
		if (myRow < 0)
			return true;
		
		//current column being examined, default to 0
		int currentCol = 0;
		
		//else
		//Traverse the row until a place is found
		while (currentCol < M.length)
		{
			if (!isThreatened(M, myRow, currentCol))
			{
				M[myRow][currentCol] = 'Q';
				if (placeQueen(M, myRow - 1))
					return true;
				else
					M[myRow][currentCol] = ' ';
			}//end if
			
			
			currentCol++;
			
		}//end while
		
		//If this is reached, no position found
		return false;
	
	}//end placeQueen
	
	/*************************************************
	 * 			isThreatened
	 * 
	 * Checks the, from the index, left diagonal, 
	 * right diagonal, and below for any other Queens
	 * Returns true if a Queen is found 
	 * 
	 **************************************************/
	static boolean isThreatened(char[][] M, int row, int col)
	{
		
		//If first row, there will be no threats
		if (row == M.length - 1)
			return false;
		
		
		//int n is used to check the diagonals
		//It is the offset for the row of the index to the 
		//row that is currently being checked
		//For each row beneath the current index being checked,
		//the left diagonal to be tested is column - n.
		//Likewise, the right diagonal is column + n.
			//Ex. If index is in row 0, and checking left diagonal in 
			//row 2, the formula is column - 2
		int n = 1;
		
		//Check if threatened
		for (int i = row + 1; i < M.length; i++)
		{
			
			//Check left diagonal
			if (col - n >= 0 && M[i][col - n] == 'Q')
				return true;
			
			//Check below
			if (M[i][col] == 'Q')
				return true;
			
			//Check right diagonal
			if (col + n < M.length && M[i][col + n] == 'Q')
				return true;

			//Increment n
			n++;
		}//end for
		
		//Return false if this is reached
		return false;
		
	}//end isThreatened
	
	/*********************************************
	 * 				displayBoard
	 * 
	 * Displays the board with the solution 
	 * that was found.
	 * 
	 * Will display as such (given #Queens = 4):
	 * 
	 * -----------------
	 * |   |   | Q |   |
	 * -----------------
	 * | Q |   |   |   |
	 * -----------------
	 * |   |   |   | Q |
	 * -----------------
	 * |   | Q |   |   |
	 * -----------------
	 * 
	 **********************************************/
	static void displayBoard(char[][] M, int queens)
	{
		//Loop for the rows
		for (int i = M.length - 1; i != -1; i--)
		{
			
			//Print line of dashes
			System.out.print("\n-");
			for (int j = 1; j <= queens; j++)
				System.out.print("----");
			
			//Start the new line which will display the actual grid spaces
			System.out.print("\n|");
			
			//Print the '|'s or 'Q's appropriately
			//Loop for the columns
			for (int n = 0; n < M.length; n++)
			{
				if (M[i][n] == 'Q')
					System.out.print(" Q |");
				else
					System.out.print("   |");
			}//end for n
			
			
		}//end for i
		
		//Print final line of dashes to clean things up
		System.out.print("\n-");
		for(int j = 1; j <= queens; j++)
			System.out.print("----");
		
	}//end displayBoard
	
}//end class
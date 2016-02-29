import javax.swing.JOptionPane;

//Main method and other methods 
//for n_Queens (GUI Version)
//Coded by Nicholas Drazenovic

public class n_QueensGUIMain {
	
	static boardFrame board;

	public static void main(String[] args) {
		
		
		//Number of queens
		int n = 8;
		
		//Create the initial board
		board = new boardFrame(n);
		
		//Create the array to be used
		char[][] M = new char[n][n];
		
		if (placeQueen (M, n - 1))
			JOptionPane.showMessageDialog(null, "A solution was found!");
		else
			JOptionPane.showMessageDialog(null, "No solution was found.");
		
		//Wait so the user can view final board, then shutdown.
		board.sleep(1500);
		System.exit(0);

	}//end main
	
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
				//Change the icon of the tile
				board.changeIcon(true, myRow, currentCol);
				
				M[myRow][currentCol] = 'Q';
				if (placeQueen(M, myRow - 1))
					return true;
				else
				{
					//Revert back
					board.changeIcon(false, myRow, currentCol);
					M[myRow][currentCol] = ' ';
				}
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

}

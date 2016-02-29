/******************************************************************
*  BiPartite graph Matching  									  *
*  Written by < Nicholas Drazenovic - 14 February 2015 >   		  *
*  Given a bipartite graph stored in an adjacency matrix, this    *
*  program finds a matching or reports that one does not exist.   *
*******************************************************************/

public class BiPartiteMatch_Start
{
		
 	// **************** main  ****************
	public static void main(String[] args)
	{
 		//System.out.println("Case 1: No matching exists. \n");

		//	                    a    b    c    d    e    No matching A, C, E
		//                    -----------------------   will only take a & d 
//		char [][]M = { /*E*/ {'y', 'n', 'n', 'y', 'n'},
//		               /*D*/ {'n', 'y', 'n', 'y', 'n'},
//					   /*C*/ {'y', 'n', 'n', 'y', 'n'},
//					   /*B*/ {'y', 'n', 'y', 'y', 'y'},
//					   /*A*/ {'y', 'n', 'n', 'y', 'n'} };
							

		//System.out.println("Case 2: Matching with no backtracking needed. \n");	
// 						
// 		//	                    a    b    c    d    e    Matching with no 
// 		//                    -----------------------   backtracking needed
// 		char [][]M = { /*E*/ {'y', 'n', 'n', 'y', 'y'},
// 		               /*D*/ {'n', 'y', 'n', 'y', 'n'},
// 		  		       /*C*/ {'n', 'y', 'y', 'n', 'n'},
// 					   /*B*/ {'y', 'n', 'y', 'n', 'n'},
// 					   /*A*/ {'n', 'y', 'n', 'n', 'y'} };


 		System.out.println("Case 3: Matching with backtracking. \n");
// 		
// 		//	                    a    b    c    d    e    matching with 
// 		//                    -----------------------   backtracking
 		char [][]M = { /*E*/ {'n', 'y', 'n', 'n', 'y'},
 		               /*D*/ {'y', 'n', 'y', 'n', 'n'},
 					   /*C*/ {'n', 'y', 'y', 'n', 'n'},
 					   /*B*/ {'n', 'y', 'n', 'y', 'n'},
 					   /*A*/ {'y', 'n', 'n', 'y', 'y'} };


		if (findMatch(M, M.length-1)) // Find matches starting with the last row
			displayMatches(M);	
		else
			System.out.println("There is no matching.");		  

	}// end main
	


	// **************** recursive findMatch  ****************
	public static boolean findMatch(char [][]M, int myRow)
	{ 
	
		//Boolean variable to show
		//whether a matching has been found.
		//Default to false.
		boolean found = false;
		
		//currentCol keeps track of
		//the column that is currently being checked.
		int currentCol = 0;
		
		//Traverse the row until you hit the end.
		while (currentCol < M.length)
		{
			//Hold the original value
			/*
			 * Old value required in order to not 
			 * simply reset everything to 'y' in the 
			 * backtracking case
			 */
			char oldValue = M[myRow][currentCol];
			
			//Check if it's a 'y' and if it's taken
			if (M[myRow][currentCol] == 'y' && !isTaken(M, myRow, currentCol))
			{
				//if 'y' and not taken, replace 'y' with 't'
				//and adjust boolean
				M[myRow][currentCol] = 't';
				found = true;
			}
			
			/* Base case */
			//If it's found a match 
			//and it's the final row,
			//return true.
			if (found && myRow == 0)
				return true;
			
			/* Recursive case */
			//If match was found, 
			//make the next call
			if (found)
				found = findMatch(M, myRow - 1);
			
			/* Backtracking case */
			//If match was not found,
			//revert the index back
			//to original value
			if (!found)
				M[myRow][currentCol] = oldValue;
			else //If match was found, return true
				return true;
			
			//Increment the current column index
			currentCol++;
			
		}//end while
	
		//If no matches were found,
		//then this return will be triggered
		return false;
		
	}// end findMatch

	
	// **************** isTaken  ******************
	// *******is this column already taken? ********
	public static boolean isTaken(char [][]M, int row_Im_In, int col_Im_In)
	{
		//Traverse the column
		for (int i = row_Im_In + 1; i <= M.length - 1; i++)
		{
			//If the current position is taken, return true.
			if (M[i][col_Im_In] == 't')
				return true;
		}
		
		//else
		return false;
	
	}// end isTaken

	
	// **************** displayMatches  ****************
	public static void displayMatches(char [][]M)
	{
		//Arrays that hold the values that are to be printed out.
		final char []MatchFrom = {'E', 'D', 'C', 'B', 'A'};
		final char []MatchTo   = {'a', 'b', 'c', 'd', 'e'};
		
		//Traverse the array, printing out the matches
		for (int i = M.length - 1; i >= 0; i--)
		{
			for(int n = 0; n < 5; n++)
			{
				//If 2D index contains a 't', then there was a match
				if (M[i][n] == 't')
					System.out.println(MatchFrom[i] 
							+ " matches to " + MatchTo[n]);
			}//end inner for
			
		}//end outer for

	}// end displayMatches
					
}// end class declaration

/* Output code from 3 runs of the program 
 	  === Nicholas Drazenovic === 

Case 1: No matching exists. 

There is no matching.


Case 2: Matching with no backtracking needed. 

A matches to b
B matches to a
C matches to c
D matches to d
E matches to e


Case 3: Matching with backtracking. 

A matches to a
B matches to d
C matches to b
D matches to c
E matches to e

*/

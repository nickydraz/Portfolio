//GUI Method for creating the interface for AntWalk Game
//Coded by Nicholas Drazenovic

import java.awt.*; //for GUI elements
import javax.swing.*; //for GUI elements

public class GUI {

	//Create necessary GUI elements
	static JLabel askNumMoves; //Holds the "How many moves" question
	static JLabel posResult; //Holds result to be displayed after all the 
										//moves have been completed
	static JButton[][] board = new JButton[102][102]; //The board itself that the ant walks on
	static JPanel gameBoard = new JPanel(); //create the panel that will hold the grid
	
	
	/**
	 * addContents method
	 * 
	 * builds the GUI for the user, takes in the container and the size of the board
	 * adds them all to the panel, allowing the GUI to be visible to the user
	 * 
	 * @param BPan
	 * @param size
	 */
	public static void addContents(Container BPan, int size)
	{
	 	//Set Layout
		BPan.setLayout(new BorderLayout());
		
		//Set Layout of the panel to hold the board
		gameBoard.setLayout(new GridLayout(size, size));

		
		//Begin building the keypad
		for (int i = 0; i < size; i++)
		{
			for (int n = 0; n < size; n++)
			{
				
				//Set button to RED
				board[i][n] = new JButton();
				
				board[i][n].setBackground(Color.RED);
			
				//Set buttons so that the color is visible
				board[i][n].setBorderPainted(false);
				board[i][n].setOpaque(true);
			
				//Change size of buttons
				board[i][n].setPreferredSize(new Dimension(25, 25));
				
				//Add to the gameBoard panel
				gameBoard.add(board[i][n]);
				
			}//end inner for
			
			
		
		}//end outer for
		
		//Begin building the upper portion of the board
			//With dialogs for user
		askNumMoves = new JLabel("Enter the number of moves (or 0 to quite)");
		posResult = new JLabel(); //Blank for now, will fill after a game is played
		
		//Create the input field for number of moves
		JTextField moveInput = new JTextField(); 
		moveInput.setEditable(false);
		
		//Create panel to hold dialog and input
		//Add the items to it
		JPanel textPan = new JPanel();
		textPan.add(askNumMoves);
		textPan.add(moveInput);
		
		//Add the contents to the main panel
		BPan.add(BorderLayout.NORTH, textPan);
		BPan.add(BorderLayout.CENTER, posResult);
		BPan.add(BorderLayout.SOUTH, gameBoard);
		
		
		
	}//end addContents
	
	
	
}

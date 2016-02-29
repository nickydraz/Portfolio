//Coded by Nicholas Drazenovic and Emily Huizenga

// PlayTicTacToe - A simple demonstration of some Java graphics
// used to play a game of Tic-Tac-Toe

import java.awt.*;
import java.awt.event.*;  // needed for event handlers
import javax.swing.*;

public class PlayTicTacToe 
{
    // These variables are used in setting the constraints of each 
	// component as it is added to the frame
	
   final static boolean shouldFill = true;
   final static boolean shouldWeightX = true;
   final static boolean RIGHT_TO_LEFT = false;
    
    // Declare the components
    
   static JLabel msg;  // text string "Player: "
   static JButton buttonX, buttonO;  // selectors for which symbol is playing
   static JButton [] boardButtons = new JButton[9];  // for the 9 cells on the board
    
   static char player = 'X';  // used to indicate 'X' or 'O'

    // This method adds components to the pane.  It is called by 
    // createAndShowGUI, below, which creates the JFrame and the pane.
    //
   public static void addComponentsToPane(Container pane) 
   {
      if (RIGHT_TO_LEFT) 
      {
         pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      }
   
        // define the layout manager for the pane
      pane.setLayout(new GridBagLayout());
   
   	// Each component in a GridBagLayout has a set of constraints that determine things like
   	// position on the screen, margins, padding space, etc.  The variable 'c' is being
   	// used to manage those constraints.  The constraints are set as public data members of the 
   	// GridBagConstraint class.  The constraints are specified when the component is added to the 
   	// pane, as the second parameter to add().
   	
      GridBagConstraints c = new GridBagConstraints();
      if (shouldFill) 
      {
      	//natural height, maximum width
         c.fill = GridBagConstraints.HORIZONTAL;
      }
   
   	// Define the first row.  This will have the text "Player:", followed by buttons labeled "X" and "O"
   	
      msg = new JLabel("Player: ");
      msg.setFont(new Font("Sans-Serif", Font.ITALIC, 30));
      c.fill = GridBagConstraints.EAST;
      c.ipadx = 10;
      c.ipady = 100;
      c.gridx = 0;
      c.gridy = 0;
      pane.add(msg, c);
   	
      buttonX = new JButton("X");
      buttonX.setBackground(Color.black);
      buttonX.setForeground(Color.white);
      buttonX.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      c.fill = GridBagConstraints.NONE;
      c.gridx = 1;
      c.gridy = 0;
      c.ipadx = 25;
      c.ipady = 25;
      pane.add(buttonX, c);
   	
      buttonO = new JButton("O");
      buttonO.setBackground(Color.LIGHT_GRAY);
      buttonO.setForeground(Color.gray);
      buttonO.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      c.fill = GridBagConstraints.NONE;
      c.gridx = 2;
      c.gridy = 0;
      c.ipadx = 25;
      c.ipady = 25;
      pane.add(buttonO, c);
   	
   	// define the second row.  This is the first row of the board.  
   	// Each cell is set up with
   	// - a blank for the text field
   	// - a listener
   	// The following constraints are used for positioning
   	// - c.gridy: the row in the grid, starting at 0
   	// - c.gridx: the column in the grid, starting at 0
   	
      boardButtons[0] = new JButton(" ");
      boardButtons[0].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[0].setActionCommand("0");
      boardButtons[0].addActionListener(new GameListener());
      if (shouldWeightX) {
         c.ipadx = 100;  // set height and width
         c.ipady = 100;
         c.weightx = 0.5;
      }
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 1;
      pane.add(boardButtons[0], c);
   
      boardButtons[1] = new JButton(" ");
      boardButtons[1].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[1].setActionCommand("1");
      boardButtons[1].addActionListener(new GameListener());
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 1;
      c.gridy = 1;
      pane.add(boardButtons[1], c);
   
      boardButtons[2] = new JButton(" ");
      boardButtons[2].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[2].setActionCommand("2");
      boardButtons[2].addActionListener(new GameListener());
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 1;
      pane.add(boardButtons[2], c);
   
   	// define the third row.  This is the second row of the board
   	
      boardButtons[3] = new JButton(" ");
      boardButtons[3].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[3].setActionCommand("3");
      boardButtons[3].addActionListener(new GameListener());
      if (shouldWeightX) {
         c.ipadx = 100;  // set height and width
         c.ipady = 100;
         c.weightx = 0.5;
      }
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 2;
      pane.add(boardButtons[3], c);
   
      boardButtons[4] = new JButton(" ");
      boardButtons[4].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[4].setActionCommand("4");
      boardButtons[4].addActionListener(new GameListener());
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 1;
      c.gridy = 2;
      pane.add(boardButtons[4], c);
   
      boardButtons[5] = new JButton(" ");
      boardButtons[5].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[5].setActionCommand("5");
      boardButtons[5].addActionListener(new GameListener());
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 2;
      pane.add(boardButtons[5], c);
   	
   	// define the fourth row.  This is the last row of the board
   	
      boardButtons[6] = new JButton(" ");
      boardButtons[6].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[6].setActionCommand("6");
      boardButtons[6].addActionListener(new GameListener());
      if (shouldWeightX) {
         c.ipadx = 100;  // set height and width
         c.ipady = 100;
         c.weightx = 0.5;
      }
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 3;
      pane.add(boardButtons[6], c);
   
      boardButtons[7] = new JButton(" ");
      boardButtons[7].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[7].setActionCommand("7");
      boardButtons[7].addActionListener(new GameListener());
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 1;
      c.gridy = 3;
      pane.add(boardButtons[7], c);
   
      boardButtons[8] = new JButton(" ");
      boardButtons[8].setFont(new Font("Sans-Serif", Font.BOLD, 50));
      boardButtons[8].setActionCommand("8");
      boardButtons[8].addActionListener(new GameListener());
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.5;
      c.gridx = 2;
      c.gridy = 3;
      pane.add(boardButtons[8], c);
   
       // That's it.  The pane is in the JFrame, and is put
   	// on the screen by the call to setVisible(), below
   }
    

   // This class is used for the listener
    
   private static class GameListener implements ActionListener
   {
   /**
    The actionPerformed method executes when the user
    clicks on a cell in the board
    @param e The event object.
   */
   
      public void actionPerformed(ActionEvent e)
      {
      // Here is what the listener needs to do:
      //
      // 1)  Determine which cell/button was clicked.  Use the value
      //     returned by getActionCommand and the event to find this. IMPLEMENTED
      //
      // 2)  If the cell is currently unmarked,                   NOT IMPLEMENTED
      
      //    a) Put an 'X' or a 'O' in the cell.                       IMPLEMENTED
      
      //    b) Toggle so that the next cell clicked will have the other
      //       symbol.  Change the highlighting at the top of the screen
      //       so that the current active symbol is highlighted.      IMPLEMENTED
      
      //    c) Determine if the game is finished. Finished means: NOT IMPLEMENTED
      
      //       i)  One player has three of the same symbol in a row,
      //           vertical, horizontal, or on one of the two diagonals.
      //           This is a winning outcome.  The cells that make up
      //           the three-in-a-row should be highlighted by
      //           changing the colors of the letters on those cells 
      //           to red.
      
      //       ii) No player has three in a row, and there are no remaining
      //           cells to place a move in.  This is a 'cats game' and 
      //           indicates that the game ended in a draw.  Hmm- if this
      //           happens, can you draw a giant 'C' on the screen?
      
      
      // 3)  Else if the cell already has a mark in it,           NOT IMPLEMENTED
      //     do nothing.
      
      // Step 1)
      //
         String who = e.getActionCommand();
      
      // i will be the index into the boardButtons array
         int i = Character.getNumericValue(who.charAt(0));
      
         
      //Step 2
      //
         if (boardButtons[i].getText().equals(" "))
         {
      // Step 2a)
      //
         boardButtons[i].setText(String.valueOf(player));
      
         
         //check to see if player has won before allowing next player to go
         //This is step 2C. Placed before 2B as it will not run properly otherwise.
         if ((boardButtons[0].getText().equals(String.valueOf(player))) && (boardButtons[1].getText().equals(String.valueOf(player))) && (boardButtons[2].getText().equals(String.valueOf(player))))
         {
        	 boardButtons[0].setForeground(Color.red);
        	 boardButtons[1].setForeground(Color.red);
        	 boardButtons[2].setForeground(Color.red);
        	 
        	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
        	 System.exit(0);
         }
         else
       	  	if ((boardButtons[3].getText().equals(String.valueOf(player))) && (boardButtons[4].getText().equals(String.valueOf(player))) && (boardButtons[5].getText().equals(String.valueOf(player))))
             {
            	 boardButtons[3].setForeground(Color.red);
            	 boardButtons[4].setForeground(Color.red);
            	 boardButtons[5].setForeground(Color.red);
            	 
            	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
            	 System.exit(0);
             } 
       	  	else
        	  	if ((boardButtons[6].getText().equals(String.valueOf(player))) && (boardButtons[7].getText().equals(String.valueOf(player))) && (boardButtons[8].getText().equals(String.valueOf(player))))
              {
             	 boardButtons[6].setForeground(Color.red);
             	 boardButtons[7].setForeground(Color.red);
             	 boardButtons[8].setForeground(Color.red);
             	 
             	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
             	 System.exit(0);
              } 
        	  	 else
        	       	  	if ((boardButtons[0].getText().equals(String.valueOf(player))) && (boardButtons[3].getText().equals(String.valueOf(player))) && (boardButtons[6].getText().equals(String.valueOf(player))))
        	             {
        	            	 boardButtons[0].setForeground(Color.red);
        	            	 boardButtons[3].setForeground(Color.red);
        	            	 boardButtons[6].setForeground(Color.red);
        	            	 
        	            	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
        	            	 System.exit(0);
        	             } 
        	       	  	else
        	        	  	if ((boardButtons[1].getText().equals(String.valueOf(player))) && (boardButtons[4].getText().equals(String.valueOf(player))) && (boardButtons[7].getText().equals(String.valueOf(player))))
        	              {
        	             	 boardButtons[1].setForeground(Color.red);
        	             	 boardButtons[4].setForeground(Color.red);
        	             	 boardButtons[7].setForeground(Color.red);
        	             	 
        	             	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
        	             	 System.exit(0);
        	              } 
        	        	  	 else
        	        	       	  	if ((boardButtons[2].getText().equals(String.valueOf(player))) && (boardButtons[5].getText().equals(String.valueOf(player))) && (boardButtons[8].getText().equals(String.valueOf(player))))
        	        	             {
        	        	            	 boardButtons[2].setForeground(Color.red);
        	        	            	 boardButtons[5].setForeground(Color.red);
        	        	            	 boardButtons[8].setForeground(Color.red);
        	        	            	 
        	        	            	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
        	        	            	 System.exit(0);
        	        	             } 
        	        	       	  	else
        	        	        	  	if ((boardButtons[0].getText().equals(String.valueOf(player))) && (boardButtons[4].getText().equals(String.valueOf(player))) && (boardButtons[8].getText().equals(String.valueOf(player))))
        	        	              {
        	        	             	 boardButtons[0].setForeground(Color.red);
        	        	             	 boardButtons[4].setForeground(Color.red);
        	        	             	 boardButtons[8].setForeground(Color.red);
        	        	             	 
        	        	             	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
        	        	             	 System.exit(0);
        	        	              } 
        	        	        	  	 else
        	        	        	       	  	if ((boardButtons[2].getText().equals(String.valueOf(player))) && (boardButtons[4].getText().equals(String.valueOf(player))) && (boardButtons[6].getText().equals(String.valueOf(player))))
        	        	        	             {
        	        	        	            	 boardButtons[2].setForeground(Color.red);
        	        	        	            	 boardButtons[4].setForeground(Color.red);
        	        	        	            	 boardButtons[6].setForeground(Color.red);
        	        	        	            	 
        	        	        	            	 JOptionPane.showMessageDialog(null, "Player " + String.valueOf(player) + " won!");
        	        	        	            	 System.exit(0);
        	        	        	             } 
         

         
      // Step 2b)
      //
         switch (player)
         {
            case 'X': player = 'O';   // set to the other symbol
            
               buttonO.setBackground(Color.black);
               buttonO.setForeground(Color.white);
            
               buttonX.setBackground(Color.LIGHT_GRAY);
               buttonX.setForeground(Color.gray);
               break;
             
            case 'O': player = 'X';  // set to the other symbol
            
               buttonX.setBackground(Color.black);
               buttonX.setForeground(Color.white);
            
               buttonO.setBackground(Color.LIGHT_GRAY);
               buttonO.setForeground(Color.gray);
               break;
         }
         
         //Step 2c)
         //Step 2C was moved to before 2B, in order to run properly

       
         //Step 2C ii -- Check for cat's-game
   
         if (!(boardButtons[0].getText().equals(" ")) && !(boardButtons[1].getText().equals(" ")) && !(boardButtons[2].getText().equals(" ")) && !(boardButtons[3].getText().equals(" ")) && !(boardButtons[4].getText().equals(" "))
        		 && !(boardButtons[5].getText().equals(" ")) && !(boardButtons[6].getText().equals(" ")) && !(boardButtons[7].getText().equals(" ")) && !(boardButtons[8].getText().equals(" ")))
         {
        	
        	 
        	for (int cat = 0; cat < boardButtons.length; cat++)
        	{
        		boardButtons[cat].setFont(new Font("Sans-Serif", Font.BOLD, 25));
        		boardButtons[cat].setText("Meow");
        	}
        	msg.setText("Cat Boss");
        	buttonX.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        	buttonX.setText("Hiss");
        	buttonO.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        	buttonO.setText("Hiss");
        	 JOptionPane.showMessageDialog(null, "No winner this time!");
        	 System.exit(0);
         }
         }//end big if yo
         
       //Step 3
       //
         else 
         {
        	 JOptionPane.showMessageDialog(null, "Nah ah. >:[");
         }
        	 
              
         
      }  // end actionPerformed
   
   }  // end class GameListener

 
// Create the window, populate it, and put it on the screen.
// This method is called from main(), below.

   private static void createAndShowGUI() {
        //Create and set up the window.
      JFrame frame = new JFrame("Let's Play Tic-Tac-Toe");
        
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        //Set up the content pane.
      addComponentsToPane(frame.getContentPane());
   
        //Display the window.
      frame.pack();
      frame.setVisible(true);
   }  // end createAndShowGUI

   public static void main(String[] args) 
   {
      createAndShowGUI();
   }  // end main
    
}  // end class
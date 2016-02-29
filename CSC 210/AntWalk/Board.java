//AntWalk Board Object/GUI
//See below for original author notes
//This document has been slightly modified by Nicholas Drazenovic

/**
 * Author: J. Walters
 * GUI for AntWalk Board * 
 * Draws a board whose cells can be red or blue.  */
import java.awt.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class Board extends JFrame 
{
    JLabel[][] cell;
    int numPixels = 12;
    int boardSize;
    
    // constructor 
    public Board(int size) 
    {   
        boardSize = size;
        // Set window size and layout
        setSize(size * numPixels, size * numPixels);
        setLayout(new GridLayout(size, size));

        createBoard(size);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
		//Center the frame
		setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // ************ createBoard **************
    public void createBoard(int N)
    {
        // Create an array of JLabels 
        cell = new JLabel[N][N];
            
        for(int row = 0; row < N; row++)
        {
            for(int col = 0; col < N; col++)
            {
                // Create next JLabel with desired properties
                cell[row][col] = new JLabel();
                cell[row][col].setBackground(Color.RED);
                cell[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell[row][col].setSize(numPixels, numPixels);
                cell[row][col].setPreferredSize(new Dimension(numPixels, numPixels));
                cell[row][col].setOpaque(true);
                
                // Add the JLabel to the frame
                this.add(cell[row][col]);
             }
        }
    }// end createBoard
   
    // ************** resetBoard *****************
    public void resetBoard()
    {
        for(int row = 0; row < boardSize; row++)
        {   for(int col = 0; col < boardSize; col++)
               cell[row][col].setBackground(Color.RED);
        }
    }// end resetBoard

    // ************** chgColor *****************
    public void chgColor(int row, int col)
    {
         Color theColor = cell[row][col].getBackground();
         
         if(theColor.equals(Color.RED))
             cell[row][col].setBackground(Color.BLUE);
         else
             cell[row][col].setBackground(Color.RED);
       
    }// end chgColor
    
    //*************** getColor *******************
    public int getColor(int row, int col)
    {
        int red = 0, blue = 1;
         
        Color theColor = cell[row][col].getBackground();
        
        if(theColor.equals(Color.RED))
            return red;
        else
            return blue;

    }// end getColor
    
  // **************** sleep ***************
   public void sleep(int ms)
   {
      try
      {
          Thread.sleep(ms);
      }
      catch(InterruptedException ex)
      {
          // DO NOTHING. 
          // This is just here because Thread sleep requires exception handle.
      } 
   } // end sleep
 
    
}// end Board class declaration


//Maze GUI Version
//Written by Nicholas Drazenovic
import java.awt.*;

import javax.swing.*;

public class MazeGUI extends JFrame {

	JLabel[][] cell;
	int numPixels = 100;
	int boardSize;
	
	
	public MazeGUI(int size, char[][] M)
	{
		 boardSize = size;
        // Set window size and layout
       setSize(size * numPixels, size * numPixels);
       setLayout(new GridLayout(size, size));

        createBoard(size, M);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
		//Center the frame
		setLocationRelativeTo(null);
        setVisible(true);
	}

	
	public void createBoard(int n, char[][] M)
	{
	     // Create an array of JLabels 
        cell = new JLabel[n][n];
            
        for(int row = 0; row < M.length; row++)
        {
            for(int col = 0; col < M.length; col++)
            {
            	
            	if (M[row][col] == 'X')
            	{
	                // Create next JLabel with desired properties
	                cell[row][col] = new JLabel();
	                cell[row][col].setBackground(Color.BLACK);
	                //cell[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                cell[row][col].setSize(numPixels, numPixels);
	                cell[row][col].setPreferredSize(new Dimension(numPixels, numPixels));
	                cell[row][col].setOpaque(true);
	                
	                // Add the JLabel to the frame
	                this.add(cell[row][col]);
            	}
            	else if (M[row][col] == '0')
            	{
            		// Create next JLabel with desired properties
	                cell[row][col] = new JLabel();
	                cell[row][col].setBackground(Color.WHITE);
	                //cell[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                cell[row][col].setSize(numPixels, numPixels);
	                cell[row][col].setPreferredSize(new Dimension(numPixels, numPixels));
	                cell[row][col].setOpaque(true);
	                
	                // Add the JLabel to the frame
	                this.add(cell[row][col]);
            	}
            	else if (M[row][col] == '@')
            	{
            		// Create next JLabel with desired properties
	                cell[row][col] = new JLabel();
	                cell[row][col].setBackground(Color.YELLOW);
	                //cell[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                cell[row][col].setSize(numPixels, numPixels);
	                cell[row][col].setPreferredSize(new Dimension(numPixels, numPixels));
	                cell[row][col].setOpaque(true);
	                
	                // Add the JLabel to the frame
	                this.add(cell[row][col]);
            	}
            	else
            	{
            		// Create next JLabel with desired properties
	                cell[row][col] = new JLabel();
	                cell[row][col].setBackground(Color.GREEN);
	                //cell[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                cell[row][col].setSize(numPixels, numPixels);
	                cell[row][col].setPreferredSize(new Dimension(numPixels, numPixels));
	                cell[row][col].setOpaque(true);
	                
	                // Add the JLabel to the frame
	                this.add(cell[row][col]);
            	}//end else
             }//end inner for
        }//end outer for		
	}//end constructor


	public void changeTile(int row, int col, int type) 
	{
	
		sleep(350);
		
		if(type == 0)
			cell[row][col].setBackground(Color.RED);
		else
			cell[row][col].setBackground(Color.BLUE);
		
	}
	
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
}// end class

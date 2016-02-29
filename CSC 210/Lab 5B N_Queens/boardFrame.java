//Original file coded by Prof. Muganda and Walters
//Modified by Nicholas Drazenovic

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@SuppressWarnings("serial")
public class boardFrame extends JFrame
{
	
	//Array of labels to hold
	//the spaces
	JLabel[][] cell;
	
	public boardFrame(int size)
	{
	
		setSize(100 * size, 100 * size);
		setLayout(new GridLayout(size, size));
		
		//Create the board
		createBoard(size);
		
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Center the panel on the screen
		setLocationRelativeTo(null);
		setVisible(true);
		
	}//end constructor
	
	
	//Create the board
	public void createBoard(int size)
	{
		cell = new JLabel[size][size];
		
	
		
		for (int row = 0; row < size; row++)
		{
			for (int col = 0; col < size; col++)
			{
		
				cell[row][col] = new JLabel();
				
				if ((row + col) % 2 == 0)
					cell[row][col].setBackground(Color.BLACK);
				else
					cell[row][col].setBackground(Color.WHITE);
				
				
				cell[row][col].setPreferredSize(new Dimension(100, 100));
				cell[row][col].setOpaque(true);
				cell[row][col].setHorizontalAlignment(SwingConstants.CENTER);;
				
				this.add(cell[row][col]);
				
			}//end inner for
		}//end outer for
		
	}//end createBoard
	
	
	//Update the icon
	public void changeIcon(boolean queen, int row, int col)
	{
		
		//Delay so the change is visible to user
		sleep(150);
		
		if (queen)
		{
			//Scale the queen image so that it fits a variable
			//board size.
			try
			{
				BufferedImage originalIcon = ImageIO.read(new File("queen.png"));
				Image scaledIcon = originalIcon.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
				ImageIcon queenIcon = new ImageIcon(scaledIcon);
				
				//Change the icon of the tile
				cell[row][col].setIcon(queenIcon);
				
					
				
			}
			catch (IOException e)
			{
				//Do nothing
			}
		
		}
		else
			cell[row][col].setIcon(null);
	
			
	}//end changeIcon
	
	
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
	
}//end boardFrame class

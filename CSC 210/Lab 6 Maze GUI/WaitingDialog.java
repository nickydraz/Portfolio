//Class for Waiting Dialog box
//Used when loading in the maps, 
//to prevent the user thinking 
//the program is frozen.
//Coded by Nicholas Drazenovic

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class WaitingDialog extends JFrame {
	
	JPanel box;
	JLabel dialog;
	
	//Constructor
	public WaitingDialog()
	{
		
		
       // Set window size and layout
       setSize(250, 100);
       setLayout(new GridLayout(1, 1));

       //Create the label
       dialog = new JLabel("\t\t\t\t\tPlease wait, the maze is loading...");
       dialog.setSize(150, 150); //Set the label size
       dialog.setPreferredSize(new Dimension(250, 100));
		
       //Add the label to the panel
       this.add(dialog);
		
		
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       pack();
        
       //Center the frame and display it
       setLocationRelativeTo(null);
       setVisible(true);
	}

}

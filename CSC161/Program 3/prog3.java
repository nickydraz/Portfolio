//Coded by Nicholas Drazenovic
//Program to read user data from file, 
//Prompt user to log in
//If log in data matches data from file, 
//Print out greeting, else indicate so

import java.io.*;
import javax.swing.*;

public class prog3 {

	public static void main(String[] args) throws IOException {
		
		//Create the window
		JFrame frame = new JFrame("Coding 4 Dayz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		programGUI.addContents(frame.getContentPane());
		frame.pack();
		
		//Center the frame
		frame.setLocationRelativeTo(null);
		
		
		//read user data from file
		methodCentral.readFile();
		
		//So long as the user is still entering in data, 
		//The window will remain open
		while (!(methodCentral.getDone()))
		{
			frame.setVisible(true);
		}
		
		//Close the window after data input
		frame.dispose();
		
		//validate input
		JOptionPane.showMessageDialog(null, methodCentral.findID(methodCentral.getID(), methodCentral.getList(), methodCentral.getPW()));
		
	}//end main
	
}//end prog3

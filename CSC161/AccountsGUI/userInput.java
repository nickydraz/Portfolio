//coded by Nicholas Drazenovic

import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class userInput extends JPanel implements ActionListener {

	
	//create Document to be displayed in textField
	private static Document display = null;
	//private static Document password = null;
	
	//begin constructor
	public userInput() {
		super (new BorderLayout());
		//create objects
		JPanel mainPanel;
		JPanel keypad;
		JButton pad;
		JLabel message;
		
		//generate keypad
		keypad = new JPanel(new GridLayout(4,3));
		
		//for loop to generate buttons
		for (int i = 1; i <= 12; i++)
		{
			if (i == 10)
				pad = new JButton("*");
			else if (i == 11)
				pad = new JButton("0");
			else if(i == 12)
				pad = new JButton("#");
			else
				pad = new JButton(Integer.toString(i));
			
			//add actionListener
			pad.addActionListener(this);
			//add to the keypad panel
			keypad.add(pad);
		}//end for loop
		
		//create text field to hold user input from buttons
		JTextField input = new JTextField();
		display = input.getDocument();
		
		/**
		//create password field
		JPasswordField pass = new JPasswordField();
		password = pass.getDocument();
		**/
		
		add(BorderLayout.CENTER, keypad);
		add(BorderLayout.NORTH, input);
	}//end constructor

	public static void createGUI()
	{
		//create frame
		JFrame frame = new JFrame("Accounts");
		
		//shut down program when window is exited
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add the mainPanel to the frame
		frame.getContentPane().add(new userInput());
		
		//display the window
		frame.pack();
		frame.setVisible(true);
		
	}//end createGUI
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String key = event.getActionCommand();
		
		try 
		{
			display.insertString(display.getLength(), key, null);
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}

	}//end actionPerformed
	

}

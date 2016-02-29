//Coded by Nicholas Drazenovic

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class programGUI {

	//variable to help listener tell whether it is accepting the userID or userPW
	static int count = 0;
	
	//Hold/show user's input
	static Document display = null;
	static Document password = null;
	
	//String to hold instructions to be given to user
	static String instruction = "<html><center>Please enter your userID followed by the '#'. Press '*' to restart.</center></html>";

	//Create output box
	static JLabel outField = new JLabel(instruction);

	// These variables are used in setting the constraints of each 
	// component as it is added to the frame
		
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    static JPanel textPan =  new JPanel();
    static JPanel text1 = new JPanel();
	static JPanel pane = new JPanel();
	static JButton[] keypad = new JButton[12];
	
	//Main window with keypad
	public static void addContents(Container BPan)
	{
		//Set layout of main panel
		BPan.setLayout(new BorderLayout());	
		
		if (RIGHT_TO_LEFT) 
	    {
	       pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	    }
		
				
		//define the layout for the internal/keypad pane
		pane.setLayout(new GridBagLayout());
		
		
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill)
		{
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		
		/* Begin building the actual keypad
		 * 
		 * Will look like:
		 *      1 2 3
		 *      4 5 6
		 *      7 8 9
		 *      * 0 #
		 *      
		 * Each number will have either '---' underneath the number value, 
		 * Or the corresponding text values
		 * i.e: 2
		 * 	   ABC
		 * 	or: 1
		 *     ---
		 *     
		 * Am using HTML formatting to avoid having more variables
		 * 	to hold formatted Strings
		 * 	As well as to ensure proper formatting is done
		 * 
		 * 	HTML is also flippin' neat and, in this case, easy to understand. So yeah.
		 * 
		 */
		
		//First row of keys
		//Will hold '1', '2', '3' buttons
		//Each button will have the same font, 
		//Then add Listener
		keypad[0] = new JButton("<html><center>1<br><br></center></html>");
		keypad[0].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[0].setActionCommand("0");
		keypad[0].addActionListener(new KeyListener());
		if(shouldWeightX)
		{
			c.ipadx = 50; //set the height and width
			c.ipady = 50;
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; //set the position within the grid
		c.gridy = 0;
		pane.add(keypad[0], c); //add to panel
		
		
		keypad[1] = new JButton("<html><center>2<br>ABC</center></html>");
		keypad[1].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[1].setActionCommand("1");
		keypad[1].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(keypad[1], c);
		
		
		keypad[2] = new JButton("<html><center>3<br>DEF</center></html>");
		keypad[2].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[2].setActionCommand("2");
		keypad[2].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(keypad[2], c);
		
		
		//Begin adding second row
		//To hold '4', '5', '6' keys
		keypad[3] = new JButton("<html><center>4<br>GHI</center></html>");
		keypad[3].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[3].setActionCommand("3");
		keypad[3].addActionListener(new KeyListener());
		keypad[3].setForeground(Color.red);
		keypad[3].setBackground(Color.black);
		if(shouldWeightX)
		{
			c.ipadx = 50; //set the height and width
			c.ipady = 50;
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; //set the position within the grid
		c.gridy = 1;
		pane.add(keypad[3], c); //add to panel
		
		
		keypad[4] = new JButton("<html><center>5<br>JKL</center></html>");
		keypad[4].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[4].setActionCommand("4");
		keypad[4].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(keypad[4], c);
		
		keypad[5] = new JButton("<html><center>6<br>MNO</center></html>");
		keypad[5].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[5].setActionCommand("5");
		keypad[5].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(keypad[5], c);
		
		
		//Third row
		//'7', '8', '9' keys
		keypad[6] = new JButton("<html><center>7<br>PQRS</center></html>");
		keypad[6].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[6].setActionCommand("6");
		keypad[6].addActionListener(new KeyListener());
		if(shouldWeightX)
		{
			c.ipadx = 50; //set the height and width
			c.ipady = 50;
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; //set the position within the grid
		c.gridy = 2;
		pane.add(keypad[6], c); //add to panel
		
		
		keypad[7] = new JButton("<html><center>8<br>TUV</center></html>");
		keypad[7].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[7].setActionCommand("7");
		keypad[7].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(keypad[7], c);
		
		keypad[8] = new JButton("<html><center>9<br>WXYZ</center></html>");
		keypad[8].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[8].setActionCommand("8");
		keypad[8].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		pane.add(keypad[8], c);
		
		//fourth row
		//'*', '0', '#' keys
		keypad[9] = new JButton("<html><center>*<br><br></center></html>");
		keypad[9].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[9].setActionCommand("9");
		keypad[9].addActionListener(new KeyListener());
		if(shouldWeightX)
		{
			c.ipadx = 50; //set the height and width
			c.ipady = 50;
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; //set the position within the grid
		c.gridy = 3;
		pane.add(keypad[9], c); //add to panel
		
		
		keypad[10] = new JButton("<html><center>0<br><br></center></html>");
		keypad[10].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[10].setActionCommand("10");
		keypad[10].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		pane.add(keypad[10], c);
		
		keypad[11] = new JButton("<html><center>#<br><br></center></html>");
		keypad[11].setFont(new Font("Serif", Font.PLAIN, 25));
		keypad[11].setActionCommand("11");
		keypad[11].addActionListener(new KeyListener());
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		pane.add(keypad[11], c);
		
		
		//for loop to change the color of all the buttons/their text
		//saved me some keystrokes by using the loop
		for (int ww = 0; ww < keypad.length; ww++)
		{
			keypad[ww].setForeground(Color.blue);
			keypad[ww].setBackground(Color.darkGray);
		}
		//Format font of instructions box
		outField.setFont(new Font("Serif", Font.PLAIN, 25));
		//Add the instructions box to a panel
			//Am doing so because adding it directly to the main panel 
			//Does not allow the text to center properly
		text1.add(outField);
		
		//Create textbox to show userID input
		JLabel lbl1 = new JLabel("UserID: ");
		lbl1.setFont(new Font("Serif", Font.PLAIN, 25));
		JTextField inputField = new JTextField(12);
		inputField.setEditable(false);
		inputField.setFont(new Font("Serif", Font.PLAIN, 25));
		display = inputField.getDocument();
		
		//Create textbox to show password input
		JLabel lbl2 = new JLabel("Password: ");
		lbl2.setFont(new Font("Serif", Font.PLAIN, 25));
		JTextField pwField = new JTextField(12);
		pwField.setEditable(false);
		pwField.setFont(new Font("Serif", Font.PLAIN, 25));
		password = pwField.getDocument();
		
		//Add input boxes to panel
		textPan.add(lbl1);
		textPan.add(inputField);
		textPan.add(lbl2);
		textPan.add(pwField);
		
		//Add objects to the main panel
		BPan.add(BorderLayout.NORTH, text1);
		BPan.add(BorderLayout.CENTER, pane);
		BPan.add(BorderLayout.SOUTH, textPan);
		
	}//end addContents
		
	//Begin defining the listener
	private static class KeyListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//String to get which button was pressed
			String who = e.getActionCommand();
			
			//variable to hold index for buttons
			//charAt does not work since buttons eventually surpass single character ActionCommands
			int i = Integer.parseInt(who);
			
			String input = "";
			
			//Add the button pressed to the input string
			methodCentral.setInput(keypad[i].getText().charAt(14));
			
			
			/*Following if statements determine what action the listener
			 *Will take based on which button is pressed
			 *
			 *If the '#' key is pressed, user is finished input that data field
			 *Program will then set that to the appropriate data field
			 *Will then clear the current stored input so that it is ready for next input
			 *
			 *Raise the counter variable by 1
			 *This will help to determine whether the userID or password is being entered
			 *
			 *Finally, change the instructions message at top of window to appropriate message
			 *
			 *If the counter is 1 and the '#' is pressed,
			 *Set the done variable to true
			 *This will tell the main method to close the keypad window and begin searching for the user in the system
			 *
			 *
			 *If the '*' key is pressed, reset the window. 
			 *This will clear all input, set counter variable back to zero if changed
			 *And clear the displaying input fields
			 *
			 *
			 *If any other key is pressed, it simply adds it to the input
			 *And shows it in the appropriate displaying field
			 *
			 */
			
			if ( i == 11)
			{
				if (count == 0)
				{
					methodCentral.setID(methodCentral.getInput());
					methodCentral.clearInput();
					input = "";
					count++;
					outField.setText("Please enter your password followed by the '#'. Press '*' to restart.");
				}//end else if count == 0
				else if (count == 1)
				{
					methodCentral.setPW(methodCentral.getInput());
					methodCentral.setDone(true);
				}//end else if count == 1
			}//end if i == 11
			else if ( i == 9)
			{
				methodCentral.clearInput();
				input = "";
				count = 0;
				outField.setText("<html><center>Please enter your userID followed by '#'. Press '*' to restart.</center></html>");
				try {
					display.remove(0, display.getLength());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
				try {
					password.remove(0, password.getLength());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}//end else if i== 9
			else
			{
			input += keypad[i].getText().charAt(14);
			}//end else
			
			/* 
			 * These nested if statements are for showing the user's input in the appropriate field
			 * 
			 * If entering the userID (the counter variable is 0)
			 * Then display the userID as it is being typed in
			 * 
			 * If entering the password (the counter variable is 1)
			 * Then replace each number the user enters with the '*' symbol
			 * Does this because it is not good to show the user's password in plain text as it is being entered
			 * Cause spies and stuff
			 * Checks the value of i so that it does not enter the previous '#' keypress from userID field
			 * into the password display field
			 * 
			 */
			if (count == 0)
			{
				try 
				{
					display.insertString(display.getLength(), input, null);
				} catch (BadLocationException b)
				{
					b.printStackTrace();
				}
			}//end if
			else if (count == 1 && i != 11)
			{
				try 
				{
					password.insertString(password.getLength(), "*", null);
				} catch (BadLocationException b)
				{
					b.printStackTrace();
				}
			}//end else if
			
			
		}//end actionPerformed
		
	}//end KeyListener
	

	
}//end class

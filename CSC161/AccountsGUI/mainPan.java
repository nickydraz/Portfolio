//coded by Nicholas Drazenovic

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class mainPan extends JFrame {

	public static void createGUI()
	{
		//create frame
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new userInput());
		
		frame.pack();
		frame.setVisible(true);
	}
}

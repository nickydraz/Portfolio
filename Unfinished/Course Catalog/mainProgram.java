//Coded by Nicholas Drazenovic

import java.awt.Dimension;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class mainProgram {

	public static void main(String[] args) {
		
		//Instantiate master list
		@SuppressWarnings("unused")
		MasterFile MList = new MasterFile();
		
		//Create the scanner
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Welcome");
		System.out.println("Please enter your desired Department: ");
		String dept = JOptionPane.showInputDialog(null, "Please enter you're desired department: ").toUpperCase();
		

		ArrayList<ArrayList<course>> list = new ArrayList<ArrayList<course>>(MasterFile.getMaster());

		String messages = "================================================================\n\t\t" + dept
				+ "\n================================================================\n\n";
		//search for the department
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).get(0).getDept().equals(dept))
			{
			//search and print out the departments list
			for (int v = 0; v < list.get(i).size(); v++)
			{
				if (list.get(i).get(v).getDept().equals(dept))
				{
					System.out.printf("\t    %3s\t   %3s\t    %s\n\t%s\n", list.get(i).get(v).getDept(), list.get(i).get(v).getCourseNum(), 
							list.get(i).get(v).getCredit(), list.get(i).get(v).getName());
					System.out.println("==================================================");
					
					//add to String to be displayed in panel
					messages += "\t    " + list.get(i).get(v).getDept() + "\t    " + list.get(i).get(v).getCourseNum() + 
							"\t    " + list.get(i).get(v).getCredit() + "\n\t" + list.get(i).get(v).getName() + "\n\n";
					messages += "----------------------------------------------------------------"
							+ "--------------------------------------------------------\n";
					
				}//end if
				
			}//end inner for
			
			}//end if
		}//end outer for
		
		//Display the results
		
		JPanel pan = new JPanel();
		
		JTextArea ScrollText = new JTextArea(messages);
		ScrollText.setEditable(false);
		JScrollPane scrolly = new JScrollPane(ScrollText);
		scrolly.setPreferredSize(new Dimension(500, 600));
		pan.add(scrolly);
		
		JFrame frame = new JFrame();
		frame.setContentPane(pan);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		//cleanup
		kb.close();
		

	}//end main

}//end class

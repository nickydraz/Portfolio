/************************************************
   Client Code for the Animal Game Tree Program
      Written by Prof. Walters
      Modified by Nicholas Drazenovic
      and Emily Huizenga
/***********************************************/	
import java.io.IOException;
import java.util.Scanner;

public class AnimalGamePlayer
{
	public static void main(String [] args) throws IOException
	{
		int newGame;
		char playAgain;
		char saveGame; //like playAgain, this determines if the user
					   //wants to save the game
		String inputLine, 
		       fname;
		Scanner keyboard = new Scanner(System.in);
		animalGame myGame = new animalGame();

		do
		{	System.out.println("Think of an animal and I will try to "
				+ "guess it.\n\n");
			System.out.println("1 - Start New Game\n");
			System.out.println("2 - Resume Old Game\n");
		   newGame = keyboard.nextInt();
		} while (newGame < 1 || newGame > 2);
		
		if (newGame == 1)                   // Start a new game
			myGame.startNewGame();
		else                                // Resume old game
		{
			System.out.print("What is the name of the file holding the game? "); 
			fname = keyboard.next();
			myGame.resumeOldGame(fname);
		}

	   do
	   {  myGame.playGame();
		
		   System.out.print("\nPlay again (Y/N)? ");
		   inputLine = keyboard.next();
		   playAgain = inputLine.charAt(0);
		}while (playAgain == 'y' || playAgain == 'Y');
		
		System.out.println("Do you want to save this game (Y/N)?");
		inputLine = keyboard.next();
		saveGame = inputLine.toUpperCase().charAt(0);
		while (!validateInput(saveGame))
		{
			System.out.println("Entry is invalid, please try again.");
			inputLine = keyboard.next();
			saveGame = inputLine.toUpperCase().charAt(0);
			
		}//end while
		
		//Save the game, if the user wanted to
		if (saveGame == 'Y')
		{
			System.out.println("Please enter a file name for your saved game: ");
			inputLine = keyboard.next();
			myGame.startGameSave(inputLine);
		}	
		
	   
	}// end main
	
	/******************************************
	 * 				validateInput
	 * This method validates the character sent
	 * to it.
	 ******************************************/
	public static boolean validateInput(char input)
	{
		
		return (input == 'Y' || input == 'N');
	}//end valudateInput
}// end AnimalGamePlayer    

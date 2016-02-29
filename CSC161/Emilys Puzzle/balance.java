import java.util.ArrayList;
import java.util.Random;


public class balance {

	public static void main(String[] args) {
//counter variable for tries
		int tries = 0;
		int limit = 3;
		
		int weightR = new Random().nextInt(100) + 1; //variable to hold the random weight
		
		int guess1; 
		boolean done;
		
		done = false;
		while ((tries < limit) && (! done))
		{
			tries++;
			System.out.println("Enter first set to compare, separated by spaces: ");
			ArrayList<int[]> guess1 = new ArrayList<int[]>();
			
		}//end while
	}

}

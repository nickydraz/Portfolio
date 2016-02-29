//coded by Nicholas Drazenovic

public class checker {

	
	//method for checking if palindrome
	public static int palCheck(String toCheck, int index, int palLength, int valid)
	{		
		//while not done checking letters and while a nonmatch has not bee retuned
		while ((index < (toCheck.length()/2)) && (valid != -1))
		{
			//characters to hold letters at opposite ends to be checked
			char A = toCheck.charAt(index);
			char B = toCheck.charAt(palLength);
			if (A == B)
			{
				//if matches, call method again, checking the next opposing letters
				valid = palCheck(toCheck, index + 1, palLength - 1, valid);
				//so long as one of the checks has not failed, set valid to 0
				if (valid != -1)
					return 0;
			}
			else
			{
				//if character check fails, set valid to -1
				valid = -1;
				return valid;
			}
		}//end while
		return valid;
	}//end palCheck
}

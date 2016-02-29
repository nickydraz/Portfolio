//Coded by Nicholas Drazenovic


public class keyData implements Comparable<keyData>{

	private String word;
	private int data;
	
public keyData(String w)
{
	word = w;
	data = 1;
}

public String getWord()
{
	return word;
}

public int getData()
{
	return data;
}


//Method to increment the number of occurences
public void increment()
{
	data++;
}

public int compareTo(keyData r) {

	return (data - r.data);
}

}//end class

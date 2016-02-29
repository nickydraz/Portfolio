//Class file to hold customer data for program 1
//Coded by Nicholas Drazenovic

import java.util.List;



public class custAccount 
{

	//Strings to hold original values
	private String firstName, lastName, title, userid, password;
	//Strings to hold converted userID and password of each object
	private String convID, convPW;
	
	//constructors
	public custAccount(String first, String last, String title, String userid, String password) 
	{
		this.firstName = first;
		this.lastName = last;
		this.title = title;
		this.userid = userid;
		this.password = password;
	}//end constructor

	//begin set methods
	
	public void setFirst(String first)
	{
		this.firstName = first;
	}//end setFirst
	
	public void setLast(String last)
	{
		this.lastName = last;
	}//end setLast
	
	public void setTitle(String title)
	{
		this.title = title;
	}//end setTitle
	
	public void setUserID(String user)
	{
		this.userid = user;
	}//end setUserID
	
	public void setPass(String pass)
	{
		this.password = pass;
	}//end setPass
	
	public void setConvID(String convID)
	{
		this.convID = convID;
	}//end setConvID
	
	public void setConvPW(String convPW)
	{
		this.convPW = convPW;
	}
	
	//end set methods
	
	//begin get methods
	public String getFirst()
	{
		return this.firstName;
	}//end getFirst
	
	public String getLast()
	{
		return this.lastName;
	}//end getLast
	
	public String getTitle()
	{
		return this.title;
	}//end getTitle
	
	public String getID()
	{
		return this.userid;
	}//end getID
	
	public String getPass()
	{
		return this.password;
	}//end getPass
	
	//Method used when searching the list, allowing data to be passed in to search specific indexes
	public static String getTitle(List<custAccount> custList, int i)
	{
		return custList.get(i).title;
	}//end getTitle

	//Method used when searching the list, allowing data to be passed in to search specific indexes
	public static String getLast(List<custAccount> custList, int i)
	{
		return custList.get(i).lastName;
	}//end getlast
	
	//Method to retrieve converted ID
	public String getConvID()
	{
		return this.convID;
	}//end getConvID
	
	//Method to retrieve converted password
	public String getConvPW()
	{
		return this.convPW;
	}//end getConvPW
	
	//end get methods
	

	



}//End class

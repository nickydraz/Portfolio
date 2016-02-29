//coded by Nicholas Drazenovic

public class course {

	String department;
	String courseNum;
	String name;
	double credit;
	String creds;
	int index;
	
	boolean completed = false;
	boolean options = false;
	boolean startOfSection = false;
	boolean elective = false;
	int level;
	String type = "";
	
	//constructor
	public course(String d, String num, double c)
	{
		department = d;
		courseNum = num;
		credit = c;	
	}//end constructor
	
	
	public course(String d, String num, double c, boolean opt)
	{
		department = d;
		courseNum = num;
		credit = c;
		options = opt;
	}
	
	public course(String d, String num, double c, boolean opt, boolean start)
	{
		department = d;
		courseNum = num;
		credit = c;
		options = opt;
		startOfSection = start;
	}
	public course(String d, String num, double c, boolean opt, boolean start, boolean e)
	{
		department = d;
		courseNum = num;
		credit = c;
		options = opt;
		startOfSection = start;
		elective = e;
	}
	
	/**
	public course(String d, String num, double c, double lvl, String t)
	{
		department = d;
		courseNum = num;
		credit = c;
		level = lvl;
		type = t;
	}
	**/
	
	public course(String d, String num, String n, double c)
	{
		department = d;
		courseNum = num;
		name = n;
		credit = c;
	}
	
	public course(String d, String num, String n, String c)
	{
		department = d;
		courseNum = num;
		name = n;
		creds = c;
	}
	public course(String d, String num, String n, double c, int i, int lvl)
	{
		department = d;
		courseNum = num;
		name = n;
		credit = c;
		index = i;
		level = lvl;
	}
	
	public course(String d, String num, String n, double c, int i, int lvl, String t)
	{
		department = d;
		courseNum = num;
		name = n;
		credit = c;
		index = i;
		level = lvl;
		type = t;
	}
	
	public void setCompleted(boolean com)
	{
		completed = com;
	}
	public void setOption(boolean opt)
	{
		options = opt;
	}
	
	public void setDept(String dept)
	{
		department = dept;
	}
	
	public void setCourse(String num)
	{
		courseNum = num;
	}
	
	public void setCredit(double cred)
	{
		credit = cred;
	}
	
	public void setStart(boolean start)
	{
		startOfSection = start;
	}
	
	public void setType(String t)
	{
		type = t;
	}
	
	public void setLevel(int i)
	{
		level = i;
	}
	
	public void setIndex(int i)
	{
		index = i;
	}
	
	public String getDept()
	{
		return department;
	}
	
	public String getCourseNum()
	{
		return courseNum;
	}
	
	public double getCred()
	{
		return credit;
	}
	
	public boolean getOption()
	{
		return options;
	}
	
	public boolean getCompleted()
	{
		return completed;
	}
	
	public boolean getStart()
	{
		return startOfSection;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getLevel()
	{
		return level;
	}
	public int getIndex()
	{
		return index;
	}
	public String getName()
	{
		return name;
	}
	
	public String getCredit()
	{
		return creds;
	}
}

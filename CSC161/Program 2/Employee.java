//coded by Nicholas Drazenovic

import java.util.ArrayList;

public abstract class Employee {

	String title, name, address, id;
	double hourlyRate, hoursWorked, annualPay, bonus;
	ArrayList<String> IDList;
	
	//begin constructor
	public Employee(String t, String n, String a, String ID)
	{
		title = t;
		name = n;
		address = a;
		id = ID;
	}
	
	//hourly constructor
	public Employee(String t, String n, String a, String ID, double hr, double hw)
	{
		title = t;
		name = n;
		address = a;
		id = ID;
		hourlyRate = hr;
		hoursWorked = hw;
	}
	
	//salaried constructor
	public Employee(String t, String n, String a, String ID, double ann)
	{
		title = t;
		name = n;
		address = a;
		id = ID;
		annualPay = ann;
	}
	
	//Supervisor constructor
	public Employee(String t, String n, String a, String ID, double ann, double bon, ArrayList<String> reports)
	{
		title = t;
		name = n;
		address = a;
		id = ID;
		annualPay = ann;
		bonus = bon;
		//copy the contents of list to local
		IDList = new ArrayList<String>(reports);
		//Collections.copy(IDList, reports);
		
	}
	
	
	
	public void setTitle(String t)
	{
		title = t;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setAddress(String a)
	{
		address = a;
	}
	
	public void setID(String ID)
	{
		id = ID;
	}
	
	public void setHourlyRate(double hr)
	{
		hourlyRate = hr;
	}
	
	public void setHoursWorked(double hw)
	{
		hoursWorked = hw;
	}
	
	public void setAnnualPay(double ann)
	{
		annualPay = ann;
	}
	
	public void setBonus(double bon)
	{
		bonus = bon;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getID()
	{
		return id;
	}
	
	public double getHourlyRate()
	{
		return hourlyRate;
	}
	
	public double getHoursWorked()
	{
		return hoursWorked;
	}
	
	public double getAnnualPay()
	{
		return annualPay;
	}
	
	public double getBonus()
	{
		return bonus;
	}
	 
	public ArrayList<String> getIDList()
	{
		return IDList;
	}
	
	public abstract double getGrossWeeklyPay();
	
}

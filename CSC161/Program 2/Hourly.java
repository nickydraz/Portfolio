//coded by Nicholas Drazenovic

public class Hourly extends Employee {

	public Hourly(String t, String n, String a, String ID, double hr, double hw) {
		super(t, n, a, ID, hr, hw);
	}
	
	public double getGrossWeeklyPay()
	{
		return hourlyRate * hoursWorked;
	}

}

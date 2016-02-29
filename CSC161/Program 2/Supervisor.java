//coded by Nicholas Drazenovic

import java.util.ArrayList;

public class Supervisor extends Employee {

	public Supervisor(String t, String n, String a, String ID, double ann,
			double bon, ArrayList<String> reports) {
		super(t, n, a, ID, ann, bon, reports);
	}

	public double getGrossWeeklyPay()
	{
		
		return (annualPay / 52.0) + (bonus / 52.0);
	}
}

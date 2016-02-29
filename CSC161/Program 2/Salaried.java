//coded by Nicholas Drazenovic

public class Salaried extends Employee{

	public Salaried(String t, String n, String a, String ID, double ann) {
		super(t, n, a, ID, ann);
	}
	
public double getGrossWeeklyPay()
{
	return annualPay / 52.0;
}

}

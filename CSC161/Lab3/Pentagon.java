
public class Pentagon extends RegularPolygon{

	public Pentagon(int s) {
		super(s);
	}//end constructor
	
	public double getArea()
	{
		return (0.25)*Math.sqrt(5*(5+2*Math.sqrt(5)))* (getSide() * getSide());
	}//end getArea


}

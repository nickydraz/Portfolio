//coded by Nicholas Drazenovic


public class EQTriangle extends RegularPolygon {

	public EQTriangle(int s) {
		super(s);
	}//end constructor
	
	public double getArea()
	{
		return (Math.sqrt(3)/4) * Math.pow(getSide(), 2);
	}

}

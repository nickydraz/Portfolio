//coded by Nicholas Drazenovic

public abstract class RegularPolygon {

	private int side;
	
	//begin constructor
	public RegularPolygon(int s)
	{
		
		side = s;
		
	}//end constructor
	
	//method to get area of the regular polygon
	//abstract, to be overriden by other objects
	public abstract double getArea();
	
	public int getSide()
	{
		return side;
	}
}//end class

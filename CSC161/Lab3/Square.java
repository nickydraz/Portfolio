//coded by Nicholas Drazenovic

public class Square extends RegularPolygon{

	public Square(int s) {
		super(s);
	}//end constructor
	
	public double getArea()
	{
		return Math.pow(getSide(), 2);
	}

}

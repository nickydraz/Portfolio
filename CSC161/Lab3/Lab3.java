//coded by Nicholas Drazenovic
public class Lab3 {

	public static void main(String[] args) {

		//create triangle
		RegularPolygon tri = new EQTriangle(4);
		//create square
		RegularPolygon square = new Square(4);
		//create pentagon
		RegularPolygon pent = new Pentagon(4);
		
		System.out.println("The area of the equilateral triangle is: " + tri.getArea() + ".");
		
		System.out.println("The area of the square is: " + square.getArea() + ".");
		
		System.out.println("The area of the pentagon is: " + pent.getArea() + ".");
		
	}//end main

}//end class

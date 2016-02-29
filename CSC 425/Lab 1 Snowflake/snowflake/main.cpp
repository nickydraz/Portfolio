// Simple Window - This program creates a white window to draw in.
// Snowflake code added by Nicholas Drazenovic

#include <GL/glut.h>
#include <math.h>
#include <stdio.h>
#include <iostream>

//Counter for number of generations or layers for the snowflake.
//Default is 0, should the user enter a negative number, or leave the prompt blank
int generations = 0;

//Some math values to make life easier
float cos60 = 0.5;
float sin60 = sqrt(3.0) * 0.5;

// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void myinit(void)
{ // Set the state of the rendering engine
	glClearColor(0.0, 0.0, 0.0, 0.0); // black background
	glColor3f(1.0, 0.0, 0.0); //draw in red

	//view a 500 X 500 window with origin lower left
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, 500.0, 0.0, 500.0);
	glMatrixMode(GL_MODELVIEW);
}

void drawLine(float x1, float y1, float x2, float y2)
{
	glBegin(GL_LINES);
	glVertex2f(x1, y1);
	glVertex2f(x2, y2);
	glEnd();
}
void drawSnowflake(float x1, float y1, float x2, float y2, int genIndex)
{
	//IF done, end out of the recursion
	if (genIndex >= generations)
	{
		drawLine(x1, y1, x2, y2);
	}
	else
	{
		//Do the math to get the necessary points
		float dX = (x2 - x1) / 3;
		float dY = (y2 - y1) / 3;

		float pX1 = x1 + dX;
		float pY1 = y1 + dY;
		float pX2 = x2 - dX;
		float pY2 = y2 - dY;

		//Make it outward facing with mathematical magic!
		//Pythagoras' formulas help make it all work
		float tipX = pX1 + (dX * cos60 + dY * sin60);
		float tipY = pY1 + (dY * cos60 - dX * sin60);

		//Draw each part of the line
		drawSnowflake(x1, y1, pX1, pY1, genIndex + 1);
		drawSnowflake(pX1, pY1, tipX, tipY, genIndex + 1);
		drawSnowflake(tipX, tipY, pX2, pY2, genIndex + 1);
		drawSnowflake(pX2, pY2, x2, y2, genIndex + 1);
	}
}//end snowflake
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void display(void)
{
	while (generations != 999)
	{
		glClear(GL_COLOR_BUFFER_BIT);

		drawSnowflake(250.0, 410.0, 100.0, 150.0, 0);
		drawSnowflake(100.0, 150.0, 400.0, 150.0, 0);
		drawSnowflake(400.0, 150.0, 250.0, 410.0, 0);

		glFlush();

		std::cout << "\n\nPlease enter the desired number of generations (try to keep it under 10...),";
		std::cout << "Alternatively, enter 999 to exit the program:";
		std::cin >> generations;

		if (generations > 20 && generations != 999)
		{
			generations = 0;
			std::cout << "Values over 20 are not accepted, as this would freeze the program. \n The value has been set to 0.";
		}
	}
	std::exit(0);
}

// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * M A I N * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void main(int argc, char** argv)
{ //Standard GLUT initialization
	//Prompt the user for the number of generations they want
	std::cout << "Please enter the desired number of generations (try to keep it under 10...):";
	std::cout << "Alternatively, enter 999 to exit the program:";
	std::cin >> generations;
	if (generations > 20)
	{
		generations = 0;
		std::cout << "Values over 20 are not accepted, as this would freeze the program. \n The value has been set to 0.";
	}
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500); //500x500 pixel window
	glutInitWindowPosition(0, 0); //place window top left
	glutCreateWindow("Super Awesome Snowflake"); //window title
	glutDisplayFunc(display); //callback invoked when window opened
	myinit(); // set attributes

	glutMainLoop(); // enter event loop
}
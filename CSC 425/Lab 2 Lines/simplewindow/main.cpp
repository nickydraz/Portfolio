// Simple Window - This program creates a white window to draw in.
// Insert your own code to run an openGL program.

#include <GL/glut.h>
#include <math.h>
#include <stdio.h>
#include <iostream>

#define MAX 10
GLsizei screenHeight = 500;
GLsizei screenWidth = 500;

//Counter points
int pointCount = 0;
float xCoords[10];
float yCoords[10];
int polycount = 0;

// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void myinit(void)
{ // Set the state of the rendering engine
	glClearColor(1.0, 1.0, 1.0, 1.0); // white background
	glColor3f(1.0, 0.0, 0.0); //draw in red

	//view a 500 X 500 window with origin lower left
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, 500.0, 0.0, 500.0);
	glMatrixMode(GL_MODELVIEW);
}

void drawSquare(float x1, float y1, float x2, float y2)
{

	polycount++;
	glPushAttrib(GL_ALL_ATTRIB_BITS);

	if (yCoords[0] < 250)
	{


		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		glColor3f(1.0, 1.0, 0.0);

		glBegin(GL_POLYGON);

		glVertex2f(x1, y1);
		glVertex2f(x1, y2);
		glVertex2f(x2, y2);
		glVertex2f(x2, y1);

		glEnd();

		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glColor3f(1.0, 0.0, 0.0);
		glBegin(GL_POLYGON);

		glVertex2f(x1, y1);
		glVertex2f(x1, y2);
		glVertex2f(x2, y2);
		glVertex2f(x2, y1);

		glEnd();


		//glPopAttrib();
	}
	else
	{

		//glPushAttrib(GL_ALL_ATTRIB_BITS);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		glColor3f(1.0, 0.0, 0.0);

		glBegin(GL_POLYGON);

		glVertex2f(x1, y1);
		glVertex2f(x1, y2);
		glVertex2f(x2, y2);
		glVertex2f(x2, y1);

		glEnd();

		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glColor3f(0.0, 0.0, 0.0);
		glBegin(GL_POLYGON);

		glVertex2f(x1, y1);
		glVertex2f(x1, y2);
		glVertex2f(x2, y2);
		glVertex2f(x2, y1);

		glEnd();

		//glPopAttrib();
	}

	pointCount = 0;
	glPopAttrib();
}
void drawPoly()
{
	polycount++;
	glPushAttrib(GL_ALL_ATTRIB_BITS);

	if (yCoords[0] < 250)
	{

		
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		glColor3f(1.0, 1.0, 0.0);

		glBegin(GL_POLYGON);

		for (int i = 0; i < pointCount; i++)
		{
			glVertex2f(xCoords[i], yCoords[i]);
		}

		glEnd();

		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glColor3f(1.0, 0.0, 0.0);
		glBegin(GL_POLYGON);

		for (int i = 0; i < pointCount; i++)
		{
			glVertex2f(xCoords[i], yCoords[i]);
		}

		glEnd();


		//glPopAttrib();
	}
	else
	{
		
		//glPushAttrib(GL_ALL_ATTRIB_BITS);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

		glColor3f(1.0, 0.0, 0.0);

		glBegin(GL_POLYGON);

		

		for (int i = 0; i < pointCount; i++)
		{
			glVertex2f(xCoords[i], yCoords[i]);
		}

		glEnd();
		
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glColor3f(0.0, 0.0, 0.0);
		glBegin(GL_POLYGON);

		for (int i = 0; i < pointCount; i++)
		{
			glVertex2f(xCoords[i], yCoords[i]);
		}

		glEnd();

		//glPopAttrib();
	}

	pointCount = 0;
	glPopAttrib();
}

void plotPoint(float x, float y)
{
	
	xCoords[pointCount] = x;
	yCoords[pointCount] = y;
	pointCount++;

	if (pointCount == 9)
	{
		drawPoly();
	}
	
}

// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void display(void)
{
	
	// code to create your display goes here



	glFlush(); 
}


void mouse(GLint button, GLint state, GLint xPos, GLint yPos)
{ // figure out which event occurred



	switch(button)
	{
	case GLUT_LEFT_BUTTON: // put point in poly
		if ((pointCount < MAX - 1) && (state == GLUT_DOWN))
		{
			plotPoint(xPos, screenHeight - yPos);	
		}
		 break;

	case GLUT_RIGHT_BUTTON: // draw poly
		if (state == GLUT_DOWN)
		{
			if (polycount == 0)
			{
				glClear(GL_COLOR_BUFFER_BIT);
			}

			if (pointCount > 2 )
			{
				drawPoly();
			}
			else if (pointCount == 2)
			{
				drawSquare(xCoords[0], yCoords[0], xCoords[1], yCoords[1]);
			}
			
		}
		break;

	}// end switch
} //end mouse


// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * M A I N * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void main(int argc, char** argv)
{ //Standard GLUT initialization

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500); //500x500 pixel window
	glutInitWindowPosition(0, 0); //place window top left
	glutCreateWindow("Polygon Maker 2016"); //window title
	glutDisplayFunc(display); //callback invoked when window opened
	glutMouseFunc(mouse);
	myinit(); // set attributes
	glClear(GL_COLOR_BUFFER_BIT);
	glutMainLoop(); // enter event loop
}
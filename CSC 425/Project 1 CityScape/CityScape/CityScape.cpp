// CityScape.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

// Simple Window - This program creates a white window to draw in.
// Insert your own code to run an openGL program.

#include <GL/glut.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <iostream>

typedef float color[3];

enum ColorName { GREY, DARKGREY };
const color Color[] = {
	{0.6f, 0.6f, 0.6f}, //GREY
	{0.3f, 0.3f, 0.3f} //DARKGREY
};



// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void myinit(void)
{ // Set the state of the rendering engine
	glClearColor(0.2, 0.2, 0.2, 0.2); // black background
	glColor3f(1.0, 0.5, 0.5); //draw in red

							  //view a 500 X 500 window with origin lower left
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, 800.0, 0.0, 800.0);
	glMatrixMode(GL_MODELVIEW);
}

void drawCircle(float radius, float originX, float originY, float c1, float c2, float c3)
{
	glColor3f(c1, c2, c3);
	/* draw a solid disc from a bunch of triangles */

	float vectorY1 = originY;
	float vectorX1 = originX;


	glBegin(GL_TRIANGLES);

	for (int i = 0; i <= 360; i++)

	{

		float angle = (float)(((double)i) / 57.29577957795135);

		float vectorX = originX + (radius*(float)sin((double)angle));

		float vectorY = originY + (radius*(float)cos((double)angle));

		glVertex2d(originX, originY);

		glVertex2d(vectorX1, vectorY1);

		glVertex2d(vectorX, vectorY);

		vectorY1 = vectorY;

		vectorX1 = vectorX;

	}

	glEnd();
}

// Method to draw the background stars
void drawBack()
{

	glPushAttrib(GL_CURRENT_BIT);

	//Initialize srand
	srand(time(NULL));
	//Loop for 200 stars
	for (int i = 0; i < 200; i++) 
	{


		glColor4f(1.0, 1.0, 204.0 / 255.0, 0.0); //star color

		//Position the stars randomly in the sky behind the buildings.
		float randomX = rand() % 800;
		float randomY = rand() % 800 + 325;
		float randX5 = randomX + 5;
		float randY5 = randomY + 5;


		glPolygonMode(GL_FRONT, GL_FILL);

		glBegin(GL_POLYGON);

		glVertex3f(randomX, randomY, 0.0);
		glVertex3f(randX5, randomY, 0.0);
		glVertex3f(randX5, randY5, 0.0);
		glVertex3f(randomX, randY5, 0.0);

		glEnd();
	}

	//Draw the moon
	drawCircle(200.0, 0.0, 800.0, 1.0f, 0.9216f, 0.7019f);
	drawCircle(50.0, 0.0, 800.0, 0.8f, 0.8216f, 0.6019f);
	drawCircle(10.0, 20.0, 700.0, 0.8f, 0.8216f, 0.6019f);
	drawCircle(10.0, 25.0, 670.0, 0.8f, 0.8216f, 0.6019f);
	drawCircle(30.0, 140.0, 750.0, 0.8f, 0.8216f, 0.6019f);
	drawCircle(25.0, 100.0, 720.0, 0.8f, 0.8216f, 0.6019f);

	glPopAttrib();




}//end drawBack

void drawWindows(float startX, float startY, float height, float width)
{
	glPushAttrib(GL_CURRENT_BIT);
	glPushAttrib(GL_POLYGON_MODE);

	glColor3f(1.0f, 0.898f, 0.6f);

	glPolygonMode(GL_FRONT, GL_FILL);

	for (float i = height; i >= 50.0; i -= 20.0)
	{
		for (float x = (width + startX); x >= startX + 30.0; x -= 35.0)
		{
			if ((x - 35) >= startX)
			{
				glBegin(GL_QUADS);
				glVertex2f(x - 5.0, i - 10.0);
				glVertex2f(x - 35.0, i - 10.0);
				glVertex2f(x - 35.0, i - 20.0);
				glVertex2f(x - 5.0, i - 20.0);
				glEnd();
			}
		}
	}//end for

	glPopAttrib();
	glPopAttrib();
	

}//end drawWindows

void drawBuilding(float startX, float startY, float height, float width, const float color[3])
{
	
	//Push current state to attribute stack
	glPushAttrib(GL_CURRENT_BIT);
	glPushAttrib(GL_POLYGON_MODE);
	
	//Change to assigned color
	glColor3f(color[0], color[1], color[2]);
	glPolygonMode(GL_FRONT, GL_FILL);
	glBegin(GL_QUADS);

	//Plot points for building
	glVertex2f(startX, startY);
	glVertex2f(startX + width, startY);
	glVertex2f(startX + width, startY + height);
	glVertex2f(startX, startY + height);

	glEnd();

	glPopAttrib();
	glPopAttrib();

	//Draw the windows
	drawWindows(startX, startY, height, width);

	/* Draw border around each building */
	//Push current state to attribute stack
	glPushAttrib(GL_CURRENT_BIT);
	glPushAttrib(GL_POLYGON_MODE);

	//Change to assigned color
	glColor3f(0.0f, 0.0f, 0.0f);
	glPolygonMode(GL_FRONT, GL_LINE);
	glBegin(GL_QUADS);

	//Plot points for building
	glVertex2f(startX, startY);
	glVertex2f(startX + width, startY);
	glVertex2f(startX + width, startY + height);
	glVertex2f(startX, startY + height);

	glEnd();

	//Pop attribute stack back to previous state
	glPopAttrib();
	glPopAttrib();

}//end drawBuilding


// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void display(void)
{
	//Draw the stars and moon
	drawBack();

	//Draw background buildings
	drawBuilding(0.0, 0.0, 300.0, 150.0, Color[DARKGREY]);
	drawBuilding(75.0, 0.0, 450.0, 150.0, Color[DARKGREY]);
	drawBuilding(275.0, 0.0, 375.0, 200.0, Color[DARKGREY]);
	drawBuilding(125.0, 0.0, 500.0, 125.0, Color[DARKGREY]);
	drawBuilding(350.0, 0.0, 575.0, 130.0, Color[DARKGREY]);
	drawBuilding(575.0, 0.0, 600.0, 160.0, Color[DARKGREY]);

	//Draw foreground buildings
	drawBuilding(0.0, 0.0, 200.0, 100.0, Color[GREY]);
	drawBuilding(250.0, 0.0, 300.0, 200.0, Color[GREY]);
	drawBuilding(150.0, 0.0, 350.0, 150.0, Color[GREY]);
	drawBuilding(130.0, 0.0, 110.0, 80.0, Color[GREY]);
	drawBuilding(225.0, 0.0, 250.0, 100.0, Color[GREY]);
	drawBuilding(400.0, 0.0, 120.0, 175.0, Color[GREY]);
	drawBuilding(475.0, 0.0, 225.0, 100.0, Color[GREY]);
	drawBuilding(550.0, 0.0, 300.0, 120.0, Color[GREY]);
	drawBuilding(600.0, 0.0, 288.0, 200.0, Color[GREY]);



	//Flush the buffer
	glFlush();
}





// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * M A I N * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void main(int argc, char** argv)
{ //Standard GLUT initialization

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(800, 800); //500x500 pixel window
	glutInitWindowPosition(0, 0); //place window top left
	glutCreateWindow("CityScape"); //window title
	glutDisplayFunc(display); //callback invoked when window opened
	myinit(); // set attributes

	glutMainLoop(); // enter event loop
}

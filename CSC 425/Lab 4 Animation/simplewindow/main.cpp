// Lab 4 Animation
// Nicholas Drazenovic && Emily Huizenga

#include <GL/glut.h>
#include <math.h>
#include <stdio.h>
#include <iostream>

using namespace std;

#define PI 3.141592
#define sleepMax 20
#define sleepMin 0
int sleepNumber = 10;

struct coord { GLfloat x, y; };
coord p[4];
GLint x = 250, y = 250;
GLint xOld = 0, yOld = 0;
bool drag = false;

GLint screenwidth = 500, screenheight = 500;
GLint sqSize = 50;

GLint speedX = 5, speedY = 3;


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

void drawSquare()
{
	float radius = sqSize / (sqrt(2.0));

	glPushAttrib(GL_ALL_ATTRIB_BITS);
	glBegin(GL_QUADS);

	int counter = 0;
	for (float angle = PI / 4; angle < 2 * PI; angle += PI / 2)
	{
		p[counter].x = x + (radius * cos(angle));
		p[counter].y = y + (radius * sin(angle));
		glVertex2f(p[counter].x,p[counter].y);
		counter++;
	}//end for

	glEnd();
	glPopAttrib();

}

bool isWithin(GLint xPos, GLint yPos)
{
	if ((p[1].x <= xPos) && (xPos <= p[0].x) && (p[3].y <=  yPos) && ( yPos <= p[0].y))
	{
		return true;
	}

	return false;
}
void mouse(GLint button, GLint state, GLint xPos, GLint yPos)
{
	
	switch (button)
	{
		
		case GLUT_LEFT_BUTTON:
		{
			int mousey = screenheight - yPos;
		
			if ((state == GLUT_DOWN) && (isWithin(xPos, mousey)))
			{
				
				drag = true;
			}
			else if (state == GLUT_UP)
			{
				drag = false;
			}

		}
	}//end switch
}//end mouse

void move(GLint xPos, GLint yPos)
{

	if (drag && (xPos < screenwidth - (sqSize / 2)) && (yPos < screenheight - (sqSize / 2)) && (xPos >0 + (sqSize / 2)) && (yPos>0 + (sqSize / 2)))
	{
		xOld = x;
		yOld = y;
		x = xPos;
		y = screenheight- yPos;
		glutPostRedisplay();
	}

}


void wander(){
	
	Sleep(sleepNumber);
	if (drag) return;
	
	x += speedX;
	y += speedY;

	if (y > screenheight)
	{
		speedY = -speedY;
	}
	if (x > screenwidth)
	{
		speedX = -speedX;
	}
	if (y < (sqSize/2))
	{
		speedY = -speedY;
	}
	if (x < (sqSize / 2))
	{
		speedX = -speedX;
	}

	glutPostRedisplay();
}


void speed(int key, int x, int y)
{
	switch (key)
	{
		case GLUT_KEY_UP:
		{
			if ((sleepNumber - 2) > sleepMin)
			{
				sleepNumber -= 2;
			}
			break;
		}
		case GLUT_KEY_DOWN:
		{
			if ((sleepNumber + 2) < sleepMax)
			{
				sleepNumber += 2;
			}
			break;
		}
	}
}
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT);
	drawSquare();
	glFlush();	
	glutSwapBuffers();
}



// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * M A I N * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void main(int argc, char** argv)
{ //Standard GLUT initialization

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE);
	glutInitWindowSize(500, 500); //500x500 pixel window
	glutInitWindowPosition(0, 0); //place window top left
	glutCreateWindow("Animation"); //window title
	glutDisplayFunc(display); //callback invoked when window opened
	glutMouseFunc(mouse);
	glutMotionFunc(move);
	glutSpecialFunc(speed);
	glutIdleFunc(wander);
	myinit(); // set attributes

	glutMainLoop(); // enter event loop

	
}
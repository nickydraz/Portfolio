// Menu Lab
//Written by Emily Huizenga and Nicholas Drazenovic

#include <GL/glut.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

using namespace std;

#define PI 3.141592

int squareSize = 9;
float rad = 0;
//color variables
GLfloat r = 0.0, g = 0.0, b = 1.0;
//fill mode variable
bool fill = true;

void size_menu(int);
void color_menu(int);
void figure_menu(int);
void right_menu(int);

// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void myinit(void)
{ // Set the state of the rendering engine
	glClearColor(0.0, 0.0, 0.0, 1.0); // black background
	glColor3f(r, g, b); //draw in blue

	//view a 600 X 400 window with origin lower left
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, 600.0, 0.0, 400.0);
	glMatrixMode(GL_MODELVIEW);
}

void drawSquare(GLint x, GLint y) {
	rad = squareSize / (sqrt(2));
	glColor3f(r, g, b); //update draw color
	if(fill)glBegin(GL_QUADS);
	else glBegin(GL_LINE_LOOP);
	for (float t = PI / 4; t < 2 * PI; t += PI / 2) {
		glVertex2f(x + (rad*cos(t)), y + (rad*sin(t)));
	}
	glEnd();
}

void mouseClick(GLint button, GLint state, GLint xpos, GLint ypos) {
	switch (button) {
		case GLUT_LEFT_BUTTON: {
			drawSquare(xpos, 400- ypos);
			break;
		}
		case GLUT_RIGHT_BUTTON: {
			break;
		}
	}
}

// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void display(void)
{
	
	// code to create your display goes here

	

	glFlush();
}

void size_menu(int id) {
	if (id == 1) {
		//random case
		srand(time(NULL)); //initialize srand
		float random = rand() % 100 + 1;
		squareSize = random;
	}
	else squareSize = id;
}
void color_menu(int id) {
	switch (id) {
		case 1: {//blue
			r = 0.0;
			g = 0.0;
			b = 1.0;
			break;
		}
		case 2: { //red
			r = 1.0;
			g = 0.0;
			b = 0.0;
			break;
		}
		case 3: { //white
			r = 1.0;
			g = 1.0;
			b = 1.0;
			break;
		}
		case 4: {
			//random
			srand(time(NULL)); //initialize srand
			r = ((float)(rand() % 100))/100;
			g = ((float)(rand() % 100))/100;
			b = ((float)(rand() % 100))/100;
		}
	}
}
void figure_menu(int id) {
	fill = (id == 1) ? 1 : 0; 
}
void right_menu(int id) {
	if (id == 1)exit(0);
	else glClear(GL_COLOR_BUFFER_BIT);
}
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * M A I N * * * * * * * * * * * * * * * * * * * * * *
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
void main(int argc, char** argv)
{ //Standard GLUT initialization

	int s_menu, f_menu, c_menu;

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(600, 400); //600x400 pixel window
	glutInitWindowPosition(0, 0); //place window top left
	glutCreateWindow("Menu Lab"); //window title
	glutDisplayFunc(display); //callback invoked when window opened
	glutMouseFunc(mouseClick);
	myinit(); // set attributes
	glClear(GL_COLOR_BUFFER_BIT);


	//create a menu for the size
	s_menu = glutCreateMenu(size_menu);
	glutAddMenuEntry("5x5", 5);
	glutAddMenuEntry("9x9", 9);
	glutAddMenuEntry("15x15", 15);
	glutAddMenuEntry("21x21", 21);
	glutAddMenuEntry("RaNdOm", 1);
	//menu for color
	c_menu = glutCreateMenu(color_menu);
	glutAddMenuEntry("Blue", 1);
	glutAddMenuEntry("Red", 2);
	glutAddMenuEntry("White", 3);
	glutAddMenuEntry("rAnDoM", 4);
	//menu for figure
	f_menu = glutCreateMenu(figure_menu);
	glutAddMenuEntry("Solid", 1);
	glutAddMenuEntry("Outline", 2);
	//create main menu
	glutCreateMenu(right_menu);
	glutAddMenuEntry("Exit", 1);
	glutAddMenuEntry("Clear", 2);
	glutAddSubMenu("Size", s_menu);
	glutAddSubMenu("Color", c_menu);
	glutAddSubMenu("Style", f_menu);
	glutAttachMenu(GLUT_RIGHT_BUTTON);


	glutMainLoop(); // enter event loop
}
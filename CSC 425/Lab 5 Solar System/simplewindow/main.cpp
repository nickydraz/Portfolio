//Lab 5
// Written by Nick Drazenovic and Emily Huizenga

#include <GL/glut.h>
#include <stdio.h>


// color definitions
#define RED        1
#define GREEN      2
#define BLUE       3
#define CYAN       4
#define MAGENTA    5
#define YELLOW     6
#define WHITE      7
#define BLACK      8

const GLfloat Color[9][4] =
{ 0.0f, 0.0f, 0.0f, 1.0f,
1.0f, 0.0f, 0.0f, 1.0f, // red
0.0f, 1.0f, 0.0f, 1.0f, // green
0.0f, 0.0f, 1.0f, 1.0f, // blue
1.0f, 1.0f, 0.0f, 1.0f, // cyan
1.0f, 0.0f, 1.0f, 1.0f, // magenta
1.0f, 1.0f, 0.0f, 1.0f, // yellow
1.0f, 1.0f, 1.0f, 1.0f, // white
0.0f, 0.0f, 0.0f, 1.0f  // black
};

static int year = 0, day = 0, solarDay = 0, moonYear=0;
static bool animate = false;

void init(void)
{
	glClearColor(0.0, 0.0, 1.0, 0.0);
	glShadeModel(GL_FLAT);
}

void display(void)
{  // draw a sun & 1 small planet
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.0f, 1.0f, 1.0f);

	glPushMatrix();

	// draw the sun
	glRotatef((GLfloat)solarDay, 0.0, 1.0f, 0.0f); //the sun's spin
	glColor3fv(Color[YELLOW]);
	glutWireSphere(0.7, 20, 16);
	
	glPopMatrix();

	glPushMatrix();

	// draw the planet
	glRotatef((GLfloat)year, 0.0f, 1.0f, 0.0f); // the earth's orbit
	glTranslatef(2.0f, 0.0f, 0.0f); //move the earth to it's orbit
	glRotatef((GLfloat)day, 0.0, 1.0f, 0.0f); //the earth's spin
	glColor3fv(Color[GREEN]);
	glutWireSphere(0.2, 10, 8);  // earth?

	glPopMatrix();

	glPushMatrix();

	//draw the moon
	glRotatef((GLfloat)year, 0.0f, 1.0f, 0.0f); //orbit around sun
	glTranslatef(2.0f, 0.0f, 0.0f); //earth location
	glRotatef((GLfloat)moonYear, 0.0, 1.0f, 0.0f); //orbit around earth
	glTranslatef(-0.7f, 0.0f, 0.0f); //moon location
	glRotatef((GLfloat)day, 0.0, 1.0f, 0.0f); // moon spin
	glColor3fv(Color[WHITE]);
	glutWireTeapot(.05);

	glFlush();
	glPopMatrix();

	glutSwapBuffers();
}

void reshape(int w, int h)
{
	glViewport(0, 0, (GLsizei)w, (GLsizei)h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(60.0, (GLfloat)w / (GLfloat)h, 1.0, 20.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(0.0, 0.0, 5.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0); //location of camera
}



void keyboard(unsigned char key, int x, int y)
{
	
	switch (key)
	{
	case 'd':
		day = (day + 10) % 360;
		glutPostRedisplay();
		break;
	case 'D':
		day = (day - 10) % 360;
		glutPostRedisplay();
		break;
	case 'y':
		year = (year + 5) % 360;
		glutPostRedisplay();
		break;
	case 'Y':
		year = (year - 5) % 360;
		glutPostRedisplay();
		break;
	case 'q':
		exit(0);
	case 's':
		solarDay = (solarDay + 10) % 360;
		glutPostRedisplay();
		break;
	case 'S':
		solarDay = (solarDay - 10) % 360;
		glutPostRedisplay();
		break;
	case 'a':
		animate = true;
		break;
	case 'A':
		animate = false;
		break;
	default:
		break;
	}
}

void animateSystem(){
	Sleep(50);
	if (animate){
		day = (day + 10) % 360;
		year = (year + 5) % 360;
		solarDay = (solarDay + 10) % 360;
		moonYear = (moonYear + 5) % 360;
		glutPostRedisplay();
	}
}

void main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Solar System");
	init();
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(keyboard);
	glutIdleFunc(animateSystem);
	glutMainLoop();
}


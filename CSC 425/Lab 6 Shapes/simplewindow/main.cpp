//Lab 6
// Written by Nick Drazenovic and Emily Huizenga

#include <GL/glut.h>
#include <stdio.h>
#include <math.h>

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


class CCamera
{
protected:
	float cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ;
	float angle;
	float lx = 0.0f;
	float lz = -1.0f;
public:
	CCamera(float cX, float cY, float cZ, float tX, float tY, float tZ, float uX, float uY, float uZ)
	{
		cameraX = cX;
		cameraY = cY;
		cameraZ = cZ;
		targetX = tX;
		targetY = tY;
		targetZ = tZ;
		upX = uX;
		upY = uY;
		upZ = uZ;
		gluLookAt(cX, cY, cZ, tX, tY, tZ, uX, uY, uZ);
	}
	void moveUp() // move camera up
	{
		cameraY += 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
	void moveDown()
	{
		cameraY -= 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
	void moveLeft()
	{
		cameraX -= 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
	void moveRight()
	{
		cameraX += 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}

	void panLeft() // rotate camera left
	{
		targetX -= 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
	void panRight()
	{
		targetX += 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
	void lookUp()
	{
		upY += 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
	void lookDown()
	{
		upY -= 0.1;
		gluLookAt(cameraX, cameraY, cameraZ, targetX, targetY, targetZ, upX, upY, upZ);
	}
	void cameraPos(float cX, float cY, float cZ, float tX, float tY, float tZ, float uX, float uY, float uZ)
	{
		cameraX = cX;
		cameraY = cY;
		cameraZ = cZ;
		targetX = tX;
		targetY = tY;
		targetZ = tZ;
		upX = uX;
		upY = uY;
		upZ = uZ;
		gluLookAt(cX, cY, cZ, tX, tY, tZ, uX, uY, uZ);
	}
	void cameraOn(); //push matrix, load identity, gluLookAt
	void cameraOff(); // pop matrix
};

CCamera * camera = new CCamera(0.0, 0.0, 2 * 2.45, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

void init(void)
{
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glShadeModel(GL_FLAT);
}

void display(void)
{  
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(153/255.0, 76/255.0, 0.0f);

	glPushMatrix();

	glutWireCube(0.75);

	glPopMatrix();
	
	glPushMatrix();
	glTranslatef(0.0,0.75, 0.0);
	glutWireCube(0.75);
	glPopMatrix();

	glPushMatrix();
	glTranslatef(0.0, 1.25, 0.0);
	glRotatef(-65.0, 1.0, 0.0, 0.0);
	glColor3f(0.0f, 1.0f, 0.0f);
	glutWireCone(1.0, 1.2, 20, 20);
	glPopMatrix();

	glPushMatrix();

	glColor3f(1.0f, 0.0f, 0.0f);
	glTranslatef(0.90, 0.0, 0.0);
	glRotatef(-180, 1.0, 0.0, 0.0);
	glutWireTeapot(0.25);

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
	gluLookAt(0.0, 0.0, 2 * 2.45, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0); //location of camera
}



void keyboard(unsigned char key, int x, int y)
{
	glutPostRedisplay();
	switch (key)
	{
	case 'l':
		camera->panLeft();
		break;
	case 'r':
		camera->panRight();
		break;
	case 'u':
		camera->lookUp();
		break;
	case 'd':
		camera->lookDown();
		break;
	case 'h':
		camera->cameraPos(0.0, 0.0, 2 * 2.45, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
		break;
	default:
		break;
	}
}


void main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutInitWindowPosition(100, 100);
	glutCreateWindow("Shapes! :D");
	init();
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(keyboard);
	glutMainLoop();
}


//File CShape.cpp
// Author S. Renk 2/2006
//Modified by Nicholas Drazenovic

#include <GL/glut.h>
#include <Windows.h>
#include "CShape.h"
#include <string>
#include <math.h>
using std::string;

int lWidth;
int getWidth(){ return lWidth; }
void setWidth(int width) { lWidth = width; }

// *********************** CShape **********************
bool CShape::insertVertex(CCoord * vertPtr) //place in vertex list
{
	if (nbrVert == 0) //empty list
		vertHead = vertTail = vertPtr;
	else
	{
		vertTail->nextCoord = vertPtr;
		vertTail = vertPtr;
	}
	nbrVert++;

	return true;
}

CShape::~CShape() // delete the linked list of vertices
{
	CCoord * ptr;

	while (ptr = vertHead)
	{
		vertHead = vertHead->nextCoord;
		delete ptr;
	}
}

// ************************ CPoint ************************
void CPoint::draw() //draw a set of points
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	glPointSize(size);
	glBegin(GL_POINTS);
	for (CCoord * ptr = vertHead; ptr; ptr = ptr->nextCoord)
	{
		glColor3f(ptr->r, ptr->g, ptr->b);
		glVertex2i(ptr->x, ptr->y);
	}
	glEnd();
	glFlush();
	glPopAttrib();
}

// ****************** CLine *************************
void CLine::draw() //draw a set of lines
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	glLineWidth(lineWidth);
	glColor3f(vertHead->r, vertHead->g, vertHead->b);
	glBegin(GL_LINES);
	for (CCoord * ptr = vertHead; ptr; ptr = ptr->nextCoord)
	{
		glVertex2i(ptr->x, ptr->y);
	}
	glEnd();
	glFlush();
	glPopAttrib();
}

// ****************** CLineStr *************************
void CLineStr::draw() //draw a set of line strips
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	glLineWidth(lineWidth);
	glBegin(GL_LINE_STRIP);
	for (CCoord * ptr = vertHead; ptr; ptr = ptr->nextCoord)
	{
		glColor3f(ptr->r, ptr->g, ptr->b);
		glVertex2i(ptr->x, ptr->y);
	}
	glEnd();
	glFlush();
	glPopAttrib();
}

// ****************** CCircle *************************
void CCircle::draw() //draw a circle
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	int x = vertTail->x - vertHead->x;
	int y = vertTail->y - vertHead->y;
	radius = sqrt((float)x * x + y * y);

	float Y1 = (float)vertHead->y;
	float X1 = (float)vertHead->x + (float)radius;

	if (fill) glBegin(GL_POLYGON);
	else { glLineWidth(lineWidth); glBegin(GL_LINE_LOOP); }
	for (float angle = 0.0f; angle <= (2.0f*3.14159); angle += 0.01f)
	{
		glColor3d(vertHead->r, vertHead->g, vertHead->b);
		glVertex2f(X1, Y1);
		X1 = (float)(vertHead->x + (radius * (float)(cos((double)angle))));
		Y1 = (float)(vertHead->y + (radius * (float)(sin((double)angle))));
	}
	glEnd();
	glFlush();
	glPopAttrib();
}


void CArc::draw()
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	int x = vertTail->x - vertHead->x; 
	int y = vertTail->y - vertHead->y;
	radius = sqrt((float)x * x + y * y); //pythag

	float Y1 = (float)vertHead->y;// +radius;
	float X1 = (float)vertHead->x + (float)radius;
	glColor3d(vertHead->r, vertHead->g, vertHead->b);
	if (fill){glBegin(GL_POLYGON); glVertex2i(vertHead->x, vertHead->y); }
	else { glLineWidth(lineWidth); glBegin(GL_LINE_STRIP); }
	for (float a = 0.0f; a <= angle; a += 0.01f)
	{
		glVertex2f(X1, Y1);
		X1 = (float)(vertHead->x + (radius * (float)(cos((double)a))));
		Y1 = (float)(vertHead->y + (radius * (float)(sin((double)a))));
	}
	glEnd();

	glPopAttrib();
	glFlush();
	
}

// ************** CRect *********************
void CRect::draw() //draw a set of rectables
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	int tx, ty, bx, by;

	if (fill) glBegin(GL_POLYGON);
	else { glLineWidth(lineWidth); glBegin(GL_LINE_LOOP); }
	for (CCoord * ptr = vertHead; ptr; ptr = ptr->nextCoord)
	{
		tx = ptr->x; ty = ptr->y;
		glColor3f(ptr->r, ptr->g, ptr->b);
		if (ptr->nextCoord != NULL)
		{
			ptr = ptr->nextCoord;
			bx = ptr->x; by = ptr->y;

			glVertex2i(tx, ty);
			glVertex2i(tx, by);
			glVertex2i(bx, by);
			glVertex2i(bx, ty);
		}
	}

	glEnd();
	glFlush();
	glPopAttrib();
}

// ******************** CPoly **********************
void CPoly::draw() //draw a polygon
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	if (fill) glBegin(GL_POLYGON);
	else { glLineWidth(lineWidth); glBegin(GL_LINE_LOOP); }
		for (CCoord * ptr = vertHead; ptr; ptr = ptr->nextCoord)
		{			
			glColor3f(ptr->r, ptr->g, ptr->b);
			glVertex2i(ptr->x, ptr->y);
		}

		
	glEnd();
	glFlush();
	glPopAttrib();
}

// ************************** CButton ************************
int CButton::selectedBtn = CPOINT;

void CButton::draw() // draw a set of rectangles
{
	printf(" %d = %d \n", btnType, selectedBtn); // ###############
	if (btnType != selectedBtn)
	{
		glColor3d(0.0, 0.6, 0.6); // active button is BLUE
	}
	else
	{
		printf("match \n");
		glColor3d(1.0, 0.2, 0.0); // RED
	}

	glBegin(GL_POLYGON);
	glVertex2i(x, y);
	glVertex2i(x, y - h);
	glVertex2i(x + w, y - h);
	glVertex2i(x + w, y);
	glEnd();

	glFlush();
}

bool CButton::isPicked(int px, int py, int pz)
{
	if ((px >= x && px <= x + w) && (py <= y && py >= y - BTN_HEIGHT))
	{
		printf("match %d \n", btnType); // ################
		selectedBtn = btnType;
		return true;
	}
	else
	{
		return false;
	}
}

void CText::draw()
{
	string textInput = getText();
	CCoord * ptr = vertHead;

	glColor3f(ptr->r, ptr->g, ptr->b);
	glRasterPos2i(ptr->x, ptr->y);
	for (string::iterator i = textInput.begin(); i != textInput.end(); i++)
	{
		char c = *i;
		glutBitmapCharacter(GLUT_BITMAP_9_BY_15, c);
	}
}

void CPoint::write(std::ofstream &filename)
{
	//Need type and coordinates, as well as point size
	filename << type << ";";
	filename << size << ";";
	filename << vertHead->x << ";";
	filename << vertHead->y << ";";
	filename << vertHead->r << ";";
	filename << vertHead->g << ";";
	filename << vertHead->b << ";";
}

void CLine::write(std::ofstream &filename)
{
	filename << type << ";";
	filename << lineWidth << ";";
	filename << vertHead->x << ";";
	filename << vertHead->y << ";";
	filename << vertTail->x << ";";
	filename << vertTail->y << ";";
	filename << vertHead->r << ";";
	filename << vertHead->g << ";";
	filename << vertHead->b << ";";
}

void CLineStr::write(std::ofstream &filename)
{
	filename << type << ";";
	filename << lineWidth << ";";
	filename << nbrVert << ";";
	for (CCoord * ptr = vertHead; ptr; ptr = ptr->nextCoord)
	{
		filename << ptr->x << ";";
		filename << ptr->y << ";";
		filename << ptr->r << ";";
		filename << ptr->g << ";";
		filename << ptr->b << ";";
	}

	
}

void CCircle::write(std::ofstream &filename)
{
	filename << type << ";";
	filename << lineWidth << ";";
	filename << fill << ";";
	filename << radius << ";";
	
	filename << vertHead->x << ";";
	filename << vertHead->y << ";";
	filename << vertTail->x << ";";
	filename << vertTail->y << ";";
	filename << vertHead->r << ";";
	filename << vertHead->g << ";";
	filename << vertHead->b << ";";

}

void CArc::write(std::ofstream &filename)
{
	filename << type << ";";
	filename << lineWidth << ";";
	filename << fill << ";";
	filename << radius << ";";
	filename << angle << ";";

	filename << vertHead->x << ";";
	filename << vertHead->y << ";";
	filename << vertTail->x << ";";
	filename << vertTail->y << ";";
	filename << vertHead->r << ";";
	filename << vertHead->g << ";";
	filename << vertHead->b << ";";


}

void CRect::write(std::ofstream &filename)
{
	filename << type << ";";
	filename << lineWidth << ";";
	filename << fill << ";";

	filename << vertHead->x << ";";
	filename << vertHead->y << ";";
	filename << vertTail->x << ";";
	filename << vertTail->y << ";";
	filename << vertHead->r << ";";
	filename << vertHead->g << ";";
	filename << vertHead->b << ";";
}

void CPoly::write(std::ofstream &filename)
{
	filename << type << ";";
	filename << lineWidth << ";";
	filename << nbrVert << ";";
	filename << fill << ";";
	for (CCoord * ptr = vertHead; ptr; ptr = ptr->nextCoord)
	{
		filename << ptr->x << ";";
		filename << ptr->y << ";";
		filename << vertHead->r << ";";
		filename << vertHead->g << ";";
		filename << vertHead->b << ";";
	}


}

void CText::write(std::ofstream &filename)
{
	filename << type << ";";
	filename << inputText << ";";
	filename << vertHead->x << ";";
	filename << vertHead->y << ";";
	filename << vertHead->r << ";";
	filename << vertHead->g << ";";
	filename << vertHead->b << ";";
}

// a simple paint program, soon to become A-Dopy-Illustrator V1
// S. Renk 
// program is in serious need of an upgrade
// it needs:
//     persistence to be able to capture drawn objects so they stay on the screen
//     the ability to load and save images created
//     rubberbanding 


//Modified by Nicholas Drazenovic


// include needed libraries
#include <GL/glut.h>
#include <math.h>
#include <stdio.h>
#include "CImage.h"
#include "CShape.h"
#include <iostream>
#include <string>

using std::string;

#define NULL 0

// defins shapes
#define LINE 1
#define RECTANGLE 2
#define TRIANGLE  3
#define POINTS 4
#define TXT 5
#define LINE_STRIP 6
#define POLYGON 7
#define CIRCLE 8
#define ARC 9

// function prototypes
void mouse(int, int, int, int);
void key(unsigned char, int, int);
void display(void);
void drawSquare(int, int);
void myReshape(GLsizei, GLsizei);
void myinit(void);
void screen_box(int, int, int);
void right_menu(int);
void middle_menu(int);
void color_menu(int);
void pixel_menu(int);
void fill_menu(int);
int pick(int, int);

//global vars program state vars
GLsizei wh = 800, ww = 500;  // window size
GLfloat size = 3.0;          // half side length of square
int draw_mode = NULL;        // current drawing mode 
int rx, ry;                  //raster position (rx, ry)
//global vars for rendering engine state
GLdouble r = 0.0, g = 0.0, b = 0.0; // current drawing color 
GLdouble cr = 0.8, cg = 0.8, cb = 0.8; //Clear color
bool fill = true;           // draw solid or outlines
GLfloat lineWidth = 1;
CImage * imageList = new CImage(); //Linked List to hold all the shapes we draw
int buttonSize = 40;
bool drawing = false;
int nbrVerts = 0;
static int xp[9], yp[9]; // x/y location of verticies
int mouseX, mouseY;

int redSliderX = 80, greenSliderX = 80, blueSliderX = 80; //slider positions on X
int redSliderY = 85, greenSliderY = 55, blueSliderY = 25;

static int count;  // # of verticies in current figure
string textInput;

string mode;

string getMessage(){
	switch (draw_mode)
	{
	case (LINE) :
		return "Click twice to choose a start point and an end point for the line.";
	case(RECTANGLE) :
		return "Click one for one corner of the rectangle, then click again for the opposite corner.";
	case (TRIANGLE) :
		return "Click three times to create all three vertices needed.";
	case (POINTS) :
		return "Click to create a point.";
	case(TXT) :
		return textInput;
	case(LINE_STRIP) :
		return "Click to begin the line strip. Click again for each subsequent vertex in the strip for a maximum of 10 vertices.";
	case (POLYGON) :
		return "Click to begin the polygon. Click again for each subsequent vertex in the polygon for a maximum of 10 vertices.";
	case (CIRCLE) :
		return "Click to set center point, then click again to set radius.";
	case(ARC) :
		return "Click once to set center point, then once more to set both the angle and radius.";
	case(NULL) :
		return "Please select a mode.";
	default:
		return "NULL";
	}
}

string getMode()
{
	switch (draw_mode)
	{
	case (LINE) :
		return "LINE";
	case(RECTANGLE) :
		return "RECTANGLE";
	case (TRIANGLE) :
		return "TRIANGLE";
	case (POINTS) :
		return "POINTS";
	case(TXT) :
		return "TEXT";
	case(LINE_STRIP) :
		return "LINE STRIP";
	case (POLYGON) :
		return "POLYGON";
	case (CIRCLE) :
		return "CIRCLE";
	case(ARC) :
		return "ARC";
	case(NULL) :
		return "--";
	default:
		return "NULL";
	}
}
void drawSquare(int x, int y)
{

	y = wh - y;
	//glColor3ub((char)rand() % 256, (char)rand() % 256, (char)rand() % 256);
	glBegin(GL_POLYGON);
	glVertex2f(x + size, y + size);
	glVertex2f(x - size, y + size);
	glVertex2f(x - size, y - size);
	glVertex2f(x + size, y - size);
	glEnd();
}


// rehaping routine called whenever window is resized or moved 

void myReshape(GLsizei w, GLsizei h)
{
	// adjust clipping box
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0.0, (GLdouble)w, 0.0, (GLdouble)h, -1.0, 1.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	// adjust viewport and  clear 
	glViewport(0, 0, w, h);
	//glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
	glClear(GL_COLOR_BUFFER_BIT);
	display();
	glFlush();

	// set global size for use by drawing routine
	ww = w; wh = h;
}

void myinit(void)
{
	glViewport(0, 0, ww, wh);
	// Pick 2D clipping window to match size of X window 
	// This choice avoids having to scale object coordinates
	// each time window is resized 
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0.0, (GLdouble)ww, 0.0, (GLdouble)wh, -1.0, 1.0);
	glClearColor(0.8f, 0.8f, 0.8f, 1.0f);  // set clear color to black and clear window 
	glClear(GL_COLOR_BUFFER_BIT);
	glFlush();
}


void getColors(int x, int y)
{
	if ((75 <= y) && (y <= 95))
	{
		GLfloat rPix = NULL;
		glReadBuffer(GL_FRONT);
		glReadPixels(x, y, 1, 1, GL_RED, GL_FLOAT, &rPix);
		redSliderX = x;
		
		r = (double)rPix;
	}

	if ((45 <= y) && (y <= 65))
	{
		GLfloat gPix = NULL;
		glReadBuffer(GL_FRONT);
		glReadPixels(x, y, 1, 1, GL_GREEN, GL_FLOAT, &gPix);

		greenSliderX = x;

		g = (double)gPix;
	}

	if ((15 <= y) && (y <= 35))
	{
		GLfloat bPix = NULL;
		glReadBuffer(GL_FRONT);
		glReadPixels(x, y, 1, 1, GL_BLUE, GL_FLOAT, &bPix);
		blueSliderX = x;
		b = (double)bPix;
	}

	printf("Colors are %f, %f, %f\n", r, g, b);
}
void mouse(int btn, int state, int x, int y)  //defines the drawing shapes
{
	
	int where;
	int fixedY = wh - y;

	//If in the color slider area
	if (((0 <= x) && (x <= 150)) && (((75 <= fixedY) && (fixedY <= 95)) || ((45 <= fixedY) && (fixedY <= 65)) || ((15 <= fixedY) && (fixedY <= 35))))
	{
		getColors(x, fixedY);
		return;
	}

	
	if (btn == GLUT_LEFT_BUTTON && state == GLUT_DOWN)
	{
		glPushAttrib(GL_ALL_ATTRIB_BITS);
		where = pick(x, y);

		if (where != 0)
		{
			count = 0;
			draw_mode = where;
		}
		else
		{
			//If trying to click within the bottom interface area
			if (fixedY <= 100 || fixedY >= wh - buttonSize || x <= buttonSize)
			{
				return;
			}

			
			switch (draw_mode)
			{
			case(LINE) :
				if (count == 0)
				{
					drawing = true;
					xp[0] = x;
					yp[0] = fixedY;
					count++;
				}
				else
				{
					CLine * line = new CLine();

					CCoord * coord1 = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);
					CCoord * coord2 = new CCoord(xp[0], yp[0], 0, (float)r, (float)g, (float)b);
					coord1->nextCoord = coord2;
					line->insertVertex(coord1);

					line->insertVertex(coord2);

					line->setWidth(lineWidth);
					imageList->insertFigure(line);
					count = 0;
					drawing = false;

					glutPostRedisplay();
				}
					   break;
			case(LINE_STRIP) :

				if (nbrVerts == 0)
				{
					drawing = true;
					xp[0] = x;
					yp[0] = fixedY;
					nbrVerts++;
				}
				else if (nbrVerts < 9)
				{

					xp[nbrVerts] = x;
					yp[nbrVerts] = fixedY;
					nbrVerts++;

				}
				else
				{
					drawing = false;
					CLineStr * line = new CLineStr();

					for (int i = 0; i < nbrVerts; i++)
					{
						CCoord * pt = new CCoord(xp[i], yp[i], 0, (float)r, (float)g, (float)b);
						line->insertVertex(pt);
					}
					CCoord * point = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);

					line->vertTail->nextCoord = point;
					line->insertVertex(point);

					line->setWidth(lineWidth);
					imageList->insertFigure(line);
					nbrVerts = 0;
					glutPostRedisplay();
				}

				break;
			case(RECTANGLE) :
				if (count == 0)
				{
					drawing = true;
					count++;
					xp[0] = x;
					yp[0] = fixedY;
				}
				else
				{

					CRect * rect = new CRect();

					CCoord * coord1 = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);
					CCoord * coord2 = new CCoord(xp[0], yp[0], 0, (float)r, (float)g, (float)b);
					coord1->nextCoord = coord2;
					rect->insertVertex(coord1);

					rect->insertVertex(coord2);
					rect->setFill(fill);
					rect->setWidth(lineWidth);
					imageList->insertFigure(rect);
					count = 0;
					drawing = false;
					glutPostRedisplay();
				}
							break;
			case (POLYGON) :
				if (nbrVerts == 0)
				{
					drawing = true;
					xp[0] = x;
					yp[0] = fixedY;
					nbrVerts++;
				}
				else if (nbrVerts < 9)
				{

					xp[nbrVerts] = x;
					yp[nbrVerts] = fixedY;
					nbrVerts++;

				}
				else
				{
					drawing = false;
					CPoly * poly = new CPoly();

					for (int i = 0; i < nbrVerts; i++)
					{
						CCoord * pt = new CCoord(xp[i], yp[i], 0, (float)r, (float)g, (float)b);
						poly->insertVertex(pt);
					}
					CCoord * point = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);

					poly->vertTail->nextCoord = point;
					poly->insertVertex(point);

					poly->setFill(fill);
					poly->setWidth(lineWidth);
					imageList->insertFigure(poly);
					nbrVerts = 0;
					glutPostRedisplay();
				}
				break;
			case (TRIANGLE) :
				switch (count)
			{
				case(0) :
					drawing = true;
					count++;
					xp[0] = x;
					yp[0] = fixedY;
					break;
				case(1) :
					count++;
					xp[1] = x;
					yp[1] = fixedY;
					break;
				case(2) :

					CPoly * tri = new CPoly();

					CCoord * coord1 = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);
					CCoord * coord2 = new CCoord(xp[0], yp[0], 0, (float)r, (float)g, (float)b);
					CCoord * coord3 = new CCoord(xp[1], yp[1], 0, (float)r, (float)g, (float)b);
					coord1->nextCoord = coord2;
					coord2->nextCoord = coord3;
					tri->insertVertex(coord1);
					tri->insertVertex(coord2);
					tri->insertVertex(coord3);
					tri->setFill(fill);
					tri->setWidth(lineWidth);

					imageList->insertFigure(tri);
					count = 0;
					drawing = false;
					glutPostRedisplay();
			}
							break;
			case(POINTS) :
			{
				CPoint * point = new CPoint();
				point->insertVertex(new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b));
				point->setSize(size);
				imageList->insertFigure(point);
				glutPostRedisplay();
			}
						 break;
			case(TXT) :
			{
				CText * text = new CText();

				CCoord * pt = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);

				text->insertVertex(pt);

				text->setText(textInput);

				imageList->insertFigure(text);
				glutPostRedisplay();
			}
					  break;
			case(CIRCLE) :
			{
				if (count == 0)
				{
					drawing = true;
					count++;
					xp[0] = x;
					yp[0] = fixedY;
				}
				else
				{
					CCircle * circle = new CCircle();

					CCoord * center = new CCoord(xp[0], yp[0], 0, (float)r, (float)g, (float)b);
					circle->insertVertex(center);
					CCoord * outer = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);
					circle->insertVertex(outer);

					circle->setFill(fill);
					circle->setWidth(lineWidth);
					imageList->insertFigure(circle);
					count = 0;
					drawing = false;
					glutPostRedisplay();
				}
			}
						 break;
			case(ARC) :
			{
				if (count == 0)
				{
					drawing = true;
					count++;
					xp[0] = x;
					yp[0] = fixedY;
				}
				else
				{

					printf("x1: %d y1: %d X2: %d y2: %d \n", xp[0], yp[0], x, wh - y);
					CArc * arc = new CArc();

					CCoord * center = new CCoord(xp[0], yp[0], 0, (float)r, (float)g, (float)b);
					arc->insertVertex(center);
					CCoord * arcPoint = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);
					arc->insertVertex(arcPoint);

					arc->setFill(fill);
					arc->setWidth(lineWidth);
					imageList->insertFigure(arc);
					count = 0;
					drawing = false;
					glutPostRedisplay();
				}
			}
					  break;
			}//end switch

			glPopAttrib();
			glFlush();
		}
	}
	else if (btn == GLUT_MIDDLE_BUTTON && state == GLUT_DOWN)
	{
		//glPushAttrib(GL_ALL_ATTRIB_BITS);
		 switch (draw_mode)
		{
		case (LINE_STRIP):
			{
				drawing = false;
				CLineStr * line = new CLineStr();

				for (int i = 0; i < nbrVerts; i++)
				{
					CCoord * pt = new CCoord(xp[i], yp[i], 0, (float)r, (float)g, (float)b);
					line->insertVertex(pt);
				}
				CCoord * point = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);

				line->vertTail->nextCoord = point;
				line->insertVertex(point);
				line->setWidth(lineWidth);

				imageList->insertFigure(line);
				nbrVerts = 0;
				glutPostRedisplay();
			}
			break;
		case (POLYGON) :
		{
			drawing = false;
			CPoly * poly = new CPoly();

			for (int i = 0; i < nbrVerts; i++)
			{
				CCoord * pt = new CCoord(xp[i], yp[i], 0, (float)r, (float)g, (float)b);
				poly->insertVertex(pt);
			}
			CCoord * point = new CCoord(x, fixedY, 0, (float)r, (float)g, (float)b);

			poly->vertTail->nextCoord = point;
			poly->insertVertex(point);

			poly->setFill(fill);
			setWidth(lineWidth);
			imageList->insertFigure(poly);
			nbrVerts = 0;
			glutPostRedisplay();
		}
			break;
		}//end switch

		//glPopAttrib();
		//glFlush();
		
	}
}

int pick(int x, int y)  //which button is selected?
{
	y = wh - y;
	if (y > wh - buttonSize && y < wh  && x >0 && x < buttonSize)
	{
		printf("Saving the pic");
		imageList->save("currentImage.pic");
	}
	else if (y > wh - buttonSize && y < wh  && x > buttonSize && x < (2 * buttonSize))
	{
		printf("Loading\n");
		imageList->load("currentImage.pic");
	}
	else if (x > 40)       return NULL;
	else if (y > wh - (buttonSize * 2) && y < wh - (buttonSize * 2) + buttonSize)      return LINE;
	else if (y > wh - (buttonSize * 3) && y < wh - (buttonSize * 3 ) + buttonSize)       return RECTANGLE;
	else if (y > wh - (buttonSize * 4) && y < wh - (buttonSize * 4) + buttonSize)  return TRIANGLE;
	else if (y > wh - (buttonSize * 5) && y < wh - (buttonSize * 5) + buttonSize)   return POINTS;
	else if (y > wh - (buttonSize * 6) && y < wh - (buttonSize * 6) + buttonSize)       {
		textInput = "";
		return TXT;
	}
	else if (y > wh - (buttonSize * 7) && y < wh - (buttonSize * 7) + buttonSize)
	{
		if (!drawing)
			return LINE_STRIP;
		else {
			drawing = false;
			CLineStr * line = new CLineStr();

			for (int i = 0; i < nbrVerts; i++)
			{
				CCoord * pt = new CCoord(xp[i], yp[i], 0, (float)r, (float)g, (float)b);
				line->insertVertex(pt);
			}
			line->setWidth(lineWidth);
			imageList->insertFigure(line);
			nbrVerts = 0;
			return LINE_STRIP;
		}
			
	}
	else if ((y > wh - (buttonSize * 8)) && (y < wh - (buttonSize * 8) + buttonSize))
	{
		if (!drawing)
			return POLYGON;
		else
		{
			drawing = false;
			CPoly * poly1 = new CPoly();

			for (int i = 0; i < nbrVerts; i++)
			{
				CCoord * pt1 = new CCoord(xp[i], yp[i], 0, (float)r, (float)g, (float)b);
				poly1->insertVertex(pt1);
			}

			poly1->setFill(fill);
			poly1->setWidth(lineWidth);
			imageList->insertFigure(poly1);
			nbrVerts = 0;
			return POLYGON;
		
		}
	}
	else if (y > wh - (buttonSize * 9) && y < wh - (buttonSize * 9) + buttonSize)
	{
		if (!drawing)
		{
			return CIRCLE;
		}
		return draw_mode;
	}
	else if (y > wh - (buttonSize * 10) && y < wh - (buttonSize *10) + buttonSize)
	{
		if (!drawing)
			return ARC;

		return draw_mode;
	}
	else return 0;
}

// draw a box at (x,y)
void screen_box(int x, int y, int s) 
{
	glBegin(GL_QUADS);
	glVertex2i(x, y);
	glVertex2i(x + s, y);
	glVertex2i(x + s, y + s);
	glVertex2i(x, y + s);
	glEnd();
}

// mouse menu code
void right_menu(int id)
{
	if (id == 1) exit(0);
	else{
		glutPostRedisplay();
		imageList->~CImage();
		glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	}
}

void middle_menu(int id)
{

}
void figureMenu(int id)
{

}
void screen_menu(int id)
{
	if (id == 1)
	{
		glutPostRedisplay();
		imageList->~CImage();
		glClearColor((float)cr, (float)cg, (float)cb, 0.0f);
		glClear(GL_COLOR_BUFFER_BIT);
	}
}
// set the current drawing color
void color_menu(int id)
{
	printf("Color menu id: %d \n", id);
	if (id == 1) { r = 1.0; g = 0.0; b = 0.0; }
	else if (id == 2) { r = 0.0; g = 1.0; b = 0.0; }
	else if (id == 3) { r = 0.0; g = 0.0; b = 1.0; }
	else if (id == 4) { r = 0.0; g = 1.0; b = 1.0; }
	else if (id == 5) { r = 1.0; g = 0.0; b = 1.0; }
	else if (id == 6) { r = 1.0; g = 1.0; b = 0.0; }
	else if (id == 7) { r = 1.0; g = 1.0; b = 1.0; }
	else if (id == 8) { r = 0.0; g = 0.0; b = 0.0; }

}

void clear_color_menu(int id)
{
	glutPostRedisplay();
	printf("Clear Color menu id: %d \n", id);
	if (id == 1) { cr = 1.0; cg = 0.0; cb = 0.0; }
	else if (id == 2) { cr = 0.0; cg = 1.0; cb = 0.0; }
	else if (id == 3) { cr = 0.0; cg = 0.0; cb = 1.0; }
	else if (id == 4) { cr = 0.0; cg = 1.0; cb = 1.0; }
	else if (id == 5) { cr = 1.0; cg = 0.0; cb = 1.0; }
	else if (id == 6) { cr = 1.0; cg = 1.0; cb = 0.0; }
	else if (id == 7) { cr = 1.0; cg = 1.0; cb = 1.0; }
	else if (id == 8) { cr = 0.0; cg = 0.0; cb = 0.0; }
	else if (id == 9) { cr = 0.8; cg = 0.8; cb = 0.8; }
	else if (id == 10) { cr = r; cg = g; cb = b; }
	glClearColor((float)cr, (float)cg, (float)cb, 0.0f);
	glClear(GL_COLOR_BUFFER_BIT);
}
void sizeMenu(int id)
{

}

// set point size
void pixel_menu(int id)
{
	size = (float)id;
}

// set fill mode
void fill_menu(int id)
{
	fill = (id == 1) ? 1 : 0;
}

void line_menu(int id)
{
	lineWidth = id;
}
// KB input
void key(unsigned char k, int xx, int yy)
{
	
	if (draw_mode != TXT) return;
	textInput += k;
	glutPostRedisplay();
}
void mouseMove(GLint x, GLint y)
{
	if (x > buttonSize) mouseX = x;
	if(wh - y >= 100 || wh - y <= wh - buttonSize)mouseY = wh - y;
	glutPostRedisplay();
}
void rubber()
{
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	glColor3d(r, g, b);
	if (mouseY < 100)
		mouseY = 100;
	if (!drawing) return;

	switch (draw_mode)
	{
	case(LINE) :
	{
		glLineWidth(lineWidth);
		glBegin(GL_LINES);
		glVertex2i(xp[0], yp[0]);
		glVertex2i(mouseX, mouseY);
		glEnd();
	}

		break;
	case (RECTANGLE):
	{
		if (count == 0)
			break;
		
		glColor3d(r, g, b);
		if (fill) glBegin(GL_POLYGON);
		else { glLineWidth(lineWidth); glBegin(GL_LINE_LOOP); }
		glVertex2i(xp[0], yp[0]);
		glVertex2i(xp[0], mouseY);
		glVertex2i(mouseX, mouseY);
		glVertex2i(mouseX, yp[0]);
		glEnd();

	}
		break;
	case(TRIANGLE) :
		if (count < 2)
			break;
		else
		{
			glColor3d(r, g, b);
			if (fill) glBegin(GL_POLYGON);
			else { glLineWidth(lineWidth); glBegin(GL_LINE_LOOP); }
			glVertex2i(xp[0], yp[0]);
			glVertex2i(xp[1], yp[1]);
			glVertex2i(mouseX, mouseY);
			glEnd();
		}
				   break;
	case (POLYGON):
		if (nbrVerts < 2)
			break;
		else
		{
			glColor3d(r, g, b);
			if (fill) glBegin(GL_POLYGON);
			else { glLineWidth(lineWidth); glBegin(GL_LINE_LOOP); }
			for (int i = 0; i < nbrVerts; i++)
			{
				glVertex2i(xp[i], yp[i]);
			}

			glVertex2i(mouseX, mouseY);
			glEnd();
		}
				  break;
	case(LINE_STRIP):
	{
		glColor3d(r, g, b);
		glLineWidth(lineWidth);
		glBegin(GL_LINE_STRIP);
		for (int i = 0; i < nbrVerts; i++)
		{
			glVertex2i(xp[i], yp[i]);
		}

		glVertex2i(mouseX, mouseY);
		glEnd();
		
	}
					break;
	case(CIRCLE) :
	{
		glColor3d(r, g, b);
		int x = mouseX - xp[0];
		int y = mouseY - yp[0];
		float radius = sqrt((float)x * x + y * y);

		float Y1 = (float)yp[0];
		float X1 = (float)xp[0] + radius;

		if (fill) glBegin(GL_POLYGON);
		else { glLineWidth(lineWidth); glBegin(GL_LINE_LOOP); }
		for (float angle = 0.0f; angle <= (2.0f*3.14159); angle += 0.01f)
		{
			glVertex2f(X1, Y1);
			X1 = (float)(xp[0] + (radius * (float)(cos((double)angle))));
			Y1 = (float)(yp[0] + (radius * (float)(sin((double)angle))));
		}
		glEnd();
	}
				 break;
	case(ARC) :
	{
	
		if (count == 0)
			break;
		CCoord * center = new CCoord(xp[0], yp[0], 0, (float)r, (float)g, (float)b);
		CCoord * arcPoint = new CCoord(mouseX, mouseY, 0, (float)r, (float)g, (float)b);
 
		int x = arcPoint->x - center->x;
		int y = arcPoint->y - center->y;
		float radius = sqrt((float)x * x + y * y); //pythag

		float Y1 = (float)center->y;// +radius;
		float X1 = (float)center->x + (float)radius;

		float angle = (float)atan2((double)y, x);
		if (angle < 0)
		{
			angle = (float)(2 * 3.14159) + angle;
		}
		glColor3d(center->r, center->g, center->b);
		if (fill){ glBegin(GL_POLYGON); glVertex2i(center->x, center->y); }
		else { glLineWidth(lineWidth); glBegin(GL_LINE_STRIP); }
		for (float a = 0.0f; a <= angle; a += 0.01f)
		{
			glVertex2f(X1, Y1);
			X1 = (float)(center->x + (radius * (float)(cos((double)a))));
			Y1 = (float)(center->y + (radius * (float)(sin((double)a))));
		}
		glEnd();
	}
	}//end switch

	glPopAttrib();

}
// drawing callback
void display(void)
{
	int shift = 0;
	glClearColor((float)cr, (float)cg, (float)cb, 0.0f);
	glClear(GL_COLOR_BUFFER_BIT);

	// save rendering state
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	imageList->draw();
	rubber();
	
	// draw the painting screen

	//Create the status area
	
	
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	glColor3f(0.0, 0.0, 0.0);
	glBegin(GL_POLYGON);
	glVertex2i(0, wh);
	glVertex2i(0, wh - buttonSize);
	glVertex2i(ww, wh - buttonSize);
	glVertex2i(ww, wh);
	glEnd();

	glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	glColor3f(1.0, 0.0, 0.0);
	glBegin(GL_POLYGON);
	glVertex2i(0, wh);
	glVertex2i(0, wh - buttonSize);
	glVertex2i(ww, wh - buttonSize);
	glVertex2i(ww, wh);
	glEnd();

	
	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	glBegin(GL_POLYGON);
	glColor3d(r, g, b);
	glVertex2i(85, wh - buttonSize / 4);
	glVertex2i(85, wh - (buttonSize) + 10);
	glVertex2i(125, wh - (buttonSize) + 10);
	glVertex2i(125, wh - buttonSize / 4);
	glEnd();

	glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	glBegin(GL_POLYGON);
	glColor3d(1.0, 1.0, 1.0);
	glVertex2i(85, wh - buttonSize / 4);
	glVertex2i(85, wh - (buttonSize) + 10);
	glVertex2i(125, wh - (buttonSize) + 10);
	glVertex2i(125, wh - buttonSize / 4);
	glEnd();
	glPopAttrib();
	//Create the mode buttons
	glColor3f(1.0, 1.0, 1.0);
	screen_box(0, wh - (buttonSize * 2), buttonSize);
	glColor3f(1.0, 0.0, 0.0);
	screen_box(0, wh - (buttonSize *3), buttonSize);
	glColor3f(0.0, 1.0, 0.0);
	screen_box(0, wh - (buttonSize * 4), buttonSize);
	glColor3f(0.0, 0.0, 1.0);
	screen_box(0, wh - (buttonSize * 5), buttonSize);	
	glColor3f(1.0, 1.0, 0.0);
	screen_box(0, wh - (buttonSize * 6), buttonSize);
	glColor3f(0.0, 0.0, 0.0);
	screen_box(0, wh - (buttonSize * 7), buttonSize);
	glColor3f(1.0, 1.0, 0.0);
	screen_box(0, wh - (buttonSize *8), buttonSize);
	glColor3f(1.0, 0.0, 0.0);
	screen_box(0, wh - (buttonSize * 9), buttonSize);
	glColor3f(0.0, 0.0, 1.0);
	screen_box(0, wh - (buttonSize * 10), buttonSize);

	//draw icons on the buttons
	glColor3f(0.0, 0.0, 0.0);
	screen_box(9, wh - (buttonSize * 3) + 9, 800 /35);
	glBegin(GL_LINES);
	glVertex2i(10, wh - (buttonSize + 5));
	glVertex2i(30, wh - (buttonSize * 2) + 5);
	glEnd();
	glBegin(GL_TRIANGLES);
	glVertex2i(5, wh - (buttonSize * 4) + 5);
	glVertex2d(20, wh - (buttonSize * 3.5) + 10);
	glVertex2i(35, wh - (buttonSize * 4) + 5);
	glEnd();
	glPointSize(3.0);
	glBegin(GL_POINTS);
	glVertex2i(20, wh - (buttonSize * 5) + 20);
	glEnd();
	glRasterPos2i(5, wh - (buttonSize * 6) + 5);
	glutBitmapCharacter(GLUT_BITMAP_9_BY_15, 'A');
	shift = glutBitmapWidth(GLUT_BITMAP_9_BY_15, 'A');
	glRasterPos2i(5 + shift, wh - (buttonSize * 6) + 5);
	glutBitmapCharacter(GLUT_BITMAP_9_BY_15, 'B');
	shift += glutBitmapWidth(GLUT_BITMAP_9_BY_15, 'B');
	glRasterPos2i(5 + shift, wh - (buttonSize * 6) + 5);
	glutBitmapCharacter(GLUT_BITMAP_9_BY_15, 'C');
	glColor3d(1.0, 1.0, 1.0);
	glBegin(GL_LINES);
	glVertex2i(10, wh - (buttonSize * 7) + 5);
	glVertex2i(30, wh - (buttonSize * 7) + 20);

	glVertex2i(30, wh - (buttonSize * 7) + 20);
	glVertex2i(10, wh - (buttonSize * 7) + 35);
	glEnd();
	glColor3d(0.0, 0.0, 0.0);
	glBegin(GL_POLYGON);
	glVertex2i(10, wh - (buttonSize * 8) + 10);
	glVertex2i(10, wh - (buttonSize * 8) + 20);
	glVertex2i(15, wh - (buttonSize * 8) + 20);
	glVertex2i(20, wh - (buttonSize * 8) + 25);
	glVertex2i(25, wh - (buttonSize * 8) + 20);
	glVertex2i(30, wh - (buttonSize * 8) + 20);
	glVertex2i(30, wh - (buttonSize * 8) + 10);
	glEnd();

	int x = (buttonSize - 5) - (buttonSize / 2);
	int y = (wh - (buttonSize * 9) + 20) - (wh - (buttonSize * 9) + 20);
	float radius = sqrt((float)x * x + y * y);

	float Y1 = (float)wh - (buttonSize * 9) + 20;
	float X1 = (float)buttonSize/2 + (float)radius;

	glColor3d(0.0, 0.0, 0.0);
	glBegin(GL_POLYGON);
	for (float angle = 0.0f; angle <= (2.0f*3.14159); angle += 0.01f)
	{
		glVertex2f(X1, Y1);
		X1 = (float)(buttonSize / 2 + (radius * (float)(cos((double)angle))));
		Y1 = (float)(wh - (buttonSize * 9) + 20 + (radius * (float)(sin((double)angle))));
	}
	glEnd();

	x = (buttonSize - 5) - (buttonSize / 2);
	y = (wh - (buttonSize * 10) + 20) - (wh - (buttonSize * 10) + 20);
	radius = sqrt((float)x * x + y * y);

	Y1 = (float)wh - (buttonSize * 10) + 20;
	X1 = (float)buttonSize / 2 + (float)radius;

	glColor3d(0.0, 0.0, 0.0);
	glLineWidth(3);
	glBegin(GL_LINE_STRIP);
	for (float angle = 0.0f; angle <= (1.25f*3.14159); angle += 0.01f)
	{
		glVertex2f(X1, Y1);
		X1 = (float)(buttonSize / 2 + (radius * (float)(cos((double)angle))));
		Y1 = (float)(wh - (buttonSize * 10) + 20 + (radius * (float)(sin((double)angle))));
	}
	glEnd();
	glLineWidth(1);
	
	//Draw Color sliders area
	glPushAttrib(GL_ALL_ATTRIB_BITS);
	glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	glColor3d(0.0, 0.0, 0.0);
	glBegin(GL_POLYGON);
	glVertex2i(0, 100);
	glVertex2i(0, 0);
	glVertex2i((800 / 20) * 4, 0);
	glVertex2i((800/ 20) * 4, 100);
	glEnd();

	//Draw the actual sliders
	glColor3f(1.0f, (float)g, (float)b);
	glBegin(GL_POLYGON);
	glVertex2i(10, 95);
	glVertex2i(10, 75);
	glColor3f(0.0f, (float)g, (float)b);
	glVertex2i(150, 75);
	glVertex2i(150, 95);
	glEnd();

	glColor3f((float)r, 1.0f, (float)b);
	glBegin(GL_POLYGON);
	glVertex2i(10, 65);
	glVertex2i(10, 45);
	glColor3f((float)r, 0.0f, (float)b);
	glVertex2i(150, 45);
	glVertex2i(150, 65);
	glEnd();

	glColor3f((float)r, (float)g, 1.0f);
	glBegin(GL_POLYGON);
	glVertex2i(10, 35);
	glVertex2i(10, 15);
	glColor3f((float)r, (float)g, 0.0f);
	glVertex2i(150, 15);
	glVertex2i(150, 35);
	glEnd();

	//Draw message area
	glColor3d(0.0, 0.0, 0.0);
	glBegin(GL_QUADS);
	glVertex2i((800 / 20) * 4, 100);
	glVertex2i((800 / 20) * 4, 0);
	glVertex2i(ww, 0);
	glVertex2i(ww, 100);
	glEnd();


	//Draw color sliders area with only outline
	glColor3f(1.0f, 0.0f, 0.0f);
	glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
	glBegin(GL_POLYGON);
	glVertex2i(0, 100);
	glVertex2i(0, 0);
	glVertex2i((800 / 20) * 4, 0);
	glVertex2i((800 / 20) * 4, 100);
	glEnd();

	//draw the actual sliders
	glColor3f(1.0f, 1.0f, 1.0f);
	glBegin(GL_POLYGON);
	glVertex2i(10, 95);
	glVertex2i(10, 75);
	glVertex2i(150, 75);
	glVertex2i(150, 95);
	glEnd();

	glColor3f(1.0f, 1.0f, 1.0f);
	glBegin(GL_POLYGON);
	glVertex2i(10, 65);
	glVertex2i(10, 45);
	glVertex2i(150, 45);
	glVertex2i(150, 65);
	glEnd();

	glColor3f(1.0f, 1.0f, 1.0f);
	glBegin(GL_POLYGON);
	glVertex2i(10, 35);
	glVertex2i(10, 15);
	glVertex2i(150, 15);
	glVertex2i(150, 35);
	glEnd();

	//Draw message area
	glColor3f(1.0f, 0.0f, 0.0f);
	glBegin(GL_QUADS);
	glVertex2i((800 / 20) * 4, 100);
	glVertex2i((800 / 20) * 4, 0);
	glVertex2i(ww, 0);
	glVertex2i(ww, 100);
	glEnd();

	glPopAttrib();

	glColor3f(1.0f, 1.0f, 1.0f);
	glBegin(GL_LINES);
	glLineWidth(25);
	glVertex2i(redSliderX, redSliderY + 10);
	glVertex2i(redSliderX, redSliderY - 10);
	glEnd();

	glColor3f(1.0f, 1.0f, 1.0f);
	glBegin(GL_LINES);
	glLineWidth(25);
	glVertex2i(greenSliderX, greenSliderY + 10);
	glVertex2i(greenSliderX, greenSliderY - 10);
	glEnd();

	glColor3f(1.0f, 1.0f, 1.0f);
	glBegin(GL_LINES);
	glLineWidth(25);
	glVertex2i(blueSliderX, blueSliderY + 10);
	glVertex2i(blueSliderX, blueSliderY - 10);
	glEnd();
	

	//Draw save and load buttons
	glColor3f(0.8f, 0.0f, 0.8f);
	screen_box(0, wh - buttonSize, buttonSize);
	glColor3f(0.0f, 0.8f, 0.8f);
	screen_box(buttonSize, wh - buttonSize, buttonSize);

	glColor3f(0.0f, 0.0f, 0.0f);
	string save = "SAVE";
	glRasterPos2d(1, wh - (buttonSize / 2) - 5);

	for (string::iterator i = save.begin(); i != save.end(); i++)
	{
		glutBitmapCharacter(GLUT_BITMAP_9_BY_15, *i);
	}

	string load = "LOAD";
	glRasterPos2d(buttonSize + 1, wh - (buttonSize / 2) - 5);

	for (string::iterator i = load.begin(); i != load.end(); i++)
	{
		glutBitmapCharacter(GLUT_BITMAP_9_BY_15, *i);
	}

	//Write out the status
	glColor3f(1.0, 1.0, 1.0);
	int adjustedMX = mouseX - buttonSize;
	string xPos = std::to_string(adjustedMX);
	string yPos = std::to_string(mouseY - 80);
	string lineSize = std::to_string((int)lineWidth);


	string status = "Current Mode: " +getMode() + " Line Size: " +lineSize + "; X Position " + xPos + ", Y Position: " + yPos;
	glRasterPos2d((ww / 4) / 1.5 , wh - buttonSize/2);

	for (string::iterator i = status.begin(); i != status.end(); i++)
	{
		glutBitmapCharacter(GLUT_BITMAP_9_BY_15, *i);
	}

	string message = getMessage();
	int width = 0;
	int rasterY = 75;
	if (draw_mode == TXT)
		glColor3d(r, g, b);

	glRasterPos2i((800 / 4), rasterY);
	for (string::iterator i = message.begin(); i != message.end(); i++)
	{
		if (width > 500 && *i == ' ')
		{
			rasterY -= 20;
			glRasterPos2i((800 / 4), rasterY);
			width = 0;
		}
		glutBitmapCharacter(GLUT_BITMAP_9_BY_15, *i);
		width += glutBitmapWidth(GLUT_BITMAP_9_BY_15, *i);
	}


	//'Rubberbanding' for the text mode
	if (draw_mode == TXT)
	{
		glColor3d(r, g, b);
		glRasterPos2i(mouseX, mouseY);

		for (string::iterator i = textInput.begin(); i != textInput.end(); i++)
		{
			char c = *i;
			glutBitmapCharacter(GLUT_BITMAP_9_BY_15, c);
		}
	}
	
	glViewport(buttonSize, 100, ww, wh - buttonSize);
	glFlush();
	// restore state
	glPopAttrib();

	glutSwapBuffers();
	
}

// setup window
int main(int argc, char** argv)
{
	int c_menu, p_menu, f_menu, s_menu, l_menu, size_menu;

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize(wh, ww);
	glutCreateWindow("Simple Paint");
	glutDisplayFunc(display);
	glutReshapeFunc(myReshape);
	glutPassiveMotionFunc(mouseMove);
	glutKeyboardFunc(key);
	glutMouseFunc(mouse);
	myinit();

	// create a menu for the colors
	c_menu = glutCreateMenu(clear_color_menu);
	glutAddMenuEntry("Red", 1);
	glutAddMenuEntry("Green", 2);
	glutAddMenuEntry("Blue", 3);
	glutAddMenuEntry("Cyan", 4);
	glutAddMenuEntry("Magenta", 5);
	glutAddMenuEntry("Yellow", 6);
	glutAddMenuEntry("White", 7);
	glutAddMenuEntry("Black", 8);
	glutAddMenuEntry("Grey", 9);
	glutAddMenuEntry("Custom", 10);


	// create a menu for pixel size
	l_menu = glutCreateMenu(line_menu);
	glutAddMenuEntry("1", 1);
	glutAddMenuEntry("2", 2);
	glutAddMenuEntry("3", 3);
	glutAddMenuEntry("4", 4);
	glutAddMenuEntry("5", 5);

	// create a menu for pixel size
	p_menu = glutCreateMenu(pixel_menu);
	glutAddMenuEntry("1", 1);
	glutAddMenuEntry("2", 2);
	glutAddMenuEntry("3", 3);
	glutAddMenuEntry("4", 4);
	glutAddMenuEntry("5", 5);

	size_menu = glutCreateMenu(sizeMenu);
	glutAddSubMenu("Point Size", p_menu);
	glutAddSubMenu("Line Width", l_menu);

	s_menu = glutCreateMenu(screen_menu);
	glutAddMenuEntry("Clear", 1);
	glutAddSubMenu("Clear Color", c_menu);

	f_menu = glutCreateMenu(fill_menu);
	glutAddSubMenu("Size", size_menu);
	glutAddMenuEntry("Filled", 1);
	glutAddMenuEntry("Outlined", 2);

	// create options menu
	glutCreateMenu(right_menu);
	glutAddSubMenu("Screen", s_menu);
	glutAddSubMenu("Figure", f_menu);
	glutAddMenuEntry("Quit", 1);
	glutAttachMenu(GLUT_RIGHT_BUTTON); // attach options menu to middle button

	glutMainLoop();
}
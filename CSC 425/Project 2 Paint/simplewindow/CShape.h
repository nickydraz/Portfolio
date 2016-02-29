// File CShape.h
// Author: S. Renk 10/07
// Shape classes that form a graphics library for library for a Paint program.

//Modified by Nicholas Drazenovic


#ifndef _CShape
#define _CShape

//#include <windows.h>
#include <stdio.h>
#include <math.h>
#include <string>
#include <iostream>
#include <fstream>
using std::string;
//#include "color.h"

// define constants for all shapes
#define CNONE 0
#define CPOINT 1
#define CLINE 2
#define CLINESTR 3
#define CCIRCLE 4
#define CRECT 5
#define CARC 6
#define CTEXT 7
#define CTRI 8
#define CPOLY 9

#define CLOAD 10
#define CSAVE 11

#define CBUTTON 15
void setWidth(int);
int getWidth();

// ********************** CCord class ********************
class CCoord // basic vertex class
{
public:
	int x, y, z; // vertex cartesian coord (z=0 for 2D)
	float r, g, b, a; // vertex color (a=1.0f)
	CCoord * nextCoord; // ptr. to next vertex in a linked list

	CCoord(int nx = 0, int ny = 0, int nz = 0, float nr = 0, float ng = 0, float nb = 0, float na = 0)
	{
		nextCoord = NULL; x = nx; y = ny; z = nz; r = nr; g = ng; b = nb; a = na;

	}

	void setXYZ(int nx, int ny, int nz = 0) {
		x = nx;
		y = ny;
		z = nz;
	}
};

// ********************* CShape class ********************
class CShape // abstract base class for all Shapes
{
protected:
	int type; // 0- point, 1-line, ...
	bool good;
	bool fill; //is it a filled figure?
	
	CCoord * vertHead;

	int nbrVert; // # vertices, if = 0 bad figure
	//bool CShape::insert(CCoord * vert); // links in a vertex

public:
	CCoord * vertTail; // list of vertices in figure
	CShape * nextShape; // points to next shape in list
	GLfloat lineWidth = 1;
	CShape(int ntype =CNONE){ vertHead = vertTail = NULL; nextShape = NULL; nbrVert = 0; type = ntype; good = false; }
	~CShape(); // delete linked list of verts
	int getType(){ return type; }
	void setFill(bool Fill)
	{
		fill = Fill;
	}
	void setWidth(GLfloat width)
	{
		lineWidth = width;
	}

	virtual void draw() = 0; //Draws the figure
	virtual bool isPicked(int x, int y, int z = 0) = 0; // true if (x,y,z) is inside figure
	virtual bool insertVertex(CCoord * vert) = 0; // place in vertex list

	 virtual void mouseDrag(int x, int y, int state) = 0; // mouse drag for rubberbanding
	virtual void read(std::ifstream &filename) = 0; // read object from file -- I'd actually overload << & >>
	virtual void write(std::ofstream &filename) = 0; // write object to file
};

extern CShape * Sptr;

// ******************** CPoint class ********************
class CPoint : public CShape // A list of points
{
private:
	float size; //size of al pts in list
public:
	CPoint() :CShape(CPOINT){ size = 3; } // pass type to base
	void setSize(float sz) { size = sz; }
	void draw(); // draw a set of points
	bool isPicked(int x, int y, int z = 0){ return false; }
	bool insertVertex(CCoord * vertex){ CShape::insertVertex(vertex); return good = true; }
	void mouseDrag(int x, int y, int state){} // do nothing
	void read(std::ifstream &filename){} //read object from file
	void write(std::ofstream &filename); //write obejct to file
};

// ******************** CLine class *********************
class CLine : public CShape // A list of lines. Every pair is a line.
{
public:
	CLine() :CShape(CLINE){} // pass type to base
	void draw();            // draw a set of lines
	bool isPicked(int x, int y, int z = 0){ return false; }
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);
		return good = (nbrVert > 1);
	}
	void mouseDrag(int x, int y, int state) {} //mouse drag function
	void write(std::ofstream &filename);//write obejct to file
	void read(std::ifstream &filename) {} //read object from file

};

// ******************* CLineStr class ********************
class CLineStr : public CShape //a list of lines. Every pair is a line
{
public:
	CLineStr() :CShape(CLINESTR){}
	void draw();
	bool isPicked(int x, int y, int z = 0){ return false; }
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);
		return good = (nbrVert > 1);
	}
	void mouseDrag(int x, int y, int state){} //mouse drag function
	void write(std::ofstream &filename); //write obejct to file
	void read(std::ifstream &filename) {} //read object from file
};



// ******************* CCircle class ********************
class CCircle : public CShape // A single circle
{
protected:
	double radius;

public:
	CCircle() :CShape(CCIRCLE){} // pass type to base
	void draw(); // draw a set of lines
	bool isPicked(int x, int y, int z = 0){ return false; }
	void mouseDrag(int x, int y, int state) {} //do nothing
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);

		if (nbrVert == 2)
		{
			int x = vertex->x - vertHead->x;
			int y = vertex->y - vertHead->y;
			radius = sqrt((double)(x * x + y * y));
			//printf(" drag %f  %f \n, x, y");
			good = true;
		}
		return good;
	}
	void write(std::ofstream &filename); //write obejct to file
	void read(std::ifstream &filename) {} //read object from file
};

class CArc : public CShape // A single arc
{
protected:
	double radius;
	double angle;

public:
	CArc() :CShape(CARC){} // pass type to base
	void draw(); // draw a set of lines
	bool isPicked(int x, int y, int z = 0){ return false; }
	void mouseDrag(int x, int y, int state) {} //do nothing
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);

		if (nbrVert == 2)
		{
			int x = vertex->x - vertHead->x;
			int y = vertex->y - vertHead->y;
			radius = sqrt((double)(x * x + y * y));
		
			angle = atan2((double)y, x);
			if (angle < 0)
			{
				angle = (2 * 3.14159) + angle;
			}

			//printf(" drag %d  %d \n", x, y);
			//printf(" angle at %f \n", angle);
			good = true;
		}
		return good;
	}
	void write(std::ofstream &filename); //write obejct to file
	void read(std::ifstream &filename) {} //read object from file
};

// ***************** CRect class ***********************
class CRect : public CShape
{
public:
	CRect() :CShape(CRECT){}
	void draw();
	bool isPicked(int x, int y, int z = 0){ return false; }
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);
		return good = (nbrVert > 1);
	}
	void mouseDrag(int x, int y, int state){} //mouse drag function
	void write(std::ofstream &filename); //write obejct to file
	void read(std::ifstream &filename) {} //read object from file
};

class CPoly : public CShape
{
public:
	CPoly() :CShape(CPOLY){}
	void draw();
	bool isPicked(int x, int y, int z = 0){ return false; }
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);
		return good = (nbrVert > 1);
	}
	void mouseDrag(int x, int y, int state) {} //mouse drag function
	void write(std::ofstream &filename); //write obejct to file
	void read(std::ifstream &filename) {} //read object from file
};


class CText : public CShape
{
protected:
	string inputText;
public:
	CText() :CShape(CTEXT){};
	void setText(string text) { inputText = text; }
	string getText() { return inputText; }
	void draw();
	bool isPicked(int x, int y, int z = 0){ return false; }
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);
		return good = (nbrVert > 1);
	}
	void mouseDrag(int x, int y, int state) {} //mouse drag function
	void write(std::ofstream &filename); //write obejct to file
	void read(std::ifstream &filename) {} //read object from file
};

/* CButton Class */
#define BTN_HEIGHT 30
#define BTN_WIDTH 40

class CButton : public CShape
{
	int btnType, x, y, w, h;
	static int selectedBtn;

public:
	CButton(int nx, int ny, int btn, int nw = BTN_WIDTH, int nh = BTN_HEIGHT) :CShape(CBUTTON)
	{
		x = nx; y = ny; btnType = btn; w = nw; h = nh;
	}
	int getBtnType(){ return btnType; }
	void draw();
	bool isPicked(int x, int y, int z = 0);
	bool insertVertex(CCoord * vertex)
	{
		CShape::insertVertex(vertex);
		return good = (nbrVert > 1);
	}
	void mouseDrag(int x, int y, int state); //mouse drag function
	void write(std::ofstream &filename){} //write obejct to file
};
#endif

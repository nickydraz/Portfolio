//CImage file for save/load functions
//Nicholas Drazenovic

#include <GL/glut.h>
#include <Windows.h>
#include <iostream>
#include <fstream>
#include "CImage.h"
#include <string>
#include <math.h>
using namespace std;

void CImage::save(char * filename)
{
	ofstream saveFile;
	saveFile.open(filename);
	curr = head;
	while (curr)
	{
		printf("saving \n");
		curr->write(saveFile);
		curr = curr->nextShape;
		saveFile << endl;
	}

}

void CImage::load(char * filename)
{
	ifstream infile(filename);
	if (infile)
	{
		string line;

		clear();
		while (getline(infile, line))
		{

			vector<string> tokens;
			split(line, ';', tokens);
			int type = stoi(tokens[0]);
			//Switch on type of shape
			switch (type)
			{
			case (CPOINT) :
			{
				CPoint * pt = new CPoint();
				pt->setSize(stoi(tokens[1]));
				CCoord * c = new CCoord(stoi(tokens[2]), stoi(tokens[3]), 0, stof(tokens[4]), stof(tokens[5]), stof(tokens[6]));
				pt->insertVertex(c);
				insertFigure(pt);
				break;
			}
			case(CLINE):
			{
				CLine * line = new CLine();

				CCoord * coord1 = new CCoord(stoi(tokens[2]), stoi(tokens[3]), 0.0, stof(tokens[6]), stof(tokens[7]), stof(tokens[8]));
				CCoord * coord2 = new CCoord(stoi(tokens[4]), stoi(tokens[5]), 0.0, stof(tokens[6]), stof(tokens[7]), stof(tokens[8]));
				coord1->nextCoord = coord2;
				line->insertVertex(coord1);

				line->insertVertex(coord2);

				line->setWidth(stoi(tokens[1]));
				insertFigure(line);
				break;
			}
			case(CLINESTR) :
			{
				CLineStr * line = new CLineStr();
				line->setWidth(stof(tokens[1]));
				int start = 3;
				int offset = 5;
				for (int i = 0; i < stoi(tokens[2]); i++)
				{
					CCoord * pt1 = new CCoord(stoi(tokens[start + (i * offset)]), stoi(tokens[start + (i * offset) + 1]), 0, stof(tokens[start + (i * offset) + 2]), stof(tokens[start + (i * offset) + 3]), stof(tokens[start + (i * offset) + 4]));
					line->insertVertex(pt1);
				}
				insertFigure(line);
				break;
			}
			case(CTRI) :
			{
				//Fall through to CPOLY case
			}
			case(CPOLY) :
			{
				CPoly * poly = new CPoly();
				poly->setWidth(stof(tokens[1]));
				poly->setFill(stoi(tokens[2]));
				int start = 4;
				int offset = 5;
				for (int i = 0; i < stoi(tokens[2]); i++)
				{
					CCoord * pt1 = new CCoord(stoi(tokens[start + (i * offset)]), stoi(tokens[start + (i * offset) + 1]), 0, stof(tokens[start + (i * offset) + 2]), stof(tokens[start + (i * offset) + 3]), stof(tokens[start + (i * offset) + 4]));
					poly->insertVertex(pt1);
				}
				insertFigure(poly);
				break;
			}
			case(CRECT) :
			{

				CRect * rect = new CRect();

				CCoord * coord1 = new CCoord(stoi(tokens[3]), stoi(tokens[4]), 0, stof(tokens[7]), stof(tokens[8]), stof(tokens[9]));
				CCoord * coord2 = new CCoord(stoi(tokens[5]), stoi(tokens[6]), 0, stof(tokens[7]), stof(tokens[8]), stof(tokens[9]));
				coord1->nextCoord = coord2;
				rect->insertVertex(coord1);

				rect->insertVertex(coord2);
				rect->setFill(stoi(tokens[1]));
				rect->setWidth(stof(tokens[1]));
				insertFigure(rect);
				break;
			}

			case(CCIRCLE) :
			{
				CCircle * circle = new CCircle();

				circle->setWidth(stoi(tokens[1]));
				circle->setFill(stoi(tokens[2]));

				CCoord * center = new CCoord(stoi(tokens[4]), stoi(tokens[5]), 0, stof(tokens[8]), stof(tokens[9]), stof(tokens[10]));
				CCoord * outer = new CCoord(stoi(tokens[6]), stoi(tokens[7]), 0, stof(tokens[8]), stof(tokens[9]), stof(tokens[10]));

				circle->insertVertex(center);
				circle->insertVertex(outer);

				insertFigure(circle);
				break;
			}
			case(CARC) :
			{
				CArc * arc = new CArc();

				arc->setWidth(stoi(tokens[1]));
				arc->setFill(stoi(tokens[2]));
				
				CCoord * center = new CCoord(stoi(tokens[5]), stoi(tokens[6]), 0, stof(tokens[9]), stof(tokens[10]), stof(tokens[11]));
				CCoord * outer = new CCoord(stoi(tokens[7]), stoi(tokens[8]), 0, stof(tokens[9]), stof(tokens[10]), stof(tokens[11]));

				arc->insertVertex(center);
				arc->insertVertex(outer);

				insertFigure(arc);
			}
			case(CTEXT) :
			{
				CText * text = new CText();
				text->setText(tokens[1]);

				CCoord * pos = new CCoord(stoi(tokens[2]), stoi(tokens[3]), 0, stof(tokens[4]), stof(tokens[5]), stof(tokens[6]));

				text->insertVertex(pos);

				insertFigure(text);

			}
			}
		}
	}
	else
	{
		cout << "There was an error opening the file.\n";
	}



}
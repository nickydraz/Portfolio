// File: CImage.h
// Author S. Renk 2/2006
// Manages a linked list of objects
//Modified by Nicholas Drazenovic


#ifndef CIMAGE
#define CIMAGE


#include "CShape.h"
#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>

class CImage
{
	CShape * head, *tail, *curr, *prev;

public:
	CImage() { head = tail = curr = prev = NULL; }
	~CImage() { clear(); };

	void insertFigure(CShape * newShape) //adds new shape to end of list
	{
		if (!head)
			head = newShape;
		else
		{
			tail->nextShape = newShape;
		}
		tail = newShape;
		printf(" new pic in list \n");
	}

	void draw() //draws all the shapes
	{
		// printf("Painting \n");
		curr = head;
		while (curr) //traverse and print the list
		{
			curr->draw();
			curr = curr->nextShape;
		}
	}

	CShape * isPicked(int px, int py)
	{
		CShape * rtn = NULL;

		curr = head;
		while (curr) //traverse and print the list
		{
			if (curr->isPicked(px, py))
			{
				printf(" yes 2 %d\n", curr->getType()); // ***********
				return curr;
			}
			curr = curr->nextShape;
		}
		return rtn;
	}
	void clear()
	{
		while (curr = head)
		{
			head = head->nextShape; 
			delete curr;
		}
		printf("deleted pic\n");
	}

	void load(char * filename); //creates a lsit from a file
	void save(char * filename); //writes a file from the current list


	//Function Taken from StackOverflow
	//splits a string into tokens
	std::vector<string> &split(const string &s, char delim, std::vector<string> &tokens)
	{
		std::stringstream ss(s);
		string token;
		while(getline(ss, token, delim))
		{
			tokens.push_back(token);
		}
		return tokens;
	}
};
#endif
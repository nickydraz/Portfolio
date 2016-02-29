using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComputeGrade
{

    class StudentData
    {
        string name = "";
        int score = 0;
        public StudentData(string name)
        {
            this.name = name;
        }

        public void setScore(int score)
        {
            this.score = score;
        }

        public string getName()
        {
            return this.name;
        }
        public int getScore()
        {
            return this.score;
        }
    }
    class Program
    {
        static void Main(string[] args)
        {

            string input = Console.ReadLine();

            string[] pieces = input.Split('&');

            List<StudentData> students = new List<StudentData>();

            foreach (var part in pieces)
            {
                
                if (part.Contains("stud"))
                {
                    string[] stud = part.Split('=');
                    string studName = stud[1];

                    StudentData student = new StudentData(studName);
                    students.Add(student);

                    
                }

                if (part.Contains("score"))
                {
                    string[] stud = part.Split('=');
                    int studScore = int.Parse(stud[1]);

                    students[students.Count() - 1].setScore(studScore);
                }
            }//end foreach

            string htmlTable = "";
            
            htmlTable += @"<table><tr><th>Name</th><th>Score</th></tr>";

            List<StudentData> sortedStudents = new List<StudentData>();

            sortedStudents = students.OrderByDescending(key => key.getScore()).ToList();

            for (int i = 0; i < sortedStudents.Count(); i++)
            {
                string name = sortedStudents[i].getName();
                int score = sortedStudents[i].getScore();

                htmlTable += @"<tr><td>" + name + "</td><td>" + score + "</td></tr>";
            }
                

            htmlTable += @"</table>";

            HTMLTemplates.computedGrades(htmlTable);
          
        }//end main

        char Grade(int score)
        {
            return 'a';
        }
    }//end class

    class HTMLTemplates
    {
        public static void computedGrades(string table)
        {
            string html = @"<html><body><h1>Computed Grades</h1><hr />" + table;
            html += @"<hr/><p><a href='CSC415Project1.exe'>Do Another Class</a></p></body></html>";

            Console.WriteLine("Content-Type:text/html\n\n");
            Console.WriteLine(html);
        }
    }//end class
}

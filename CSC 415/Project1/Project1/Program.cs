using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;

namespace CSC415Project1
{
    class Program
    {

        static void Main(string[] args)
        {
            String reqType = Environment.GetEnvironmentVariable("REQUEST_METHOD");
            switch (reqType)
            {
                case "GET":
                    HTMLTemplates.createTemplate();
                    break;
                case "POST":
                    MakeTable();
                    break;
            }

        }

        static void MakeTable()
        {
           
            string input = Console.ReadLine();
           
            string[] pieces = input.Split('&');
            string[] courseName_pieces = pieces[0].Split('=');
            string[] numStudents_pieces = pieces[1].Split('=');

          string courseName = courseName_pieces[1];
          int numStudents = int.Parse(numStudents_pieces[1]);


            string table = @"<form name='scores' id='scores' method='post' action='ComputeGrade.exe'>";
            for (int i = 0; i < numStudents; i++)
            {
                table += @"<br/>Student Name:<input type='text' value='' name='stud" + i + @"' />";
                table += @"Grade: <input type='text' value='' name='score" + i + @"' />";
            }//end for

            table += @"<br/><br/><input type='submit'/></form>";
            HTMLTemplates.gradeInput(table, courseName);

        }//end makeTable
    }//end class

    class HTMLTemplates
    {
      public static void createTemplate()
        {
            Console.WriteLine("Content-Type:text/html");
            Console.WriteLine("");
            Console.WriteLine(@"<html><head><title>CourseGrades!</title></head>
<body>

    <h2>Course Info</h2>
    <form name='CourseInfo' id='CourseInfo' method='POST' action='CSC415Project1.exe' >


    Course Name: <input type='text' value='' name='CourseName' id='CourseName' >
    <br/>
    Number of Students: <input type='text' value='' name='NumStudents' id='NumStudents'>
    <br/>

    <input type='submit' >

    </form>
</body>

</html>");
        }
        public static void gradeInput(string table, string courseName)
        {
            string htmlForm = @"<html>

<body>
    <h2>Course Info<h2>
    <h3>" + courseName + @"
    </h3><p>Please enter in the students' names and grades.</p>
    <form name='grades' method='POST' action='ComputeGrade.exe'>" + table +
    @"</body></html>";

            Console.WriteLine("Content-Type:text/html\n\n");
            Console.WriteLine(htmlForm);
        }
    }
}

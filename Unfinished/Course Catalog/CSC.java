import java.util.ArrayList;


public class CSC {

	//Computer Science Department
	public CSC()
	{
		ArrayList<course> CSCDept = new ArrayList<course>();
				
		//Course Creation
		CSCDept.add(new course("CSC", "160", "Computer Science I", "3.5"));
		CSCDept.add(new course("CSC", "161", "Computer Science II", "3.5"));
		CSCDept.add(new course("CSC", "171", "Elements of Java I", "1.5"));
		CSCDept.add(new course("CSC", "172", "Elements of Java II", "1.5"));
		CSCDept.add(new course("CSC", "210", "Data Structures and Algoritms", "3.0"));
		CSCDept.add(new course("CSC", "215", "Introduction to Web Programming", "3.0"));//5
		CSCDept.add(new course("CSC", "220", "Computer Systems Comcepts", "3.0"));
		CSCDept.add(new course("CSC", "225", "Web and Animation Programming with Flash", "1.5"));
		CSCDept.add(new course("CSC", "230", "Discrete Structures I", "3.0"));
		CSCDept.add(new course("CSC", "231", "Discrete Structures II", "3.0"));
		CSCDept.add(new course("CSC", "242", "Introduction to Bioinformatics", "3.0"));//10
		
		CSCDept.add(new course("CSC", "297", "Internship", "0 - 4"));
		CSCDept.add(new course("CSC", "299", "Independant Study", "1 - 4"));
		
		CSCDept.add(new course("CSC", "306", "Software Development in C++", "3.0"));//20
		CSCDept.add(new course("CSC", "340", "Algorithms", "3.0"));
		CSCDept.add(new course("CSC", "355", "Windows and .NET Programming", "3.0"));

		CSCDept.add(new course("CSC", "397", "Internship", "0 - 4"));
		CSCDept.add(new course("CSC", "399", "Independant Study", "1 - 4"));
		
		CSCDept.add(new course("CSC", "415", "Web Applications", "3.0"));
		CSCDept.add(new course("CSC", "420", "Operating Systems I", "3.0"));
		CSCDept.add(new course("CSC", "425", "Computer Graphics", "3.0"));
		CSCDept.add(new course("CSC", "427", "Linux Programming", "3.0"));//35
		CSCDept.add(new course("CSC", "435", "Windows Game Programming", "3.0"));
		CSCDept.add(new course("CSC", "436", "Human Computer Interaction", "3.0"));
		CSCDept.add(new course("CSC", "453", "Systems Analysis", "3.0"));
		CSCDept.add(new course("CSC", "460", "Database Management Systems", "3.0"));
		CSCDept.add(new course("CSC", "464", "Data Mining", "3.0"));//40
		CSCDept.add(new course("CSC", "469", "Computer Networks", "3.0"));
		CSCDept.add(new course("CSC", "479", "computer and Network Security", "3.0"));
		CSCDept.add(new course("CSC", "487", "Parallel Processing and Concurrent Programming", "3.0"));
		CSCDept.add(new course("CSC", "490", "Special Topics", "3.0"));
		CSCDept.add(new course("CSC", "495", "Capstone Seminar", "3.0"));//45
		
		CSCDept.add(new course("CSC", "497", "Internship", "0 - 4"));
		CSCDept.add(new course("CSC", "499", "Independant Study", "1 - 4"));
		
		//Add to master list
		MasterFile.addList(CSCDept);
	}
}

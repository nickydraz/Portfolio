import java.util.ArrayList;

public class IFS {

	//Information Systems
	public IFS()
	{
		ArrayList<course> IFSDept = new ArrayList<course>();
		
		//Begin creating the courses
		IFSDept.add(new course("IFS", "103", "Data Analysis and Modeling Using Spreadsheets", "1.5"));
		IFSDept.add(new course("IFS", "106", "Information Management Using Databases", "1.5"));
		IFSDept.add(new course("IFS", "109", "Image Processing", "1.5"));
		IFSDept.add(new course("IFS", "115", "Web Page Development", "1.5"));
		IFSDept.add(new course("IFS", "116", "Website Design and Development", "1.5"));
		IFSDept.add(new course("IFS", "125", "Computer Animation with Flash", "1.5"));
		IFSDept.add(new course("IFS", "141", "Designing Graphics", "1.5"));
		IFSDept.add(new course("IFS", "220", "Video Editing and Production", "1.5"));
		IFSDept.add(new course("IFS", "230", "Digital Audio Editing and Production", "1.5"));
		IFSDept.add(new course("IFS", "297", "Internship", "0 - 4"));
		IFSDept.add(new course("IFS", "299", "Independent Study", "1 - 4"));
		IFSDept.add(new course("IFS", "320", "Multimedia Presentations", "1.5"));
		IFSDept.add(new course("IFS", "397", "Internship", "0 - 4"));
		IFSDept.add(new course("IFS", "399", "Independent Study", "1 - 4"));
		IFSDept.add(new course("IFS", "497", "Internship", "0 - 4"));
		IFSDept.add(new course("IFS", "499", "Independent Study", "1 - 4"));
	
		//Add to master list
		MasterFile.addList(IFSDept);
		
	}
	
}

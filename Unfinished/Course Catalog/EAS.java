
import java.util.ArrayList;

public class EAS {

	//East Asian Studies Deptartment
	public EAS()
	{

		//Create department list
		ArrayList<course> EASDept = new ArrayList<course>();
		
		EASDept.add(new course("EAS", "165", "Introduction to East Asia", "3.0"));
		EASDept.add(new course("EAS", "292", "Japanese Culture and Society", "3.0"));
		EASDept.add(new course("EAS", "397", "Internship", "0 - 9"));
		EASDept.add(new course("EAS", "399", "Independent Study", "1 - 9"));
		EASDept.add(new course("EAS", "497", "Internship", "0 - 9"));
		EASDept.add(new course("EAS", "499", "Independent Study", "1 - 9"));
	
		//Add to master list
		MasterFile.addList(EASDept);

	}
}


import java.util.ArrayList;

public class IMS {

	//Interactive Media Studies
	public IMS()
	{
		ArrayList<course> IMSDept = new ArrayList<course>();
		
		IMSDept.add(new course("IMS", "100", "Intoduction to Visual Literacy", "3.0"));
		IMSDept.add(new course("IMS", "113", "Multimedia Practicum", "0 - 1.5"));
		IMSDept.add(new course("IMS", "125", "Computer Animation with Flash", "1.5"));
		IMSDept.add(new course("IMS", "200", "Writing Across Media", "3.0"));
		IMSDept.add(new course("IMS", "215", "Introduction to Web Programming", "3.0"));
		IMSDept.add(new course("IMS", "220", "Video Editing and Production", "1.5"));
		IMSDept.add(new course("IMS", "230", "Digital Audio Editing and Production", "1.5"));
		IMSDept.add(new course("IMS", "260", "Introduction to New Media", "3.0"));
		
		//internships with varied credits
		IMSDept.add(new course("IMS", "297", "Internship", "0 - 3"));
		
		//Independent Studies with varied credits
		IMSDept.add(new course("IMS", "299", "Independent Study", "1 - 9"));
		
		IMSDept.add(new course("IMS", "320", "Motion Graphics", "3.0"));
		IMSDept.add(new course("IMS", "343", "Digital Illustration and Prepress Production", "3.0"));
		IMSDept.add(new course("IMS", "390", "Special Topics", "3.0"));
		

		//internships with varied credits
		IMSDept.add(new course("IMS", "397", "Internship", "0 - 3"));
		
		//Independent Studies with varied credits
		IMSDept.add(new course("IMS", "399", "Independent Study", "1 - 9"));
		
		IMSDept.add(new course("IMS", "490", "Portfolio Workshop", "3.0"));
		
		//internships with varied credits
		IMSDept.add(new course("IMS", "497", "Internship", "0 - 3"));
		
		//Independent Studies with varied credits
		IMSDept.add(new course("IMS", "499", "Independent Study", "1 - 9"));
		
		
		//Add to master list
		MasterFile.addList(IMSDept);
		
	}
}

import java.util.ArrayList;

public class ARL {

	//The Division of Arts and Letters Department
	public ARL()
	{
		
		//create department list
		ArrayList<course> ARLDept = new ArrayList<course>();
		
		//begin building
		//Internships
		ARLDept.add(new course("ARL", "297", "Internship", "0 - 9"));
		
		//Indepentdent Studies
		ARLDept.add(new course("ARL", "299", "Independent Study", "1 - 9"));
		
		ARLDept.add(new course("ARL", "385", "Symposium", "3.0"));
		
		//Internships
		ARLDept.add(new course("ARL", "397", "Internship", "0 - 9"));
		
		//Indepentdent Studies
		ARLDept.add(new course("ARL", "399", "Independent Study", "1 - 9"));
		
		ARLDept.add(new course("ARL", "485", "Symposium", "3.0"));
		
		//Internships
		ARLDept.add(new course("ARL", "497", "Internship", "0 - 9"));
		
		//Indepentdent Studies
		ARLDept.add(new course("ARL", "499", "Independent Study", "1 - 9"));
		
		//Add to master list
		MasterFile.addList(ARLDept);
		
	}
}

import java.util.ArrayList;

public class ECB {

	
	//Economics and Business Department
	public ECB()
	{
		
		//Create department list
		ArrayList<course> ECBDept = new ArrayList<course>();
		
		//Begin building the lsit
		ECBDept.add(new course("ECB", "110", "Business and Society", "3.0"));
		ECBDept.add(new course("ECB", "190", "Special Topics", "1.0 - 3.0"));
		ECBDept.add(new course("ECB", "200", "SIFE, Leadership Experiential", "1.50"));
		ECBDept.add(new course("ECB", "390", "Special Topics", "1.0 - 3.0"));
		ECBDept.add(new course("ECB", "490", "Special Topics", "1.0 - 3.0"));
		
		//Add to master list
		MasterFile.addList(ECBDept);
		
	}
}

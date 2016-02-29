
import java.util.ArrayList;

public class BCM {

	//Biochemistry
	public BCM()
	{
		
		//Create the list
		ArrayList<course> BCMDept = new ArrayList<course>();
		
		//Begin building
		BCMDept.add(new course("BCM", "140", "Nutrition", "3.5"));
		BCMDept.add(new course("BCM", "365", "Biochemistry", "3.75"));
		BCMDept.add(new course("BCM", "465", "Advanced Biochemistry", "3.75"));
		
		//Add to master list
		MasterFile.addList(BCMDept);
	}
}

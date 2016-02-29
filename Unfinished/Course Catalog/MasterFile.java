import java.util.ArrayList;


public class MasterFile {

	//Master Array List to hold all the departments
	static ArrayList<ArrayList<course>> MasterList = new ArrayList<ArrayList<course>>();
	
	//Initiate the departments to allow for traversing
	ACC acc = new ACC();
	ART art = new ART();
	ARL arl = new ARL();
	BCM bcm = new BCM();
	BIO bio = new BIO();
	CMC cmc = new CMC();
	CHM chm = new CHM();
	EAS eas = new EAS();
	ECB ecb = new ECB();
	ECN ecn = new ECN();
	ENG eng = new ENG();
	CSC csc = new CSC();
	IMS ims = new IMS();
	IFS ifs = new IFS();
	
	//Method to retrieve the master list
	public static ArrayList<ArrayList<course>> getMaster()
	{
		return MasterList;
	}
	
	//method to add a department to this list
	public static void addList(ArrayList<course> dept)
	{
		MasterList.add(dept);
	}
}

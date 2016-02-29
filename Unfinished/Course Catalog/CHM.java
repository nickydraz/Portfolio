import java.util.ArrayList;

public class CHM {

	//Chemistry Department
	public CHM()
	{
		
		//Create the department list
		ArrayList<course> CHMDept = new ArrayList<course>();
		
		CHMDept.add(new course("CHM", "100", "Chemistry Today", "3.50"));
		CHMDept.add(new course("CHM", "141", "General Chemistry I: Bio-organic Molecules", "3.75"));
		CHMDept.add(new course("CHM", "142", "General Chemistry II: Environmental Chemistry", "4.0"));
		CHMDept.add(new course("CHM", "190", "ACCA Seminar", "0.0"));
		CHMDept.add(new course("CHM", "205", "Descriptive Inorganic Chemistry", "4.0"));
		CHMDept.add(new course("CHM", "210", "Chemical Analysis", "3.75"));
		CHMDept.add(new course("CHM", "215", "Organic Chemistry I", "4.0"));
		CHMDept.add(new course("CHM", "216", "Organic Chemistry II", "4.0"));
		CHMDept.add(new course("CHM", "220", "Organic Chemistry I", "2.75"));
		CHMDept.add(new course("CHM", "221", "Organic Chemistry II", "2.75"));
		CHMDept.add(new course("CHM", "220", "Organic Chemistry III", "3.0"));
		CHMDept.add(new course("CHM", "272", "Spectral Interpretation I", "1.0"));
		CHMDept.add(new course("CHM", "273", "Spectral Interpretation II", "1.0"));
		CHMDept.add(new course("CHM", "299", "Independent Study", "1 - 9"));
		CHMDept.add(new course("CHM", "340", "Thermodynamics", "4.0"));
		CHMDept.add(new course("CHM", "341", "Kinetics, Quantum Theory, & Spectroscopy", "4.0"));
		CHMDept.add(new course("CHM", "397", "Internship", "0 - 9"));
		CHMDept.add(new course("CHM", "399", "Independent Study", "1 - 9"));
		CHMDept.add(new course("CHM", "405", "Advanced Inorganic Chemistry", "3.75"));
		CHMDept.add(new course("CHM", "410", "Instrumental Analysis", "4.0"));
		CHMDept.add(new course("CHM", "420", "Advanced Organic Chemistry", "3.75"));
		CHMDept.add(new course("CHM", "425", "Organometallic Chemistry", "3.75"));
		CHMDept.add(new course("CHM", "430", "Special Topics", "1 - 3.75"));
		CHMDept.add(new course("CHM", "475", "Seminar", "0 - 1"));
		CHMDept.add(new course("CHM", "480", "Research", "1 - 6"));
		CHMDept.add(new course("CHM", "497", "Internship", "0 - 9"));
		CHMDept.add(new course("CHM", "499", "Independent Study", "1 - 9"));
		
		//Add to master list
		MasterFile.addList(CHMDept);
		
	}
}

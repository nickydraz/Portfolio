import java.util.ArrayList;

public class ECN {

	//Economics Department
	public ECN()
	{
		
		//Create department list
		ArrayList<course> ECNDept = new ArrayList<course>();
		
		ECNDept.add(new course("ECN", "100", "Economics of Social Issues", "3.0"));
		ECNDept.add(new course("ECN", "210", "History of Economic Thought", "3.0"));
		ECNDept.add(new course("ECN", "241", "Business and Economic Statistics", "3.0"));
		ECNDept.add(new course("ECN", "250", "Microeconomic Principles", "3.0"));
		ECNDept.add(new course("ECN", "252", "Macroeconomic Principles", "3.0"));
		ECNDept.add(new course("ECN", "290", "Special Topics: Contemporary Issues in Economics", "1 - 3"));
		ECNDept.add(new course("ECN", "297", "Internship", "0 - 9"));
		ECNDept.add(new course("ECN", "299", "Independent Study", "1 - 9"));
		ECNDept.add(new course("ECN", "310", "Economics of Sports", "3.0"));
		ECNDept.add(new course("ECN", "320", "Industrial Structure and Public Policy", "3.0"));
		ECNDept.add(new course("ECN", "324", "International Political Economy", "3.0"));
		ECNDept.add(new course("ECN", "330", "Labor Economics", "3.0"));
		ECNDept.add(new course("ECN", "340", "International Economics", "3.0"));
		ECNDept.add(new course("ECN", "350", "Public Finance and Social Welfare", "3.0"));
		ECNDept.add(new course("ECN", "360", "Money and Banking", "3.0"));
		ECNDept.add(new course("ECN", "390", "Special Topics: Contemporary Issues in Economics", "3.0"));
		ECNDept.add(new course("ECN", "397", "Internship", "0 - 9"));
		ECNDept.add(new course("ECN", "399", "Independent Study", "1 - 9"));
		ECNDept.add(new course("ECN", "423", "Intermediate Microeconomics", "3.0"));
		ECNDept.add(new course("ECN", "427", "Intermediate Macroeconomics", "3.0"));
		ECNDept.add(new course("ECN", "440", "Mathematical Economics", "3.0"));
		ECNDept.add(new course("ECN", "445", "Econometrics", "3.0"));
		ECNDept.add(new course("ECN", "497", "Internship", "0 - 9"));
		ECNDept.add(new course("ECN", "499", "Independent Study", "1 - 9"));
		
		//Add to the master list
		MasterFile.addList(ECNDept);
	
	}//end constructor
}//end class

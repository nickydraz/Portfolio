import java.util.ArrayList;

public class ACC {

	//Accounting
	public ACC()
	{
		
		//Create the list
		ArrayList<course> ACCDept = new ArrayList<course>();
		
		//begin adding courses
		ACCDept.add(new course("ACC", "190", "Topics in Accounting - 1 Credit", "1.0"));
		ACCDept.add(new course("ACC", "190", "Topics in Accounting - 2 Credits", "2.0"));
		ACCDept.add(new course("ACC", "190", "Topics in Accounting - 3 Credits", "3.0"));
		ACCDept.add(new course("ACC", "201", "Accounting Principles I/Financial", "3.0"));
		ACCDept.add(new course("ACC", "202", "Accounting Principles II/Managerial", "3.0"));
		ACCDept.add(new course("ACC", "290", "Topics in Accounting - 1 Credit", "1.0"));
		ACCDept.add(new course("ACC", "290", "Topics in Accounting - 2 Credits", "2.0"));
		ACCDept.add(new course("ACC", "290", "Topics in Accounting - 3 Credits", "3.0"));
		
		//Internships with varying credits
		ACCDept.add(new course("ACC", "297", "Internship", "0 - 9"));
		
		//Independent Studies with varying credits
		ACCDept.add(new course("ACC", "299", "Independent Study", "1 - 9"));
		
		ACCDept.add(new course("ACC", "307", "Cost Accounting", "2.0"));
		ACCDept.add(new course("ACC", "310", "Accounting Information Systems", "3.0"));
		ACCDept.add(new course("ACC", "317", "Intermediate Accounting I", "3.0"));
		ACCDept.add(new course("ACC", "318", "Intermediate Accounting II", "3.0"));
		ACCDept.add(new course("ACC", "319", "Intermediate Accounting III", "3.0"));
		
		//Internships with varying credits
		ACCDept.add(new course("ACC", "397", "Internship", "0 - 9"));
		
		//Independent Studies with varying credits
		ACCDept.add(new course("ACC", "399", "Independent Study", "1 - 9"));
		
		ACCDept.add(new course("ACC", "430", "Taxes I", "3.0"));
		ACCDept.add(new course("ACC", "431", "Tazes II", "3.0"));
		ACCDept.add(new course("ACC", "440", "Advanced Accounting", "3.0"));
		ACCDept.add(new course("ACC", "460", "Accounting For Not-For-Profits", "3.0"));
		ACCDept.add(new course("ACC", "470", "Auditing and Attestation", "3.0"));
		ACCDept.add(new course("ACC", "480", "Seminar: Managerial Accounting", "3.0"));
		ACCDept.add(new course("ACC", "490", "Seminar: Financial Accounting", "3.0"));
		
		//Internships with varying credits
		ACCDept.add(new course("ACC", "497", "Internship", "0 - 9"));
		
		//Independent Studies with varying credits
		ACCDept.add(new course("ACC", "499", "Independent Study", "1 - 9"));
		
		//Add to master list
		MasterFile.addList(ACCDept);
	}
}

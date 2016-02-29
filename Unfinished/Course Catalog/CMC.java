import java.util.ArrayList;

public class CMC {

	//Chemical Microscopy
	public CMC()
	{
		//Create the list
		ArrayList<course> CMCDept = new ArrayList<course>();
		
		//Begin building
		CMCDept.add(new course("CMC", "400", "Modern Polarized Light Microscopy", "3.0"));
		CMCDept.add(new course("CMC", "405", "Techniques of Optical Crystallography", "3.0"));
		CMCDept.add(new course("CMC", "410", "Scanning Electron Microscopy", "3.0"));
		CMCDept.add(new course("CMC", "411", "Advanced Imaging Techniques for Scanning Electron Microscopy", "2.0"));
		CMCDept.add(new course("CMC", "415", "Pharmaceutical Materials and Contaminants", "3.0"));
		CMCDept.add(new course("CMC", "416", "USP {768} Particulate Matter in Injections", "0.5"));
		CMCDept.add(new course("CMC", "417", "Freeze-Dry Microscopy", "2.0"));
		CMCDept.add(new course("CMC", "420", "Transmission Electron Microscopy", "2.0"));
		CMCDept.add(new course("CMC", "430", "Microscopic Particle Handling: Particle Isolation, Maniipulation and Mounting", "3.0"));
		CMCDept.add(new course("CMC", "431", "Sample Preparation: Pharmaceutical and Medical Devices", "1.5"));
		CMCDept.add(new course("CMC", "432", "Sample Preparation: Sperm Isolation", "0.5"));
		CMCDept.add(new course("CMC", "433", "Sample PreparationL Polymers, Paints and Coatings", "1.5"));
		CMCDept.add(new course("CMC", "434", "Sample Preparation: Forensics and Trace Evidence", "1.5"));
		CMCDept.add(new course("CMC", "435", "Forensic Soil Examination I", "3.0"));
		CMCDept.add(new course("CMC", "440", "White Powder Unknowns I", "3.0"));
		CMCDept.add(new course("CMC", "450", "Forensic Trace Evidence Particles", "3.0"));
		CMCDept.add(new course("CMC", "451", "An Introduction to Forensic Document Examination: Basic Theory and Practical Applications", "1.5"));
		CMCDept.add(new course("CMC", "452", "Fundamentals of Microscopy for Forensic Document Examiners: Basic Theory and Practical Applications", "1.5"));
		CMCDept.add(new course("CMC", "455", "Pigments Identifcation", "3.0"));
		CMCDept.add(new course("CMC", "456", "Conversation Materials", "3.0"));
		CMCDept.add(new course("CMC", "460", "Infrared Microscopy", "2.0"));
		CMCDept.add(new course("CMC", "470", "Raman Microscopy", "2.0"));
		CMCDept.add(new course("CMC", "490", "Fiber Identifcation", "3.0"));
		CMCDept.add(new course("CMC", "495", "Forensic Hair Comparison", "3.0"));
		
		//Add to master list
		MasterFile.addList(CMCDept);
		
		
	}
}

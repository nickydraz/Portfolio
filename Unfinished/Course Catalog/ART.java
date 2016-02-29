import java.util.ArrayList;

public class ART {

	//Art Department
	public ART()
	{
		//Create department list
		ArrayList<course> ARTDept = new ArrayList<course>();
		
		//Building it
		ARTDept.add(new course("ART", "100", "Introduction to Visual Literacy", "3.0"));
		ARTDept.add(new course("ART", "105", "Art Through Photography", "2.0"));
		ARTDept.add(new course("ART", "107", "2-Dimensional Design", "3.0"));
		ARTDept.add(new course("ART", "109", "Image Processing", "1.5"));
		ARTDept.add(new course("ART", "110", "3-Dimensional Design", "3.0"));
		ARTDept.add(new course("ART", "117", "Silver Photography I", "3.0"));
		ARTDept.add(new course("ART", "120", "Drawing I", "3.0"));
		ARTDept.add(new course("ART", "130", "Painting I", "3.0"));
		ARTDept.add(new course("ART", "135", "Watercolor Painting", "3.0"));
		ARTDept.add(new course("ART", "140", "Oriental Brush Painting", "3.0"));
		ARTDept.add(new course("ART", "141", "Designing Graphics", "1.5"));
		ARTDept.add(new course("ART", "143", "Beginning Typography", "3.0"));
		ARTDept.add(new course("ART", "150", "Ceramics I", "3.0"));
		ARTDept.add(new course("ART", "205", "Digital Photography", "3.0"));
		ARTDept.add(new course("ART", "215", "Sculpture", "3.0"));
		ARTDept.add(new course("ART", "220", "Drawing II", "3.0"));
		ARTDept.add(new course("ART", "225", "Figure Drawing I", "3.0"));
		ARTDept.add(new course("ART", "230", "Painting II", "3.0"));
		ARTDept.add(new course("ART", "245", "Art and Culture", "1.5"));
		ARTDept.add(new course("ART", "250", "Ceramics II", "3.0"));
		ARTDept.add(new course("ART", "261", "Chicago Art and Architecture", "3.0"));
		ARTDept.add(new course("ART", "264", "Global Art Survey", "3.0"));
		ARTDept.add(new course("ART", "270", "Asethetics", "3.0"));
		ARTDept.add(new course("ART", "272", "Art History I: Prehistoric to Medieval Art", "3.0"));
		ARTDept.add(new course("ART", "274", "Art History II: Renaissance to Rococo Art", "3.0"));
		ARTDept.add(new course("ART", "276", "Art History III: Late 18th Century to Contemporary Art", "3.0"));
		ARTDept.add(new course("ART", "280", "Printmaking I", "3.0"));
		
		//Internships
		ARTDept.add(new course("ART", "297", "Internship", "0 - 9"));
		
		//Indepentdent Studies
		ARTDept.add(new course("ART", "299", "Independent Study", "1 - 9"));
		
		ARTDept.add(new course("ART", "315", "Sculpture II", "3.0"));
		ARTDept.add(new course("ART", "325", "Figure Drawing II", "3.0"));
		ARTDept.add(new course("ART", "340", "Avanced Painting I", "3.0"));
		ARTDept.add(new course("ART", "341", "Advanced Painting II", "3.0"));
		ARTDept.add(new course("ART", "343", "Digital Illustration and Prepress Production", "3.0"));
		ARTDept.add(new course("ART", "344", "2-Dimensional Computer Graphics and Animation", "1.5"));
		ARTDept.add(new course("ART", "345", "3-Dimensional Computer Graphics and Animation", "1.5"));
		ARTDept.add(new course("ART", "350", "Advanced Studies I", "3.0"));
		ARTDept.add(new course("ART", "351", "Advanced Studies II", "3.0"));
		ARTDept.add(new course("ART", "355", "Ceramics III", "3.0"));
		ARTDept.add(new course("ART", "360", "Art Theory and Criticism", "3.0"));
		ARTDept.add(new course("ART", "370", "Art of the Nineteenth Century", "3.0"));
		ARTDept.add(new course("ART", "371", "African-American Art", "3.0"));
		ARTDept.add(new course("ART", "374", "Art of the Twentieth Century: 1900-1950", "3.0"));
		ARTDept.add(new course("ART", "376", "Art of the Twentieth Century: 1950-Contemporary", "3.0"));
		ARTDept.add(new course("ART", "380", "Printmaking II", "3.0"));
		
		//Internships
		ARTDept.add(new course("ART", "397", "Internship", "0 - 9"));
		
		//Indepentdent Studies
		ARTDept.add(new course("ART", "399", "Independent Study", "1 - 9"));
		
		ARTDept.add(new course("ART", "442", "Advanced Painting III", "3.0"));
		ARTDept.add(new course("ART", "452", "Advanced Studies III", "3.0"));
		ARTDept.add(new course("ART", "455", "Ceramics IV", "3.0"));
		ARTDept.add(new course("ART", "460", "Studio Seminar", "3.0"));
		ARTDept.add(new course("ART", "470", "Art History Seminar", "3.0"));
		
		//Internships
		ARTDept.add(new course("ART", "497", "Internship", "0 - 9"));
		
		//Indepentdent Studies
		ARTDept.add(new course("ART", "499", "Independent Study", "1 - 9"));
		
		//Add to the master list
		MasterFile.addList(ARTDept);
	}
}

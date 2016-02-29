import java.util.ArrayList;


public class ENG {
	
	//English Studies Department	
	public ENG()
	{
			ArrayList<course> ENGDept = new ArrayList<course>();

			//Course creation
			ENGDept.add(new course("ENG", "101", "English as a Second Language I", "3.0"));//0
			ENGDept.add(new course("ENG", "103", "English as a Second Language II", "3.0"));//1
			ENGDept.add(new course("ENG", "105", "English as a Second Language III", "1 - 3"));
			ENGDept.add(new course("ENG", "115", "First-Year Writing", "3.0"));//4
			ENGDept.add(new course("ENG", "125", "First-Year Seminar", "3.0"));//5
			ENGDept.add(new course("ENG", "128", "College Humor Magazine Practicum","0 - 1.5"));
			ENGDept.add(new course("ENG", "130", "College Literary Magazine Practicum", "0 - 1.5"));
			ENGDept.add(new course("ENG", "132", "College Newspaper Practicum", "0 - 1.5"));
			ENGDept.add(new course("ENG", "196", "Reading Literature", "3.0"));//15
			ENGDept.add(new course("ENG", "200", "Writing Across Media", "3.0"));
			ENGDept.add(new course("ENG", "203", "English Literature to 1660", "3.0"));
			ENGDept.add(new course("ENG", "205", "Eighteenth-Century Literature", "3.0"));
			ENGDept.add(new course("ENG", "207", "Nineteenth- and Twentieth-Century American Literature", "3.0"));
			ENGDept.add(new course("ENG", "209", "Ninteenth- and Twentieth-Century British Literature", "3.0"));
			ENGDept.add(new course("ENG", "214", "Children's Literature", "2.0"));
			ENGDept.add(new course("ENG", "216", "Adolescent Literature", "2.0"));
			ENGDept.add(new course("ENG", "220", "News Writing", "3.0"));
			ENGDept.add(new course("ENG", "240", "Introduction to Film", "3.0"));
			ENGDept.add(new course("ENG", "245", "Literature, Science, and Culture", "3.0"));
			ENGDept.add(new course("ENG", "250", "News Editing", "3.0"));
			ENGDept.add(new course("ENG", "260", "Integrating Word and Image", "3.0"));
			ENGDept.add(new course("ENG", "265", "Style", "3.0"));
			ENGDept.add(new course("ENG", "270", "Writing, Rhetoric, and Culture", "3.0"));
			ENGDept.add(new course("ENG", "275", "Creative Writing", "3.0"));
			ENGDept.add(new course("ENG", "280", "Introduction to Women's Literature", "3.0"));
			ENGDept.add(new course("ENG", "285", "Writing Theories and Practices", "3.0"));
			
			ENGDept.add(new course("ENG", "297", "Internship", "0 - 9"));
			ENGDept.add(new course("ENG", "299", "Independent Study", "1 - 9"));
			
			ENGDept.add(new course("ENG", "301", "Studies in American Literature", "3.0"));
			ENGDept.add(new course("ENG", "303", "Studies in British Literature", "3.0"));
			ENGDept.add(new course("ENG", "305", "Studies in Contemporary Literature", "3.0"));
			ENGDept.add(new course("ENG", "307", "Studies in Literature of Cultural Identity", "3.0"));//55
			ENGDept.add(new course("ENG", "315", "Advanced Writing", "3.0"));
			ENGDept.add(new course("ENG", "325", "News Reporting", "3.0"));
			ENGDept.add(new course("ENG", "330", "Multicultural Literature of North America", "3.0"));
			ENGDept.add(new course("ENG", "332", "Magazine Writing", "3.0"));
			ENGDept.add(new course("ENG", "340", "Global Films", "3.0"));//60
			ENGDept.add(new course("ENG", "350", "In Theory", "3.0"));
			ENGDept.add(new course("ENG", "365", "Writing Creative Nonfiction", "3.0"));
			ENGDept.add(new course("ENG", "370", "Language and Linguistics", "3.0"));
			ENGDept.add(new course("ENG", "375", "Writing Fiction", "3.0"));
			ENGDept.add(new course("ENG", "377", "Writing Poetry", "3.0"));//65
			ENGDept.add(new course("ENG", "380", "Global Literature", "3.0"));
			ENGDept.add(new course("ENG", "390", "Sacred Texts as Literature", "3.0"));
			ENGDept.add(new course("ENG", "397", "Internship", "0 - 9"));
			ENGDept.add(new course("ENG", "399", "Independent Study", "1 - 9"));
			ENGDept.add(new course("ENG", "401", "Seminar in Drama", "3.0"));
			ENGDept.add(new course("ENG", "403", "Seminar in Fiction", "3.0"));
			ENGDept.add(new course("ENG", "405", "Seminar in Poetry", "3.0"));
			ENGDept.add(new course("ENG", "407", "Seminar in Selected Authors", "3.0"));//90
			ENGDept.add(new course("ENG", "409", "Seminar in Theory", "3.0"));
			ENGDept.add(new course("ENG", "455", "Writing in Technical & Professional Settings", "3.0"));
			ENGDept.add(new course("ENG", "460", "Seminar in Special Topics", "3.0"));
			ENGDept.add(new course("ENG", "462", "Writing for Social Change", "3.0"));
			ENGDept.add(new course("ENG", "465", "Advanced Creative Nonficiton --Multimedia", "3.0"));//95
			ENGDept.add(new course("ENG", "475", "Advanced Workshop in Creative Writing", "3.0"));
			ENGDept.add(new course("ENG", "480", "Senior Portfolio", "1.0"));
			ENGDept.add(new course("ENG", "497", "Internship", "0 - 9"));
			ENGDept.add(new course("ENG", "499", "Independent Study", "1 - 9"));
			
			
			//add ENG department to the masterList
			MasterFile.addList(ENGDept);
			//end ENG

}
}
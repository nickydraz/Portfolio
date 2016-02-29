
import java.util.ArrayList;

public class BIO {

	public BIO()
	{
		
		//Create list
		ArrayList<course> BIODept = new ArrayList<course>();
		
		//Begin building
		BIODept.add(new course("BIO", "100", "Principles of Biology", "3.50"));
		BIODept.add(new course("BIO", "104", "Human Biology", "3.50"));
		BIODept.add(new course("BIO", "106", "Introduction to Environmental Science", "3.50"));
		BIODept.add(new course("BIO", "108", "Water, Food, and Sex", "3.50"));
		BIODept.add(new course("BIO", "109", "Genes, Genomes, and Genetics", "3.50"));
		BIODept.add(new course("BIO", "120", "Topics in Modern Biology", "3 - 3.50"));
		BIODept.add(new course("BIO", "147", "Anatomy and Physiology", "3.75"));
		BIODept.add(new course("BIO", "151", "Biological Investigations I", "4.0"));
		BIODept.add(new course("BIO", "152", "Biological Invesetigations II", "4.0"));
		BIODept.add(new course("BIO", "200", "Cellular Biology", "3.75"));
		BIODept.add(new course("BIO", "201", "Botany", "3.75"));
		BIODept.add(new course("BIO", "202", "Zoology", "3.75"));
		BIODept.add(new course("BIO", "216", "Ecology - How Organisms Interact with their Environment", "3.75"));
		BIODept.add(new course("BIO", "222", "Estuarine Ecology", "2.0"));
		BIODept.add(new course("BIO", "228", "Desert Ecology", "2.0"));
		BIODept.add(new course("BIO", "242", "Introduction to Bioinformatics", "3.0"));
		BIODept.add(new course("BIO", "260", "Genetics", "3.75"));
		BIODept.add(new course("BIO", "290", "ACCA Seminar in Organismal Biology and Ecology", "0.0"));
		BIODept.add(new course("BIO", "291", "ACCA Seminar in Molecular and Cellular Biology", "0.0"));
		
		//Internships
		BIODept.add(new course("BIO", "297", "Internship", "0 - 9"));
		//Independent Study
		BIODept.add(new course("BIO", "299", "Independent Study", "1 - 9"));
		
		BIODept.add(new course("BIO", "300", "Human Sexuality: A Clash of Values", "3.0"));
		BIODept.add(new course("BIO", "301", "Plant Physiology", "3.75"));
		BIODept.add(new course("BIO", "302", "Animal Physiology", "3.75"));
		BIODept.add(new course("BIO", "305", "Animal Behavior", "4.0"));
		BIODept.add(new course("BIO", "340", "Microbiology", "4.0"));
		BIODept.add(new course("BIO", "360", "The Molecular Biology of Cancer", "3.75"));
		BIODept.add(new course("BIO", "395", "Directed Research", "0.0"));
		
		//Internship
		BIODept.add(new course("BIO", "397", "Internship", "0 - 9"));
		//Independent Study
		BIODept.add(new course("BIO", "399", "Independent Study", "1 - 9"));
		
		BIODept.add(new course("BIO", "400", "Evolution", "3.50"));
		BIODept.add(new course("BIO", "410", "Microscopic Anatomy", "3.75"));
		BIODept.add(new course("BIO", "416", "Environmental Biology", "3.75"));
		BIODept.add(new course("BIO", "430", "Developmental Biology", "3.75"));
		BIODept.add(new course("BIO", "440", "Virology and Immunology", "3.0"));
		BIODept.add(new course("BIO", "475", "Seminar", "0 - 1"));
		
		//Independent Study
		BIODept.add(new course("BIO", "499", "Independent Study", "1 - 9"));
		
		//Add to master list
		MasterFile.addList(BIODept);
	}
}

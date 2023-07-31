package edu.kh.collection.practice.run;

import edu.kh.collection.practice.model.service.StudentService;

public class StudentRun {
	
	public static void main(String[] args) {
		
		StudentService service = new StudentService();
		
		service.displayMenu();
	}

}

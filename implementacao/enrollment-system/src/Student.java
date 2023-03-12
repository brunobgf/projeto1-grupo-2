import java.io.*;
import java.util.*;

public class Student extends User {

	Student(String [] dados, List <Course> courses){
		super(dados, courses);
		setAccessLevel(2);
	}

	private static int MAX_REQ_SUBJECTS = 4;
	private static int MAX_OPT_SUBJECTS = 2;
	private int enrolledOptionalSubjects;
	private int enrolledRequiredSubjects;

	public void newSubject(String strNomeCurso, Subject subject) {
		
	}

	public void requestSubjectEnrollmentCancelation() {
		
	}

	private void verifyRequiredSubjects() {
		
	}

	private void verifyOptionalSubjects() {
		
	}

}

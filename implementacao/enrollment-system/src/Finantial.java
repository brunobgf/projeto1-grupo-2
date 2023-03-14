import java.io.*;
import java.util.*;

public class Finantial {

	private static double TAX_ENROLL = 1000.00;
	private static double TAX_PER_SUBJECT = 100.00;

	public void generateBilling(Student student) {
		
		int countStudentCourses = (int) student.getCourse().stream()
									.map(c->c.getSubjectsList())
									.count();

		double subjectsSum = TAX_PER_SUBJECT * countStudentCourses;
		double total = TAX_ENROLL + subjectsSum;

		// StringBuilder bill = new StringBuilder("\n Discipline list:");

		System.out.println("\n Subject list:");

		student.getCourse().stream()
		.map(c->c.getSubjectsList())				
		.forEach(s -> System.out.println(String.format("\n %s", ((Course) s).getName())));

		System.out.println(String.format("\n Enrollment fix tax: %.2f", TAX_ENROLL));
		System.out.println(String.format("\n Enrollment fix tax: %.2f", subjectsSum));
		System.out.println(String.format("\n TOTAL: %.2f", total));

	}

}

import java.util.*;

public class Secretary {

	private Finantial finantial;

	public void notifyFinantial(Student student) {
		finantial.generateBilling(student);

		System.out.println("Finantial system was warned about " + student.getUser() + " enrollment.");
	}
}

import java.util.*;

public class Secretary {

	private Date cancelationDateLimit;

	private Finantial finantial;

	public void notifyFinantial(Student student) {
		finantial.generateBilling(student);

		System.out.println("Finantial system was warned about " + student.getUser() + " enrollment.");
	}
}

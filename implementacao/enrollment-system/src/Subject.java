public class Subject {

	private Boolean required;
	private Boolean active;
	private int studentEnrollmentQtd;
	private static int MAX_STUDENT = 60;
	private static int MIN_STUDENTS = 3;
	private String name;

	Subject(String[] dados){
		name = dados[0];
		active = dados[1].equals("A");
		required = dados[2].equals("O");
		studentEnrollmentQtd = Integer.parseInt(dados[3]);
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public int getStudentEnrollmentQtd() {
		return studentEnrollmentQtd;
	}

	public void setStudentEnrollmentQtd(int studentEnrollmentQtd) {
		this.studentEnrollmentQtd = studentEnrollmentQtd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void verifyEnrollPeriod() {
		
	}

	public boolean activateDeactivate() {
		return false;
	}

	private void cancelSubject() {
		
	}

	private void closeSubject() {
		
	}

}

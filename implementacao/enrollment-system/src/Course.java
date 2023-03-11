import java.util.*;

public class Course {

	private List<Subject> subjectsList;
	private String name;

	Course(String name, List<Subject> subjects){
		subjectsList = subjects;
		this.name = name;
	}

	public List<Subject> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subject> subjectsList) {
		this.subjectsList = subjectsList;
	}

	public void newSubject(String strDados) {
		
	}

	public void newSubject(Subject newSubject) {
		subjectsList.add(newSubject);
	}	

	public void addSubject(String strName) {
		
	}

	public Subject subjectAvailable(String subjectName) {
		return null;
	}

	public Subject getSubject(String subjectName) {
		return subjectsList.stream()
						   .filter(c -> c.getName().equals(subjectName))
						   .findFirst().get();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){

		String listSubjects = "";

		for (int i = 0; i < subjectsList.size(); i++){
			listSubjects = listSubjects + i+1 + ". " + subjectsList.get(i).getName();
		}

		return listSubjects;
	}

}

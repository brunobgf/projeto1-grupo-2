import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class University {
	// #region - ATTRIBUTES
	private static LinkedList<Course> courses = new LinkedList<Course>();
	private static LinkedList<Subject> subjectList = new LinkedList<Subject>();
	private static LinkedList<User> users = new LinkedList<User>();
	private static Scanner keyboard = new Scanner(System.in, "UTF-8");
	// #endregion

	// #region UTILS
	public static int getKeyAccess(String user, String password) {
		return users.stream()
					.filter(c -> c.getUser().equals(user))
					.filter(c -> c.getPassword().equals(password))
					.findFirst().get().getKeyAccess();
	}

	static User getUser(int keyAccess) {
		return users.stream()
				.filter(c -> c.getKeyAccess() == keyAccess)
				.findFirst().get();
	}

	// #endregion

	// #region - ACTIONS
	static void addStudent() {
		String[] dados = new String[2];
		LinkedList<Course> listCourses = new LinkedList<Course>();

		System.out.println("Digite o nome do aluno");
		dados[0] = keyboard.next();

		System.out.println("Digite a senha de acesso do aluno");
		dados[1] = keyboard.next();

		System.out.println("Digite o nome do curso em que o estudade está matriculado");
		String nameCourse = keyboard.next();

		int opc = -1;
		LinkedList<Subject> subjects = new LinkedList<Subject>();

		while (opc != 0) {
			System.out.println("Escolha a disciplina. Para parar, digite 0");
			getCourse(nameCourse).toString();
			opc = keyboard.nextInt();

			if (opc != 0)
				subjects.add(getCourse(nameCourse).getSubjectsList().get(opc - 1));
		}

		listCourses.add(new Course(nameCourse, subjects));
		User newStudent = new Student(dados, listCourses);
		users.add(newStudent);
	}

	private static void addProfessor() {

	}

	private static void addSubject() {

	}

	private static void enrollStudent(int key) {
		System.out.println("Digite o curso");
		String course = keyboard.next();

		System.out.println("Digite a disciplina");
		String subject = keyboard.next();

		Subject newSubject;

		newSubject = getUser(key).getCourse(course).subjectAvailable(subject);

		if (newSubject != null)
			getUser(key).getCourse(course).newSubject(newSubject);
	}

	static void getStudents() {
		System.out.println("Digite o curso");
		String course = keyboard.next();

		System.out.println("Digite a disciplina");
		String subject = keyboard.nextLine();

		users.stream()
				.filter(c -> c.getAccessLevel() == 2)
				.filter(c -> c.getCourse(course) != null)
				.filter(c -> c.getCourse(course).subjectAvailable(subject) != null)
				.forEach(System.out::println);
	}
	// #endregion

	// #region - FILE READERS

	public static void loadCourseData(String coursePath) {
		try {
			Path path = Paths.get(coursePath.concat("/Course.txt"));
			Scanner sc = new Scanner(path, "UTF-8");
			while (sc.hasNextLine()) {
				String row = sc.nextLine();

				addCourseToList(row);
			}
			sc.close();
		} catch (IOException io) {
			System.out.println("Error at opening the file");
		}
	}

	public static void addCourseToList(String row) {
		String[] rowSplit = row.split(";");
		String courseName = rowSplit[0];

		LinkedList<Subject> subjectsFromCourse = new LinkedList<Subject>();

		for (int i = 1; i < rowSplit.length; i++) {
			if (rowSplit[i].equals(getSubject(rowSplit[i]).getName())) {
				subjectsFromCourse.add(getSubject(rowSplit[i]));
			}
		}

		Course course = new Course(courseName, subjectsFromCourse);

		courses.add(course);
	}

	public static Subject getSubject(String subjectName) {

		return subjectList.stream()
				.filter(c -> c.getName().equals(subjectName))
				.findFirst().get();

	}

	public static void loadSubjectData(String subjectPath) {
		try {
			Path path = Paths.get(subjectPath.concat("/Subject.txt"));
			Scanner sc = new Scanner(path, "UTF-8");
			while (sc.hasNextLine()) {
				String row = sc.nextLine();

				addSubjectToList(row);
			}
			sc.close();
		} catch (IOException io) {
			System.out.println("Error at opening the file");
		}
	}

	public static void addSubjectToList(String row) {
		String[] rowSplit = row.split(";");
		Subject subject = new Subject(rowSplit);

		subjectList.add(subject);

	}

	public static void loadProfessorData(String professorPath) {
		try {
			Path path = Paths.get(professorPath.concat("/Professor.txt"));
			Scanner sc = new Scanner(path, "UTF-8");
			while (sc.hasNextLine()) {
				String row = sc.nextLine();

				loadProfessor(row);
			}
			sc.close();
		} catch (IOException io) {
			System.out.println("Error at opening the file");
		}
	}

	public static Course getCourse(String name) {
		return courses.stream()
				.filter(c -> c.getName().equals(name))
				.findFirst().get();
	}

	public static void loadProfessor(String dados) {
		String[] professor = dados.split(";");
		String[] courses = professor[5].split(",");
		User newProfessor = new Professor(professor, loadActiveCourses(courses));
		users.add(newProfessor);
	}

	private static LinkedList<Course> loadActiveCourses(String[] courses) {
		LinkedList<Subject> listSubjects = new LinkedList<Subject>();
		LinkedList<Course> listCourses = new LinkedList<Course>();

		for (int i = 0; i < courses.length; i++) {
			String[] vetAux = courses[i].split("=");
			String nameCourse = vetAux[0];
			String[] subjects = vetAux[1].split("/");
			for (int y = 0; y < subjects.length; y++) {
				Subject subject = getCourse(nameCourse).getSubject(subjects[y]);
				listSubjects.add(subject);
			}

			listCourses.add(new Course(nameCourse, listSubjects));
		}

		return listCourses;
	}

	public static void loadStudentData(String professorPath) {
		try {
			Path path = Paths.get(professorPath.concat("/Student.txt"));
			Scanner sc = new Scanner(path, "UTF-8");
			while (sc.hasNextLine()) {
				String row = sc.nextLine();
				loadStudent(row);
			}
			sc.close();
		} catch (IOException io) {
			System.out.println("Error at opening the file");
		}
	}

	public static void loadStudent(String dados) {
		String[] student = dados.split(";");
		String[] courses = student[5].substring(2, student[5].length()-1).split("~"); 

		User newStudent = new Student(student, loadActiveCourses(courses));
		users.add(newStudent);
	}


	// #endregion

}

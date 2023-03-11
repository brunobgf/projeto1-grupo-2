import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class University {

	private static LinkedList<Course> courses = new LinkedList<Course>();
	private static LinkedList<User> users = new LinkedList<User>();
	private static Secretary secretary;
	private static int keyAccess;
	private static Scanner teclado = new Scanner(System.in, "UTF-8");

	public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in, "UTF-8");

		//TESTE PQ NAO TEM DADOS INSERIDOS AINDA
		LinkedList<Subject> listTest = new LinkedList<Subject>();
		LinkedList<Subject> listTest2 = new LinkedList<Subject>();

		String[] vetTest = {"Calculo", "A", "O", "20"};
		Subject newSubject = new Subject(vetTest);
		listTest.add(newSubject);
		Course test = new Course("Engenharia de Software", listTest);
		courses.add(test);

		
		String[] vetTest2 = {"Estatistica", "A", "O", "20"};
		Subject newSubject2 = new Subject(vetTest2);
		listTest2.add(newSubject2);
		Course test2 = new Course("Administracao", listTest2);
		courses.add(test2);
        //FIM TESTE
		
		loadProfessorData("C:/Users/Cliente Vip Infomac/Desktop/PUC/LABPS/projeto1-grupo-2/implementacao/enrollment-system/src");
		login();
		userMenu();
		teclado.close();
    }

	public static void login() {
		System.out.println("Digite seu usuário");
		String user = teclado.next();	
		System.out.println("Digite sua senha");
		String password = teclado.next();

		keyAccess = users.stream()
                    	 .filter(c -> c.getUser().equals(user))
                         .filter(c -> c.getPassword().equals(password))
                         .findFirst().get().getKeyAccess();

		if (keyAccess == 0)
		   System.out.println("Usuário não encontrado!");
	}

	public static void userMenu() {
		switch (getUser(keyAccess).getAccessLeve()){
	       case 1: optionsAccessLevelOne();
		   		   break;
	       case 2: optionsAccessLevelTwo();
		  		   break;
		   default: optionsAccessLevelAdm();
		}
		
	}

	private static User getUser(int keyAccess){
		return users.stream()
                    .filter(c -> c.getKeyAccess() == keyAccess)
					.findFirst().get();
	}

	private static void optionsAccessLevelTwo() {
		
	}

	private static void optionsAccessLevelOne() {
		System.out.println("\nOlá " + getUser(keyAccess).getUser() + ".\nO que deseja fazer?\n");
		System.out.println("01. Visualizar lista de alunos");
		int opc = teclado.nextInt();

		switch (opc){
			case 1: getStudents();
					break;
		} 
	}

	private static void optionsAccessLevelAdm() {
		System.out.println("Incluir novo estudante");
		int opc = teclado.nextInt();

		switch (opc){
			case 1: addStudent();
					break;
		} 
		 
	}

	private static void addStudent() {		
		String[] dados = new String[2];
		LinkedList<Course> listCourses = new LinkedList<Course>();

		System.out.println("Digite o nome do aluno");
		dados[0] = teclado.next();

		System.out.println("Digite a senha de acesso do aluno");
		dados[1] = teclado.next();

		System.out.println("Digite o nome do curso em que o estudade está matriculado");
		String nameCourse= teclado.next();

		int opc = -1;
		LinkedList<Subject> subjects = new LinkedList<Subject>();

		while (opc != 0){
			System.out.println("Escolha a disciplina. Para parar, digite 0");
			getCourse(nameCourse).toString();
			opc = teclado.nextInt();

			if (opc != 0)
			subjects.add(getCourse(nameCourse).getSubjectsList().get(opc-1));
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
		String course = teclado.next();

		System.out.println("Digite a disciplina");
		String subject = teclado.next();

		Subject newSubject;

		newSubject = getUser(key).getCourse(course).subjectAvailable(subject);

		if (newSubject != null) 
		   getUser(key).getCourse(course).newSubject(newSubject);	
	}

	private static void getStudents() {
		System.out.println("Digite o curso");
		String course = teclado.next();

		System.out.println("Digite a disciplina");
		String subject = teclado.nextLine();

	 users.stream()
	      .filter(c -> c.getAccessLeve() == 3)
		  .filter(c -> c.getCourse(course) != null)
		  .filter(c -> c.getCourse(course).subjectAvailable(subject) != null)
		  .forEach(System.out::println);
	}

	public static void loadProfessorData(String caminho) {
        try {
            Path path = Paths.get(caminho.concat("/Professor.txt"));
            Scanner sc = new Scanner(path, "UTF-8");
            while (sc.hasNextLine()) {
                String linha = sc.nextLine();

                loadProfessor(linha);
            }
            sc.close();
        } catch (IOException io) {
            System.out.println("Erro ao abrir arquivo");
        }
    }

	public static Course getCourse(String name){
		return courses.stream()
						   .filter(c -> c.getName().equals(name))
						   .findFirst().get();
	}

	public static void loadProfessor(String dados){
		String[] professor = dados.split(";");
		String[] courses = professor[5].split(",");
		LinkedList<Course> listCourses = new LinkedList<Course>();
		LinkedList<Subject> listSubjects = new LinkedList<Subject>();

		for (int i = 0; i < courses.length; i++){
		   String[] vetAux = courses[i].split("=");
		   String nameCourse = vetAux[0];
		   String[] subjects = vetAux[1].split("/");

		   for (int y = 0; y < subjects.length; y++)
		     listSubjects.add(getCourse(nameCourse).getSubject(subjects[y]));

		   listCourses.add(new Course(nameCourse, listSubjects));
		}

		User newProfessor = new Professor(professor, listCourses);
		users.add(newProfessor);
	}

}

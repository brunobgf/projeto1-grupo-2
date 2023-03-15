import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

public class App {
    // #region AUXILIARY VARIABLES
    private static Scanner keyboard = new Scanner(System.in, "UTF-8");
    static University universityEnrollmentSystem = new University();
    private static int keyAccess;
    private static String path = System.getProperty("user.dir").replaceAll("\\\\", "/") + "/src";
    // #endregion

    // #region UTILS
    public static void clear() {
        System.out.print("/033[H\033[2J");
        System.out.flush();
    }

    static void pause() {
        System.out.println("\nPress enter to continue.");
        keyboard.nextLine();
    }

    // #endregion

    // #region - MENUS
    public static int menu() {
        System.out.println();
        System.out.println("Sistema de matriculas");
        System.out.println("========================================");
        System.out.println("Seja bem vindo! O que você gostaria de fazer?");
        System.out.println("1 - Login");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        try {
            int option = keyboard.nextInt();
            keyboard.nextLine();
            return option;
        } catch (InputMismatchException ie) {
            return -1;
        }
    }

    // #region -LOGIN FLOW
    public static void login() {
        System.out.println("Digite seu usuário");
        String user = keyboard.nextLine();
        System.out.println("Digite sua senha");
        String password = keyboard.nextLine();

        keyAccess = University.getKeyAccess(user, password);

        if (keyAccess == 0)
            System.out.println("Usuário não encontrado!");
    }

    public static void userMenu() {
        switch (University.getUser(keyAccess).getAccessLevel()) {
            case 1:
                optionsAccessLevelOne();
                break;
            case 2:
                optionsAccessLevelTwo();
                break;
            default:
                optionsAccessLevelAdm();
        }
    }

    private static void optionsAccessLevelTwo() {
        System.out.println("\nOlá " + University.getUser(keyAccess).getUser() + ".\nO que deseja fazer?\n");
        System.out.println("01. Matricular-se em disciplina");
        int opc = keyboard.nextInt();

        switch (opc) {
            case 1:
                enrollStudent();
                break;
        }
    }

    private static void optionsAccessLevelOne() {
        System.out.println("\nOlá " + University.getUser(keyAccess).getUser() + ".\nO que deseja fazer?\n");
        System.out.println("01. Visualizar lista de alunos");
        int opc = keyboard.nextInt();

        switch (opc) {
            case 1:
                listStudents();
                break;
        }
    }

    private static void listStudents() {
        System.out.println("Digite o curso");
        String course = keyboard.next();

        System.out.println("Digite a disciplina");
        String subject = keyboard.next();

        University.getStudents(course, subject);
    }

    private static void enrollStudent() {
        System.out.println("Digite o curso");
        String course = keyboard.next();

        System.out.println("Digite a disciplina");
        String subject = keyboard.next();

        if (University.enrollStudent(keyAccess, course, subject))
            System.out.println("Matricula registrada com sucesso.");
        else
            System.out.println("Não foi possível realizar matricula.");
    }

    private static void optionsAccessLevelAdm() {
        System.out.println("Incluir novo estudante");
        int opc = keyboard.nextInt();

        switch (opc) {
            case 1:
                University.addStudent();
                break;
        }

    }
    // #endregion

    // #endregion

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in, "UTF-8");
        University.loadSubjectData(path);
        University.loadCourseData(path);
        University.loadProfessorData(path);
        University.loadStudentData(path);

        int option;

        do {
            option = menu();
            switch (option) {
                case 1:
                    login();
                    userMenu();
                    break;
            }
            pause();
        } while (option != 0);

        keyboard.close();

        try {
            saveUser(path + "/Student.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void saveUser(String caminhoArquivo) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo));

        for (int i = 0; i < University.getUsers().size(); i++) {
            bw.write(University.getUsers().get(i).dataUser());
            bw.newLine();
        }

        bw.close();
    }

}

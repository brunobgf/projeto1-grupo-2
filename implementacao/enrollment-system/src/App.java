import java.util.InputMismatchException;
import java.util.Scanner;

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
        String user = keyboard.next();
        System.out.println("Digite sua senha");
        String password = keyboard.next();

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
        System.out.println("\nOlá " + University.getUser(keyAccess).getCourses() + ".\nO que deseja fazer?\n");
    }

    private static void optionsAccessLevelOne() {
        System.out.println("\nOlá " + University.getUser(keyAccess).getUser() + ".\nO que deseja fazer?\n");
        System.out.println("01. Visualizar lista de alunos");
        int opc = keyboard.nextInt();

        switch (opc) {
            case 1:
                University.getStudents();
                break;
        }
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
            clear();
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

    }

}

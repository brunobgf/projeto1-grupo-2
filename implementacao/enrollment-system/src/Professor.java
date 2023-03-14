import java.util.*;

public class Professor extends User {

    Professor(String[] dados, List <Course> courses){
        super(dados, courses);
        setAccessLevel(1);
    }

    public void accessStudentList(Subject subject) {

    }

}

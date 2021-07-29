import java.util.ArrayList;
import interfaces.Student;
import interfaces.Observer;

public class StudentManager {

    ArrayList<Observer> observers = new ArrayList<>();


    public void makeStudentListen(Student student){
        student.listen();
    }


    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void updateData(String data){
        observers.stream().forEach(ob->{ob.dataChanged(data);});
    }
    
}

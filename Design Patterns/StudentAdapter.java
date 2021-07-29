import interfaces.Student;
import interfaces.ExternalStudent;

public class StudentAdapter implements Student {

    ExternalStudent externalStudent;

    StudentAdapter(ExternalStudent externalStudent){
        this.externalStudent = externalStudent;
    }

    @Override
    public void listen() {
        externalStudent.Hear();   
    }

    @Override
    public void takeNotes() {
        externalStudent.WriteNotes();   
    }

    @Override
    public int getId() {
        return externalStudent.getRollNo();
    }

    @Override
    public String getName() {
        return externalStudent.getName();
    }
    
}

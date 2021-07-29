import interfaces.*;
public class ExternalJuniorStudent implements ExternalStudent,Observer{

    int rollNo;
    String name;
    String data;

    ExternalJuniorStudent(int rollNo,String name){
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public void Hear() {
        System.out.println(name+" is Hearing");
        
    }

    @Override
    public void WriteNotes() {
        System.out.println(name+" is Writing Notes");
        
    }

    @Override
    public int getRollNo() {
        return rollNo;
    }

    @Override
    public String getName() {
        
        return name;
    }

    @Override
    public String toString() {
        return "rollNo=>"+rollNo+"\nname=>"+name;
    }

    @Override
    public void dataChanged(Object o) {
        
        data = (String) o;
        System.out.println("Update!!"+data);
        
    }

    

    
    
}

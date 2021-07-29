import interfaces.Student;

public class StudentFactory {

    public static enum StudentType{Junior,Senior};
    private static StudentFactory INSTANCE = null;

    private StudentFactory(){
        //defaultContructor
    }

    
    public Student getStudent(StudentType type,int Id,String name){
        switch(type){
            case Junior:
                return new Junior.Builder(Id,name).build();
            case Senior:
                return new Senior.Builder(Id, name).build();
            default:
                return null;
                
        }
    }

    public static StudentFactory getInstance(){
        
        if(INSTANCE==null){
            synchronized(StudentFactory.class){
                if(INSTANCE==null){
                    return new StudentFactory();
                }
            }

        }
        return INSTANCE;
    }
    
}

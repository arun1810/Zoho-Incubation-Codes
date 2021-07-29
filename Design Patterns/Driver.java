import ObjectPool.HeadMaster;
import ObjectPool.HeadMasterObjectPool;
import interfaces.*;
public class Driver {
    public static void main(String[] args) {
         
        Student junior = new Junior.Builder(1,"name1")
                                .mobileNo("123456789")
                                .address("XYZ")
                                .build();//builder
    
                            
    StudentManager manager = new StudentManager();
    
    manager.makeStudentListen(junior);
    
    StudentFactory studentFactory = StudentFactory.getInstance();// factory and Singleton
    Student senior = studentFactory.getStudent(StudentFactory.StudentType.Senior, 2, "name2");
    
    manager.makeStudentListen(senior);

    ExternalStudent externalJuniorStudent = new ExternalJuniorStudent(3,"name3");
    manager.makeStudentListen(new StudentAdapter(externalJuniorStudent));//Adapter
    
    manager.addObserver((ExternalJuniorStudent)externalJuniorStudent);//Observer
    manager.updateData("data1");

    Student senior2 = ((Senior)senior).clone();//Prototype
    ((Senior)senior2).payFee();

    SoccerPlayer soccerPlayer = new SoccerPlayer(4, "name4");

    manager.makeStudentListen(new StudentAdapter(soccerPlayer));
    manager.makeStudentListen(new StudentAdapter(externalJuniorStudent));


    HeadMasterObjectPool headMasterObjectPool = HeadMasterObjectPool.getInstance();

    HeadMaster headMaster = headMasterObjectPool.getObject();//Object pooling
    

    }
}

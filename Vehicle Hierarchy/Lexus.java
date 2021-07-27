import Automobile_Parts_Interfaces.Engine;
import Automobile_Parts_Interfaces.Wheel;

public class Lexus extends Toyota {

    private final int torque=210;

    Lexus(Engine engine,Wheel wheel){
        super(engine,wheel);
    }
    
    Lexus(Engine engine,Wheel wheel,String carname,int topSpeed){
        super(engine,wheel,carname,topSpeed);
    }

    public int getTorque(){
        return torque;
    }

    @Override
    public void applyHorn() {
        System.out.println("Lexus applied horn");
    }
    
    @Override
    public void run(){
        System.out.println("Lexus goes vroom..");
    }
}

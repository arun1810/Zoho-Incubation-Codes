import Automobile_Parts_Interfaces.Engine;
import Automobile_Parts_Interfaces.Wheel;

public class Toyota extends Car {

    String carname ;
    int topSpeed ;

    Engine engine;
    Wheel wheel;

    Toyota(Engine engine,Wheel wheel){
        super(engine,wheel);
        carname="Toyota";
        topSpeed=150;
    }

    Toyota(Engine engine,Wheel wheel,String carname,int topSpeed){
        super(engine, wheel);
        this.carname = carname;
        this.topSpeed = topSpeed;
    }

    public void printCarname(){
        System.out.println("car name is "+carname);
    }

    public void run(){
        System.out.println("Toyota goes vroom..");
    }

    @Override
    public void applyHorn() {
        System.out.println("Toyota applied horn");
    }

    @Override
    public void bodyDesign() {
        System.out.println("Toyota's body design");
        
    }

    @Override
    public String toString() {
        return "car name is " +carname +" and its top speed is "+topSpeed;
    }
  
}


import Automobile_Parts_Interfaces.Wheel;
import Automobile_Parts_Interfaces.Engine;

public abstract class Car {
    Engine engine;
    Wheel wheel;
    final int wheels = 4;

    Car(Engine engine,Wheel wheel){
        this.engine = engine;
        this.wheel = wheel;
    }

    public abstract void bodyDesign();

    
    public void startEngine(){
        engine.start();
    }

    public void inflate(){
        wheel.inflate();
    }

    public void applyHorn(){
        System.out.println("car applies horn");
    }

    public final void applybrake(){ //chils classes cant change the body of the method
        System.out.println("Break applied");
    }

    
}

import Automobile_Parts_Interfaces.Engine;
import Automobile_Parts_Interfaces.Wheel;
import Factory.*;

public class driver {
    public static void main(String[] args) {

        Enginefactory enginefactory = new Enginefactory();
        Wheelfactory wheelfactory = new Wheelfactory();
        
        Engine normalEngine  = new Engine(){

            @Override
            public void start() {
                
                
            }};
        Wheel steelWheel = wheelfactory.getWheel(Wheelfactory.WheelType.Steel);
        
        Toyota toyotacar = new Toyota(normalEngine,steelWheel);
        toyotacar.printCarname();
        toyotacar.applyHorn();
        toyotacar.applybrake();
        toyotacar.inflate();
        toyotacar.startEngine();
        toyotacar.run();
        System.out.println("-----------------------------------");

        Toyota toyotacar2 = new Toyota(normalEngine,steelWheel,"Toyotacar2",150);
        toyotacar2.printCarname();
        toyotacar2.applyHorn();
        toyotacar2.applybrake();
        toyotacar2.inflate();
        toyotacar2.startEngine();
        toyotacar2.run();
        System.out.println("-----------------------------------");


        Engine sportsEngine  = enginefactory.getEngine(Enginefactory.EngineType.Sports);
        Wheel alloyWheel = wheelfactory.getWheel(Wheelfactory.WheelType.Alloy);

        Lexus lexuscar = new Lexus(sportsEngine,alloyWheel,"Lexus-NX300h",200);
        lexuscar.printCarname();
        lexuscar.applyHorn();
        lexuscar.applybrake();
        lexuscar.inflate();
        lexuscar.startEngine();
        System.out.println("Lexus car's torque=>"+lexuscar.getTorque());
        System.out.println("-----------------------------------");


        
       Car customCar =new Toyota(sportsEngine,alloyWheel);//Upcast
        System.out.println(customCar);
        customCar.applyHorn();
        

        Lexus l = (Lexus) customCar; //Downcast -> ClassCastException
        l.run();
        System.out.println(l.getTorque());
        System.out.println("-----------------------------------");
        
    }
}

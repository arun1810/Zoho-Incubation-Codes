package Helpers;
import Automobile_Parts_Interfaces.Wheel;

public class AlloyWheel implements Wheel{

    @Override
    public void inflate() {
        System.out.println("Alloy Wheel's tyre inflated");
        
    }
    
}

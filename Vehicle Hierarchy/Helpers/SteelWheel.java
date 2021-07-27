package Helpers;
import Automobile_Parts_Interfaces.Wheel;
public class SteelWheel implements Wheel{

    @Override
    public void inflate() {
        System.out.println("Steel Wheel's tyre inflated");
        
    }
    
}

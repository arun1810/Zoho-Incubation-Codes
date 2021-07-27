package Factory;

import Automobile_Parts_Interfaces.Wheel;
import Helpers.AlloyWheel;
import Helpers.SteelWheel;

public class Wheelfactory {
    public static enum WheelType{Steel,Alloy};

    public Wheel getWheel(WheelType engineType){
        
        switch(engineType){
            case Steel:
                return new SteelWheel();
            case Alloy:
                return new AlloyWheel();
            default:
                return null;
        }
    }
}

package Factory;
import Helpers.NormalEngine;
import Helpers.SportsEngine;
import Automobile_Parts_Interfaces.Engine;

public class Enginefactory {

    public static enum EngineType{Normal,Sports};

    public Engine getEngine(EngineType engineType){
        
        switch(engineType){
            case Normal:
                return new NormalEngine();
            case Sports:
                return new SportsEngine();
            default:
                return null;
        }
    }
    
}

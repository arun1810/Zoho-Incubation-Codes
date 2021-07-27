package Helpers;
import Automobile_Parts_Interfaces.Engine;

public class NormalEngine implements Engine {

    @Override
    public void start() {
       System.out.println("Normal Engine Started");
        
    }
    
}

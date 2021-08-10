package Utilities;

public class PatternChecker {

    private static PatternChecker Instance;

    private PatternChecker(){}//Singleton
    
    public boolean checkMobileNumber(String mobileNo){
        return mobileNo.matches("[0-9]{10}");
    }

    public boolean checkDOB(String DOB){
        return DOB.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}");
    }

    public static PatternChecker getInstance(){
        if(Instance==null){ Instance=new PatternChecker();}
        return Instance;
    }
}

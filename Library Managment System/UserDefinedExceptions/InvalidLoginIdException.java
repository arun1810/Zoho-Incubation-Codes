package UserDefinedExceptions;

public class InvalidLoginIdException extends Exception{
    public InvalidLoginIdException(String s){
        super(s);
    }
}

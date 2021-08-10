package CustomExceptions;

public class InvalidLoginCredentialsException extends Exception{
    public InvalidLoginCredentialsException(String msg){
        super(msg);
    }
}

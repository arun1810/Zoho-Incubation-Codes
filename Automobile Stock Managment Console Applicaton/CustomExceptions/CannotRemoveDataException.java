package CustomExceptions;

public class CannotRemoveDataException extends Exception {
    public CannotRemoveDataException(String msg){
        super(msg);
    }
}

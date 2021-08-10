package CustomExceptions;

public class InsufficientStockCountException extends Exception {
    public InsufficientStockCountException(String msg){
        super(msg);
    }
}

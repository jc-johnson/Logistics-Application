package src.main.java.exceptions;

/**
 * Created by Jordan on 5/16/2017.
 */
public class DataValidationException extends Exception {

    public DataValidationException() {}

    public DataValidationException(String message){
        super(message);
    }

}

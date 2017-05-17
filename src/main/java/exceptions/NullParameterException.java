package src.main.java.exceptions;

/**
 * Created by Jordan on 5/16/2017.
 */
public class NullParameterException extends Exception {

    public NullParameterException() {}

    public NullParameterException(String message) {
        super(message);
    }
}

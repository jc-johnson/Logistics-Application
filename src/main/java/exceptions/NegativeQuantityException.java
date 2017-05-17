package src.main.java.exceptions;

import com.sun.org.apache.xpath.internal.operations.Neg;

/**
 * Created by Jordan on 5/14/2017.
 */
public class NegativeQuantityException extends Exception {

    public NegativeQuantityException() {}

    public NegativeQuantityException(String message){
        super(message);
    }
}

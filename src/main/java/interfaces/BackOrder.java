package src.main.java.interfaces;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;

/**
 * Created by Jordan on 5/15/2017.
 */
public interface BackOrder {

    String getItemId();
    void setItemId(String itemId) throws DataValidationException;
    Integer getBackOrderQuantity();
    void setBackOrderQuantity(Integer Quantity) throws NegativeQuantityException;
    void print();

}

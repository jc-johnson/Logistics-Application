package src.main.java.interfaces;

import src.main.java.Item;
import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NullParameterException;

import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public interface Inventory {

    void setInventoryItem(Item item, Integer quantity) throws DataValidationException;
    void updateInventoryItem(String itemID, Integer quantity) throws DataValidationException, NullParameterException;
    void printInventory();
    boolean containsItem(Item item);
    Integer getItemQuantity(Item item);




}

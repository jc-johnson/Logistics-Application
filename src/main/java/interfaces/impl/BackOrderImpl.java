package src.main.java.interfaces.impl;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.interfaces.BackOrder;

/**
 * Created by Jordan on 5/15/2017.
 */
public class BackOrderImpl implements BackOrder {

    String itemId;
    Integer backOrderQuantity;

    public BackOrderImpl(String itemId, Integer backOrderQuantity){
        this.itemId = itemId;
        this.backOrderQuantity = backOrderQuantity;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public void setItemId(String itemId) throws DataValidationException {
        if (itemId.isEmpty() || itemId.equals("")) throw new DataValidationException("Empty string in BackOrderImpl.setItemId()");
        this.itemId = itemId;
    }

    @Override
    public Integer getBackOrderQuantity() {
        return backOrderQuantity;
    }

    @Override
    public void setBackOrderQuantity(Integer backOrderQuantity) throws NegativeQuantityException {
        if (backOrderQuantity < 0) throw new NegativeQuantityException("Negative quantity parameter in BackOrderImpl.setBackOrderQuantity");

        this.backOrderQuantity = backOrderQuantity;
    }

    public void print() {
        System.out.println("Back-order for item: " + itemId);
        System.out.println("Quantity back-ordered: " + backOrderQuantity);
    }
}

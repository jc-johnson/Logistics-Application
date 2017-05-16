package src.main.java.interfaces.impl;

import src.main.java.interfaces.BackOrder;

/**
 * Created by Jordan on 5/15/2017.
 */
public class BackOrderImpl implements BackOrder {

    String itemId;
    Integer backOrderQuanity;

    public BackOrderImpl(String itemId, Integer backOrderQuanity){
        this.itemId = itemId;
        this.backOrderQuanity = backOrderQuanity;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public Integer getBackOrderQuanity() {
        return backOrderQuanity;
    }

    @Override
    public void setBackOrderQuanity(Integer backOrderQuanity) {
        this.backOrderQuanity = backOrderQuanity;
    }

    public void print() {
        System.out.println("Back-order for item: " + itemId);
        System.out.println("Quantity back-ordered: " + backOrderQuanity);
    }
}

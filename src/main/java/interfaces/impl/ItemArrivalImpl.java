package src.main.java.interfaces.impl;

import src.main.java.interfaces.ItemArrival;

/**
 * Created by Jordan on 5/13/2017.
 */
public class ItemArrivalImpl implements ItemArrival {

    private Integer totalQuantity;
    private Integer arrivalDay;
    private Integer itemsProcessed;



    private Integer getPercentOfTotal() {
        return (itemsProcessed / totalQuantity) * 100;
    }

    @Override
    public void print() {
        System.out.println("\tDay " + arrivalDay + ": " + itemsProcessed +
                " (" + getPercentOfTotal() + "," );

    }
}

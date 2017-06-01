package src.main.java.interfaces.impl;

import src.main.java.interfaces.ItemArrival;

/**
 * Created by Jordan on 5/13/2017.
 */
public class ItemArrivalImpl implements ItemArrival {

    private Integer arrivalDay;
    private Integer itemsProcessed;
    private Integer percentOfTotal;
    private Integer percentOfItemArrivals;


    public Integer getPercentOfTotal() {
        return percentOfTotal;
    }

    public void setPercentOfTotal(Integer totalQuantity) {
        percentOfTotal = (itemsProcessed / totalQuantity) * 100;
    }

    @Override
    public Integer getArrivalDay() {
        return null;
    }

    @Override
    public void setArrivalDay(Integer day) {
        arrivalDay = day;
    }

    @Override
    public Integer getItemsProcessed() {
        return null;
    }

    @Override
    public void setItemsProcessed(Integer quantity) {
        itemsProcessed = quantity;
    }

    public Integer getPercentOfItemArrivals() {
        return percentOfItemArrivals;
    }

    public void setPercentOfItemArrivals(Integer percentOfItemArrivals) {
        this.percentOfItemArrivals = percentOfItemArrivals;
    }


    @Override
    public void print() {
        System.out.println("\tDay " + arrivalDay + ": " + itemsProcessed +
                " (" + getPercentOfTotal() + "%, " + percentOfItemArrivals + "% of total)");

    }
}

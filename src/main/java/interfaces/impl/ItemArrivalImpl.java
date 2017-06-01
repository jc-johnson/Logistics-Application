package src.main.java.interfaces.impl;

import src.main.java.interfaces.ItemArrival;

/**
 * Created by Jordan on 5/13/2017.
 */
public class ItemArrivalImpl implements ItemArrival, Comparable<ItemArrival> {

    private Integer arrivalDay;
    private Integer itemsProcessed;
    private Double percentOfTotal;
    private Double percentOfItemArrivals;


    public Double getPercentOfTotal() {
        return percentOfTotal;
    }

    public void setPercentOfTotal(Double percent) {
        percentOfTotal = percent;
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
        return itemsProcessed;
    }

    @Override
    public void setItemsProcessed(Integer quantity) {
        itemsProcessed = quantity;
    }

    public Double getPercentOfItemArrivals() {
        return percentOfItemArrivals;
    }

    public void setPercentOfItemArrivals(Double percentOfItemArrivals) {
        this.percentOfItemArrivals = percentOfItemArrivals;
    }


    @Override
    public void print() {
        System.out.println("\tDay " + arrivalDay + ": " + itemsProcessed +
                " (" + percentOfTotal + "%, " + percentOfItemArrivals + "% of total)");

    }

    @Override
    public int compareTo(ItemArrival o) {
        return this.arrivalDay > o.getArrivalDay() ? 1 : (this.arrivalDay < o.getArrivalDay() ? -1 : 0);
    }
}

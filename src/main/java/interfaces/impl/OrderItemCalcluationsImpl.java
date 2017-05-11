package src.main.java.interfaces.impl;

import src.main.java.Item;
import src.main.java.interfaces.OrderItemCalculations;

/**
 * Created by Jordan on 5/11/2017.
 */
public class OrderItemCalcluationsImpl implements OrderItemCalculations {

    String ItemId;
    Integer quantity;
    Integer cost;
    Integer numberOfSources;
    Integer firstDay;
    Integer lastDay;

    @Override
    public String getItemId() {
        return ItemId;
    }

    @Override
    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Integer getCost() {
        return cost;
    }

    @Override
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public Integer getNumberOfSources() {
        return numberOfSources;
    }

    @Override
    public void setNumberOfSources(Integer sources) {
        this.numberOfSources = sources;
    }

    @Override
    public Integer getFirstDay() {
        return firstDay;
    }

    @Override
    public void setFirstDay(Integer day) {
        this.firstDay = day;
    }

    @Override
    public Integer getLastDay() {
        return lastDay;
    }

    @Override
    public void setLastDay(Integer day) {
        this.lastDay = day;
    }
}

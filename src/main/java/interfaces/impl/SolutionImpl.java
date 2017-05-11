package src.main.java.interfaces.impl;

import src.main.java.interfaces.OrderItemCalculations;
import src.main.java.interfaces.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */
public class SolutionImpl implements Solution {

    Integer totalCost = 0;
    Integer firstDeliveryDay = 0;
    Integer lastDeliveryDay = 0;
    List<OrderItemCalculations> orderItemCalculationsList = new ArrayList<>();

    @Override
    public void print() {
        System.out.println("Processing Solution");
    }

    @Override
    public Integer getTotalCost() {
        return null;
    }

    @Override
    public Integer getFirstDeliveryDay() {
        return null;
    }

    @Override
    public Integer getLastDeliveryDay() {
        return null;
    }

    @Override
    public Integer printOrderItems(List<OrderItemCalculations> itemCalculationsList) {
        return null;
    }
}

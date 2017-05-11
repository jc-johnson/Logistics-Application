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
        System.out.println("Processing Solution:");
        System.out.println("\tTotal Cost:\t\t$" + getTotalCost());
        System.out.println("\t1st Delivery Day: " + getFirstDeliveryDay());
        System.out.println("\tLast Delivery Day: " + getLastDeliveryDay());
        printOrderItems(orderItemCalculationsList);
    }

    @Override
    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer cost) {
        this.totalCost = cost;
    }

    @Override
    public Integer getFirstDeliveryDay() {
        return firstDeliveryDay;
    }

    public void setFirstDeliveryDay(Integer day) {
        this.firstDeliveryDay = day;
    }

    @Override
    public Integer getLastDeliveryDay() {
        return lastDeliveryDay;
    }

    public void setLastDeliveryDay(Integer day) {
        this.lastDeliveryDay = day;
    }

    public void addOrderItemCalculation(OrderItemCalculations orderItemCalculation) {
        orderItemCalculationsList.add(orderItemCalculation);
    }

    @Override
    public void printOrderItems(List<OrderItemCalculations> itemCalculationsList) {
        System.out.println("Item ID\t\tQuantity\t\tCost\t\t# Sources Used\t\tFirst Day\t\tLast Day");
        for(OrderItemCalculations orderItemCalculations : orderItemCalculationsList) {
            System.out.println(orderItemCalculations.getItemId() + "\t\t" + orderItemCalculations.getQuantity() +
                    "\t\t" + orderItemCalculations.getCost() + "\t\t" + orderItemCalculations.getNumberOfSources() +
                    "\t\t" + orderItemCalculations.getFirstDay() + "\t\t" + orderItemCalculations.getLastDay());
        }
    }
}

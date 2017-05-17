package src.main.java.interfaces.impl;

import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.FacilityRecord;
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

    List<FacilityRecord> facilityRecords = new ArrayList<>();

    public SolutionImpl() {}

    public SolutionImpl(Integer totalCost, Integer firstDeliveryDay, Integer lastDeliveryDay) {
        this.totalCost = totalCost;
        this.firstDeliveryDay = firstDeliveryDay;
        this.lastDeliveryDay = lastDeliveryDay;
    }

    @Override
    public Integer getTotalCost() {
        return totalCost;
    }

    @Override
    public void setTotalCost(Integer cost) throws NegativeQuantityException {
        if (cost < 0 ) throw new NegativeQuantityException("SolutionImpl.setTotalCost()");

        this.totalCost = cost;
    }

    @Override
    public Integer getFirstDeliveryDay() {
        return firstDeliveryDay;
    }

    @Override
    public void setFirstDeliveryDay(Integer day) throws NegativeQuantityException {
        if (day < 0) throw new NegativeQuantityException("SolutionImpl.setFirstDeliveryDay()");

        this.firstDeliveryDay = day;
    }

    @Override
    public Integer getLastDeliveryDay() {
        return lastDeliveryDay;
    }

    @Override
    public void setLastDeliveryDay(Integer day) throws NegativeQuantityException {
        if (day < 0 ) throw new NegativeQuantityException("SolutionImpl.setLastDeliveryDay()");
        this.lastDeliveryDay = day;
    }

    @Override
    public void addOrderItemCalculation(OrderItemCalculations orderItemCalculation) throws NullParameterException {
        if (orderItemCalculation == null) throw new NullParameterException("SolutionImpl.addOrderItemCalculation()");
        orderItemCalculationsList.add(orderItemCalculation);
    }

    @Override
    public void addFacilityRecord(FacilityRecord facilityRecord) throws NullParameterException {
        if (facilityRecord == null) throw new NullParameterException("SolutionImpl.addFacilityRecord()");
        facilityRecords.add(facilityRecord);
    }

    @Override
    public void print() throws NullParameterException {
        System.out.println("Processing Solution:");
        System.out.println("\tTotal Cost:\t\t$" + getTotalCost());
        System.out.println("\t1st Delivery Day: " + getFirstDeliveryDay());
        System.out.println("\tLast Delivery Day: " + getLastDeliveryDay());
        printOrderItems(orderItemCalculationsList);
    }

    @Override
    public void printOrderItems(List<OrderItemCalculations> itemCalculationsList) throws NullParameterException {
        if (itemCalculationsList == null) throw new NullParameterException("SolutionImpl.printOrderItems()");
        System.out.println("Item ID\t\tQuantity\t\tCost\t\t# Sources Used\t\tFirst Day\t\tLast Day");
        for(OrderItemCalculations orderItemCalculations : orderItemCalculationsList) {
            System.out.println(orderItemCalculations.getItemId() + "\t\t" + orderItemCalculations.getQuantity() +
                    "\t\t" + orderItemCalculations.getCost() + "\t\t" + orderItemCalculations.getNumberOfSources() +
                    "\t\t" + orderItemCalculations.getFirstDay() + "\t\t" + orderItemCalculations.getLastDay());
        }
    }
}

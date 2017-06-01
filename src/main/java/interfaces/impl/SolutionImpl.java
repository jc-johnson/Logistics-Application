package src.main.java.interfaces.impl;

import src.main.java.ItemCatalogManager;
import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.FacilityRecord;
import src.main.java.interfaces.LogisticsRecord;
import src.main.java.interfaces.OrderItemCalculation;
import src.main.java.interfaces.Solution;
import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */
public class SolutionImpl implements Solution {

    Integer totalCost = 0;
    Integer firstDeliveryDay = 0;
    Integer lastDeliveryDay = 0;
    List<OrderItemCalculation> orderItemCalculationsList = new ArrayList<>();

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
        if (cost < 0 ) throw new NegativeQuantityException("Cost cannot be negative");

        this.totalCost = cost;
    }

    @Override
    public Integer getFirstDeliveryDay() {
        return firstDeliveryDay;
    }

    @Override
    public void setFirstDeliveryDay(Integer day) throws NegativeQuantityException {
        if (day < 0) throw new NegativeQuantityException("Day cannot be negative");

        this.firstDeliveryDay = day;
    }

    @Override
    public Integer getLastDeliveryDay() {
        return lastDeliveryDay;
    }

    @Override
    public void setLastDeliveryDay(Integer day) throws NegativeQuantityException {
        if (day < 0 ) throw new NegativeQuantityException("Day cannot be negative");
        this.lastDeliveryDay = day;
    }

    @Override
    public void addOrderItemCalculation(OrderItemCalculation orderItemCalculation) throws DataValidationException {
        if (orderItemCalculation == null) throw new DataValidationException("Empty orderItemCalculation");
        orderItemCalculationsList.add(orderItemCalculation);
    }

    @Override
    public void addFacilityRecord(FacilityRecord facilityRecord) throws DataValidationException {
        if (facilityRecord == null) throw new DataValidationException("Empty Facility Record");
        facilityRecords.add(facilityRecord);
    }

    @Override
    public void computeSolution(LogisticsRecord logisticsRecord) {
        /*Integer totalCost = computeTotalCost(logisticsRecord);
        Integer firstDeliveryDay = getFirstDeliveryDay(logisticsRecord);
        Integer lastDeliveryDay = getLastDeliveryDay(logisticsRecord);

        this.totalCost = totalCost;
        this.firstDeliveryDay = firstDeliveryDay;
        this.lastDeliveryDay = lastDeliveryDay;

        OrderItemCalculation orderItemCalculation = new OrderItemCalculationImpl();*/



    }



    private Integer computeTotalCost(LogisticsRecord logisticsRecord) {
        return 0;

    }

    private Integer computeSolutionTotalCost(){
        Integer totalCost = 0;

        // for each OrderItemCalculation in this solution
            // totalCost += OrderItemCalculation.getCost

        return totalCost;
    }

    @Override
    public void print() throws NullParameterException, DataValidationException {
        System.out.println("Processing Solution:");
        System.out.println("\tTotal Cost:\t\t$" + getTotalCost());
        System.out.println("\t1st Delivery Day: " + getFirstDeliveryDay());
        System.out.println("\tLast Delivery Day: " + getLastDeliveryDay());
        // printOrderItems(orderItemCalculationsList);
    }

    @Override
    public void printOrderItems(List<OrderItemCalculation> itemCalculationsList) throws DataValidationException {
        if (itemCalculationsList == null) throw new DataValidationException("Empty itemsCalculationsList");
        System.out.println("Item ID\t\tQuantity\t\tCost\t\t# Sources Used\t\tFirst Day\t\tLast Day");
        for(OrderItemCalculation orderItemCalculations : orderItemCalculationsList) {
            System.out.println(orderItemCalculations.getItemId() + "\t\t" + orderItemCalculations.getQuantity() +
                    "\t\t" + orderItemCalculations.getCost() + "\t\t" + orderItemCalculations.getNumberOfSources() +
                    "\t\t" + orderItemCalculations.getFirstDay() + "\t\t" + orderItemCalculations.getLastDay());
        }
    }
}

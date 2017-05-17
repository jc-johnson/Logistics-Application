package src.main.java.interfaces;

import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;

import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */
public interface Solution {

    void print() throws NullParameterException;

    Integer getTotalCost();

    void setTotalCost(Integer cost) throws NegativeQuantityException;

    Integer getFirstDeliveryDay();

    void setFirstDeliveryDay(Integer day) throws NegativeQuantityException;

    Integer getLastDeliveryDay();

    void setLastDeliveryDay(Integer day) throws NegativeQuantityException;

    void printOrderItems(List<OrderItemCalculations> itemCalculationsList) throws NullParameterException;

    void addOrderItemCalculation(OrderItemCalculations orderItemCalculation) throws NullParameterException;

    void addFacilityRecord(FacilityRecord facilityRecord) throws NullParameterException;
}

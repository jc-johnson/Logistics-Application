package src.main.java.interfaces;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;

import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */
public interface Solution {

    void print() throws NullParameterException, DataValidationException;

    Integer getTotalCost();

    void setTotalCost(Integer cost) throws NegativeQuantityException;

    Integer getFirstDeliveryDay();

    void setFirstDeliveryDay(Integer day) throws NegativeQuantityException;

    Integer getLastDeliveryDay();

    void setLastDeliveryDay(Integer day) throws NegativeQuantityException;

    void printOrderItems(List<OrderItemCalculation> itemCalculationsList) throws NullParameterException, DataValidationException;

    void addOrderItemCalculation(OrderItemCalculation orderItemCalculation) throws NullParameterException, DataValidationException;

    void addFacilityRecord(FacilityRecord facilityRecord) throws NullParameterException, DataValidationException;

    void computeSolution(LogisticsRecord logisticsRecord);
}

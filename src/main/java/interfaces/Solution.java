package src.main.java.interfaces;

import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */
public interface Solution {

    void print();

    Integer getTotalCost();

    void setTotalCost(Integer cost);

    Integer getFirstDeliveryDay();

    void setFirstDeliveryDay(Integer day);

    Integer getLastDeliveryDay();

    void setLastDeliveryDay(Integer day);

    void printOrderItems(List<OrderItemCalculations> itemCalculationsList);
}

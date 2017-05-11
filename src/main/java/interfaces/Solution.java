package src.main.java.interfaces;

import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */
public interface Solution {

    void print();

    Integer getTotalCost();

    Integer getFirstDeliveryDay();

    Integer getLastDeliveryDay();

    Integer printOrderItems(List<OrderItemCalculations> itemCalculationsList);
}

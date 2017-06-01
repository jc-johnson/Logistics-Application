package src.main.java.interfaces;

import src.main.java.Item;
import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NullParameterException;

import java.util.List;

/**
 * Created by Jordan on 5/7/2017.
 */
public interface Order {

    String getId();
    void setId(String id);
    String getDestination();
    void setDestination(String destination);
    List<Item> getOrderItems();
    Integer getOrderTime();
    void setOrderTime(Integer orderTime);
    void addOrderItem(Item item, Integer quantity);
    void printOutput() throws NullParameterException, DataValidationException;
    Integer getItemQuantity(Item item) throws NullParameterException;
    void addOrderItemCalculation(OrderItemCalculation orderItemCalculation);
    Integer getOrderItemCalulationSize();
    OrderItemCalculation getOrderItemCalculation(Integer i);
    void addSolution(Solution solution);

}

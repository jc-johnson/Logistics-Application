package src.main.java.interfaces;

import src.main.java.Item;

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
    void printItemList();
    void printProcessingSolution();
    void printOutput();
    Integer getItemQuantity(Item item);

}

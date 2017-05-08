package src.main.java.Interfaces;

import src.main.java.Item;

import java.util.List;

/**
 * Created by Jordan on 5/7/2017.
 */
public interface Order {

    Integer getId();
    void setId(Integer id);
    String getDestination();
    void setDestination(String destination);
    List<Item> getOrderItems();
    Integer getOrderTime();
    void setOrderTime(Integer orderTime);
    void addOrderItem(Item item);
    void printItemList();
    void printProcessingSolution();
    void printOutput();

}

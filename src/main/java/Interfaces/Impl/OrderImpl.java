package src.main.java.interfaces.impl;

import src.main.java.interfaces.ItemPrinter;
import src.main.java.interfaces.Order;
import src.main.java.interfaces.OrderOutput;
import src.main.java.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/7/2017.
 */
public class OrderImpl implements Order{

    private String id;
    private String destinaiton;
    private List<Item> items;
    private Map<Item, Integer> itemsList = new HashMap<>();

    private Integer orderTime = 0;

    private ItemPrinter itemPrinter = new ItemPrinterImpl();
    private OrderOutput orderOutput = new OrderOutputImpl(this);


    public OrderImpl(String id, String destinaiton, Integer orderTime) {
        this.id = id;
        this.destinaiton = destinaiton;
        this.orderTime = orderTime;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getDestination() {
        return destinaiton;
    }

    @Override
    public void setDestination(String destination) {
        this.destinaiton = destination;
    }

    @Override
    public List<Item> getOrderItems() {
        List<Item> itemsCopy = new ArrayList<>();
        itemsCopy = items;
        return itemsCopy;
    }

    @Override
    public Integer getOrderTime() {
        return this.orderTime;
    }

    @Override
    public void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public void addOrderItem(Item item, Integer quantity) {
        itemsList.put(item, quantity);
    }

    @Override
    public void printItemList() {
        itemPrinter.print(itemsList);
    }

    @Override
    public void printProcessingSolution() {

    }

    @Override
    public void printOutput() {
        orderOutput.printOrder();
    }
}

package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.ItemPrinter;
import src.main.java.Interfaces.Order;
import src.main.java.Interfaces.OrderOutput;
import src.main.java.Interfaces.XmlReader;
import src.main.java.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 5/7/2017.
 */
public class OrderImpl implements Order{

    private Integer id;
    private String destinaiton;
    private List<Item> items;

    private Integer orderTime = 0;

    private ItemPrinter itemPrinter = new ItemPrinterImpl();
    private OrderOutput orderOutput = new OrderOutputImpl(this);


    public OrderImpl(Integer id, String destinaiton) {
        this.id = id;
        this.destinaiton = destinaiton;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
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
    public void addOrderItem(Item item) {
        items.add(item);
    }

    @Override
    public void printItemList() {
        itemPrinter.print(items);
    }

    @Override
    public void printProcessingSolution() {

    }

    @Override
    public void printOutput() {
        orderOutput.printOrder();
    }
}

package src.main.java.interfaces.impl;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.*;
import src.main.java.Item;

import java.util.*;

/**
 * Created by Jordan on 5/7/2017.
 */
public class OrderImpl implements Order{

    private String id;
    private String destination;
    private Map<Item, Integer> itemsList = new HashMap<>();
    private Solution solution = new SolutionImpl();
    private Integer orderTime = 0;

    private List<OrderItemCalculation> orderItemCalculations = new ArrayList<>();


    public OrderImpl(String id, String destination, Integer orderTime) {
        this.id = id;
        this.destination = destination;
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
        return destination;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
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
    public void printOutput() throws NullParameterException, DataValidationException {
        System.out.println("\tOrder Id:\t\t" + this.getId());
        System.out.println("\tOrder Time:\t\tDay " + this.getOrderTime());
        System.out.println("\tDestination:\t" + this.getDestination());
        System.out.println("");

        System.out.println("\tList of Order Items:");
        this.printItemList();
        System.out.println("");

        this.printProcessingSolution();
    }

    private void printProcessingSolution() throws NullParameterException, DataValidationException {
        solution.print();
    }

    private void printItemList() {
        if (itemsList == null) try {
            throw new NullParameterException();
        } catch (NullParameterException e) {
            e.printStackTrace();
        }

        int i = 1;
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println(i + ") Item ID:\t\t" + item.getId() + ",\t\tQuantity: " + quantity);
            i++;
        }
    }

    @Override
    public Integer getItemQuantity(Item item) throws NullParameterException {
        if (item == null) throw new NullParameterException("Null Item");
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            if (entry.getKey().getId().equals(item.getId())) {
                return entry.getValue();
            }

        }
        return null;
    }

    private void printOrderItemCalculations() {
        System.out.println("Item ID\t\tQuantity\t\tCost\t\t# Sources Used\t\tFirst Day\t\tLast Day");
        for(OrderItemCalculation orderItemCalculation : orderItemCalculations) {
            System.out.println(orderItemCalculation.getItemId() + "\t\t" + orderItemCalculation.getQuantity() +
                    "\t\t" + orderItemCalculation.getCost() + "\t\t" + orderItemCalculation.getNumberOfSources() +
                    "\t\t" + orderItemCalculation.getFirstDay() + "\t\t" + orderItemCalculation.getLastDay());
        }
    }

    @Override
    public void addOrderItemCalculation(OrderItemCalculation orderItemCalculation) {
        orderItemCalculations.add(orderItemCalculation);
    }

    @Override
    public Integer getOrderItemCalulationSize() {
        return orderItemCalculations.size();
    }

    @Override
    public OrderItemCalculation getOrderItemCalculation(Integer i) {
        return orderItemCalculations.get(i);
    }

    @Override
    public void addSolution(Solution solution) {
        this.solution = solution;
    }

    @Override
    public boolean containItem(String itemId) {
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            if (entry.getKey().getId().equals(itemId)) return true;
        }
        return false;
    }

    @Override
    public Integer getItemSize() {
        return itemsList.size();
    }

    @Override
    public List<Item> getOrderItems() {
        List<Item> itemsCopy = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            itemsCopy.add(entry.getKey());
        }
        return itemsCopy;
    }



}

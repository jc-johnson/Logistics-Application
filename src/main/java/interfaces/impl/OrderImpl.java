package src.main.java.interfaces.impl;

import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.*;
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
    private String destination;
    private List<Item> items;
    private Map<Item, Integer> itemsList = new HashMap<>();

    private Solution solution = new SolutionImpl();

    private Integer orderTime = 0;

    private ItemPrinter itemPrinter = new ItemPrinterImpl();
    private OrderOutput orderOutput = new OrderOutputImpl(this);

    private Map<Item, List<FacilityRecord>> itemFacilityRecords = new HashMap<>();


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
    public List<Item> getOrderItems() {
        List<Item> itemsCopy = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            itemsCopy.add(entry.getKey());
        }
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
    public void printOutput() throws NullParameterException {

        orderOutput.printOrder();
        solution.print();
    }

    @Override
    public Integer getItemQuantity(Item item) {
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            if (entry.getKey().getId().equals(item.getId())) {
                return entry.getValue();
            }

        }
        return null;
    }

    @Override
    public void addFacilityRecord(Item  item, FacilityRecord facilityRecord) {
        List<FacilityRecord> facilityRecords = itemFacilityRecords.get(item);

        // if list does note exist create it
        if (facilityRecords == null) {
            facilityRecords = new ArrayList<>();
            facilityRecords.add(facilityRecord);
            itemFacilityRecords.put(item, facilityRecords);
        } else {
            facilityRecords.add(facilityRecord);
            itemFacilityRecords.put(item, facilityRecords);
        }
    }

}

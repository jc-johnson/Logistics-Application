package src.main.java.interfaces.impl;

import src.main.java.Item;
import src.main.java.interfaces.FacilityRecord;
import src.main.java.interfaces.ItemArrival;
import src.main.java.interfaces.LogisticsDetail;
import src.main.java.interfaces.LogisticsRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/13/2017.
 */
public class LogisticsRecordImpl implements LogisticsRecord {

    private String itemId;
    private Integer itemQuantity;

    private Map<Integer, Integer> itemArrivals = new HashMap<>();
    private List<LogisticsDetail> logisticsDetails = new ArrayList<>();

    private List<Integer> totalPercentages = new ArrayList<>();

    Map<Item, List<LogisticsDetail>> logisticDetails;


    private List<FacilityRecord> facilityRecords = new ArrayList<>();

    public String getItemId() {
        return itemId;
    }


    public LogisticsRecordImpl(List<ItemArrival> itemArrivals, List<LogisticsDetail> logisticDetails, String itemId, Integer itemQuantity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.logisticDetails = logisticDetails;
    }

    @Override
    public Integer getItemQuantityProcessed() {
        return null;
    }

    @Override
    public void setItemId() {

    }

    @Override
    public void setItemQuantityProcessed() {

    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    private Integer computeQuantityPercent(Integer number) {

        return (getItemQuantity() / number) * 100;
    }

    private Integer computeTotalPercent() {
        Integer value = 0;
        for (Integer integer : totalPercentages) {
            value += integer;
        }
        return value;
    }

    @Override
    public void print() {
        System.out.println("Item ID: " + getItemId() + ", Quantity: " + getItemQuantity());
        System.out.println("");
        System.out.println("Item Arrivals:");
        System.out.println("");
        for (Map.Entry<Integer, Integer> entry : itemArrivals.entrySet()) {
            Integer currentQuantityPercent = computeQuantityPercent(itemQuantity);
            System.out.println("\tDay " + entry.getKey() + ": " + entry.getValue() + " (" +
                     currentQuantityPercent + "%, " + computeTotalPercent() + "% of total)");
            totalPercentages.add(computeQuantityPercent(itemQuantity));
        }



        System.out.println("Logistics Details");
        for (int i = 0; i < logisticsDetails.size(); i++) {
            System.out.println(i + ")");
            logisticsDetails.get(i).print();
        }
    }
}

package src.main.java.interfaces.impl;

import src.main.java.FacilityManager;
import src.main.java.Item;
import src.main.java.ItemCatalogManager;
import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.FacilityNotFoundException;
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

    // private Map<Integer, Integer> itemArrivals = new HashMap<>();
    private List<LogisticsDetail> logisticsDetails = new ArrayList<>();
    private List<ItemArrival> itemArrivals = new ArrayList<>();

    private List<Integer> totalPercentages = new ArrayList<>();

    // Map<Item, List<LogisticsDetail>> logisticDetails;


    private List<FacilityRecord> facilityRecords = new ArrayList<>();


    public LogisticsRecordImpl(String itemId) {
        this.itemId = itemId;
    }


    public String getItemId() {
        return itemId;
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

    @Override
    public void addLogisticsDetail(LogisticsDetail logisticsDetail) {
        logisticsDetails.add(logisticsDetail);
    }

    @Override
    public Integer getLogisticsDetailSize() {
        return logisticsDetails.size();
    }

    @Override
    public void addItemArrival(ItemArrival itemArrival) {
        itemArrivals.add(itemArrival);
    }

    @Override
    public Integer getTotalItemCost() throws DataValidationException, FacilityNotFoundException {
        Integer itemCost = getItemCost(this.getItemId());
        Integer facilityProcessingCost = getFacilityProcessingCost();
        Integer travelCost = getTravelCosts();

        Integer totalItemcost = itemCost + facilityProcessingCost + travelCost;
        return totalItemcost;

    }

    @Override
    public Integer getFirstProcessingDay() {
        return null;
    }

    @Override
    public Integer getLastProcessingDay() {
        return null;
    }

    @Override
    public Integer getTotalSources() {
        return null;
    }

    private Integer getItemCost(String itemId) {
        Integer itemPrice =  ItemCatalogManager.getInstance().getItemPrice(this.itemId);
        Integer itemQuantity = this.getItemQuantity();
        return itemPrice * itemQuantity;
    }

    private Integer getFacilityProcessingCost() throws DataValidationException, FacilityNotFoundException {
        Integer totalFacilityCosts = 0;
        for (LogisticsDetail logisticsDetail : logisticsDetails) {
            String facilityLocation = logisticsDetail.getFacilityLocation();
            Integer dailyFacilityCost = FacilityManager.getInstance().getDailyFacilityCost(facilityLocation);
            Integer totalProcessingDays = logisticsDetail.getProcessingEnd() - logisticsDetail.getTravelStart();
            Integer totalProcessingCost = totalProcessingDays * dailyFacilityCost;

            totalFacilityCosts += totalProcessingCost;
        }
        return  totalFacilityCosts;
    }

    private Integer getTravelCosts() {
        Integer travelCosts = 0;
        Integer travelDays = 0;

        // TODO: find travel cost constant in code
        for (LogisticsDetail logisticsDetail : logisticsDetails) {
            travelDays += logisticsDetail.getTravelEnd() - logisticsDetail.getTravelStart();
        }
        travelCosts *= 500 * travelDays;
        return travelCosts;
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

        // TODO: print item arrivals

        System.out.println("Logistics Details");
        for (int i = 0; i < logisticsDetails.size(); i++) {
            System.out.println(i + ")");
            logisticsDetails.get(i).print();
        }
    }
}

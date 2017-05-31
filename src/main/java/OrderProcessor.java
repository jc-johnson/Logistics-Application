package src.main.java;

import org.xml.sax.SAXException;
import src.main.java.exceptions.*;
import src.main.java.interfaces.*;
import src.main.java.interfaces.impl.*;

import javax.xml.crypto.Data;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Jordan on 5/7/2017.
 */
public final class OrderProcessor {

    private static OrderProcessor instance;

    public static OrderProcessor getInstance() {
        if (instance == null) {
            instance = new OrderProcessor();
        }
        return instance;
    }

    private OrderProcessor() {}


    public void generateFacilityRecords(List<Order> orders) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException, NoAvailableDaysException, NegativeQuantityException, DataValidationException, NullParameterException {
        if (orders.size() == 0) throw new DataValidationException("Empty Orders List");


        for (Order order : orders) {

            order.printOutput();
            String destination = order.getDestination();
            List<Item> orderItems = order.getOrderItems();
            Integer arrivalDay;

            // go through each item in the order
            for (Item item : orderItems) {

                Boolean isRealItem = ItemCatalogManager.getInstance().isRealItem(item.getId());
                if (!isRealItem) {
                    System.out.println("Not a real item. Skipping item...");
                    continue;
                }

                List<Facility> facilitiesWithItem = FacilityManager.getInstance().getFacilitiesWithItem(item);

                // generate back order if no facilities have the item
                // TODO: where to print items on backorder?
                if (facilitiesWithItem.size() == 0) {
                    BackOrder backOrder = new BackOrderImpl(item.getId(), order.getItemQuantity(item));
                    // TODO: Add back order to order
                    // backOrder.print();
                    continue;
                }

                System.out.println("Item: " + item.getId());
                System.out.println("");
                System.out.println("Facilities with item: ");
                System.out.println("");

                for (Facility facility : facilitiesWithItem) {
                    System.out.println(facility.getLocation());
                }
                System.out.println("");

                List<FacilityRecord> facilityRecords = new ArrayList<>();
                Integer quantityNeeded = order.getItemQuantity(item);

                // create facility record for each facility with the item
                for (Facility facility : facilitiesWithItem) {
                    // skip destination facility
                    if (facility.getLocation().equals(destination)) continue;

                    Integer facilityItems = facility.getItemQuantity(item);
                    System.out.println("Item Quantity available at facility: " + facilityItems);

                    Integer processingEndDay = facility.getProcessingDays(facilityItems);
                    Integer travelDays = FacilityManager.getInstance().getShortestPathInDays(facility.getLocation(), destination);
                    arrivalDay = travelDays + processingEndDay;

                    // compute total cost
                    // Integer orderItemQuantity = order.getItemQuantity(item);
                    // Integer itemCost = getItemCost(item, orderItemQuantity, facility, travelDays);

                   // System.out.println("Processing Days: " + processingEndDay);
                   // System.out.println("Number of travel days to " + destination + ": " + travelDays + " via shortest path");
                   // System.out.println("Arrival Day: " + arrivalDay);
                    FacilityRecord facilityRecord = new FacilityRecordImpl(destination, arrivalDay);
                    facilityRecord.setItemID(item.getId());
                    facilityRecord.setNumberOfItemsAbleToProcess(facilityItems);
                    facilityRecord.setProcessingEndDay(processingEndDay);
                    facilityRecord.setTravelTime(travelDays);
                    facilityRecord.setArrivalDay(arrivalDay);
                    facilityRecord.setTotalItemQuantity(order.getItemQuantity(item));
                    // facilityRecord.setItemCost(itemCost);
                    facilityRecord.setFacilityLocation(facility.getLocation());
                    facilityRecords.add(facilityRecord);
                }

                // Print each facility record
                /*System.out.println("Unsorted Facility Records");
                for (FacilityRecord facilityRecord : facilityRecords) {
                    facilityRecord.print();
                }*/


                // sort facility records based on shortest arrivalDay
                Collections.sort(facilityRecords, new Comparator<FacilityRecord>() {
                    @Override
                    public int compare(FacilityRecord facilityRecord1, FacilityRecord facilityRecord2) {
                        return Integer.compare(facilityRecord1.getArrivalDay(), facilityRecord2.getArrivalDay());
                    }
                });

                System.out.println("Sorted Facility Records");
                for (FacilityRecord facilityRecord : facilityRecords) {
                    facilityRecord.print();
                }

                while (quantityNeeded > 0) {

                    // Process Facility Record
                    for (FacilityRecord facilityRecord : facilityRecords) {

                        Integer itemsProcessedAtFacility = facilityRecord.getNumberOfItemsAbleToProcess();

                        String currentFacilityLocation = facilityRecord.getFacilityLocation();
                        Facility currentFacility = FacilityManager.getInstance().getFacility(currentFacilityLocation);
                        Integer currentFacilityItems = currentFacility.getItemQuantity(item);
                        quantityNeeded -= currentFacilityItems;
                        Integer newFacilityQuantity = 0;
                        currentFacility.updateInventory(item, newFacilityQuantity);

                        // Process schedule days
                        while (currentFacilityItems > 0) {
                            Integer newScheduleDayAmount;
                            Integer processingDays = facilityRecord.getProcessingEndDay();
                            Integer startDay = facilityRecord.getArrivalDay();
                            Integer scheduleQuantityNeeded = facilityRecord.getNumberOfItemsAbleToProcess();

                            for (Integer i = 0; i <= processingDays; i++) {
                                Integer currentScheduleDayAmount = currentFacility.getAvailableScheduleItems(startDay);
                                if (currentScheduleDayAmount == 0) {
                                    startDay = currentFacility.getNextAvailableDay(startDay);
                                }

                                // get next available start day if currentScheduleDayAmount is 0
                                if (scheduleQuantityNeeded >= currentScheduleDayAmount) {
                                    // take all available items for the day
                                    newScheduleDayAmount = currentScheduleDayAmount;
                                    currentFacility.updateSchedule(startDay, 0);
                                    scheduleQuantityNeeded -= currentScheduleDayAmount;
                                } else {
                                    newScheduleDayAmount = currentScheduleDayAmount-scheduleQuantityNeeded;
                                    currentFacility.updateSchedule(startDay, currentScheduleDayAmount-scheduleQuantityNeeded);
                                }
                                startDay = startDay + 1;
                                currentFacilityItems -= newScheduleDayAmount;
                                }
                            }

                        // generate log record from facility record
                        LogisticsRecordManager.getInstance().gernerateLogisticsRecord(facilityRecord);
                    }
                }
            }
        }

        LogisticsRecordManager.getInstance().printLogisticsRecords();
    }

    private void generateSolutions(){


    }

    private int getItemCost(Item item, Integer itemQuantity, Facility facility, Integer travelDays) {
        Integer travelCost = 500 * travelDays;
        Integer facilityProcessingCost = facility.getCostPerDay();
        Integer totalItemCost = item.getPrice() * itemQuantity;

        return travelCost + facilityProcessingCost + totalItemCost;
    }

    public void printOrders() {
        System.out.println("Order List: ");
        /*for (Order order : orders) {
            order.printOutput();
        }*/
    }
}

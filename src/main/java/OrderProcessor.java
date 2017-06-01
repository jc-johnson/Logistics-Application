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


    public void generateFacilityRecords(List<Order> orders) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException, NoAvailableDaysException, NegativeQuantityException, DataValidationException, NullParameterException, FacilityNotFoundException {
        if (orders.size() == 0) throw new DataValidationException("Empty Orders List");

        for (Order order : orders) {

            String destination = order.getDestination();
            System.out.println("Destination facility: " + destination);

            List<Item> orderItems = order.getOrderItems();
            Integer arrivalDay;
            for (Item item : orderItems) {

                Boolean isRealItem = ItemCatalogManager.getInstance().isRealItem(item.getId());
                if (!isRealItem) {
                    System.out.println("Not a real item. Skipping item...");
                    continue;
                }

                List<Facility> facilitiesWithItem = FacilityManager.getInstance().getFacilitiesWithItem(item);

                // generate back order if no facilities have the item
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
                Integer totalItemQuantityNeeded = order.getItemQuantity(item);

                // create facility record for each facility with the item
                for (Facility facility : facilitiesWithItem) {
                    // skip destination facility
                    if (facility.getLocation().equals(destination)) continue;

                    Integer totalFacilityItems = facility.getItemQuantity(item);
                    Integer processingEndDay = facility.getProcessingDays(totalFacilityItems);
                    Integer travelDays = FacilityManager.getInstance().getShortestPathInDays(facility.getLocation(), destination);
                    arrivalDay = travelDays + processingEndDay;
                    // Integer recordItemsNeeded = Math.min(totalItemQuantityNeeded, totalFacilityItems);

                    FacilityRecord facilityRecord = new FacilityRecordImpl(facility.getLocation(), arrivalDay);
                    facilityRecord.setItemID(item.getId());
                    facilityRecord.setTravelDays(travelDays);
                    facilityRecord.setArrivalDay(arrivalDay);
                    facilityRecord.setTotalItemsAtFacility(totalFacilityItems);
                    facilityRecord.setProcessingEndDay(processingEndDay);
                    // facilityRecord.setItemsNeeded(recordItemsNeeded);
                    facilityRecord.setTotalOrderQuantity(totalItemQuantityNeeded);

                    facilityRecords.add(facilityRecord);

                    // totalItemQuantityNeeded = totalItemQuantityNeeded - recordItemsNeeded;
                }

                // Print each facility record
                /*System.out.println("Unsorted Facility Records");
                for (FacilityRecord facilityRecord : facilityRecords) {
                    facilityRecord.print();
                }*/

                processFacilityRecords(facilityRecords, totalItemQuantityNeeded);

                for (FacilityRecord facilityRecord : facilityRecords) {
                    LogisticsRecordManager.getInstance().gernerateLogisticsRecord(facilityRecord);
                }


            }
        }

        System.out.println("All facility records processed and generated from orders");
    }

    private void processFacilityRecords(List<FacilityRecord> facilityRecords, Integer totalItemQuantityNeeded) throws DataValidationException, NullParameterException, NegativeQuantityException {

        Collections.sort(facilityRecords, new Comparator<FacilityRecord>() {
            @Override
            public int compare(FacilityRecord facilityRecord1, FacilityRecord facilityRecord2) {
                return Integer.compare(facilityRecord1.getArrivalDay(), facilityRecord2.getArrivalDay());
            }
        });

        while (totalItemQuantityNeeded > 0) {
            for (FacilityRecord facilityRecord : facilityRecords) {
                Integer totalFacilityItems = facilityRecord.getTotalItemsAtFacility();
                Integer recordItemsNeeded = Math.min(totalItemQuantityNeeded, totalFacilityItems);
                facilityRecord.setItemsNeeded(recordItemsNeeded);
                totalItemQuantityNeeded = totalItemQuantityNeeded - recordItemsNeeded;
            }
        }

        // Print each facility record
        System.out.println("Sorted Facility Records");
        System.out.println("");
        for (FacilityRecord facilityRecord : facilityRecords) {
            facilityRecord.print();
        }


        for (FacilityRecord facilityRecord : facilityRecords) {
            FacilityManager.getInstance().processFacilityRecord(facilityRecord);
        }
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

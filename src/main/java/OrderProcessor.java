package src.main.java;

import org.xml.sax.SAXException;
import src.main.java.exceptions.*;
import src.main.java.interfaces.*;
import src.main.java.interfaces.impl.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * Created by Jordan on 5/7/2017.
 */
public final class OrderProcessor {

    // private List<Order> orders;
    // private List<FacilityRecord> facilityRecords = new ArrayList<>();

    private static OrderProcessor instance;

    public static OrderProcessor getInstance() {
        if (instance == null) {
            instance = new OrderProcessor();
        }
        return instance;
    }

    private OrderProcessor() {}

    public void loadOrdersXml(String path) throws ParserConfigurationException, SAXException, IOException, EmptyPathException {
        XmlReader xmlReader = new XmlReaderImpl();
        // orders = xmlReader.parseOrdersXml(path);
    }

    public void generateFacilityRecords(List<Order> orders) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException, NoAvailableDaysException, NegativeQuantityException, DataValidationException, NullParameterException {

        for (Order order : orders) {

            // order.printOutput();
            String destination = order.getDestination();
            List<Item> orderItems = order.getOrderItems();
            Integer arrivalDay;

            // go through each item in the order
            for (Item item : orderItems) {

                Integer ItemTotalCost;
                Boolean isRealItem = ItemCatalogManager.getInstance().isRealItem(item.getId());
                if (!isRealItem) {
                    System.out.println("Not a real item! Rejecting item...");
                    continue;
                }

                List<Facility> facilitiesWithItem = FacilityManager.getInstance().getFacilitiesWithItem(item);

                // generate back order if no facilities have the item
                // TODO: where to print items on backorder
                if (facilitiesWithItem.size() == 0) {
                    BackOrder backOrder = new BackOrderImpl(item.getId(), order.getItemQuantity(item));
                    backOrder.print();
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

                while (quantityNeeded > 0) {

                    // create facility record for each facility with the item
                    for (Facility facility : facilitiesWithItem) {

                        // skip destination facility
                        if (facility.getLocation().equals(destination)) continue;

                        // FacilityManager.getInstance().runShortestPath(facility.getLocation(), destination);
                        // Facility destinationFacility = FacilityManager.getInstance().getFacility(destination);

                        Integer facilityItems = facility.getItemQuantity(item);
                        Integer processingEndDay = facility.getProcessingDays(facilityItems);
                        Integer travelDays = FacilityManager.getInstance().getShortestPathInDays(facility.getLocation(), destination);
                        arrivalDay = travelDays + processingEndDay;
                        // compute total cost
                        Integer itemCost = getItemCost(item, order.getItemQuantity(item),facility, travelDays);

                        // FacilityManager.getInstance().resetFacilitiesMinDistance();
                        // FacilityManager.getInstance().resetPrevious();

                        System.out.println("Processing Days: " + processingEndDay);
                        System.out.println("Number of travel days to " + destination + ": " + travelDays + " via shortest path");
                        System.out.println("Arrival Day: " + arrivalDay);

                        FacilityRecord facilityRecord = new FacilityRecordImpl(destination, arrivalDay);
                        facilityRecord.setItemID(item.getId());
                        facilityRecord.setNumberOfItemsProcessed(facilityItems);
                        facilityRecord.setProcessingEndDay(processingEndDay);
                        facilityRecord.setTravelTime(travelDays);
                        facilityRecord.setItemCost(itemCost);
                        facilityRecords.add(facilityRecord);
                    }

                    // save the facility records for logistics solution later
                    for (FacilityRecord facilityRecord : facilityRecords) {
                        order.addFacilityRecord(item, facilityRecord);
                    }

                    // Print each facility record
                    for (FacilityRecord facilityRecord : facilityRecords) {
                        facilityRecord.print();
                    }

                    // sort facility records based on shortest arrivalDay
                    // TODO: create sort class? Or have Facility manager do this or facility records manager
                    Collections.sort(facilityRecords, new Comparator<FacilityRecord>() {
                        @Override
                        public int compare(FacilityRecord facilityRecord1, FacilityRecord facilityRecord2) {
                            return Integer.compare(facilityRecord1.getArrivalDay(), facilityRecord2.getArrivalDay());
                        }
                    });

                    // Update facility inventory and schedule
                    for (FacilityRecord facilityRecord : facilityRecords) {
                        String currentFacilityLocation = facilityRecord.getFacilityLocation();
                        Facility currentFacility = FacilityManager.getInstance().getFacility(currentFacilityLocation);

                        Integer currentFacilityItems = currentFacility.getItemQuantity(item);
                        Integer newFacilityQuantity = currentFacilityItems - facilityRecord.getNumberOfItemsProcessed();

                        currentFacility.updateInventory(item, newFacilityQuantity);
                        quantityNeeded -= facilityRecord.getNumberOfItemsProcessed();

                        // from arrival day to end process day update facility schedule
                        for (Integer i = facilityRecord.getArrivalDay(); i <= facilityRecord.getProcessingEndDay() ; i++) {
                            currentFacility.updateSchedule(i, currentFacility.getAvailableItems(i));
                        }
                        // item.addSolution(facilityRecord);
                        LogisticsRecordManager.getInstance().addFacilityRecord(facilityRecord);
                    }
                }
            }
        }
    }

    public int getItemCost(Item item, Integer itemQuantity, Facility facility, Integer travelDays) {
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

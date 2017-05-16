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

    private List<Order> orders;
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
        orders = xmlReader.parseOrdersXml(path);
    }

    // sort facility records
    public void sortByLowestArrivalDate() {

    }

    public void getLowestFacilityArrivalDay() {

    }

    public void reduceItemQuantityNeeded(Item item, Integer quantity) {

    }

    public Integer computeTotalCost() {
        return null;
    }

    public void computeSolution() throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException, NoAvailableDaysException, NegativeQuantityException {
        for (Order order : orders ) {

            order.printOutput();

            String destination = order.getDestination();
            List<Item> orderItems = order.getOrderItems();

            Integer arrivalDay = 0;

            // go through each item in the order
            for (Item item : orderItems) {

                Boolean isRealItem = ItemCatalogManager.getInstance().isRealItem(item.getId());
                if (!isRealItem) {
                    System.out.println("Not a real item! Rejecting item...");
                    continue;
                }

                List<Facility> facilitiesWithItem = FacilityManager.getInstance().getFacilitiesWithItem(item);
                // removes destination facility from consideration
                /*
                for (Facility facility : facilitiesWithItem) {
                    if (facility.getLocation() == destination) {
                        facilitiesWithItem.remove(facility);
                    }
                }
                */

                if (facilitiesWithItem.size() == 0) {
                    // generate back order for the item
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

                for (Facility facility : facilitiesWithItem) {

                    // skip destination facility
                    if (facility.getLocation().equals(destination)) continue;

                    // FacilityManager.getInstance().runShortestPath(facility.getLocation(), destination);
                    // Facility destinationFacility = FacilityManager.getInstance().getFacility(destination);

                    Integer processingEndDay = facility.getProcessingDays(quantityNeeded);
                    Integer travelDays = FacilityManager.getInstance().getShortestPathInDays(facility.getLocation(), destination);

                    FacilityManager.getInstance().resetFacilitiesMinDistance();
                    FacilityManager.getInstance().resetPrevious();

                    arrivalDay = travelDays + processingEndDay;

                    System.out.println("Processing Days: " + processingEndDay);
                    System.out.println("Number of travel days to " + destination + ": " + travelDays + " via shortest path");
                    System.out.println("Arrival Day: " + arrivalDay);


                    FacilityRecord facilityRecord = new FacilityRecordImpl(destination, arrivalDay);
                    facilityRecord.setNumberOfItems(quantityNeeded);
                    facilityRecord.setProcessingEndDay(processingEndDay);
                    facilityRecord.setTravelTime(travelDays);
                    facilityRecords.add(facilityRecord);
                }

                // sort TODO: create sort class? Or have Facility manager do this or facility records manager
                Collections.sort(facilityRecords, new Comparator<FacilityRecord>() {
                    @Override
                    public int compare(FacilityRecord facilityRecord1, FacilityRecord facilityRecord2) {
                        return Integer.compare(facilityRecord1.getArrivalDay(), facilityRecord2.getArrivalDay());
                    }
                });

                // Print each facility record
                for (FacilityRecord facilityRecord : facilityRecords) {
                    facilityRecord.print();
                }

                // Process facility records
                for (FacilityRecord facilityRecord : facilityRecords) {
                    String currentFacilityLocation = facilityRecord.getFacilityLocation();
                    Facility currentFacility = FacilityManager.getInstance().getFacility(currentFacilityLocation);

                    currentFacility.updateInventory(item, facilityRecord.getNumberOfItems());
                    quantityNeeded -= facilityRecord.getNumberOfItems();
                    // from arrival day to end process day update facility schedule
                    for (Integer i = facilityRecord.getArrivalDay(); i < facilityRecord.getProcessingEndDay() ; i++) {
                        currentFacility.updateSchedule(i, currentFacility.getAvailableItems(i));
                    }
                    item.addSolution(facilityRecord);


                }

                /*
                Integer quantityCollected = 0;
                double currentDay = arrivalDay;

                while (quantityNeeded > 0) {
                    for (int i = 0; i < facilityRecords.size(); i++) {
                        FacilityRecord currentRecord = facilityRecords.get(i);
                        String facilityLocation = currentRecord.getFacilityLocation();
                        Facility currentFacility = FacilityManager.getInstance().getFacility(facilityLocation);

                        Integer facilityItemQuantity = currentFacility.getItemQuantity(item);
                        Integer orderItemQuantity = order.getItemQuantity(item);
                        Integer availableItemsOnDay = 0;
                        Integer itemsTaken = 0;
                        Integer itemsToTake = 0;

                        // take all available items from the facility
                        if ( orderItemQuantity <= facilityItemQuantity) {
                            itemsToTake = facilityItemQuantity - orderItemQuantity;
                            currentFacility.updateInventory(item, itemsToTake);

                            // book schedule days
                            while (itemsTaken < quantityNeeded) {
                                availableItemsOnDay = currentFacility.getAvailableItems(currentDay);
                                currentFacility.updateSchedule(currentDay, availableItemsOnDay);
                                currentDay++;
                                itemsTaken += availableItemsOnDay;
                            }


                        } else {
                            itemsToTake = orderItemQuantity - facilityItemQuantity;
                            currentFacility.updateInventory(item, itemsToTake);

                            // book schedule days
                            while (itemsTaken < quantityNeeded) {
                                availableItemsOnDay = currentFacility.getAvailableItems(currentDay);
                                currentFacility.updateSchedule(currentDay, availableItemsOnDay);
                                currentDay++;
                                itemsTaken += availableItemsOnDay;
                            }
                        }

                        OrderItemCalculations orderItemCalculations = new OrderItemCalcluationsImpl();
                        Integer totalCost = 0;
                        Integer firstDeliveryDay = 0;
                        Integer lastDeliveryDay = 0;

                        Solution solution = new SolutionImpl(totalCost, firstDeliveryDay, lastDeliveryDay);
                        quantityNeeded -= facilityItemQuantity;

                    }
                }
                */
            }

            order.printOutput();

        }
    }

    // after records have been sorted
    public void process(List<FacilityRecord> records) {

    }

    public void printOrders() {
        System.out.println("Order List: ");
        for (Order order : orders) {
            order.printOutput();
        }
    }
}

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

    public void computeSolution() throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException, NoAvailableDaysException, NegativeQuantityException, DataValidationException, NullParameterException {
        for (Order order : orders ) {

            order.printOutput();

            String destination = order.getDestination();
            List<Item> orderItems = order.getOrderItems();

            Integer arrivalDay = 0;

            // go through each item in the order
            for (Item item : orderItems) {

                Integer ItemTotalCost = 0;
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

                // Create OrderItemLogManager to manage all orter item logistics records - keeps a list of logistics records
                // Create log record for the item


                // Process facility records
                for (FacilityRecord facilityRecord : facilityRecords) {
                    String currentFacilityLocation = facilityRecord.getFacilityLocation();
                    Facility currentFacility = FacilityManager.getInstance().getFacility(currentFacilityLocation);

                    currentFacility.updateInventory(item, facilityRecord.getNumberOfItems());
                    quantityNeeded -= facilityRecord.getNumberOfItems();
                    // from arrival day to end process day update facility schedule
                    for (Integer i = facilityRecord.getArrivalDay(); i < facilityRecord.getProcessingEndDay() ; i++) {
                        currentFacility.updateSchedule(i, currentFacility.getAvailableItems(i));
                        // TODO: might be redundant with Integer processingEndDay = facility.getProcessingDays(quantityNeeded);
                    }
                    item.addSolution(facilityRecord);

                    // compute total cost
                    Integer totalItemCost = getTotalItemCost(item, order.getItemQuantity(item), currentFacility, facilityRecord.getTravelTime());

                    // for each faciltyRecord create LogDetail
                    // add LogDetail to LogRecord

                }

                // for each LogDetail in LogRecord generate an Item arrival inside of the LogRecord class
                // give each order a logistics record
                // from the logistics record generate solution and order output for order






            }

            order.printOutput();

        }
    }

    // after records have been sorted
    public void process(List<FacilityRecord> records) {

    }

    public int getTotalItemCost(Item item, Integer itemQuantity, Facility facility, Integer travelDays) {
        Integer travelCost = 500 * travelDays;
        Integer facilityProcessingCost = facility.getCostPerDay();
        Integer totalItemCost = item.getPrice() * itemQuantity;

        return travelCost + facilityProcessingCost + totalItemCost;
    }

    public void printOrders() {
        System.out.println("Order List: ");
        for (Order order : orders) {
            order.printOutput();
        }
    }
}

package src.main.java;

import org.xml.sax.SAXException;
import src.main.java.exceptions.*;
import src.main.java.interfaces.Facility;
import src.main.java.interfaces.FacilityRecord;
import src.main.java.interfaces.impl.FacilityRecordImpl;
import src.main.java.interfaces.impl.XmlReaderImpl;
import src.main.java.interfaces.Order;
import src.main.java.interfaces.XmlReader;

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

    public void loadOrdersXml(String path) throws ParserConfigurationException, SAXException, IOException {
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

    public void computeSolution() throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException, NoAvailableDaysException {
        for (Order order : orders ) {

            String destination = order.getDestination();


            // go through each item in the order
            for (Item item : order.getOrderItems()) {


                List<Facility> facilitiesWithItem = FacilityManager.getInstance().getFacilitiesWithItem(item);
                List<FacilityRecord> facilityRecords = new ArrayList<>();

                for (Facility facility : facilitiesWithItem) {

                    // FacilityManager.getInstance().runShortestPath(facility.getLocation(), destination);
                    Facility destinationFacility = FacilityManager.getInstance().getFacility(destination);
                    Integer processingDays = destinationFacility.getProcessingDays(order.getItemQuantity(item));
                    double travelDays = FacilityManager.getInstance().getShortestPathInDays(facility.getLocation(), destination);
                    double arrivalDay = travelDays + processingDays;


                    FacilityRecord facilityRecord = new FacilityRecordImpl(facility.getLocation(), arrivalDay);
                    facilityRecords.add(facilityRecord);
                }

                Integer quantityNeeded = order.getItemQuantity(item);

                // sort records by lowest arrival day
                // Collections.sort(facilityRecords, new FacilityRecord()); {
                // get facility with lowest arrival date

                // TODO: remember to reduce quantityNeeded so you don't get infinite loop
                while (quantityNeeded > 0 ) {

                    // Integer facilityDay = facility.getFirstAvailableDay(); // TODO: Have facility manager do this
                    // Integer itemsTaken = Facility.getAvailableItems(facilityDay);


                    // Facility.update schedule(facilityDay, facilityAvailableItems);
                    // Facility.updateInventoryItem(item, itemsTaken);


                }

                // Integer totalItemCost = ...;
                // LogisticsRecord logisticsRecord = new LogisticsRecord();
                // print logisticsRecord
                // print output

            }
        }
    }

    public void printOrders() {
        System.out.println("Order List: ");
        for (Order order : orders) {
            order.printOutput();
        }
    }

    public static void main(String[] args) {


    }


}

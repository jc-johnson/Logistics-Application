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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                // get each facility with the desired item
                for (Facility facility : facilitiesWithItem) {
                    // FacilityManager.getInstance().runShortestPath(facility.getLocation(), destination);
                    Facility destinationFacility = FacilityManager.getInstance().getFacility(destination);
                    Integer processingDays = destinationFacility.getProcessingDays(order.getItemQuantity(item));
                    double travelDays = FacilityManager.getInstance().getShortestPathInDays(facility.getLocation(), destination);

                    double arrivalDay = travelDays + processingDays;

                    // keep track of facility and arrival day
                    List<FacilityRecord> facilityRecords = new ArrayList<>();
                    FacilityRecord facilityRecord = new FacilityRecordImpl(facility.getLocation(), arrivalDay);
                    facilityRecords.add(facilityRecord);
                }

                // sort records by lowest arrival day


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

        // for each order
            // Integer orderTime = order.getOrderTime
            // String destination = order.getDestination

            // for each item in the order
                // get all facilities with desired item
                // List<Facility> facilitiesWithItem = FacilityManager.getInstance().getFacilitiesWithItem(Item item);

                    // for each facilitiy in facilitiesWithItem
                        // facility.runShortestPath(facility, destination)
                        // Integer processDays = daysNeededToProcess(Facility facility, Item item)
                        // Integer arrivalDay = shortestPathTravelTime + processDays

                        // Class FacilityRecord
                            // Integer arrivalDay
                            // Facility source
                            // Facility target
                            // List<Facility> shortestPath
                            // Integer daysNeededToProcessItem



    }


}

package src.main.java;

import org.xml.sax.SAXException;
import src.main.java.exceptions.*;
import src.main.java.interfaces.Order;
import src.main.java.interfaces.XmlReader;
import src.main.java.interfaces.impl.FacilityInventoryXMLLoaderImpl;
import src.main.java.interfaces.impl.OrderImpl;
import src.main.java.interfaces.impl.XmlReaderImpl;
import src.main.java.readxmls.FacilityInventoryXMLLoader;
import src.main.java.readxmls.ItemCatalogXMLLoader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Jordan on 4/14/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, NullFacilityException, NullPriorityQueueException, EmptyNeighborListException, NullNeighborListException, ParserConfigurationException, SAXException, NoAvailableDaysException, NegativeQuantityException, EmptyPathException, DataValidationException, NullParameterException, ParseException, FacilityNotFoundException {


        // Load in facilities Network from XML - gives you all 18 facilities
        FacilityManager facilityManager = FacilityManager.getInstance();
        facilityManager.loadFacilitiesAndNeighborsFromXML("src/main/resources/FacilityNetwork.xml");

        //// Load each facilities' inventory
        facilityManager.loadFacilityInventoryFromXML("src/main/resources/FacilityInventory.xml");
        facilityManager.printEachFacilityOutput();

        //// Output 1
        //// facilityManager.printEachFacilityOutput();

        //// Output 2 : Item catalog
        //// Parse and Print Item Catalog
        ItemCatalogManager itemCatalogManager = ItemCatalogManager.getInstance();
        itemCatalogManager.parseItemsInventoryXML("src/main/resources/ItemCatalog.xml");

        //// Output 3 : Shortest path

        //System.out.println("Shortest Path Tests: ");
        //System.out.println("");

        ///*
        //// TODO: put back print statements in shortest path
        //facilityManager.runShortestPath("Santa Fe, NM", "Chicago, IL");
        //facilityManager.runShortestPath("Atlanta, GA", "St. Louis, MO");
        //facilityManager.runShortestPath("Seattle, WA", "Nashville, TN");
        //facilityManager.runShortestPath("New York City, NY", "Phoenix, AZ");
        //facilityManager.runShortestPath("Fargo, ND", "Austin, TX");
        //facilityManager.runShortestPath("Denver, CO", "Miami, FL");
        //facilityManager.runShortestPath("Austin, TX", "Norfolk, VA");
        //facilityManager.runShortestPath("Miami, FL", "Seattle, WA");
        //facilityManager.runShortestPath("Los Angeles, CA", "Chicago, IL");
        //facilityManager.runShortestPath("Detroit, MI", "Nashville, TN");
        //*/


        //// -------------------- Part 2 Code ----------------------------------

        OrderManager orderManager = OrderManager.getInstance();
        orderManager.loadOrdersXml("src/main/resources/Orders.xml");
        //orderManager.printOrders();

        orderManager.createFacilityRecordsFromOrders();
        LogisticsRecordManager.getInstance().createOrderItemCalculations();
        OrderManager.getInstance().computeSolutions();
        //LogisticsRecordManager.getInstance().printLogisticsRecords();
        //// orderManager.printOrders();

        //// Print all Facilities after order processing
        //FacilityManager.getInstance().printEachFacilityOutput();



        // Test
        // String facilityLocation = FacilityManager.getInstance().getFacilityLocationFromCity("Chicago");
        // System.out.println(facilityLocation);



    }
}

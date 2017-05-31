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

    public static void main(String[] args) throws IOException, NullFacilityException, NullPriorityQueueException, EmptyNeighborListException, NullNeighborListException, ParserConfigurationException, SAXException, NoAvailableDaysException, NegativeQuantityException, EmptyPathException, DataValidationException, NullParameterException, ParseException {


        // Load in facilities Network from XML - gives you all 18 facilities
        FacilityManager facilityManager = FacilityManager.getInstance();
        facilityManager.loadFacilitiesAndNeighborsFromXML("src/main/resources/FacilityNetwork.xml");

        // Load each facilities' inventory
        facilityManager.loadFacilityInventoryFromXML("src/main/resources/FacilityInventory.xml");
        facilityManager.printEachFacilityOutput();

        // Output 1
        // facilityManager.printEachFacilityOutput();

        // Output 2 : Item catalog
        // Parse and Print Item Catalog
        ItemCatalogManager itemCatalogManager = ItemCatalogManager.getInstance();
        itemCatalogManager.parseItemsInventoryXML("src/main/resources/ItemCatalog.xml");

        // Output 3 : Shortest path
        /*
        System.out.println("Shortest Path Tests: ");
        System.out.println("");

        facilityManager.runShortestPath("Santa Fe, NM", "Chicago, IL");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Atlanta, GA", "St. Louis, MO");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Seattle, WA", "Nashville, TN");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("New York City, NY", "Phoenix, AZ");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Fargo, ND", "Austin, TX");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Denver, CO", "Miami, FL");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Austin, TX", "Norfolk, VA");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Miami, FL", "Seattle, WA");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Los Angeles, CA", "Chicago, IL");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        facilityManager.runShortestPath("Detroit, MI", "Nashville, TN");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        */

        // -------------------- Part 2 Code ----------------------------------

        // XmlReader xmlReader = new XmlReaderImpl();
        // xmlReader.parseOrdersXml("src/main/resources/FacilityInventory.xml");

        OrderManager orderManager = OrderManager.getInstance();
        orderManager.loadOrdersXml("src/main/resources/Orders.xml");
        orderManager.createFacilityRecordsFromOrders();


        /*

        Order order = new OrderImpl("123456", "Chicago, IL", 1);

        for (int i = 1; i < 7 ; i++) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Order #" + i);
            order.printOutput();
        }
        */

        // Print all Facilities after order processing
        FacilityManager.getInstance().printEachFacilityOutput();


    }
}

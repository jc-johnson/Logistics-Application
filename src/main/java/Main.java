package src.main.java;

import org.xml.sax.SAXException;
import src.main.java.Exceptions.*;
import src.main.java.Interfaces.Impl.OrderImpl;
import src.main.java.Interfaces.Impl.OrderOutputImpl;
import src.main.java.Interfaces.Impl.XmlReaderImpl;
import src.main.java.Interfaces.Order;
import src.main.java.Interfaces.XmlReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Jordan on 4/14/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, NullFacilityException, NullPriorityQueueException, EmptyNeighborListException, NullNeighborListException, ParserConfigurationException, SAXException {
        /*
        FacilityManager facilityManager = FacilityManager.getInstance();

        // Load in Facilities Network from XML - gives you all 18 facilities
        // FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXmlLoaderImpl();
        // facilityNetworkXMLLoader.parse(facilitiesList);
        facilityManager.loadFacilitesAndNeighborsFromXML();

        // Load each facilities' inventory
        // FacilityInventoryXMLLoader facilityInventoryXMLLoader = new FacilityInventoryXMLLoaderImpl();
        // facilityInventoryXMLLoader.parse(facilitiesList);
        facilityManager.loadFacilityInventoryFromXML();

        facilityManager.initializeSchedules();


        // Output 1
        facilityManager.printEachFacilityOutput();

        // Output 2 : Item catalog
        // Parse and Print Item Catalog
        ItemCatalogXMLLoader itemCatalogXMLLoader = new ItemCatalogXMLLoader();
        itemCatalogXMLLoader.parse();

        // Output 3 : Shortest path
        System.out.println("Shortest Path Tests: ");
        System.out.println("");

        FacilityDijkstra.run("Santa Fe, NM", "Chicago, IL");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Atlanta, GA", "St. Louis, MO");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Seattle, WA", "Nashville, TN");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("New York City, NY", "Phoenix, AZ");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Fargo, ND", "Austin, TX");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Denver, CO", "Miami, FL");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Austin, TX", "Norfolk, VA");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Miami, FL", "Seattle, WA");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Los Angeles, CA", "Chicago, IL");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();

        FacilityDijkstra.run("Detroit, MI", "Nashville, TN");
        facilityManager.resetFacilitiesMinDistance();
        facilityManager.resetPrevious();
        */

        // -------------------- Part 2 Code ----------------------------------

        XmlReader xmlReader = new XmlReaderImpl();
        xmlReader.parseOrdersXml("src/main/resources/FacilityInventory.xml");

        Order order = new OrderImpl(123456, "Chicago, IL");

        for (int i = 1; i < 7 ; i++) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Order #" + i);
            order.printOutput();
        }


    }
}

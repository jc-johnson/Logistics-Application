package src.main.java;

import src.main.java.Exceptions.*;
import src.main.java.Interfaces.Facility;
import src.main.java.ReadXMLs.FacilityInventoryXMLLoader;
import src.main.java.ReadXMLs.FacilityNetworkXMLLoader;
import src.main.java.ReadXMLs.ItemCatalogXMLLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 4/14/2017.
 */
public class Run {

    public static void main(String[] args) throws FileNotFoundException, NullFacilityException, NullPriorityQueueException, EmptyNeighborListException, NullNeighborListException {
        List<Facility> facilitiesList = new ArrayList<>();

        // Load in Facilities Network from XML - gives you all 18 facilities
        FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXMLLoader();
        facilityNetworkXMLLoader.parse(facilitiesList);

        // Load each facilities' inventory
        FacilityInventoryXMLLoader facilityInventoryXMLLoader = new FacilityInventoryXMLLoader();
        facilityInventoryXMLLoader.parse(facilitiesList);

        for (Facility facility : facilitiesList) {
            FacilityManager.initializeSchedules(facilitiesList);
        }

        // Output 1
        FacilityManager.printEachFacilityOutput(facilitiesList);

        // Output 2 : Item catalog
        // Parse and Print Item Catalog
        ItemCatalogXMLLoader itemCatalogXMLLoader = new ItemCatalogXMLLoader();
        itemCatalogXMLLoader.parse();

        // Output 3 : Shortest path
        System.out.println("Shortest Path Tests: ");
        System.out.println("");

        FacilityDijkstra.run("Santa Fe, NM", "Chicago, IL");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Atlanta, GA", "St. Louis, MO");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Seattle, WA", "Nashville, TN");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("New York City, NY", "Phoenix, AZ");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Fargo, ND", "Austin, TX");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Denver, CO", "Miami, FL");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Austin, TX", "Norfolk, VA");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Miami, FL", "Seattle, WA");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Los Angeles, CA", "Chicago, IL");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);
        FacilityDijkstra.run("Detroit, MI", "Nashville, TN");
        FacilityManager.resetFacilitiesMinDistance(facilitiesList);

    }
}

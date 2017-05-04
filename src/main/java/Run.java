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

    public static void main(String[] args) throws FileNotFoundException, NullFacilityException {
        List<Facility> facilitiesList = new ArrayList<>();

        // Load in Facilities Network from XML - gives you all 18 facilities
        FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXMLLoader();
        facilityNetworkXMLLoader.parse(facilitiesList);

        // Load each facilities' inventory
        FacilityInventoryXMLLoader facilityInventoryXMLLoader = new FacilityInventoryXMLLoader();
        facilityInventoryXMLLoader.parse(facilitiesList);

        // Output 1
        for (Facility facility : facilitiesList) {
           facility.printOutput();
        }

        // Output 2 : Item catalog
        // Parse and Print Item Catalog
        ItemCatalogXMLLoader itemCatalogXMLLoader = new ItemCatalogXMLLoader();
        itemCatalogXMLLoader.parse();

        // Output 3 : Shortest path
        System.out.println("Shortest Path Tests: ");
        System.out.println("");

        try {
            // FacilityDijkstra.run("Santa Fe, NM", "Chicago, IL");
            // FacilityDijkstra.run("Atlanta, GA", "St. Louis, MO");
            // FacilityDijkstra.run("Seattle, WA", "Nashville, TN");
            // FacilityDijkstra.run("New York City, NY", "Phoenix, AZ");
            // FacilityDijkstra.run("Fargo, ND", "Austin, TX");
            // FacilityDijkstra.run("Denver, CO", "Miami, FL");
            // FacilityDijkstra.run("Austin, TX", "Norfolk, VA");
            // FacilityDijkstra.run("Miami, FL", "Seattle, WA");
            // FacilityDijkstra.run("Los Angeles, CA", "Chicago, IL");
            FacilityDijkstra.run("Detroit, MI", "Nashville, TN");
        } catch (EmptyNeighborListException e) {
            e.printStackTrace();
        } catch (NullNeighborListException e) {
            e.printStackTrace();
        } catch (NullPriorityQueueException e) {
            e.printStackTrace();
        }

        /*try {
            *//*facilityDijkstra.computeShortestPath("Santa Fe, NM", "Chicago, IL");
            facilityDijkstra.computeShortestPath("Atlanta, GA", "St. Louis, MO");
            facilityDijkstra.computeShortestPath("Seattle, WA", "Nashville, TN");
            facilityDijkstra.computeShortestPath("New York City, NY", "Phoenix, AZ");
            facilityDijkstra.computeShortestPath("Fargo, ND", "Austin, TX");
            facilityDijkstra.computeShortestPath("Denver, CO", "Miami, FL");
            facilityDijkstra.computeShortestPath("Austin, TX", "Norfolk, VA");
            facilityDijkstra.computeShortestPath("Miami, FL", "Seattle, WA");
            facilityDijkstra.computeShortestPath("Los Angeles, CA", "Chicago, IL");
            facilityDijkstra.computeShortestPath("Detroit, MI", "Nashville, TN");*//*
        } catch (NullVertexException e) {
            e.printStackTrace();
        }*/
    }
}

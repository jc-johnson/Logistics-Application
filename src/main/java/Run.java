package src.main.java;

import src.main.java.Exceptions.NullFacilityException;
import src.main.java.Exceptions.NullVertexException;
import src.main.java.Interfaces.Facility;
import src.main.java.ReadXMLs.FacilityInventoryXMLLoader;
import src.main.java.ReadXMLs.FacilityNetworkXMLLoader;
import src.main.java.ReadXMLs.ItemCatalogXMLLoader;

import java.io.FileNotFoundException;

/**
 * Created by Jordan on 4/14/2017.
 */
public class Run {

    public static void main(String[] args) throws FileNotFoundException, NullFacilityException {


        // Load in Facilities Network from XML
        FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXMLLoader();
        facilityNetworkXMLLoader.parse();

        // Parse and load inventory for each Facility
        FacilityInventoryXMLLoader facilityInventoryXMLLoader = new FacilityInventoryXMLLoader();
        facilityInventoryXMLLoader.parse();
       // facilityInventoryXMLLoader.printFacilityInventory();




        // Output 1
        // Make list of all 18 facilities ( get from Facility Manager)
            // print output

        // Output 2 : Item catalog
        // Parse and Load Item Catalog
        ItemCatalogXMLLoader itemCatalogXMLLoader = new ItemCatalogXMLLoader();
        itemCatalogXMLLoader.parse();

        // Output 3 : Shortest path
        System.out.println("Shortest Path Tests: ");
        System.out.println("");

        FacilityDijkstra facilityDijkstra = new FacilityDijkstra();
        // FacilityDijkstra.shortestPath();
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

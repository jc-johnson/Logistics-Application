package src.main.java;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 5/1/2017.
 */
public class FacilityDijkstra {

    public void computeShortestPath(Facility facilityOne, Facility facilityTwo) {

    }

    public void computeShortestPath(String facilityOne, String facilityTwo) {
        Facility start = FacilityFactory.createFacility(facilityOne);
        Facility end = FacilityFactory.createFacility(facilityTwo);

        System.out.println(facilityOne + " to " + facilityTwo + ":");
        computeShortestPath(start, end);
    }
}

package src.main.java;

import src.main.java.Interfaces.Facility;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jordan on 4/16/2017.
 */
public class ShortestPathAlgorithm {

    /***
     * Keeps track of directly connecting facilities and distance between them
     */
    private HashMap<List<Facility>, Integer> toFromFacilityHash = new HashMap<>();

    /***
     * List of connecting facilities and their distances
     */
    private HashMap<List<Facility>, LinkedList> facilityPaths = new HashMap<>();

    public void loadToFromFacilityHash(String facilityLocation) {

    }




}

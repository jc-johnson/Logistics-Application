package src.main.java;

import src.main.java.Interfaces.Facility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jordan on 4/16/2017.
 */
public class ShortestPathAlgorithm {

    /***
     * Keeps track of directly connecting facilities and distance between them
     */
    private List<FacilityMap> toFromFacilityDistances = new ArrayList<>();

    /***
     * List of connecting facilities and their distances
     */
    private HashMap<List<Facility>, LinkedList> facilityPaths = new HashMap<>();

    /***
     * List of visted cities in building graph
     */
    private List<Facility> vistied = new ArrayList<>();


    public void loadToFromFacilityHash(String facilityLocation) {

    }




}

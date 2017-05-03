package src.main.java.Interfaces;

import src.main.java.ShortestPath.FacilityEdge;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/2/2017.
 */
public interface NeighborPrinter {

    void print(ArrayList<FacilityEdge> neighbors);
}

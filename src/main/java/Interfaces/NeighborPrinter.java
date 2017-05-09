package src.main.java.interfaces;

import src.main.java.shortestpath.FacilityEdge;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/2/2017.
 */
public interface NeighborPrinter {

    void print(ArrayList<FacilityEdge> neighbors);
}

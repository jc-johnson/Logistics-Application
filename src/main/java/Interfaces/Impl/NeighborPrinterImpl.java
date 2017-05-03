package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.NeighborPrinter;
import src.main.java.ShortestPath.FacilityEdge;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/2/2017.
 */
public class NeighborPrinterImpl implements NeighborPrinter {

    @Override
    public void print(ArrayList<FacilityEdge> neighbors) {
        for (FacilityEdge facilityEdge : neighbors) {
            System.out.print(facilityEdge.toString());
        }
        System.out.println("");
    }
}

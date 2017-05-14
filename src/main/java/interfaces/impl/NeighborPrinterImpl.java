package src.main.java.interfaces.impl;

import src.main.java.interfaces.NeighborPrinter;
import src.main.java.shortestpath.FacilityEdge;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/2/2017.
 */
public class NeighborPrinterImpl implements NeighborPrinter {

    @Override
    public void print(ArrayList<FacilityEdge> neighbors) {
        System.out.println("Direct Links:");
        for (FacilityEdge facilityEdge : neighbors) {
            System.out.print(facilityEdge.toString());
        }
        System.out.println("");

    }
}

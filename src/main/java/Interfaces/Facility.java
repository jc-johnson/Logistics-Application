package src.main.java.Interfaces;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jordan on 4/14/2017.
 */
public interface Facility {

    String getLocation();

    String setLocation(String location);

    Long getRatePerDay();

    Long setRatePerDay(String ratePerDay);

    Long getCostPerDay();

    Long setCostPerDay(String ratePerday);

    void addNeighbor(String neighborLocation, String distance);

    void printOutput();





}

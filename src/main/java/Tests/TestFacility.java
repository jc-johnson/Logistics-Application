package src.main.java.Tests;

import src.main.java.Interfaces.Facility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jordan on 4/14/2017.
 */
public class TestFacility implements Facility {

    int ratePerDay = 0;
    int costPerDay = 0;
    HashMap<String, Integer> directLinks = new HashMap<>(); // <Location , Distance>
    HashMap<String, Integer> activeInventory = new HashMap<>(); // <Item ID, Quantity>
    ArrayList<String> depletedInventory = new ArrayList<>();
    HashMap<Integer, Integer> schedule = new HashMap<>(); // <Day, Available>

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String setLocation(String location) {
        return null;
    }

    @Override
    public Long getRatePerDay() {
        return null;
    }

    @Override
    public Long setRatePerDay(String ratePerDay) {
        return null;
    }

    @Override
    public Long getCostPerDay() {
        return null;
    }

    @Override
    public Long setCostPerDay(String ratePerday) {
        return null;
    }

    @Override
    public void addNeighbor(String neighborLocation, String distance) {

    }

    @Override
    public void printOutput() {

    }
}

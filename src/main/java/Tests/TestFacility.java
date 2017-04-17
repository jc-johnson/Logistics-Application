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
    public void printOutput() {

    }
}

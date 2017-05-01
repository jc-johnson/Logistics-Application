package src.main.java.Facilities;

import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.XmlReader;
import src.main.java.ShortestPath.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jordan on 4/14/2017.
 */
public class ChicagoFacility implements Facility {

    private String location = "Chicago, IL";
    private long ratePerDay = 10;
    private long costPerDay = 300;

    private HashMap<String, Integer> activeInventory = new HashMap<>();
    private HashMap<String, Integer> depletedInventory = new HashMap<>();
    private HashMap<Integer, Integer> daysAvailable = new HashMap<>();


    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public Long getRatePerDay() {
        return ratePerDay;
    }

    @Override
    public void setRatePerDay(Integer ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public Long getCostPerDay() {
        return costPerDay;
    }

    /*@Override
    public Long setCostPerDay(String ratePerday) {
        return null;
    }*/

    @Override
    public void setCostPerDay(Integer costPerDay) {
        this.costPerDay = costPerDay;
    }

    public void addInventory(String itemId, Integer quantity) {
        activeInventory.put(itemId, quantity);
    }

    @Override
    public void printActiveInventory() {

    }

    @Override
    public void printDepletedInventory() {

    }

    @Override
    public void printOutput() {

    }
}

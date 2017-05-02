package src.main.java.Facilities;

import src.main.java.Interfaces.ActiveInventoryPrinter;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.ActiveInventoryPrinterImpl;
import src.main.java.Interfaces.XmlReader;
import src.main.java.Item;
import src.main.java.ShortestPath.Edge;

import java.util.*;

/**
 * Created by Jordan on 4/14/2017.
 */
public final class ChicagoFacility implements Facility {

    private static ChicagoFacility instance;

    private String location = "Chicago, IL";
    private long ratePerDay = 10;
    private long costPerDay = 300;

    private HashMap<Facility, Long> neighbors;

    private HashMap<Item, Long> activeInventory = new HashMap<>();
    private HashMap<String, Integer> depletedInventory = new HashMap<>();
    private HashMap<Integer, Integer> daysAvailable = new HashMap<>();

    private ActiveInventoryPrinter activeInventoryPrinter= new ActiveInventoryPrinterImpl();



    private ChicagoFacility() {}

    public static ChicagoFacility getInstance() {

        if (instance == null) {
            instance = new ChicagoFacility();
        }

        return instance;
    }



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


    @Override
    public void addInventory(Item item, Long quantity) {
        activeInventory.put(item, quantity);
    }

    @Override
    public void printActiveInventory() {
        activeInventoryPrinter.print(activeInventory);
    }

    @Override
    public void printDepletedInventory() {

    }

    @Override
    public void printOutput() {

    }

}

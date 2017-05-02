package src.main.java.Facilities;

import src.main.java.Interfaces.ActiveInventoryPrinter;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.ActiveInventoryPrinterImpl;
import src.main.java.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 4/14/2017.
 */
public class MiamiFacility implements Facility {

    private String location = "Miami, FL";
    private long ratePerDay = 12;
    private long costPerDay = 300;

    private HashMap<Facility, Long> neighbors;
    private Map<String, Long> directLinks = new HashMap<>();
    private HashMap<Item, Long> activeInventory = new HashMap<>(); // <Item ID, Quantity>
    private ArrayList<String> depletedInventory = new ArrayList<>();
    private HashMap<Integer, Integer> schedule = new HashMap<>(); // <Day, Available>

    private ActiveInventoryPrinter activeInventoryPrinter= new ActiveInventoryPrinterImpl();


    private static MiamiFacility instance;

    private MiamiFacility() {}

    public static MiamiFacility getInstance() {
        if (instance == null) {
            return new MiamiFacility();
        }

        return instance;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public Long getRatePerDay() {
        return null;
    }

    @Override
    public void setRatePerDay(Integer ratePerDay) {

    }


    @Override
    public Long getCostPerDay() {
        return null;
    }

    @Override
    public void setCostPerDay(Integer ratePerday) {

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

    @Override
    public void addInventory(Item item, Long quantity) {
        activeInventory.put(item, quantity);
    }
}

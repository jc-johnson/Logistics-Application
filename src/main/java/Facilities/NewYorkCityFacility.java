package src.main.java.Facilities;

import src.main.java.Interfaces.ActiveInventoryPrinter;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.ActiveInventoryPrinterImpl;
import src.main.java.Item;
import src.main.java.ShortestPath.FacilityEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 4/14/2017.
 */
public class NewYorkCityFacility implements Facility {

    private String location = "New York City, NY";
    private long ratePerDay = 14;
    private long costPerDay = 300;

    private HashMap<Facility, Long> neighbors;
    private Map<String, Long> directLinks = new HashMap<>();
    private HashMap<Item, Long> activeInventory = new HashMap<>(); // <Item ID, Quantity>
    private ArrayList<String> depletedInventory = new ArrayList<>();
    private HashMap<Integer, Integer> schedule = new HashMap<>(); // <Day, Available>

    private ActiveInventoryPrinter activeInventoryPrinter= new ActiveInventoryPrinterImpl();

    private static NewYorkCityFacility instance;

    private NewYorkCityFacility() {}

    public static NewYorkCityFacility getInstance(){
        if (instance == null) {
            return new NewYorkCityFacility();
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

    @Override
    public void addNeighbor(FacilityEdge facilityEdge) {

    }

    @Override
    public void printNeighbors() {

    }
}

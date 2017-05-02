package src.main.java.Facilities;

import src.main.java.Interfaces.ActiveInventoryPrinter;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.ActiveInventoryPrinterImpl;
import src.main.java.Item;

import java.util.*;

/**
 * Created by Jordan on 4/14/2017.
 */
public class AtlantaFacility implements Facility {

    private static AtlantaFacility instance;

    private String location = "Atlanta, GA";
    private long ratePerDay = 10;
    private long costPerDay = 300;

    private HashMap<Facility, Long> neighbors;

    private Map<String, Long> directLinks = new HashMap<>();
    private HashMap<Item, Long> activeInventory = new HashMap<>(); // <Item ID, Quantity>
    private ArrayList<String> depletedInventory = new ArrayList<>();
    private HashMap<Integer, Integer> schedule = new HashMap<>(); // <Day, Available>

    private ActiveInventoryPrinter activeInventoryPrinter= new ActiveInventoryPrinterImpl();

    private AtlantaFacility() {}

    public static AtlantaFacility getInstance() {
        if (instance == null) {
            instance = new AtlantaFacility();
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
        return ratePerDay;
    }

    @Override
    public void setCostPerDay(Integer costPerday) {
        this.costPerDay = costPerday;
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

        System.out.println("---------------------------------------------------------------");
        System.out.println("");
        System.out.println(" " + location + "" ); // TODO: Trim to just get the city. Might need to change Xmls and xml readers.
        System.out.println("");
        System.out.println("");
        System.out.println("Rate per day: " + ratePerDay);
        System.out.println("Cost per day: $" + costPerDay);
        System.out.println("");
        System.out.println("Direct Links: ");

        // print direct links
        for (Map.Entry<String, Long> entry : directLinks.entrySet()) {
            System.out.print(entry.getKey() + " (" + entry.getValue() + "); ");
        }
        System.out.println("");

        // print active inventory
        System.out.println("Active Inventory: ");
        /*System.out.println("\tItem ID \tQuantity");
        for (Map.Entry<Item, Long> entry: activeInventory.entrySet()) {
            System.out.println("\t" + entry.getKey() + "\t" + entry.getValue());
        }*/
        printActiveInventory();

        // print depleted inventory
        if (depletedInventory.isEmpty()) {
            System.out.println("Depleted (Used-Up) Inventory: None");
        }
        System.out.println("Depleted (Used-Up) Inventory: ");
        System.out.println("\tItem ID");
        for (String s : depletedInventory) {
            System.out.println(s);
        }
        System.out.println("");

        System.out.println("Schedule: ");
        System.out.print("Day: ");
        for (Map.Entry<Integer, Integer> entry: schedule.entrySet()) {
            System.out.print(entry.getKey());
        }


        System.out.println("");
        System.out.println("Available: " );
        // printing Available
        for (Map.Entry<Integer, Integer> entry: schedule.entrySet()) {
            System.out.print(entry.getValue());
        }

    }
}

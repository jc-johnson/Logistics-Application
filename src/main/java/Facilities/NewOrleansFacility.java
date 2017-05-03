package src.main.java.Facilities;

import src.main.java.Interfaces.*;
import src.main.java.Interfaces.Impl.ActiveInventoryPrinterImpl;
import src.main.java.Interfaces.Impl.DepletedInventoryPrinterImpl;
import src.main.java.Interfaces.Impl.NeighborPrinterImpl;
import src.main.java.Interfaces.Impl.SchedulePrinterImpl;
import src.main.java.Item;
import src.main.java.ShortestPath.FacilityEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 4/14/2017.
 */
public class NewOrleansFacility implements Facility, Vertex {

    private String location = "New Orleans, LA";
    private long ratePerDay = 10;
    private long costPerDay = 300;
    private ArrayList<FacilityEdge> neighbors = new ArrayList<>();

    private HashMap<Item, Integer> activeInventory = new HashMap<>();
    private HashMap<String, Integer> depletedInventory = new HashMap<>();
    private HashMap<Integer, Integer> schedule = new HashMap<>();

    private ActiveInventoryPrinter activeInventoryPrinter = new ActiveInventoryPrinterImpl();
    private DepletedInventoryPrinter depletedInventoryPrinter = new DepletedInventoryPrinterImpl();
    private SchedulePrinter schedulePrinter = new SchedulePrinterImpl();
    private NeighborPrinter neighborPrinter = new NeighborPrinterImpl();

    // Vertex fields
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex previous = null;

    private static NewOrleansFacility instance;

    private NewOrleansFacility() {}

    public static NewOrleansFacility getInstance() {
        if (instance == null) {
            return new NewOrleansFacility();
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

    @Override
    public void setCostPerDay(Integer costPerDay) {
        this.costPerDay = costPerDay;
    }

    @Override
    public void addInventory(Item item, Integer quantity) {
        activeInventory.put(item, quantity);
    }

    @Override
    public void printActiveInventory() {
        activeInventoryPrinter.print(activeInventory);
    }

    @Override
    public void printDepletedInventory() { depletedInventoryPrinter.print(depletedInventory); }

    @Override
    public void printSchedule() { schedulePrinter.print(schedule); }

    @Override
    public void printNeighbors() { neighborPrinter.print(neighbors); }

    @Override
    public String getCity() {
        return getLocation().substring(0, getLocation().length()-4);
    }

    @Override
    public void printOutput() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("");
        System.out.println(" " + location + "" );
        System.out.println("");
        System.out.println("");
        System.out.println("Rate per day: " + ratePerDay);
        System.out.println("Cost per day: $" + costPerDay);
        System.out.println("");
        System.out.println("Direct Links: ");

        // print direct links
        printNeighbors();
        System.out.println("");

        // print active inventory
        System.out.println("Active Inventory: ");
        printActiveInventory();

        // print depleted inventory
        printDepletedInventory();
        System.out.println("");

        System.out.println("Schedule: ");
        System.out.print("Day: ");
        printSchedule();

    }

    public static void printOutput(String string) {

    }

    // Vertex Methods

    @Override
    public String getName() { return location; }

    @Override
    public void setName(String string) {
        location = string;
    }

    @Override
    public double getMinDistance() {
        return minDistance;
    }

    @Override
    public void setMinDistance(double distance) {
        minDistance = distance;
    }

    @Override
    public Vertex getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(Vertex vertex) {
        previous = vertex;
    }



    @Override
    public void addNeighbor(FacilityEdge facilityEdge) {
        neighbors.add(facilityEdge);
    }

    public void addNeighbor(Facility facility, Double distance) {
        FacilityEdge facilityEdge = new FacilityEdge(facility, distance);
        neighbors.add(facilityEdge);
    }

    @Override
    public ArrayList<FacilityEdge> getCopyOfNeighborsList() {
        // Make a copy of FacilityEdges
        ArrayList<FacilityEdge> facilityEdgesCopy = new ArrayList<>();
        facilityEdgesCopy = neighbors;
        return facilityEdgesCopy;
    }
}

package src.main.java.Facilities;

import src.main.java.Interfaces.*;
import src.main.java.Interfaces.Impl.*;
import src.main.java.Item;
import src.main.java.ShortestPath.Edge;
import src.main.java.ShortestPath.FacilityEdge;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Jordan on 4/14/2017.
 */
public final class ChicagoFacility implements Facility, Comparable<Facility> {

    private String location = "Chicago, IL";
    private Integer ratePerDay = 10;
    private Integer costPerDay = 300;
    private ArrayList<FacilityEdge> neighbors = new ArrayList<>();

    private HashMap<Item, Integer> activeInventory = new HashMap<>();
    private HashMap<String, Integer> depletedInventory = new HashMap<>();
    private HashMap<Integer, Integer> schedule = new HashMap<>();

    private ActiveInventoryPrinter activeInventoryPrinter = new ActiveInventoryPrinterImpl();
    private DepletedInventoryPrinter depletedInventoryPrinter = new DepletedInventoryPrinterImpl();
    private SchedulePrinter schedulePrinter = new SchedulePrinterImpl();
    private NeighborPrinter neighborPrinter = new NeighborPrinterImpl();
    private ScheduleSetter scheduleSetter = new ScheduleSetterImpl();

    // Vertex fields
    private double minDistance = Double.POSITIVE_INFINITY;
    private Facility previous = null;

    private static ChicagoFacility instance;

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
    public Integer getRatePerDay() {
        return ratePerDay;
    }

    @Override
    public void setRatePerDay(Integer ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public Integer getCostPerDay() {
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
    public void addInventory(HashMap<Item, Integer> inventoryList) {
        activeInventory.putAll(inventoryList);
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
    public void addScheduleDay(Integer day, Integer value) {
        scheduleSetter.addDay(day, value, schedule);
    }

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
        System.out.println("--------------------------");
        System.out.println("");
        System.out.println("Rate per day: " + ratePerDay);
        System.out.println("Cost per day: $" + costPerDay);
        System.out.println("");

        // print direct links
        printNeighbors();
        System.out.println("");

        // print active inventory
        System.out.println("Active Inventory: ");
        printActiveInventory();

        // print depleted inventory
        printDepletedInventory();
        System.out.println("");

        printSchedule();

    }

    public static void printOutput(String string) {

    }

    // Vertex Methods

    @Override
    public double getMinDistance() {
        return minDistance;
    }

    @Override
    public void setMinDistance(double distance) {
        minDistance = distance;
    }

    @Override
    public void setPrevious(Facility facility) {
        previous = facility;
    }

    @Override
    public Facility getPrevious() {
        return previous;
    }

    @Override
    public ArrayList<FacilityEdge> getNeighborList() {
        // ArrayList<FacilityEdge> neighborList = new ArrayList<>();
        // neighborList = neighbors;
        return neighbors;
    }

    @Override
    public void addNeighbor(FacilityEdge facilityEdge) {
        neighbors.add(facilityEdge);
    }

    public void addNeighbor(Facility facility, Double distance) {
        FacilityEdge facilityEdge = new FacilityEdge(facility, distance);
        neighbors.add(facilityEdge);
    }


    public static void main(String[] args) {
        ChicagoFacility chicagoFacility = new ChicagoFacility();

        // Print neighbors test
        FacilityEdge edge1 = new FacilityEdge("Boston, MA", 105);
        FacilityEdge edge2 = new FacilityEdge("Santa Fe, NM", 201);

        chicagoFacility.addNeighbor(edge1);
        chicagoFacility.addNeighbor(edge2);

        chicagoFacility.printNeighbors();

        // Print active inventory test
        Item item1 = new Item("ABC123", 550);
        Item item2 = new Item("JBL3100", 180);
        chicagoFacility.addInventory(item1, 15);
        chicagoFacility.addInventory(item2, 22);
        chicagoFacility.printActiveInventory();

        // Print depleted user inventory
        chicagoFacility.printDepletedInventory();

        // Print schedule
        chicagoFacility.printSchedule();
        System.out.println("");

        // Print city
        System.out.println(chicagoFacility.getCity());


    }

    @Override
    public int compareTo(Facility otherFacility) {
        return Double.compare(this.getMinDistance(), otherFacility.getMinDistance());
    }
}

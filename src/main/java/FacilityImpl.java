package src.main.java;

import src.main.java.interfaces.*;
import src.main.java.interfaces.impl.*;
import src.main.java.shortestpath.FacilityEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jordan on 5/8/2017.
 */
public class FacilityImpl implements Facility {

    private String location = "";
    private Integer ratePerDay = 0;
    private Integer costPerDay = 300;
    private ArrayList<FacilityEdge> neighbors = new ArrayList<>();

    // TODO: Make each of these a class inventory and schedule
    private Schedule schedule = new FacilitySchedule();

    private HashMap<Item, Integer> activeInventory = new HashMap<>();
    private HashMap<String, Integer> depletedInventory = new HashMap<>();

    private ActiveInventoryPrinter activeInventoryPrinter = new ActiveInventoryPrinterImpl();
    private DepletedInventoryPrinter depletedInventoryPrinter = new DepletedInventoryPrinterImpl();

    private NeighborPrinter neighborPrinter = new NeighborPrinterImpl();
    private ScheduleSetter scheduleSetter = new ScheduleSetterImpl();

    // Vertex fields
    private double minDistance = 435;
    private Facility previous = null;

    public FacilityImpl(String location) {
        this.location = location;
    }

    public FacilityImpl(String location, Integer ratePerDay, Integer costPerDay) {
        this.location = location;
        this.ratePerDay = ratePerDay;
        this.costPerDay = costPerDay;
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
    public double getMinDistance() {
        return minDistance;
    }

    @Override
    public void setMinDistance(double distance) {
        this.minDistance = distance;
    }

    @Override
    public Facility getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(Facility facility) {
        this.previous = facility;
    }

    @Override
    public ArrayList<FacilityEdge> getNeighborList() {
        ArrayList<FacilityEdge> neighborCopy;
        neighborCopy = this.neighbors;
        return neighborCopy;
    }

    @Override
    public String getCity() {
        return getLocation().substring(0, getLocation().length()-4);
    }

    @Override
    public void addInventory(Item item, Integer quantity) {

    }

    @Override
    public void addInventory(HashMap<Item, Integer> inventoryList) {

    }

    @Override
    public void addNeighbor(FacilityEdge facilityEdge) {
        neighbors.add(facilityEdge);
    }

    @Override
    public void addNeighbors(List<FacilityEdge> facilityEdges) {
        for (FacilityEdge facilityEdge : facilityEdges) {
            this.addNeighbor(facilityEdge);
        }
    }

    @Override
    public void addScheduleDay(Integer day, Integer value) {
        schedule.setScheduleDay(day, value);
    }

    public void changeScheduleDay(Integer day, Integer value) {
        schedule.setScheduleDay(day, value);
    }

    @Override
    public void printActiveInventory() {

    }

    @Override
    public void printDepletedInventory() {

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

    @Override
    public void printNeighbors() {
        neighborPrinter.print(neighbors);
    }

    @Override
    public void printSchedule() {
        schedule.printOutput();
    }
}

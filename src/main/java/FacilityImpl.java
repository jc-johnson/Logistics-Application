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
    private HashMap<Item, Integer> activeInventory = new HashMap<>();
    private HashMap<String, Integer> depletedInventory = new HashMap<>();
    private HashMap<Integer, Integer> schedule = new HashMap<>();

    private ActiveInventoryPrinter activeInventoryPrinter = new ActiveInventoryPrinterImpl();
    private DepletedInventoryPrinter depletedInventoryPrinter = new DepletedInventoryPrinterImpl();

    private SchedulePrinter schedulePrinter = new SchedulePrinterImpl();
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
        return null;
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

    @Override
    public void printNeighbors() {
        neighborPrinter.print(neighbors);
    }

    @Override
    public void printSchedule() {

    }
}

package src.main.java.Facilities;

import src.main.java.Interfaces.ActiveInventoryPrinter;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.ActiveInventoryPrinterImpl;
import src.main.java.Interfaces.Vertex;
import src.main.java.Interfaces.XmlReader;
import src.main.java.Item;
import src.main.java.ShortestPath.Edge;
import src.main.java.ShortestPath.FacilityEdge;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Jordan on 4/14/2017.
 */
public final class ChicagoFacility implements Facility, Vertex {

    private static ChicagoFacility instance;

    // Vertex fields
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex previous = null;

    private String location = "Chicago, IL";
    private long ratePerDay = 10;
    private long costPerDay = 300;

    // private HashMap<Facility, Long> neighbors;
    private ArrayList<FacilityEdge> neighbors = new ArrayList<>();

    private HashMap<Item, Long> activeInventory = new HashMap<>();
    private HashMap<String, Integer> depletedInventory = new HashMap<>();
    private HashMap<Integer, Integer> daysAvailable = new HashMap<>();

    private ActiveInventoryPrinter activeInventoryPrinter = new ActiveInventoryPrinterImpl();



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

    @Override
    public void printNeighbors() {

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

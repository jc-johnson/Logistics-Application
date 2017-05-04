package src.main.java.Interfaces;

import src.main.java.Item;
import src.main.java.ShortestPath.FacilityEdge;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jordan on 4/14/2017.
 */
public interface Facility {

    String getLocation();

    void setLocation(String location);

    Long getRatePerDay();

    void setRatePerDay(Integer ratePerDay);

    Long getCostPerDay();

    void setCostPerDay(Integer ratePerday);

    public double getMinDistance();

    public void setMinDistance(double distance);

    public void setPrevious(Facility facility);

    public Facility getPrevious();

    public ArrayList<FacilityEdge> getNeighborList();

    String getCity();

    void addInventory(Item item, Integer quantity);

    void addInventory(HashMap<Item, Integer> inventoryList);

    void addNeighbor(FacilityEdge facilityEdge);

    void printActiveInventory();

    void printDepletedInventory();

    void printOutput();

    void printNeighbors();

    void printSchedule();

}

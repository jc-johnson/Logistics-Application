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

    Integer getRatePerDay();

    void setRatePerDay(Integer ratePerDay);

    Integer getCostPerDay();

    void setCostPerDay(Integer ratePerday);

    double getMinDistance();

    void setMinDistance(double distance);

    void setPrevious(Facility facility);

    Facility getPrevious();

    ArrayList<FacilityEdge> getNeighborList();

    String getCity();

    void addInventory(Item item, Integer quantity);

    void addInventory(HashMap<Item, Integer> inventoryList);

    void addNeighbor(FacilityEdge facilityEdge);

    void printActiveInventory();

    void printDepletedInventory();

    void printOutput();

    void printNeighbors();

    void printSchedule();

    void addScheduleDay(Integer day, Integer value);

}

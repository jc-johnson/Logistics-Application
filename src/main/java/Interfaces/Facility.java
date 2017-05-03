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

    void printActiveInventory();

    void printDepletedInventory();

    void printOutput();

    void addInventory(Item item, Integer quantity);

    void addNeighbor(FacilityEdge facilityEdge);

    void printNeighbors();

    void printSchedule();

    String getCity();

}

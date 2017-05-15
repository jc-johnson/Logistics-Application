package src.main.java.interfaces;

import src.main.java.Item;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NoAvailableDaysException;
import src.main.java.interfaces.impl.FacilityRecordImpl;
import src.main.java.shortestpath.FacilityEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jordan on 4/14/2017.
 */
public interface Facility  {

    String getLocation();

    void setLocation(String location);

    Integer getRatePerDay();

    void setRatePerDay(Integer ratePerDay);

    Integer getCostPerDay();

    void setCostPerDay(Integer ratePerday);

    double getMinDistance();

    void setMinDistance(double distance);

    Facility getPrevious();

    void setPrevious(Facility facility);

    ArrayList<FacilityEdge> getNeighborList();

    String getCity();

    void updateInventory(Item item, Integer quantity);

    void addNeighbor(FacilityEdge facilityEdge);

    void updateSchedule(Integer day, Integer value);

    void printOutput();

    void printInventory();

    void printNeighbors();

    void printSchedule();

    boolean containsItem(Item item);

    Integer getProcessingDays(Integer itemQuantity) throws NoAvailableDaysException, NegativeQuantityException;


}

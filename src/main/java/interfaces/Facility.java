package src.main.java.interfaces;

import src.main.java.Item;
import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NoAvailableDaysException;
import src.main.java.exceptions.NullParameterException;
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

    Integer getMinDistance();

    void setMinDistance(Integer distance);

    Facility getPrevious();

    void setPrevious(Facility facility);

    ArrayList<FacilityEdge> getNeighborList();

    String getCity();

    void setInventory(Item item, Integer quantity) throws DataValidationException, NullParameterException;

    void updateInventory(String itemID, Integer quantity) throws DataValidationException, NullParameterException;

    void addNeighbor(FacilityEdge facilityEdge);

    void updateSchedule(Integer day, Integer value);

    void printOutput();

    void printInventory();

    void printNeighbors();

    void printSchedule();

    boolean containsItem(Item item);

    Integer getProcessingDays(Integer itemQuantity) throws NoAvailableDaysException, NegativeQuantityException, NullParameterException, DataValidationException;

    void processInventory(Integer quantityNeeded) throws NegativeQuantityException, NoAvailableDaysException;

    Integer getItemQuantity(Item item);

    Integer getAvailableScheduleItems(Integer day) throws NegativeQuantityException;

    void addScheduleDay(Integer day, Integer itemsAvailable);

    Integer getNextAvailableDay(Integer startDay) throws NullParameterException;

    void processOrderSchedule(Integer arrivalDay, Integer processingDays, Integer totalItemsProcessed) throws NullParameterException, NegativeQuantityException;


}

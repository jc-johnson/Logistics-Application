package src.main.java.Interfaces;

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





}

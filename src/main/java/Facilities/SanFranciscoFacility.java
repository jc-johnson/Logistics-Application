package src.main.java.Facilities;

import src.main.java.Interfaces.Facility;

import java.util.HashMap;

/**
 * Created by Jordan on 4/14/2017.
 */
public class SanFranciscoFacility implements Facility {

    private String location = "San Francisco, CA";
    private long ratePerDay = 12;
    private long costPerDay = 300;

    private static SanFranciscoFacility instance;

    private SanFranciscoFacility() {}

    public static SanFranciscoFacility getInstance(){
        if (instance == null) {
            return new SanFranciscoFacility();
        }
        return instance;
    }

    private HashMap<Facility, Long> neighbors;


    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public Long getRatePerDay() {
        return null;
    }

    @Override
    public void setRatePerDay(Integer ratePerDay) {

    }


    @Override
    public Long getCostPerDay() {
        return null;
    }

    @Override
    public void setCostPerDay(Integer ratePerday) {

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
}

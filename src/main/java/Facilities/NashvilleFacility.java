package src.main.java.Facilities;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 4/14/2017.
 */
public class NashvilleFacility implements Facility {

    private String location = "Nashville, TN";
    private long ratePerDay = 8;
    private long costPerDay = 300;

    private static NashvilleFacility instance;

    private NashvilleFacility() {}

    public static NashvilleFacility getInstance() {
        if (instance == null) {
            return new NashvilleFacility();
        }

        return instance;
    }

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

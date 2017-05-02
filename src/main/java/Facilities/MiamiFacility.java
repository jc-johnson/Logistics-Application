package src.main.java.Facilities;

import src.main.java.Interfaces.Facility;

import java.util.HashMap;

/**
 * Created by Jordan on 4/14/2017.
 */
public class MiamiFacility implements Facility {

    private String location = "Miami, FL";
    private long ratePerDay = 12;
    private long costPerDay = 300;

    private HashMap<Facility, Long> neighbors;


    private static MiamiFacility instance;

    private MiamiFacility() {}

    public static MiamiFacility getInstance() {
        if (instance == null) {
            return new MiamiFacility();
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

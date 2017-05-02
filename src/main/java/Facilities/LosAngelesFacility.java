package src.main.java.Facilities;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 4/14/2017.
 */
public class LosAngelesFacility implements Facility {

    private String location = "Los Angeles, CA";
    private long ratePerDay = 10;
    private long costPerDay = 300;

    private static LosAngelesFacility instance;

    private LosAngelesFacility() {}

    public static LosAngelesFacility getInstance() {
        if (instance == null) {
            return new LosAngelesFacility();
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

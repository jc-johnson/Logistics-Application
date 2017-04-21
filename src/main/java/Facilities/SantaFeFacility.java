package src.main.java.Facilities;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 4/14/2017.
 */
public class SantaFeFacility implements Facility {

    private String location = "Santa Fe, NM";
    private long ratePerDay = 8;
    private long costPerDay = 300;


    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String setLocation(String location) {
        return null;
    }

    @Override
    public Long getRatePerDay() {
        return null;
    }

    @Override
    public Long setRatePerDay(String ratePerDay) {
        return null;
    }

    @Override
    public Long getCostPerDay() {
        return null;
    }

    @Override
    public Long setCostPerDay(String ratePerday) {
        return null;
    }

    @Override
    public void addNeighbor(String neighborLocation, String distance) {

    }

    @Override
    public void printOutput() {

    }
}

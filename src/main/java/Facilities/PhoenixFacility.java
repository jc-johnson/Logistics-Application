package src.main.java.Facilities;

import javafx.scene.paint.PhongMaterial;
import src.main.java.Interfaces.Facility;

import java.util.HashMap;

/**
 * Created by Jordan on 4/14/2017.
 */
public class PhoenixFacility implements Facility {

    private String location = "Phoenix, AZ";
    private long ratePerDay = 8;
    private long costPerDay = 300;

    private HashMap<Facility, Long> neighbors;

    private static PhoenixFacility instance;

    private PhoenixFacility() {}

    public static PhoenixFacility getInstance() {
        if (instance == null) {
            instance = new PhoenixFacility();
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

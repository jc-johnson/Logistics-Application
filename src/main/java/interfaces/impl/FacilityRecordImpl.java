package src.main.java.interfaces.impl;

import src.main.java.interfaces.FacilityRecord;

import java.util.Comparator;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Jordan on 5/11/2017.
 */
public class FacilityRecordImpl implements FacilityRecord, Comparator<FacilityRecord>, Comparable<FacilityRecord> {

    private String facilityLocation;
    private double arrivalDay;

    public FacilityRecordImpl(String location, double day) {
        this.facilityLocation = location;
        this.arrivalDay = day;
    }

    @Override
    public double getArrivalDay() {
        return arrivalDay;
    }

    @Override
    public void setArrivalDay(Integer day) {
        arrivalDay = day;
    }

    @Override
    public String getFacilityLocation() {
        return facilityLocation;
    }

    @Override
    public void setFacility(String location) {
        facilityLocation = location;
    }

    @Override
    public int compareTo(FacilityRecord otherFacilityRecord) {
        return Double.compare(this.getArrivalDay(), otherFacilityRecord.getArrivalDay());
    }

    @Override
    public int compare(FacilityRecord facilityRecord1, FacilityRecord facilityRecord2) {
        return Double.compare(facilityRecord1.getArrivalDay(), facilityRecord2.getArrivalDay());
    }

    @Override
    public void print() {
        System.out.println("Facility Location: " + facilityLocation);
        System.out.println("Arrival Day: " + arrivalDay);
    }
}

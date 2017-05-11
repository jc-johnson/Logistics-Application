package src.main.java.interfaces.impl;

import src.main.java.interfaces.Facility;
import src.main.java.interfaces.FacilityRecord;

/**
 * Created by Jordan on 5/11/2017.
 */
public class FacilityRecordImpl implements FacilityRecord, Comparable<FacilityRecordImpl> {

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
    public int compareTo(FacilityRecordImpl otherFacilityRecord) {
        return Double.compare(this.getArrivalDay(), otherFacilityRecord.getArrivalDay());
    }
}

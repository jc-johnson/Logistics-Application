package src.main.java.interfaces.impl;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.FacilityRecord;
import java.util.Comparator;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Jordan on 5/11/2017.
 */
public class FacilityRecordImpl implements FacilityRecord, Comparator<FacilityRecord>, Comparable<FacilityRecord> {

    private String facilityLocation;
    private Integer numberOfItems;
    private Integer processingEndDay;
    private Integer travelTime;
    private Integer arrivalDay;

    public FacilityRecordImpl(String location, Integer day) {
        this.facilityLocation = location;
        this.arrivalDay = day;
    }

    @Override
    public String getFacilityLocation() {
        return facilityLocation;
    }

    @Override
    public void setFacilityLocation(String location) throws DataValidationException {
        if (location.isEmpty() || location.equals("")) throw new DataValidationException("Empty string parameter FacilityRecordImpl.setFacilityLocation");
        facilityLocation = location;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) throws NegativeQuantityException {
        if (numberOfItems < 0 ) throw new NegativeQuantityException("Negative parameter FacilityRecordImpl.setNumberOfItems");
        this.numberOfItems = numberOfItems;
    }

    public Integer getProcessingEndDay() {
        return processingEndDay;
    }

    public void setProcessingEndDay(Integer processingEndDay) throws NegativeQuantityException {
        if (processingEndDay < 0) throw new NegativeQuantityException("Negative parameter FacilityRecordImpl.setProcessingEndDay()");
        this.processingEndDay = processingEndDay;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) throws NegativeQuantityException {
        if (travelTime < 0) throw new NegativeQuantityException("Negative parameter FacilityRecordImpl.setTravelTime()");
        this.travelTime = travelTime;
    }

    @Override
    public Integer getArrivalDay() {
        return arrivalDay;
    }

    @Override
    public void setArrivalDay(Integer day) throws NegativeQuantityException {
        if (day < 0) throw new NegativeQuantityException("Negative parameter FacilityRecordImpl.setArrivalDay()");
        arrivalDay = day;
    }

    @Override
    public int compareTo(FacilityRecord otherFacilityRecord) {
        if (otherFacilityRecord == null) try {
            throw new NullParameterException("FacilityRecordImpl.compareTo");
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        return Integer.compare(this.getArrivalDay(), otherFacilityRecord.getArrivalDay());
    }

    @Override
    public int compare(FacilityRecord facilityRecord1, FacilityRecord facilityRecord2) {
        if (facilityRecord1 == null || facilityRecord2 == null) try {
            throw new NullParameterException("FacilityRecordImpl.compare");
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        return Integer.compare(facilityRecord1.getArrivalDay(), facilityRecord2.getArrivalDay());
    }

    @Override
    public void print() {
        System.out.println("Facility Location: " + facilityLocation);
        System.out.println("Arrival Day: " + arrivalDay);
    }
}

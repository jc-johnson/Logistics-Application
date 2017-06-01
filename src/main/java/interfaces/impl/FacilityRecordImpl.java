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

    private String itemID;

    private String facilityLocation;
    private Integer processingEndDay;
    private Integer travelTime;
    private Integer arrivalDay;
    private Integer totalItemsAtFacility;
    private Integer itemsNeeded;

    private Integer totalOrderQuantity;

    private Integer itemCost;

    public FacilityRecordImpl() {

    }


    public FacilityRecordImpl(String location, Integer day) {
        this.facilityLocation = location;
        this.arrivalDay = day;
    }

    public String getItemID() {
        return itemID;
    }


    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String getFacilityLocation() {
        return facilityLocation;
    }

    @Override
    public void setFacilityLocation(String location) throws DataValidationException {
        if (location.isEmpty() || location.equals("")) throw new DataValidationException("Empty string parameter");
        facilityLocation = location;
    }

    @Override
    public Integer getTotalItemsAtFacility() {
        return totalItemsAtFacility;
    }

    @Override
    public void setTotalItemsAtFacility(Integer number) throws NegativeQuantityException {
        if (number < 0 ) throw new NegativeQuantityException("Negative parameter");
        this.totalItemsAtFacility = number;
    }

    public Integer getProcessingEndDay() {
        return processingEndDay;
    }

    public void setProcessingEndDay(Integer processingEndDay) throws NegativeQuantityException {
        if (processingEndDay < 0) throw new NegativeQuantityException("Negative parameter");
        this.processingEndDay = processingEndDay;
    }

    public Integer getTravelDays() {
        return travelTime;
    }

    public void setTravelDays(Integer travelTime) throws NegativeQuantityException {
        if (travelTime < 0) throw new NegativeQuantityException("Negative parameter");
        this.travelTime = travelTime;
    }

    @Override
    public Integer getItemsNeeded() {
        return itemsNeeded;
    }

    @Override
    public void setItemsNeeded(Integer itemsNeeded) {
        this.itemsNeeded = itemsNeeded;
    }

    @Override
    public Integer getArrivalDay() {
        return arrivalDay;
    }

    @Override
    public void setArrivalDay(Integer day) throws NegativeQuantityException {
        if (day < 0) throw new NegativeQuantityException("Negative parameter ");
        arrivalDay = day;
    }

    public Integer getTotalOrderQuantity() {
        return totalOrderQuantity;
    }

    public void setTotalOrderQuantity(Integer totalOrderQuantity) {
        this.totalOrderQuantity = totalOrderQuantity;
    }

    @Override
    public int compareTo(FacilityRecord otherFacilityRecord) {
        if (otherFacilityRecord == null) try {
            throw new NullParameterException("FacilityRecordImpl.compareTo");
        } catch (NullParameterException e) {
            e.printStackTrace();
        }
        Integer currentArrivalDay = this.getArrivalDay();
        Integer otherArrivalDay = otherFacilityRecord.getArrivalDay();

        return Integer.compare(currentArrivalDay, otherArrivalDay);
    }

    @Override
    public int compare(FacilityRecord facilityRecord1, FacilityRecord facilityRecord2) {
        if (facilityRecord1 == null || facilityRecord2 == null) try {
            throw new NullParameterException("FacilityRecordImpl.compare");
        } catch (NullParameterException e) {
            e.printStackTrace();
        }

        Integer currentArrivalDay = this.getArrivalDay();
        Integer otherArrivalDay = facilityRecord2.getArrivalDay();

        return Integer.compare(currentArrivalDay, otherArrivalDay);
    }

    @Override
    public void print() {
        System.out.println("FacilityRecord:");
        System.out.println("----------------------------");
        System.out.println("Item ID: " + getItemID());
        System.out.println("Number of Items at Facility: " + getTotalItemsAtFacility());
        System.out.println("Processing End Day: " + getProcessingEndDay());
        System.out.println("Travel Time: " + getTravelDays());
        System.out.println("Facility Location: " + getFacilityLocation());
        System.out.println("Arrival Day: " + getArrivalDay());
        System.out.println("Items needing to be processed: " + getItemsNeeded());
        System.out.println("----------------------------");
        System.out.println("");
    }
}

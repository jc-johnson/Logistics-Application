package src.main.java.interfaces.impl;

import src.main.java.interfaces.LogisticsDetail;

/**
 * Created by Jordan on 5/13/2017.
 */
public class LogisticsDetailImpl implements LogisticsDetail {

    String facilityLocation;
    Integer totalQuantity;
    Integer itemsProcessed;
    Integer processingStart;
    Integer processingEnd;
    Integer travelStart;
    Integer travelEnd;
    
    public String getFacilityLocation() {
        return facilityLocation;
    }

    public void setFacilityLocation(String facilityLocation) {
        this.facilityLocation = facilityLocation;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getitemsProcessed() {
        return itemsProcessed;
    }

    public void setitemsProcessed(Integer itemsProcessed) {
        this.itemsProcessed = itemsProcessed;
    }

    public Integer getProcessingStart() {
        return processingStart;
    }

    public void setProcessingStart(Integer processingStart) {
        this.processingStart = processingStart;
    }

    public Integer getProcessingEnd() {
        return processingEnd;
    }

    public void setProcessingEnd(Integer processingEnd) {
        this.processingEnd = processingEnd;
    }

    public Integer getTravelStart() {
        return travelStart;
    }

    public void setTravelStart(Integer travelStart) {
        this.travelStart = travelStart;
    }

    public Integer getTravelEnd() {
        return travelEnd;
    }

    public void setTravelEnd(Integer travelEnd) {
        this.travelEnd = travelEnd;
    }

    @Override
    public void print() {
        System.out.println("Name: " + facilityLocation + "( " + getitemsProcessed() + " of " + getTotalQuantity());
        System.out.println("\tProcessing Start: \tDay " + getProcessingStart());
        System.out.println("\tProcessing End: \tDay " + getProcessingEnd() );
        System.out.println("\tTravel Start: \tDay " + getTravelStart());
        System.out.println("\tTravel End: \tDay " + getTravelEnd());
    }
}

package src.main.java.interfaces.impl;

import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;
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
        if (facilityLocation.isEmpty() || facilityLocation.equals("")) try {
            throw new NullParameterException();
        } catch (NullParameterException e) {
            e.printStackTrace();
        }

        this.facilityLocation = facilityLocation;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        if (totalQuantity < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.totalQuantity = totalQuantity;
    }

    public Integer getitemsProcessed() {
        return itemsProcessed;
    }

    public void setitemsProcessed(Integer itemsProcessed) {
        if (itemsProcessed < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }
        this.itemsProcessed = itemsProcessed;
    }

    public Integer getProcessingStart() {
        return processingStart;
    }

    public void setProcessingStart(Integer processingStart) {
        if (processingStart < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.processingStart = processingStart;
    }

    public Integer getProcessingEnd() {
        return processingEnd;
    }

    public void setProcessingEnd(Integer processingEnd) {
        if (processingEnd < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }
        this.processingEnd = processingEnd;
    }

    public Integer getTravelStart() {
        return travelStart;
    }

    public void setTravelStart(Integer travelStart) {
        if (travelStart < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        this.travelStart = travelStart;
    }

    public Integer getTravelEnd() {
        return travelEnd;
    }

    public void setTravelEnd(Integer travelEnd) {
        if (travelEnd < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

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

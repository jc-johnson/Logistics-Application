package src.main.java.interfaces;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;

/**
 * Created by Jordan on 5/10/2017.
 */
public interface FacilityRecord {

    Integer getArrivalDay();

    void setArrivalDay(Integer day) throws NegativeQuantityException;

    String getFacilityLocation();

    void setFacilityLocation(String facilityLocation) throws DataValidationException;

    public Integer getNumberOfItemsProcessed();

    public void setNumberOfItemsProcessed(Integer numberOfItems) throws NegativeQuantityException;

    public Integer getProcessingEndDay();

    public void setProcessingEndDay(Integer processingEndDay) throws NegativeQuantityException;

    public Integer getTravelTime();

    public void setTravelTime(Integer travelTime) throws NegativeQuantityException;

    public String getItemID();

    public void setItemID(String itemID);

    void print();

}

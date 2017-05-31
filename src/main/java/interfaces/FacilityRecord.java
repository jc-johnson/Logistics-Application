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

    Integer getNumberOfItemsAbleToProcess();

    void setNumberOfItemsAbleToProcess(Integer numberOfItems) throws NegativeQuantityException;

    Integer getProcessingEndDay();

    void setProcessingEndDay(Integer processingEndDay) throws NegativeQuantityException;

    Integer getTravelTime();

    void setTravelTime(Integer travelTime) throws NegativeQuantityException;

    String getItemID();

    void setItemID(String itemID);

    Integer getItemCost();

    void setItemCost(Integer itemCost);

    Integer getTotalItemQuantity();

    void setTotalItemQuantity(Integer totalItemQuantity);

    void print();

}

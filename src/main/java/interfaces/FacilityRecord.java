package src.main.java.interfaces;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NegativeQuantityException;

/**
 * Created by Jordan on 5/10/2017.
 */
public interface FacilityRecord {

    String getItemID();

    void setItemID(String itemID);

    String getFacilityLocation();

    void setFacilityLocation(String facilityLocation) throws DataValidationException;

    Integer getArrivalDay();

    void setArrivalDay(Integer day) throws NegativeQuantityException;

    Integer getTotalItemsAtFacility();

    void setTotalItemsAtFacility(Integer quantity) throws NegativeQuantityException;

    Integer getProcessingEndDay();

    void setProcessingEndDay(Integer processingEndDay) throws NegativeQuantityException;

    Integer getTravelDays();

    void setTravelDays(Integer travelTime) throws NegativeQuantityException;

    Integer getItemsNeeded();

    void setItemsNeeded(Integer itemsNeeded);

    void print();

}

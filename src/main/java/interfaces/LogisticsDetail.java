package src.main.java.interfaces;

import src.main.java.exceptions.FacilityNotFoundException;
import src.main.java.exceptions.NullParameterException;

/**
 * Created by Jordan on 5/13/2017.
 */
public interface LogisticsDetail {

    String getFacilityLocation();

    void setFacilityLocation(String facilityLocation);

    Integer getProcessingStart();

    void setProcessingStart(Integer processingStart);

    Integer getProcessingEnd();

    void setProcessingEnd(Integer processingEnd);

    Integer getTravelStart();

    void setTravelStart(Integer processingStart);

    Integer getTravelEnd();

    void setTravelEnd(Integer processingStart);

    Integer getItemsProcessed();

    void setitemsProcessed(Integer itemsProcessed);

    Integer getTotalQuantity();

    void setTotalQuantity(Integer totalQuantity);

    void print();

    String getFullFacilityLocation() throws NullParameterException, FacilityNotFoundException;
}

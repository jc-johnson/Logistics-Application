package src.main.java.interfaces;

/**
 * Created by Jordan on 5/10/2017.
 */
public interface FacilityRecord {

    Integer getArrivalDay();

    void setArrivalDay(Integer day);

    String getFacilityLocation();

    void setFacilityLocation(String facilityLocation);

    public Integer getNumberOfItems();

    public void setNumberOfItems(Integer numberOfItems);

    public Integer getProcessingEndDay();

    public void setProcessingEndDay(Integer processingEndDay);

    public Integer getTravelTime();

    public void setTravelTime(Integer travelTime);

    void print();
}

package src.main.java.interfaces;

/**
 * Created by Jordan on 5/13/2017.
 */
public interface LogisticsDetail {

    public String getFacilityLocation();

    public void setFacilityLocation(String facilityLocation);

    public Integer getProcessingStart();

    public void setProcessingStart(Integer processingStart);

    public Integer getProcessingEnd();

    public void setProcessingEnd(Integer processingEnd);

    public Integer getTravelStart();

    public void setTravelStart(Integer processingStart);

    public Integer getTravelEnd();

    public void setTravelEnd(Integer processingStart);

    void print();
}

package src.main.java.shortestpath;

import src.main.java.FacilityFactory;
import src.main.java.FacilityImpl;
import src.main.java.interfaces.Facility;

/**
 * Created by Jordan on 5/2/2017.
 */
public class FacilityEdge {

    private String target;
    private Integer weight = 0; // in miles

    public FacilityEdge(String targetFacility, Integer edgeWeight) {
        target = targetFacility;
        weight = edgeWeight;
    }

    public String getTarget(){
        return target;
    }

    public Integer getWeight(){
        return weight;
    }

    public Integer getDistanceInDays() {
        Integer rate = 8 * 50;
        return weight / rate;
    }

    public String toString() {
        return this.getTarget() + " (" + this.getDistanceInDays() + "d); ";
    }
}

package src.main.java.ShortestPath;

import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 5/2/2017.
 */
public class FacilityEdge {

    public Facility target;
    public double weight = 0;

    public FacilityEdge(Facility targetFacility, double edgeWeight) {
        target = targetFacility;
        weight = edgeWeight;
    }

    public FacilityEdge(String facilityLocation, Integer distance) {
        Facility facility = FacilityFactory.createFacility(facilityLocation);
        double weight = (double) distance;

        target = facility;
        this.weight = weight;
    }

    public Facility getTarget(){
        return target;
    }

    public double getWeight(){
        return weight;
    }

    public String toString() {
        return this.target.getLocation() + " (" + this.weight + "d); ";
    }
}

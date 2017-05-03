package src.main.java.ShortestPath;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 5/2/2017.
 */
public class FacilityEdge {

    public Facility target;
    public double weight = 0;

    public FacilityEdge(Facility targetFacility, double edgeWeight) {
        target = targetFacility;
        this.weight = edgeWeight;
    }

    public Facility getTarget(){
        return target;
    }

    public double getWeight(){
        return weight;
    }
}

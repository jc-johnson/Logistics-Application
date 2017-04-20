package src.main.java;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 4/19/2017.
 */
public class FacilityMap {

    // Maps a to-from connection between cities and stores distance
    // TODO: Make this an interface

    private Facility startFacility = null;
    private Facility endFacility = null;
    private int distance = 0;

    public FacilityMap(Facility startFacility, Facility endFacility, int distance) {
        startFacility = this.startFacility;
        endFacility = this.endFacility;
        distance = this.distance;
    }

    public Facility getStartFacility() {
        return startFacility;
    }

    public void setStartFacility(Facility startFacility) {
        this.startFacility = startFacility;
    }

    public Facility getEndFacility() {
        return endFacility;
    }

    public void setEndFacility(Facility endFacility) {
        this.endFacility = endFacility;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

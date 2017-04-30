package src.main.java;

import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 4/19/2017.
 */
public final class FacilityManager {


    private static FacilityManager instance;

    public static FacilityManager getInstance() {
        if (instance == null) {
            instance = new FacilityManager();
        }
        return instance;
    }

    private FacilityManager() {}

    // additional methods
    public void computeShortestDistance(Facility start, Facility end) {

    }
}

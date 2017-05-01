package src.main.java;

import src.main.java.Interfaces.Facility;
import src.main.java.ReadXMLs.FacilityNetworkXMLLoader;

/**
 * Created by Jordan on 4/19/2017.
 */
public final class FacilityManager {


    FacilityDTO facilityDTO = null;

    private static FacilityManager instance;

    public static FacilityManager getInstance() {
        if (instance == null) {
            instance = new FacilityManager();
        }
        return instance;
    }

    private FacilityManager() {}

    public void buildFacilityMap() {
        FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXMLLoader();
        facilityNetworkXMLLoader.parse();
        // use factories to create factories and add neighbors
    }

    public void loadFacilityInventory() {

    }



    // additional methods
    public void computeShortestDistance(Facility start, Facility end) {

    }
}

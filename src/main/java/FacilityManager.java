package src.main.java;

import src.main.java.Exceptions.NullFacilityException;
import src.main.java.Interfaces.Facility;
import src.main.java.ReadXMLs.FacilityNetworkXMLLoader;
import src.main.java.ReadXMLs.ItemCatalogXMLLoader;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

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

    public static void resetFacilities(List<Facility> facilities) {
        for (Facility facility : facilities) {
            facility.setMinDistance(Double.POSITIVE_INFINITY);
        }
    }


}

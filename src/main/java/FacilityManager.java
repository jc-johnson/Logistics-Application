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

    private HashMap<String, Long> itemCatalog = null;   // holds item catalog from xml
    private List<Facility> allFacilities;

    private static FacilityManager instance;

    public static FacilityManager getInstance() {
        if (instance == null) {
            instance = new FacilityManager();
        }
        return instance;
    }

    private FacilityManager() {}

    public void buildFacilityMap() throws FileNotFoundException, NullFacilityException {
        FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXMLLoader();
        facilityNetworkXMLLoader.parse();
        // use factories to create factories and add neighbors
    }

    public void loadFacilityInventory() {

    }

    public void loadItemCatalog() {
        ItemCatalogXMLLoader itemCatalogXMLLoader = new ItemCatalogXMLLoader();
        // itemCatalog = itemCatalogXMLLoader.parse();
    }



    // additional methods
    public void computeShortestDistance(Facility start, Facility end) {

    }
}

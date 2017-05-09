package src.main.java;

import src.main.java.exceptions.NullFacilityException;
import src.main.java.interfaces.Facility;
import src.main.java.interfaces.impl.FacilityInventoryXMLLoaderImpl;
import src.main.java.interfaces.impl.FacilityNetworkXmlLoaderImpl;
import src.main.java.readxmls.FacilityInventoryXMLLoader;
import src.main.java.readxmls.FacilityNetworkXMLLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 4/19/2017.
 */
public final class FacilityManager {

    List<Facility> facilitiesList = new ArrayList<>();

    FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXmlLoaderImpl();
    FacilityInventoryXMLLoader facilityInventoryXMLLoader = new FacilityInventoryXMLLoaderImpl();


    FacilityDTO facilityDTO = null;

    private static FacilityManager instance;

    public static FacilityManager getInstance() {
        if (instance == null) {
            instance = new FacilityManager();
        }
        return instance;
    }

    private FacilityManager() {}

    public void loadFacilitesAndNeighborsFromXML() throws FileNotFoundException, NullFacilityException {
        facilitiesList = facilityNetworkXMLLoader.parse();
    }

    public void loadFacilityInventoryFromXML() throws FileNotFoundException, NullFacilityException {
        // facilityNetworkXMLLoader.parse(facilitiesList);
    }

    public void printEachFacilityOutput() {
        for (Facility facility : facilitiesList) {
            facility.printOutput();
        }
    }

    public void resetFacilitiesMinDistance() {
        for (Facility facility : facilitiesList) {
            facility.setMinDistance(Double.POSITIVE_INFINITY);
        }
    }

    public void resetPrevious() {
        for (Facility facility : facilitiesList) {
            facility.setPrevious(null);
        }
    }

    // Initalized schedule to 20 days
    public void initializeSchedules() {
        for (Facility facility : facilitiesList) {
            for (int i = 0; i < 21 ; i++) {
                facility.addScheduleDay(i, 10);
            }
        }
    }


}

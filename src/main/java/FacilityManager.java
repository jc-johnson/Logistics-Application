package src.main.java;

import src.main.java.Exceptions.NullFacilityException;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.FacilityInventoryXMLLoaderImpl;
import src.main.java.Interfaces.Impl.FacilityNetworkXmlLoaderImpl;
import src.main.java.ReadXMLs.FacilityInventoryXMLLoader;
import src.main.java.ReadXMLs.FacilityNetworkXMLLoader;
import src.main.java.ShortestPath.Edge;
import src.main.java.ShortestPath.FacilityEdge;

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
        facilityNetworkXMLLoader.parse(facilitiesList);
    }

    public void loadFacilityInventoryFromXML() throws FileNotFoundException, NullFacilityException {
        facilityNetworkXMLLoader.parse(facilitiesList);
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

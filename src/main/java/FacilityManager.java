package src.main.java;

import src.main.java.exceptions.EmptyNeighborListException;
import src.main.java.exceptions.NullFacilityException;
import src.main.java.exceptions.NullNeighborListException;
import src.main.java.exceptions.NullPriorityQueueException;
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

    FacilityDijkstra facilityDijkstra = new FacilityDijkstra(facilitiesList);


    FacilityDTO facilityDTO = null;

    private static FacilityManager instance;

    public static FacilityManager getInstance() {
        if (instance == null) {
            instance = new FacilityManager();
        }
        return instance;
    }

    private FacilityManager() {}

    public void loadFacilitesAndNeighborsFromXML(String path) throws FileNotFoundException, NullFacilityException {
        facilitiesList = facilityNetworkXMLLoader.parse(path);
        facilityDijkstra = new FacilityDijkstra(facilitiesList);
    }

    public void loadFacilityInventoryFromXML(String path) throws FileNotFoundException, NullFacilityException {
        facilityInventoryXMLLoader.parse(facilitiesList, path);
    }

    public void printEachFacilityOutput() {
        System.out.println("Facility Output: ");
        System.out.println("");
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
            for (int i = 1; i < 21 ; i++) {
                facility.updateSchedule(i, 10);
            }
        }
    }

    public void runShortestPath(String sourceFacility, String targetFacility) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException {
        facilityDijkstra.run(sourceFacility, targetFacility);
    }

    public List<Facility> getFacilitiesWithItem(Item item) {
        List<Facility> facilitiesWithItem = new ArrayList<>();
        for (Facility facility : facilitiesList) {
            if (facility.containsItem(item)) {
                facilitiesWithItem.add(facility);
            }
        }
        return facilitiesWithItem;
    }

    public Facility getFacility(String location) {
        for (Facility facility : facilitiesList) {
            if(facility.getLocation().equals(location)) {
                return facility;
            }
        }
        return null;
    }

    public double getShortestPathInDays(String sourceFacility, String targetFacility) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException {
        facilityDijkstra.run(sourceFacility, targetFacility);
        return facilityDijkstra.getTotalDays();
    }
}

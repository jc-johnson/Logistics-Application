package src.main.java;

import src.main.java.Interfaces.Facility;
import src.main.java.ShortestPath.Edge;
import src.main.java.ShortestPath.FacilityEdge;

import java.util.ArrayList;
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

    public static void printEachFacilityOutput(List<Facility> facilities) {
        for (Facility facility : facilities) {
            facility.printOutput();
        }
    }

    public static void resetFacilitiesMinDistance(List<Facility> facilities) {
        for (Facility facility : facilities) {
            facility.setMinDistance(Double.POSITIVE_INFINITY);
        }
    }

    public static void resetPrevious(List<Facility> facilities) {
        for (Facility facility : facilities) {
            facility.setPrevious(null);
        }
    }

    // Initalized schedule to 20 days
    public static void initializeSchedules(List<Facility> facilities) {
        for (Facility facility : facilities) {
            for (int i = 0; i < 21 ; i++) {
                facility.addScheduleDay(i, 10);
            }
        }
    }


}

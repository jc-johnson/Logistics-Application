package src.main.java;

import src.main.java.Exceptions.NullFacilityException;
import src.main.java.ReadXMLs.FacilityInventoryXMLLoader;
import src.main.java.ReadXMLs.FacilityNetworkXMLLoader;

import java.io.FileNotFoundException;

/**
 * Created by Jordan on 4/14/2017.
 */
public class Run {

    public static void main(String[] args) throws FileNotFoundException, NullFacilityException {


        // Load in Facilities Network from XML
        FacilityNetworkXMLLoader facilityNetworkXMLLoader = new FacilityNetworkXMLLoader();
        facilityNetworkXMLLoader.parse();

        // Parse and load inventory for each Facility
        FacilityInventoryXMLLoader facilityInventoryXMLLoader = new FacilityInventoryXMLLoader();
        facilityInventoryXMLLoader.parse();
        facilityInventoryXMLLoader.printFacilityInventory();

        // TODO:
        // Call Output 1
        // Call Output 2
        // Call Output 3

        // Atlanta Facility Test
    }
}

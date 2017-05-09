package src.main.java.readxmls;

import src.main.java.exceptions.NullFacilityException;
import src.main.java.interfaces.Facility;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Jordan on 4/16/2017.
 */
public interface FacilityNetworkXMLLoader {


    /**
     * Parse xml and load each facilities' neighbors
     */
    public List<Facility> parse() throws FileNotFoundException, NullFacilityException;

    public void printFacilitiesList(List<Facility> facilities);

}

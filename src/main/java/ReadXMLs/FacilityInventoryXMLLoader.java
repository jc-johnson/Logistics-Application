package src.main.java.readxmls;

import src.main.java.exceptions.NullFacilityException;
import src.main.java.interfaces.Facility;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Jordan on 4/13/2017.
 */
public interface FacilityInventoryXMLLoader {

    public void parse(List<Facility> facilities, String path) throws FileNotFoundException, NullFacilityException;
}

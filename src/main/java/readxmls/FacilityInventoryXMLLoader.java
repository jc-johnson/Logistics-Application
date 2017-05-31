package src.main.java.readxmls;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NullFacilityException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.Facility;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Jordan on 4/13/2017.
 */
public interface FacilityInventoryXMLLoader {

    void parse(List<Facility> facilities, String path) throws FileNotFoundException, NullFacilityException, DataValidationException, NullParameterException;
}

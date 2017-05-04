package src.main.java.Interfaces;

import src.main.java.Exceptions.NullFacilityException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Jordan on 4/13/2017.
 */
public interface XmlReader {

    public List<Facility> parse() throws FileNotFoundException, NullFacilityException;
}

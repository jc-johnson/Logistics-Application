package src.main.java.Interfaces;

import src.main.java.Exceptions.NullFacilityException;

import java.io.FileNotFoundException;

/**
 * Created by Jordan on 4/13/2017.
 */
public interface XmlReader {

    public void parse() throws FileNotFoundException, NullFacilityException;
}

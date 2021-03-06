package src.main.java.interfaces;

import org.xml.sax.SAXException;
import src.main.java.exceptions.EmptyPathException;
import src.main.java.exceptions.NullFacilityException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jordan on 4/13/2017.
 */
public interface XmlReader {

    public List<Facility> parse() throws FileNotFoundException, NullFacilityException;

    public List<Order> parseOrdersXml(String path) throws IOException, ParserConfigurationException, SAXException, EmptyPathException;
}

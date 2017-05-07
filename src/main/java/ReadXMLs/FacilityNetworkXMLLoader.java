package src.main.java.ReadXMLs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.Exceptions.NullFacilityException;
import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.FacilityNetworkXmlLoaderImpl;
import src.main.java.Interfaces.XmlReader;
import src.main.java.ShortestPath.FacilityEdge;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Jordan on 4/16/2017.
 */
public interface FacilityNetworkXMLLoader {


    /**
     * Parse xml and load each facilities' neighbors
     */
    public List<Facility> parse(List<Facility> facilities) throws FileNotFoundException, NullFacilityException;

    public void printFacilitiesList(List<Facility> facilities);

}

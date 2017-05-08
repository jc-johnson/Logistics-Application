package src.main.java;

import org.xml.sax.SAXException;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Impl.XmlReaderImpl;
import src.main.java.Interfaces.Order;
import src.main.java.Interfaces.XmlReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/7/2017.
 */
// TODO: Make into Singleton??
public class OrderProcessor {

    private List<Order> orders;
    private Map<Facility, Integer> facilityRecords = new HashMap<>();


    public void loadOrdersXml(String path) throws ParserConfigurationException, SAXException, IOException {
        XmlReader xmlReader = new XmlReaderImpl();
        xmlReader.parseOrdersXml(path);
    }

    public List<Facility> getFacilitiesWithItem(Item item) {
        // ask FacilityManager for list of facilities
        return null;
    }

    // sort facility records
    public void sortByLowestArrivalDate() {

    }

    public void getLowestFacilityArrivalDay() {

    }

    public void reduceItemQuantityNeeded(Item item, Integer quantity) {

    }

    public Integer computeTotalCost() {
        return null;
    }



}

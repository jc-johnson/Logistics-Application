package src.main.java.Interfaces.Impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.Exceptions.NullFacilityException;
import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Order;
import src.main.java.Interfaces.XmlReader;
import src.main.java.Item;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jordan on 5/7/2017.
 */
public class XmlReaderImpl implements XmlReader {
    @Override
    public List<Facility> parse() throws FileNotFoundException, NullFacilityException {
        return null;
    }

    @Override
    public List<Order> parseOrdersXml(String path) throws IOException, ParserConfigurationException, SAXException {
        // Open file path to xml
        File xmlFile = new File(path);
        // File xmlFile = new File("src/main/resources/FacilityInventory.xml"); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\FacilityInventory.xml
        if (xmlFile == null) {
            throw new FileNotFoundException();
        }
        // System.out.println("File found...");

        // Parser setup
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);

        // Optional but recommended
        document.getDocumentElement().normalize();

        // System.out.println("Root element : " + document.getDocumentElement().getNodeName());

        // Parse xml
        NodeList nodeList = document.getElementsByTagName("Facility");

        HashMap<Item, Integer> itemList = new HashMap<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            // System.out.println("\nCurrent Element : " + node.getNodeName());
            // System.out.println("");

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                String facilityLocation = element.getAttribute("Location");
                // System.out.println("Facility Location : " + facilityLocation);

                // Create facility based on location and load into list
                Facility currentFacility = FacilityFactory.createFacility(facilityLocation);
                if (currentFacility == null) {
                    //throw new NullFacilityException();
                }

                // get all items ids and quantity for each facility
                NodeList facilityItems = element.getElementsByTagName("Item");

                for (int j = 0; j < facilityItems.getLength(); j++) {

                    Element itemElement = (Element) facilityItems.item(j);

                    String itemId = itemElement.getElementsByTagName("Id").item(0).getTextContent();
                    String itemQuantityString = itemElement.getElementsByTagName("Quantity").item(0).getTextContent();
                    Integer itemQuantity = Integer.parseInt(itemQuantityString);

                    // System.out.println("Item Id : " + itemId);
                    // System.out.println("Quantity : " + itemQuantity);

                    Item item = new Item(itemId);
                    // currentFacility.addInventory(item, itemQuantity);

                    // Add inventory to the correct facility
                    /*for (Facility facility : facilities) {
                        if (facility.getLocation() == currentFacility.getLocation()) {
                            facility.addInventory(item, itemQuantity);
                        }
                    }*/
                }

                // facilities.add(currentFacility);
            }
        }
        return null;
    }
}

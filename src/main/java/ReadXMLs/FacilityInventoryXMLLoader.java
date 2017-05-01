package src.main.java.ReadXMLs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.XmlReader;
import src.main.java.Item;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Jordan on 4/13/2017.
 */
public class FacilityInventoryXMLLoader implements XmlReader{

    private HashMap<String, HashMap<String, Long>> facilityInventory = new HashMap<>();
    private HashMap<String, Long> inventoryQuantity = new HashMap<>();

    @Override
    public void parse() {
        try {
            // Open file path to xml
            File xmlFile = new File("src/main/resources/FacilityInventory.xml"); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\FacilityInventory.xml
            System.out.println("File found...");

            // Build parser and parse
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // Optional but recommended
            document.getDocumentElement().normalize();

            System.out.println("Root element : " + document.getDocumentElement().getNodeName());

            // Parse xml
            NodeList nodeList = document.getElementsByTagName("Facility");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nCurrent Element : " + node.getNodeName());
                System.out.println("");

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String facilityLocation = element.getAttribute("Location");
                    System.out.println("Facility Location : " + facilityLocation);

                    // get all items ids and quantity for each facility
                    NodeList facilityItems = element.getElementsByTagName("Item");

                    for (int j = 0; j < facilityItems.getLength(); j++) {

                        Element itemElement = (Element) facilityItems.item(j);

                        String itemId = itemElement.getElementsByTagName("Id").item(0).getTextContent();
                        String itemQuantityString = itemElement.getElementsByTagName("Quantity").item(0).getTextContent();
                        Long itemQuantity = (long) NumberFormat.getNumberInstance(Locale.US).parse(itemQuantityString);

                        System.out.println("Item Id : " + itemId);
                        System.out.println("Quantity : " + itemQuantity);

                       inventoryQuantity.put(itemId, itemQuantity);
                    }

                    facilityInventory.put(facilityLocation, inventoryQuantity);
                    inventoryQuantity = new HashMap<>();
                    System.out.println("");
                }
            }


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void load() {

    }

    public void printFacilityInventory() {
        System.out.println("Inventory Data Structure: ");

        for (Map.Entry<String, HashMap<String, Long>> facilityEntry : facilityInventory.entrySet()) {
            String facility = facilityEntry.getKey();
            System.out.println("Facility: " + facility);
            for (Map.Entry<String, Long> inventoryEntry : facilityEntry.getValue().entrySet()) {
                String inventoryId = inventoryEntry.getKey();
                Long inventoryQuantity = inventoryEntry.getValue();

                System.out.println("Inventory id: " + inventoryId);
                System.out.println("Inventory Quantity " + inventoryQuantity);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        FacilityInventoryXMLLoader facilityInventoryXMLLoader = new FacilityInventoryXMLLoader();
        facilityInventoryXMLLoader.parse();
        facilityInventoryXMLLoader.printFacilityInventory();
    }
}
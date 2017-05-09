package src.main.java.interfaces.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.exceptions.NullFacilityException;
import src.main.java.FacilityFactory;
import src.main.java.interfaces.Facility;
import src.main.java.Item;
import src.main.java.readxmls.FacilityInventoryXMLLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/7/2017.
 */
public class FacilityInventoryXMLLoaderImpl implements FacilityInventoryXMLLoader {

    @Override
    public void parse(List<Facility> facilities, String path) throws FileNotFoundException, NullFacilityException {
        try {
            // Open file path to xml
            File xmlFile = new File(path); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\FacilityInventory.xml

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

                    String currentFacility = element.getAttribute("Location");
                    // System.out.println("Facility Location : " + facilityLocation);


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
                        itemList.put(item, itemQuantity);

                        // currentFacility.addInventory(item, itemQuantity);

                        // Add inventory to the correct facility
                        /*for (Facility facility : facilities) {
                            if (facility.getLocation() == currentFacility.getLocation()) {
                                facility.addInventory(item, itemQuantity);
                            }
                        }*/
                    }

                    for (Facility facility : facilities) {
                        if (facility.getLocation() == currentFacility) {
                            for (Map.Entry<Item, Integer> entry : itemList.entrySet()) {
                                facility.updateInventory(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                }
            }


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printFacilityInventory(facilities);
    }

    private void printFacilityInventory(List<Facility> facilities) {
        for (Facility facility : facilities) {
            facility.printInventory();
        }
    }
}

package src.main.java;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.exceptions.DataValidationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Jordan on 5/9/2017.
 */
public final class ItemCatalogManager {

    private HashMap<String, Long> catalog = new HashMap<>();

    private static ItemCatalogManager instance;

    public static ItemCatalogManager getInstance() {
        if (instance == null) {
            instance = new ItemCatalogManager();
        }
        return instance;
    }

    private ItemCatalogManager() {}

    public boolean isRealItem(String itemID) throws DataValidationException {
        if (itemID.equals("") || itemID.isEmpty()) {
            throw new DataValidationException("Empty string parameter in ItemCatalogManager.isRealItem");
        }

        if (catalog.containsKey(itemID)) return true;
        return false;
    }

    public void parseItemsInventoryXML(String path) throws DataValidationException {
        if (path.equals("") || path.isEmpty()) {
            throw new DataValidationException("Empty string parameter in ItemCatalogManager.parseItemsInventoryXML");
        }

        try {
            // Open file path to xml
            File xmlFile = new File(path); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\ItemCatalog.xml
            if (xmlFile == null) {
                throw new FileNotFoundException();
            }
            // System.out.println("File found...");

            // Build parser and parse
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // Optional but recommended
            document.getDocumentElement().normalize();

            // System.out.println("Root element : " + document.getDocumentElement().getNodeName());

            NodeList nodeList = document.getElementsByTagName("Item");

            // Print elements and add to hashmap
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // System.out.println("\nCurrent Element : " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String itemId = element.getAttribute("Id");
                    String itemPriceString = element.getElementsByTagName("price").item(0).getTextContent();
                    Long price = (long) NumberFormat.getNumberInstance(Locale.US).parse(itemPriceString);

                    // System.out.println("Item ID : " + itemId);
                    // System.out.println("Price : " + itemPrice);

                    catalog.put(itemId, price);
                }
            }
            printItemsCatalog();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public void printItemsCatalog() {

        System.out.println("Item Catalog: ");
        System.out.println("");
        for (HashMap.Entry entry : catalog.entrySet()) {
            System.out.println(entry.getKey() + "\t:\t$ " + entry.getValue());
        }
        System.out.println("");
    }
}

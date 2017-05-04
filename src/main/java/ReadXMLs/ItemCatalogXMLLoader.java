package src.main.java.ReadXMLs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import src.main.java.Interfaces.XmlReader;

/**
 * Created by Jordan on 4/13/2017.
 */
public class ItemCatalogXMLLoader {

    public void parse() {
        HashMap<String, Long> catalogOutput = new HashMap<>();

        try {
            // Open file path to xml
            File xmlFile = new File("src/main/resources/ItemCatalog.xml"); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\ItemCatalog.xml
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
                    String itemPrice = element.getElementsByTagName("price").item(0).getTextContent();
                    Long price = (long) NumberFormat.getNumberInstance(Locale.US).parse(itemPrice);

                    // System.out.println("Item ID : " + itemId);
                    // System.out.println("Price : " + itemPrice);

                    catalogOutput.put(itemId, price);

                }
            }

            System.out.println("");
            System.out.println("Item Catalog: ");
            // print hashmap
            for (HashMap.Entry entry : catalogOutput.entrySet()) {
                System.out.println(entry.getKey() + "\t:\t$ " + entry.getValue());
            }
            System.out.println("");

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

    public static void main(String[] args) throws NumberFormatException, ParseException {

        ItemCatalogXMLLoader itemCatalogXMLLoader = new ItemCatalogXMLLoader();
        itemCatalogXMLLoader.parse();
    }
}

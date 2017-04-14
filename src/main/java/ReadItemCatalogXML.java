package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Created by Jordan on 4/13/2017.
 */
public class ReadItemCatalogXML {

    public static void main(String[] args) {

        System.out.println("Test");

        try {
            // Open file path to xml
            File xmlFile = new File("src/main/resources/ItemCatalog.xml"); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\ItemCatalog.xml
            System.out.println("File found");

            // Build parser and parse
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // Optional but recommended
            document.getDocumentElement().normalize();


            System.out.println("Root element : " + document.getDocumentElement().getNodeName());

            NodeList nodeList = document.getElementsByTagName("Item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nCurrent Element :" + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    System.out.println("" + element.getAttribute("id"));
                    System.out.println("" + element.getAttribute("id"));

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

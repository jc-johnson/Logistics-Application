package src.main.java.ReadXMLs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.Interfaces.XmlReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Jordan on 4/16/2017.
 */
public class LoadFacilityNetworkXML implements XmlReader {

    @Override
    public void parse() {

    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        try {
            // Open file path to xml
            File xmlFile = new File("src/main/resources/FacilityNetwork.xml"); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\ItemCatalog.xml
            System.out.println("File found...");

            // Build parser and parse
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // Optional but recommended
            document.getDocumentElement().normalize();


            System.out.println("Root element : " + document.getDocumentElement().getNodeName());

            NodeList nodeList = document.getElementsByTagName("Facility");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nCurrent Element : " + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    System.out.println("Facility Location : " + element.getAttribute("Location"));
                    System.out.println("Facility Rate : " + element.getElementsByTagName("Rate").item(0).getTextContent());
                    System.out.println("Facility Cost Per Day : " + element.getElementsByTagName("CostPerDay").item(0).getTextContent());

                    // get all links
                    // System.out.println("Facility Rate : " + element.getElementsByTagName("Rate"));

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

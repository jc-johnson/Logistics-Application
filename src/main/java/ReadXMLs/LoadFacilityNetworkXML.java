package src.main.java.ReadXMLs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.XmlReader;
import src.main.java.ShortestPathAlgorithm;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 4/16/2017.
 */
public class LoadFacilityNetworkXML implements XmlReader {

    private List<Facility> facilities = new ArrayList<>();


    @Override
    public void parse() {
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
                System.out.println("");

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String facilityLocation = element.getAttribute("Location");
                    String facilityRate = element.getElementsByTagName("Rate").item(0).getTextContent();
                    String facilityCostPerDay = element.getElementsByTagName("CostPerDay").item(0).getTextContent();

                    System.out.println("Facility Location : " + facilityLocation);
                    System.out.println("Facility Rate : " + facilityRate);
                    System.out.println("Facility Cost Per Day : " + facilityCostPerDay);

                    // Create facility based on location and load into list
                    facilities.add(FacilityFactory.createFacility(facilityLocation));


                    // get all links from a facility
                    NodeList facilityLinks = element.getElementsByTagName("link");

                    for (int j = 0; j < facilityLinks.getLength(); j++) {

                        Element linkElement = (Element) facilityLinks.item(j);

                        System.out.println("Link Location : " + linkElement.getAttribute("Location"));
                        System.out.println("Distance : " + linkElement.getElementsByTagName("distance").item(0).getTextContent());
                        System.out.println("");
                    }
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

    public void printFacilitiesList() {

        System.out.println("List of Facilities: ");

        for (Facility facility : facilities) {
            System.out.println(facility.toString());
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        LoadFacilityNetworkXML facilityNetwork = new LoadFacilityNetworkXML();
        facilityNetwork.parse();
        facilityNetwork.printFacilitiesList();

    }
}

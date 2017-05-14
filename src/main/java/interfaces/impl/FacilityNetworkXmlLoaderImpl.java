package src.main.java.interfaces.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.exceptions.NullFacilityException;
import src.main.java.FacilityImpl;
import src.main.java.interfaces.Facility;
import src.main.java.readxmls.FacilityNetworkXMLLoader;
import src.main.java.shortestpath.FacilityEdge;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jordan on 5/3/2017.
 */
public class FacilityNetworkXmlLoaderImpl implements FacilityNetworkXMLLoader {

    @Override
    public List<Facility> parse(String path) throws FileNotFoundException, NullFacilityException {
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
            NodeList nodeList = document.getElementsByTagName("Facility");

            List<Facility> facilities = new ArrayList<>();

            // Parse xml and build data structure from it
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // System.out.println("\nCurrent Element : " + node.getNodeName());
                // System.out.println("");

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String facilityLocation = element.getAttribute("Location");
                    String facilityRateString = element.getElementsByTagName("Rate").item(0).getTextContent();
                    String facilityCostPerDayString = element.getElementsByTagName("CostPerDay").item(0).getTextContent();
                    Integer facilityRate = Integer.parseInt(facilityRateString);
                    Integer facilityCostPerDay = Integer.parseInt(facilityCostPerDayString);

                    // System.out.println("Facility Location : " + facilityLocation);
                    // System.out.println("Facility Rate : " + facilityRate);
                    // System.out.println("Facility Cost Per Day : " + facilityCostPerDay);

                    Facility currentFacility = new FacilityImpl(facilityLocation, facilityRate, facilityCostPerDay);

                    // get all facility neighbors
                    NodeList facilityLinks = element.getElementsByTagName("link");

                    List<FacilityEdge> facilityEdges = new ArrayList<>();

                    for (int j = 0; j < facilityLinks.getLength(); j++) {

                        Element linkElement = (Element) facilityLinks.item(j);

                        String linkLocation = linkElement.getAttribute("Location");
                        String linkDistanceString = linkElement.getElementsByTagName("distance").item(0).getTextContent();
                        Integer linkDistance = Integer.parseInt(linkDistanceString);

                        // System.out.println("Link Location : " + linkLocation);
                        // System.out.println("Distance : " + linkDistanceString);

                        // Add neighbors to Facility
                        FacilityEdge facilityEdge = new FacilityEdge(linkLocation, linkDistance);
                        facilityEdges.add(facilityEdge);
                        // currentFacility.addNeighbor(facilityEdge);

                    }

                    for (FacilityEdge facilityEdge : facilityEdges) {
                        currentFacility.addNeighbor(facilityEdge);
                    }
                    facilities.add(currentFacility);
                }
            }


            // printFacilitiesList(facilities);
            return facilities;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void printFacilitiesList(List<Facility> facilities) {

        System.out.println("List of facilities: ");

        for (Facility facility : facilities) {
            System.out.println(facility.getLocation());

            System.out.println("Neighbors: ");
            for (FacilityEdge facilityEdge : facility.getNeighborList()) {
                System.out.println("Target: " + facilityEdge.getTarget() + " Weight: " + facilityEdge.getWeight());
            }

            facility.printNeighbors();
            System.out.println("");
        }
        System.out.println("");
    }

    private boolean facilityInList(List<Facility> facilities, Facility facility) {

        // based on facility location
        for (Facility tempFacility : facilities) {
            // If location is already in list update facility attributes
            if (tempFacility.getLocation().equals(facility.getLocation()))
                return true;
        }
        return false;
    }
}

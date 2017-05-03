package src.main.java.ReadXMLs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.Exceptions.NullFacilityException;
import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.XmlReader;
import src.main.java.ShortestPath.FacilityEdge;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Jordan on 4/16/2017.
 */
public class FacilityNetworkXMLLoader implements XmlReader {

    private List<Facility> facilities = new ArrayList<>();

    private HashMap<Facility, HashMap<Facility, Long>> facilityNetwork = new HashMap<>();
    private HashMap<Facility, Long> neighborFacilities = new HashMap<>();

    @Override
    public void parse() throws FileNotFoundException, NullFacilityException {
        try {
            // Open file path to xml
            File xmlFile = new File("src/main/resources/FacilityNetwork.xml"); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\ItemCatalog.xml
            if (xmlFile == null) {
                throw new FileNotFoundException();
            }
            System.out.println("File found...");

            // Build parser and parse
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // Optional but recommended
            document.getDocumentElement().normalize();

            System.out.println("Root element : " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("Facility");

            // Parse xml and build data structure from it
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
                    Facility currentFacility = FacilityFactory.createFacility(facilityLocation);
                    if (currentFacility == null) {
                        throw new NullFacilityException();
                    }
                    facilities.add(currentFacility);

                    // get all links from a facility
                    NodeList facilityLinks = element.getElementsByTagName("link");

                    for (int j = 0; j < facilityLinks.getLength(); j++) {

                        Element linkElement = (Element) facilityLinks.item(j);

                        String linkLocation = linkElement.getAttribute("Location");
                        String linkDistanceString = linkElement.getElementsByTagName("distance").item(0).getTextContent();
                        Long linkDistance = (long) NumberFormat.getNumberInstance(Locale.US).parse(linkDistanceString);

                        System.out.println("Link Location : " + linkLocation);
                        System.out.println("Distance : " + linkDistanceString);

                        Facility linkFacility = FacilityFactory.createFacility(linkLocation);
                        if (linkFacility == null) {
                            throw new NullFacilityException();
                        }

                        // Add neighbors to Facility
                        FacilityEdge facilityEdge = new FacilityEdge(linkFacility, linkDistance);
                        currentFacility.addNeighbor(facilityEdge);

                        // Add linking facilities to inner hashmap for overall ds
                        neighborFacilities.put(linkFacility, linkDistance);

                    }
                    facilityNetwork.put(currentFacility, neighborFacilities);
                    neighborFacilities = new HashMap<>();
                    System.out.println("");
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
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullFacilityException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void printFacilitiesList() {

        System.out.println("List of Facilities: ");

        for (Facility facility : facilities) {
            System.out.println(facility.getLocation());
            facility.printNeighbors();
            System.out.println("");
        }
        System.out.println("");

        /*System.out.println("");
        System.out.println("Facilities Network Data Structure: ");
        System.out.println("");

        for (Map.Entry<Facility, HashMap<Facility, Long>> facilityEntry : facilityNetwork.entrySet()) {
            Facility facility = facilityEntry.getKey();
            System.out.println("Facility: " + facility.getLocation());
            for (Map.Entry<Facility, Long> adjacentFacility : facilityEntry.getValue().entrySet()) {
                Facility neighborFacility = adjacentFacility.getKey();
                Long distance = adjacentFacility.getValue();

                System.out.println("Neighbor Facility: " + neighborFacility.getLocation() + ". Distance: " + distance);
            }
            System.out.println("");
        }*/
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, NullFacilityException {
        FacilityNetworkXMLLoader facilityNetwork = new FacilityNetworkXMLLoader();
        facilityNetwork.parse();
        facilityNetwork.printFacilitiesList();

    }
}

package src.main.java.Interfaces.Impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.Exceptions.NullFacilityException;
import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.XmlReader;
import src.main.java.ReadXMLs.FacilityNetworkXMLLoader;
import src.main.java.ShortestPath.FacilityEdge;

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
    public List<Facility> parse(List<Facility> facilities) throws FileNotFoundException, NullFacilityException {
        try {
            // List<Facility> facilities = new ArrayList<>();

            // Open file path to xml
            File xmlFile = new File("src/main/resources/FacilityNetwork.xml"); // File Path C:\Logistics-Program\LogisticsApplication\src\main\resources\ItemCatalog.xml
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

            // Parse xml and build data structure from it
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                // System.out.println("\nCurrent Element : " + node.getNodeName());
                // System.out.println("");

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String facilityLocation = element.getAttribute("Location");
                    String facilityRate = element.getElementsByTagName("Rate").item(0).getTextContent();
                    String facilityCostPerDay = element.getElementsByTagName("CostPerDay").item(0).getTextContent();

                    // System.out.println("Facility Location : " + facilityLocation);
                    // System.out.println("Facility Rate : " + facilityRate);
                    // System.out.println("Facility Cost Per Day : " + facilityCostPerDay);

                    // Create facility based on location
                    Facility currentFacility = FacilityFactory.createFacility(facilityLocation);
                    if (currentFacility == null) {
                        throw new NullFacilityException();
                    }

                    // get all facility neighbors
                    NodeList facilityLinks = element.getElementsByTagName("link");

                    for (int j = 0; j < facilityLinks.getLength(); j++) {

                        Element linkElement = (Element) facilityLinks.item(j);

                        String linkLocation = linkElement.getAttribute("Location");
                        String linkDistanceString = linkElement.getElementsByTagName("distance").item(0).getTextContent();
                        Long linkDistance = (long) NumberFormat.getNumberInstance(Locale.US).parse(linkDistanceString);

                        // System.out.println("Link Location : " + linkLocation);
                        // System.out.println("Distance : " + linkDistanceString);

                        Facility linkFacility = FacilityFactory.createFacility(linkLocation);
                        if (linkFacility == null) {
                            throw new NullFacilityException();
                        }

                        // Add neighbors to Facility
                        FacilityEdge facilityEdge = new FacilityEdge(linkFacility, linkDistance);
                        currentFacility.addNeighbor(facilityEdge);

                    }

                    facilities.add(currentFacility);
                }
            }

            return facilities;

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
        }

        return null;
    }

    @Override
    public void printFacilitiesList(List<Facility> facilities) {

        System.out.println("List of Facilities: ");

        for (Facility facility : facilities) {
            System.out.println(facility.getLocation());
            facility.printNeighbors();
            System.out.println("");
        }
        System.out.println("");
    }
}

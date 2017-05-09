package src.main.java.interfaces.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import src.main.java.exceptions.NullFacilityException;
import src.main.java.interfaces.Facility;
import src.main.java.interfaces.Order;
import src.main.java.interfaces.XmlReader;
import src.main.java.Item;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jordan on 5/7/2017.
 */
public class XmlReaderImpl implements XmlReader {
    @Override
    public List<Facility> parse() throws FileNotFoundException, NullFacilityException {
        return null;
    }

    @Override
    public List<Order> parseOrdersXml(String path) throws IOException, ParserConfigurationException, SAXException {
        // Open file path to xml
        File xmlFile = new File(path);
        if (xmlFile == null) {
            throw new FileNotFoundException();
        }
        System.out.println("File found...");

        // Parser setup
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);

        // Optional but recommended
        document.getDocumentElement().normalize();

        System.out.println("Root element : " + document.getDocumentElement().getNodeName());

        // Parse xml
        NodeList nodeList = document.getElementsByTagName("Order");

        List<Order> orderList = new ArrayList<>();
        HashMap<Item, Integer> itemList = new HashMap<>();

        // For each order
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            // System.out.println("\nCurrent Element : " + node.getNodeName());
            // System.out.println("");

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                String orderId = element.getAttribute("Id");
                String destination = element.getElementsByTagName("Destination").item(0).getTextContent();
                String orderTimeString = element.getElementsByTagName("OrderTime").item(0).getTextContent();
                Integer orderTime = Integer.parseInt(orderTimeString);

                System.out.println("Order Id: " + orderId);
                System.out.println("Order Time: " + orderTimeString);
                System.out.println("Destination: " + destination);

                Order order = new OrderImpl(orderId, destination, orderTime);

                // get all items ids and quantity for each order
                NodeList orderItems = element.getElementsByTagName("Item");

                for (int j = 0; j < orderItems.getLength(); j++) {

                    Element itemElement = (Element) orderItems.item(j);

                    String itemId = itemElement.getAttribute("Id");
                    String itemQuantityString = itemElement.getElementsByTagName("Quantity").item(0).getTextContent();
                    Integer itemQuantity = Integer.parseInt(itemQuantityString);

                    System.out.println("Item Id : " + itemId);
                    System.out.println("Quantity : " + itemQuantity);

                    Item item = new Item(itemId);
                    order.addOrderItem(item, itemQuantity);

                }

                orderList.add(order);
            }
        }

        System.out.println("Order List: ");
        for (Order order : orderList) {
            order.printOutput();
        }

        return orderList;
    }
}


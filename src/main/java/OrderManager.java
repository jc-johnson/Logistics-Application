package src.main.java;

import org.xml.sax.SAXException;
import src.main.java.exceptions.*;
import src.main.java.interfaces.Order;
import src.main.java.interfaces.OrderItemCalculation;
import src.main.java.interfaces.Solution;
import src.main.java.interfaces.XmlReader;
import src.main.java.interfaces.impl.OrderImpl;
import src.main.java.interfaces.impl.XmlReaderImpl;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jordan on 5/18/2017.
 */
public final class OrderManager {

    private List<Order> orders;

    private static OrderManager instance;
    private static OrderProcessor orderProcessor = OrderProcessor.getInstance();

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    private OrderManager() {}

    public void loadOrdersXml(String path) throws ParserConfigurationException, SAXException, IOException, EmptyPathException {
        XmlReader xmlReader = new XmlReaderImpl();
        orders = xmlReader.parseOrdersXml(path);
    }

    public void createFacilityRecordsFromOrders() throws NoAvailableDaysException, NullParameterException, DataValidationException, NegativeQuantityException, NullPriorityQueueException, NullFacilityException, EmptyNeighborListException, NullNeighborListException, FacilityNotFoundException {
        orderProcessor.generateFacilityRecords(orders);
    }

    public void printOrders() throws NullParameterException, DataValidationException {

        Integer i = 1;
        for (Order order : orders) {
            System.out.println("-----------------------------------------------");
            System.out.println("Order #" + i);
            order.printOutput();
            i++;
            System.out.println("-----------------------------------------------");
            System.out.println("");
            System.out.println("");
        }
    }

    public void addOrderItemCalculationToOrder(String orderId, OrderItemCalculation orderItemCalculation) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                order.addOrderItemCalculation(orderItemCalculation);
            }
        }

    }

    public void computeSolutions() {

    }

    public void addSolutionToOrder(Solution solution) throws NullParameterException {
        if (solution == null) throw new NullParameterException();

        // for each order
            // if order.get id and order.get quantity
    }

    public void addItemCalculationToOrderSolution(OrderItemCalculation orderItemCalculation) throws NullParameterException {
        /*
        if (orderItemCalculation == null) throw new NullParameterException("Null Order Item Calculation");

        for (Order order : orders) {
            for (Item item : order.getOrderItems()) {
                if (item.getId() == orderItemCalculation.getItemId()) {
                    if (order.getSolution != null) {
                        order.getSolution.addItemCalculation(orderItemCalculation);
                    }
                }
            }
        }
        */
    }
}

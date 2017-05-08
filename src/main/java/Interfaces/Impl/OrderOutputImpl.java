package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.Order;
import src.main.java.Interfaces.OrderOutput;
import src.main.java.Item;

import java.util.List;

/**
 * Created by Jordan on 5/7/2017.
 */
public class OrderOutputImpl implements OrderOutput {

    private Order order;

    public OrderOutputImpl(Order order) {
        this.order = order;
    }

    @Override
    public void printOrder() {
        System.out.println("\tOrder Id:\t\t" + order.getId());
        System.out.println("\tOrder Time:\t\tDay " + order.getOrderTime());
        System.out.println("\tDestination:\t" + order.getDestination());
        System.out.println("");

        System.out.println("\tList of Order Items:");
        order.printItemList();
        System.out.println("");

        System.out.println("\tProcessing Solution:");
        System.out.println("");
        order.printProcessingSolution();

        System.out.println("Total Cost:\t");
    }
}

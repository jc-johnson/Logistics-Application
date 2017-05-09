package src.main.java.interfaces.impl;

import src.main.java.interfaces.ActiveInventoryPrinter;
import src.main.java.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordan on 5/2/2017.
 */
public class ActiveInventoryPrinterImpl implements ActiveInventoryPrinter {
    @Override
    public void print(HashMap<Item, Integer> activeInventory) {
        System.out.println("Item ID" + "\t\t" + "Quantity");
        for (Map.Entry<Item, Integer> entry : activeInventory.entrySet()) {
            System.out.println(entry.getKey().getId() + "\t\t" + entry.getValue().toString());
        }
        System.out.println("");
    }
}

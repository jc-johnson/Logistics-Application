package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.ActiveInventoryPrinter;
import src.main.java.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 5/2/2017.
 */
public class ActiveInventoryPrinterImpl implements ActiveInventoryPrinter {
    @Override
    public void print(HashMap<Item, Integer> activeInventory) {
        for (Map.Entry<Item, Integer> entry : activeInventory.entrySet()) {
            System.out.println(entry.getKey().getId() + " : " + entry.getValue().toString());
        }
        System.out.println("");
    }
}

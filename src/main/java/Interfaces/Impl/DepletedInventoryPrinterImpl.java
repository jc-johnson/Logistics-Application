package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.DepletedInventoryPrinter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 5/2/2017.
 */
public class DepletedInventoryPrinterImpl implements DepletedInventoryPrinter {

    @Override
    public void print (HashMap<String, Integer> depletedInventory) {
        if (depletedInventory.size() == 0) {
            System.out.print("Depleted (Used-Up) Inventory: ");
            System.out.println("None");
        } else {
            System.out.println("Depleted (Used-Up) Inventory: ");
            Iterator iterator = depletedInventory.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                System.out.println(pair.getKey().toString() + " = " + pair.getValue().toString());
                iterator.remove(); // avoids a ConcurrentModificationException
            }
        }
    }
}

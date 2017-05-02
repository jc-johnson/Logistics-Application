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
    public void print(HashMap<Item, Long> activeInventory) {
        Iterator iterator = activeInventory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.println(pair.getKey().toString() + " = " + pair.getValue().toString());
            iterator.remove(); // avoids a ConcurrentModificationException
        }
    }
}

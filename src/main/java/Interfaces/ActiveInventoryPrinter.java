package src.main.java.Interfaces;

import src.main.java.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordan on 5/2/2017.
 */
public interface ActiveInventoryPrinter {

    public void print(HashMap<Item, Long> activeInventory);
}

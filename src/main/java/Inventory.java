package src.main.java;

import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public interface Inventory {

    void updateInventoryItem(Item item, Integer quantity);
    void printInventory();


}

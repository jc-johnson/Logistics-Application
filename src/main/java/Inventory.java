package src.main.java;

import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public interface Inventory {

    void addInventoryItem(Item item, Integer quantity);
    void addInvnetoryItems(Map<Item, Integer> items);
    void reduceInventory(Item item, Integer quantity);
    void printInventory();
    void printActiveInventory();
    void printDepletedInventory();

}

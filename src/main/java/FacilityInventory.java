package src.main.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public class FacilityInventory implements Inventory {

    Map<Item, Integer> activeInventory = new HashMap<>();
    Map<Item, Integer> depletedInventory = new HashMap<>();

    @Override
    public void addInventoryItem(Item item, Integer quantity) {

    }

    @Override
    public void addInvnetoryItems(Map<Item, Integer> items) {

    }

    @Override
    public void reduceInventory(Item item, Integer quantity) {

    }

    @Override
    public void printInventory() {

    }

    @Override
    public void printActiveInventory() {

    }

    @Override
    public void printDepletedInventory() {

    }
}

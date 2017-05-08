package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.ItemPrinter;
import src.main.java.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/7/2017.
 */
public class ItemPrinterImpl implements ItemPrinter {

    @Override
    public void print(Map<Item, Integer> items) {

        int i = 1;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println(i + ") Item ID:\t\t" + item.getId() + ",\t\tQuantity: " + quantity);
            i++;
        }
    }
}

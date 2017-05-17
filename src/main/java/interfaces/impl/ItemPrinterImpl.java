package src.main.java.interfaces.impl;

import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.ItemPrinter;
import src.main.java.Item;

import java.util.Map;

/**
 * Created by Jordan on 5/7/2017.
 */
public class ItemPrinterImpl implements ItemPrinter {

    @Override
    public void print(Map<Item, Integer> items) {
        if (items == null) try {
            throw new NullParameterException();
        } catch (NullParameterException e) {
            e.printStackTrace();
        }

        int i = 1;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println(i + ") Item ID:\t\t" + item.getId() + ",\t\tQuantity: " + quantity);
            i++;
        }
    }
}

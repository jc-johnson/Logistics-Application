package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.ItemPrinter;
import src.main.java.Item;

import java.util.List;

/**
 * Created by Jordan on 5/7/2017.
 */
public class ItemPrinterImpl implements ItemPrinter {


    @Override
    public void print(List<Item> items) {
        for(Item item : items) {
            System.out.println(item);
        }
    }
}

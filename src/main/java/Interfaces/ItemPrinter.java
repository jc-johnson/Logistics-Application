package src.main.java.Interfaces;

import com.sun.org.apache.xpath.internal.operations.Or;
import src.main.java.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/7/2017.
 */
public interface ItemPrinter {

    void print(Map<Item, Integer> items);
}

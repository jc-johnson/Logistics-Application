package src.main.java.interfaces;

import src.main.java.Item;

/**
 * Created by Jordan on 5/13/2017.
 */
public interface LogisticsRecord {

    String getItemId();

    Integer getItemQuantityProcessed();

    void setItemId();

    void setItemQuantityProcessed();

    void print();
}

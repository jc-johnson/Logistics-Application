package src.main.java.interfaces;

import src.main.java.Item;
import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.FacilityNotFoundException;

import java.util.List;

/**
 * Created by Jordan on 5/13/2017.
 */
public interface LogisticsRecord {

    String getItemId();

    Integer getItemQuantityProcessed();

    void setItemId();

    void setItemQuantityProcessed();

    void addLogisticsDetail(LogisticsDetail logisticsDetail);

    Integer getLogisticsDetailSize();

    void addItemArrival(ItemArrival itemArrival);

    Integer getTotalItemCost() throws DataValidationException, FacilityNotFoundException;

    Integer getFirstProcessingDay();

    Integer getLastProcessingDay();

    Integer getTotalSources();

    Integer getTotalItemQuantity();

    void setTotalItemQuantity(Integer itemQuantity);

    void print();

    // returns copy of item arrivals
    List<ItemArrival> getItemArrivals();

    LogisticsDetail getLogisticsDetail(Integer i);
}

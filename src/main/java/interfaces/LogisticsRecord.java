package src.main.java.interfaces;

import src.main.java.Item;
import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.FacilityNotFoundException;

/**
 * Created by Jordan on 5/13/2017.
 */
public interface LogisticsRecord {

    String getItemId();

    Integer getItemQuantityProcessed();

    void setItemId();

    void setItemQuantityProcessed();

    void addLogisticsDetail(LogisticsDetail logisticsDetail);

    // void removeLogisticsDetail(LogisticsDetail logisticsDetail);

    Integer getLogisticsDetailSize();

    void addItemArrival(ItemArrival itemArrival);

    Integer getTotalItemCost() throws DataValidationException, FacilityNotFoundException;

    Integer getFirstProcessingDay();

    Integer getLastProcessingDay();

    Integer getTotalSources();

    void print();
}

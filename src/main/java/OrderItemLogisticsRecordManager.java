package src.main.java;

import src.main.java.interfaces.FacilityRecord;
import src.main.java.interfaces.LogisticsRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/18/2017.
 */
public final class OrderItemLogisticsRecordManager {

    private Map<Item, LogisticsRecord> itemLogisticsRecords = new HashMap<>();

    private static OrderItemLogisticsRecordManager instance;

    public static OrderItemLogisticsRecordManager getInstance(){
        if (instance == null) {
            instance = new OrderItemLogisticsRecordManager();
        }
        return instance;
    }

    private OrderItemLogisticsRecordManager() {}


    public void generateLogisticsDetails(List<FacilityRecord> facilityRecords) {


    }

}

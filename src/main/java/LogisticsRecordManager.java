package src.main.java;

import src.main.java.exceptions.DataValidationException;
import src.main.java.interfaces.*;
import src.main.java.interfaces.impl.ItemArrivalImpl;
import src.main.java.interfaces.impl.LogisticsDetailImpl;
import src.main.java.interfaces.impl.LogisticsRecordImpl;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jordan on 5/18/2017.
 */
public final class LogisticsRecordManager {

    private Map<Item, LogisticsRecord> itemLogisticsRecords = new HashMap<>();
    private List<LogisticsRecord> logisticsRecords = new ArrayList<>();
    private List<FacilityRecord> facilityRecords = new ArrayList<>();

    private static LogisticsRecordManager instance;

    public static LogisticsRecordManager getInstance(){
        if (instance == null) {
            instance = new LogisticsRecordManager();
        }
        return instance;
    }

    private LogisticsRecordManager() {}

    public void addFacilityRecord(FacilityRecord facilityRecord) {
        facilityRecords.add(facilityRecord);
    }

    public void generateLogisticsRecords() throws DataValidationException {
        List<LogisticsDetail> logisticsDetails = new ArrayList<>();
        List<ItemArrival> itemArrivals = new ArrayList<>();

        for (FacilityRecord facilityRecord : facilityRecords) {
            LogisticsDetail logisticsDetail = new LogisticsDetailImpl();
            logisticsDetail = createLogisticsDetail(facilityRecord);
            logisticsDetails.add(logisticsDetail);
        }

        // TODO: Sort logistics details

        for (LogisticsDetail logisticsDetail : logisticsDetails) {
            ItemArrival itemArrival = new ItemArrivalImpl();
            itemArrival = createItemArrival(logisticsDetail);
            itemArrivals.add(itemArrival);
        }

        LogisticsRecord logisticsRecord = new LogisticsRecordImpl();
        // add item id
        // add quantity
        // add all item arrivals
        // add all log details
        // logisticRecords.add(logicsticsRecord);

        // For each log record create a solution


    }

    public Solution createSolution(LogisticsRecord) {

        // create solution
        // find corresponding order with order ID
        // add solution to Order

    }

    public LogisticsDetail createLogisticsDetail(FacilityRecord facilityRecord) throws DataValidationException {
        LogisticsDetail logisticsDetail = new LogisticsDetailImpl();
        // TODO: Query mediator for this
        Facility facility = FacilityManager.getInstance().getFacility(facilityRecord.getFacilityLocation());
        String logisticDetailsCity = facility.getCity();

        logisticsDetail.setFacilityLocation(logisticDetailsCity);
        logisticsDetail.setProcessingStart(facilityRecord.getArrivalDay());
        logisticsDetail.setProcessingEnd(facilityRecord.getProcessingEndDay());

        Integer travelStart = facilityRecord.getTravelTime() - (facilityRecord.getTravelTime()-1);
        logisticsDetail.setTravelStart(travelStart);
        Integer travelEnd = travelStart + facilityRecord.getTravelTime();
        logisticsDetail.setTravelEnd(travelEnd);

        return logisticsDetail;
    }

    public ItemArrival createItemArrival(LogisticsDetail logisticsDetail) {
        ItemArrival itemArrival = new ItemArrivalImpl();
        itemArrival.setDay(logisticsDetail.getTravelEnd());
        itemArrival.setQuantity(logisticsDetail.getQuantity);

        return itemArrival;
    }

    public void print() {
        for (Map.Entry<Item, List<LogisticsDetail>> entry : logisticsDetailMap.entrySet()) {
            System.out.println("Item ID: " + entry.getKey().getId() + ", Quantity " + "");

        }
    }

}

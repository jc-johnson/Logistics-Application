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
 *
 * Should go in Record manager --
 * 1) OrderProcessor generates FacilityRecords from orders - and passes all FacilityRecords to RecordManager
 * 2) RecordManager generates LogisticsDetails from FacilityRecords
 * 3) RecordManager generates ItemArrivals from LogisticsDetails
 * 4) RecordManager generates LogisticsRecords from ItemArrivals
 *      LogicsticsRecords owns a List<ItemArrivals> and List<LogisticsDetails>
 * 5) RecordManager generates ProcessingSolution from LogisticsRecords
 * Note: record manager should own list of orders (or at least parse for order list) to get this process started
 *
 */
public final class LogisticsRecordManager {


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


    public void generateAllLogisticsDetails() throws DataValidationException {
        List<LogisticsDetail> logisticsDetails = new ArrayList<>();
        for (FacilityRecord facilityRecord : facilityRecords) {
            logisticsDetails.add(this.createLogisticsDetail(facilityRecord));
        }
        // TODO: sort log details on arrival day
        LogisticsRecord logisticsRecord = new LogisticsRecordImpl();
        for (LogisticsDetail logisticsDetail : logisticsDetails) {
            logisticsRecord.addLogisticsDetail(logisticsDetail);
        }
        logisticsRecords.add(logisticsRecord);
    }

    public void generateLogisticsArrivals() throws DataValidationException {

        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            if (logisticsRecords.getLogisticsDetailsSize == 0) throw new DataValidationException("LogicsticsRecordManager.generateLogisticsArrivals() : No logistics details");

            for (int i = 0; i < logisticsRecord.getLogisticsDetailSize().size ; i++) {
                ItemArrival itemArrival = createItemArrival(logisticsRecord.getLogisticsDetail(i));
                logisticsRecord.addItemArrival(itemArrival);
            }

        }
        // for each logRecord
        // sort log details
        // fore each logDetail
        // generate ItemArrival
        // add ItemArrival to logRecord
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
        itemArrival.setArrivalDay(logisticsDetail.getTravelEnd());
        itemArrival.setItemsProcessed(logisticsDetail.getItemsProcessed());

        return itemArrival;
    }

    public Solution createSolution(LogisticsRecord logisticsRecord) {

        // create solution
        // find corresponding order with order ID
        // add solution to Order
        return  null;

    }

    public void print() {
    }

}

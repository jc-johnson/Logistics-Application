package src.main.java;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.FacilityNotFoundException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.*;
import src.main.java.interfaces.impl.*;
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

    public void gernerateLogisticsRecord(FacilityRecord facilityRecord) throws DataValidationException {

        if (facilityRecord == null) throw new DataValidationException("Null Facility Record");

        LogisticsRecord logisticsRecord = new LogisticsRecordImpl(facilityRecord.getItemID());
        logisticsRecord.setTotalItemQuantity(facilityRecord.getTotalItemQuantity());

        LogisticsDetail logisticsDetail = createLogisticsDetail(facilityRecord);

        ItemArrival itemArrival = new ItemArrivalImpl();
        itemArrival = createItemArrival(logisticsDetail);

        for (LogisticsRecord currentLogisticsRecord : logisticsRecords) {
            // logistics record already exists
            if (currentLogisticsRecord.getItemId().equals(logisticsRecord.getItemId())) {
                currentLogisticsRecord.addLogisticsDetail(logisticsDetail);
                currentLogisticsRecord.addItemArrival(itemArrival);
                return;
            }
        }

        logisticsRecord.addLogisticsDetail(logisticsDetail);
        logisticsRecord.addItemArrival(itemArrival);
        logisticsRecords.add(logisticsRecord);
    }

    private LogisticsDetail createLogisticsDetail(FacilityRecord facilityRecord) throws DataValidationException {

        if (facilityRecord == null) throw new DataValidationException("Null Facility Record");

        LogisticsDetail logisticsDetail = new LogisticsDetailImpl();
        Facility facility = FacilityManager.getInstance().getFacility(facilityRecord.getFacilityLocation());
        String logisticDetailsCity = facility.getCity();

        logisticsDetail.setFacilityLocation(logisticDetailsCity);
        logisticsDetail.setProcessingStart(facilityRecord.getArrivalDay());
        logisticsDetail.setProcessingEnd(facilityRecord.getProcessingEndDay());
        logisticsDetail.setTotalQuantity(facilityRecord.getTotalItemQuantity());
        logisticsDetail.setitemsProcessed(facilityRecord.getNumberOfItemsAbleToProcess());

        Integer travelStart = facilityRecord.getTravelTime() - (facilityRecord.getTravelTime()-1);
        logisticsDetail.setTravelStart(travelStart);
        Integer travelEnd = travelStart + facilityRecord.getTravelTime();
        logisticsDetail.setTravelEnd(travelEnd);

        return logisticsDetail;
    }

    private ItemArrival createItemArrival(LogisticsDetail logisticsDetail) throws DataValidationException {

        if (logisticsDetail == null) throw new DataValidationException("Null Logistics Detail");

        ItemArrival itemArrival = new ItemArrivalImpl();
        itemArrival.setArrivalDay(logisticsDetail.getTravelEnd());
        itemArrival.setItemsProcessed(logisticsDetail.getItemsProcessed());
        itemArrival.setPercentOfTotal(logisticsDetail.getTotalQuantity());


        return itemArrival;
    }

    public Solution createSolution(LogisticsRecord logisticsRecord) throws NullParameterException, DataValidationException, FacilityNotFoundException {

        if (logisticsRecord == null) throw new DataValidationException("Null Logistics Record");

        for (LogisticsRecord currentLogisticsRecord : logisticsRecords) {
            Solution solution = new SolutionImpl();
            solution.computeSolution(currentLogisticsRecord);
            OrderManager.getInstance().addSolutionToOrder(solution);

            OrderItemCalculation orderItemCalculation = computeOrderItemCalculation(logisticsRecord);
            OrderManager.getInstance().addItemCalculationToOrderSolution(orderItemCalculation);

        }


        // create solution
        // find corresponding order with order ID
        // add solution to Order
        return  null;

    }

    private OrderItemCalculation computeOrderItemCalculation(LogisticsRecord logisticsRecord) throws DataValidationException, FacilityNotFoundException {

        if (logisticsRecord == null) throw new DataValidationException("Null Logistics Record");

        OrderItemCalculation orderItemCalculation = new OrderItemCalculationImpl();
        orderItemCalculation.setItemId(logisticsRecord.getItemId());
        orderItemCalculation.setQuantity(logisticsRecord.getItemQuantityProcessed());
        orderItemCalculation.setCost(logisticsRecord.getTotalItemCost());
        orderItemCalculation.setNumberOfSources(logisticsRecord.getTotalSources());
        orderItemCalculation.setFirstDay(logisticsRecord.getFirstProcessingDay());
        orderItemCalculation.setLastDay(logisticsRecord.getLastProcessingDay());

        return orderItemCalculation;

    }

    public void printLogisticsRecords() {
        System.out.println("ORDER ITEM LOGISTICS RECORDS");
        System.out.println("");
        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            logisticsRecord.print();
        }
    }
}

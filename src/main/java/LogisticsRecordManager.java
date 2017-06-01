package src.main.java;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.FacilityNotFoundException;
import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.*;
import src.main.java.interfaces.impl.*;
import sun.rmi.runtime.Log;

import java.util.*;

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

    public void gernerateLogisticsRecord(FacilityRecord facilityRecord) throws DataValidationException, FacilityNotFoundException, NegativeQuantityException, NullParameterException {

        if (facilityRecord == null) throw new DataValidationException("Null Facility Record");

        LogisticsRecord logisticsRecord = new LogisticsRecordImpl(facilityRecord.getItemID());
        Integer totalOrderQuantity = facilityRecord.getTotalOrderQuantity();
        logisticsRecord.setTotalItemQuantity(totalOrderQuantity);

        LogisticsDetail logisticsDetail = createLogisticsDetail(facilityRecord);

        ItemArrival itemArrival = createItemArrival(logisticsDetail);

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
        // this.computeItemArrivals();

        /*
        for (LogisticsRecord currentLogisticsRecord : logisticsRecords) {
            createOrderItemCalculations(currentLogisticsRecord);
        }
        */


    }

    private void computeItemArrivals() {


        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            // sort item arrivals by arrival day
            Collections.sort(logisticsRecord.getItemArrivals(), new Comparator<ItemArrival>() {
                @Override
                public int compare(ItemArrival itemArrival1, ItemArrival itemArrival2) {
                    return Integer.compare(itemArrival1.getArrivalDay(), itemArrival2.getArrivalDay());
                }
            });

            for (ItemArrival itemArrival : logisticsRecord.getItemArrivals()) {
                Integer result = itemArrival.getItemsProcessed() / logisticsRecord.getTotalItemQuantity();
                itemArrival.setPercentOfTotal(result * 100);
            }
        }
    }

    private LogisticsDetail createLogisticsDetail(FacilityRecord facilityRecord) throws DataValidationException {

        if (facilityRecord == null) throw new DataValidationException("Null Facility Record");

        LogisticsDetail logisticsDetail = new LogisticsDetailImpl();
        Facility facility = FacilityManager.getInstance().getFacility(facilityRecord.getFacilityLocation());
        String logisticDetailsCity = facility.getCity();

        logisticsDetail.setFacilityLocation(logisticDetailsCity);
        logisticsDetail.setProcessingStart(facilityRecord.getArrivalDay());
        logisticsDetail.setProcessingEnd(facilityRecord.getProcessingEndDay());
        logisticsDetail.setTotalQuantity(facilityRecord.getTotalOrderQuantity());
        logisticsDetail.setitemsProcessed(facilityRecord.getItemsNeeded());

        Integer travelStart = facilityRecord.getTravelDays() - (facilityRecord.getTravelDays()-1);
        logisticsDetail.setTravelStart(travelStart);
        Integer travelEnd = travelStart + facilityRecord.getTravelDays();
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

    public void createOrderItemCalculations(LogisticsRecord logisticsRecord) throws NullParameterException, DataValidationException, FacilityNotFoundException, NegativeQuantityException {

        if (logisticsRecord == null) throw new DataValidationException("Null Logistics Record");

        for (LogisticsRecord currentLogisticsRecord : logisticsRecords) {

            // create OrderItemCalculation
            OrderItemCalculation orderItemCalculation = new OrderItemCalculationImpl();
            orderItemCalculation.setFirstDay(logisticsRecord.getFirstProcessingDay());
            orderItemCalculation.setLastDay(logisticsRecord.getLastProcessingDay());
            orderItemCalculation.setNumberOfSources(logisticsRecord.getTotalSources());
            orderItemCalculation.setCost(logisticsRecord.getTotalItemCost());
            orderItemCalculation.setQuantity(logisticsRecord.getTotalItemQuantity());
            orderItemCalculation.setItemId(logisticsRecord.getItemId());

            // add orderItemCalculation to order
            OrderManager.getInstance().addOrderItemCalculationToOrder(orderItemCalculation.getItemId(), orderItemCalculation);

        }

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

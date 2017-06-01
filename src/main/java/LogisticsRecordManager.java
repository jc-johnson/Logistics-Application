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

    public void createOrderItemCalculations() throws NullParameterException, DataValidationException, FacilityNotFoundException, NegativeQuantityException {
        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            OrderItemCalculation orderItemCalculation = new OrderItemCalculationImpl();
            orderItemCalculation.setItemId(logisticsRecord.getItemId());
            orderItemCalculation.setQuantity(logisticsRecord.getTotalItemQuantity());
            Integer totalRecordCost = this.calculateTotalCost(orderItemCalculation.getItemId());
            orderItemCalculation.setCost(totalRecordCost);
            Integer numberOfSources = logisticsRecord.getLogisticsDetailSize();
            orderItemCalculation.setNumberOfSources(numberOfSources);
            Integer firstDay = this.getFirstDay(orderItemCalculation.getItemId());
            Integer lastDay = this.getLastDay(orderItemCalculation.getItemId());
            orderItemCalculation.setFirstDay(firstDay);
            orderItemCalculation.setLastDay(lastDay);
            OrderManager.getInstance().addOrderItemCalculationToOrder(orderItemCalculation.getItemId(), orderItemCalculation);
        }
    }

    private Integer calculateTotalCost(String itemId) throws DataValidationException, NullParameterException, FacilityNotFoundException {
        if (itemId.isEmpty()) throw new NullParameterException();

        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            if (logisticsRecord.getItemId().equals(itemId)) {
                Integer itemPrice = ItemCatalogManager.getInstance().getItemPrice(itemId);
                Integer totalCost = 0;

                if (logisticsRecord.getLogisticsDetailSize() == 0) throw new DataValidationException("No logistics details");

                for (int i = 0; i < logisticsRecord.getLogisticsDetailSize() ; i++) {
                    LogisticsDetail logisticsDetail = logisticsRecord.getLogisticsDetail(i);
                    String fullFacilityLocation = FacilityManager.getInstance().getFacilityLocationFromCity(logisticsDetail.getFacilityLocation());
                    Integer facilityProcessingCost = FacilityManager.getInstance().getDailyFacilityCost(fullFacilityLocation);
                    Integer logisticDetailProcessingEnd = logisticsDetail.getProcessingEnd();
                    Integer logisticsDetailProcessingStart = logisticsDetail.getProcessingStart();
                    Integer processingDays = logisticsDetailProcessingStart - logisticDetailProcessingEnd;
                    facilityProcessingCost *= processingDays;
                    Integer logisticsDetailTransportEndDay = logisticsDetail.getTravelEnd();
                    Integer logisticsDetailTransportStartDay = logisticsDetail.getTravelStart();
                    Integer transportDays = logisticsDetailTransportEndDay - logisticsDetailTransportStartDay;
                    Integer transportCost = 500 * transportDays;
                    itemPrice *= logisticsDetail.getItemsProcessed();
                    totalCost += itemPrice + transportCost + facilityProcessingCost;
                }
                return  totalCost;
            }
        }

        return 0;
    }

    private Integer getLastDay(String itemId) throws NullParameterException {
        if (itemId.isEmpty()) throw new NullParameterException();

        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            if (logisticsRecord.getItemId().equals(itemId)) {
                Integer lastDay = 0;
                for (int i = 0; i < logisticsRecord.getLogisticsDetailSize(); i++) {
                    LogisticsDetail logisticsDetail = logisticsRecord.getLogisticsDetail(i);
                    Integer logisticsDetailEndDay = logisticsDetail.getProcessingEnd();
                    if (logisticsDetailEndDay > lastDay) {
                        lastDay = logisticsDetailEndDay;
                    }
                }
                return lastDay;
            }
        }

        return 0;
    }

    private Integer getFirstDay(String itemId) throws NullParameterException {
        if (itemId.isEmpty()) throw new NullParameterException();

        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            if (logisticsRecord.getItemId().equals(itemId)) {
                Integer lowestDay = Integer.MAX_VALUE;
                for (int i = 0; i < logisticsRecord.getLogisticsDetailSize(); i++) {
                    LogisticsDetail logisticsDetail = logisticsRecord.getLogisticsDetail(i);
                    Integer logisticsDetailStartDay = logisticsDetail.getProcessingStart();
                    if (logisticsDetailStartDay < lowestDay) {
                        lowestDay = logisticsDetailStartDay;
                    }
                }
                return lowestDay;
            }
        }

        return 0;
    }

    public void printLogisticsRecords() {
        System.out.println("ORDER ITEM LOGISTICS RECORDS");
        System.out.println("");
        for (LogisticsRecord logisticsRecord : logisticsRecords) {
            logisticsRecord.print();
        }
    }
}

package src.main.java.interfaces.impl;

import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NoAvailableDaysException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.Schedule;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public class FacilitySchedule implements Schedule {

    private Map<Integer, Integer> scheduleMap = new HashMap<>();

    public void addScheduleDay(Integer day, Integer itemsAvailable) {
        if (day < 0 || itemsAvailable < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        scheduleMap.put(day, itemsAvailable);
    }

    @Override
    public Integer getNextAvailableDay(Integer startDay) throws NullParameterException {
        if (startDay == null) throw new NullParameterException();

        scheduleMap.get(startDay);
        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
            if (isLastEntry(startDay)) extendSchedule();

            startDay = startDay + 1;
            if (scheduleMap.get(startDay) > 0) return startDay;
        }

        return 0;
    }

    @Override
    public void setScheduleDay(Integer day, Integer itemNumber) {
        if (day < 0 || itemNumber < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        if (day > scheduleMap.size()) {
            extendSchedule();
        }
        scheduleMap.put(day, itemNumber);
    }

    private boolean isLastEntry(Integer day) {

        if (day < 0) try {
            throw new NegativeQuantityException();
        } catch (NegativeQuantityException e) {
            e.printStackTrace();
        }

        Boolean value = false;
        Iterator iterator = scheduleMap.entrySet().iterator();
        // Iterate until you find day
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getKey() == day) {
                if (iterator.next() == null) value = true;
            }
        }
        return value;
    }

    public Integer getLastScheduleDay() {
        Integer day = 0;
        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
            day = entry.getKey();
        }
        return day;
    }

    public void extendSchedule() {
        // adds 10 days to schedule
        Integer lastScheduleDay = 0;
        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
            lastScheduleDay = entry.getKey();
        }
        // lastScheduleDay++;
        for (int i = 0; i < 11; i++) {
            this.addScheduleDay(lastScheduleDay, 10);
            lastScheduleDay++;
        }
    }

    @Override
    public Integer getAvailableItems(Integer day) throws NegativeQuantityException {
        if (day < 0) throw new NegativeQuantityException();

        if (day >= scheduleMap.size()) {
            extendSchedule();
        }
        return scheduleMap.get(day);
    }

    @Override
    public Integer getFirstAvailableDay() throws NoAvailableDaysException {
        Integer lastDay = getLastScheduleDay();
        Integer lastDayValue = scheduleMap.get(lastDay);
        if (lastDayValue == 0)
            extendSchedule();

        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
            if (entry.getValue() > 0) {
                return entry.getKey();
            }
        }

        throw new NoAvailableDaysException();
    }



    @Override
    public void printOutput() {
        System.out.println("Schedule: ");
        System.out.print("Day: \t\t\t");
        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
            System.out.print(entry.getKey()+"\t");
        }
        System.out.println("");
        System.out.print("Available: \t\t");
        for (Map.Entry<Integer, Integer> entry : scheduleMap.entrySet()) {
            System.out.print(entry.getValue()+"\t");
        }
        System.out.println("");
        System.out.println("");
    }
}

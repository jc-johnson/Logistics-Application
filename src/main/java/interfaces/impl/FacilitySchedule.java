package src.main.java.interfaces.impl;

import src.main.java.exceptions.NoAvailableDaysException;
import src.main.java.interfaces.Schedule;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public class FacilitySchedule implements Schedule {

    Map<Integer, Integer> scheduleMap = new HashMap<>();


    @Override
    public void setScheduleDay(Integer day, Integer itemNumber) {
        scheduleMap.put(day, itemNumber);
    }

    private boolean isLastEntry(Integer day) {
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

    private Integer getLastScheduleDay() {
        Integer day = 0;
        Iterator iterator = scheduleMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            day = (Integer) entry.getKey();
        }
        return day;
    }

    private void extendSchedule() {
        // adds 10 days to schedule
        Integer lastScheduleDay = getLastScheduleDay();
        lastScheduleDay++;
        for (int i = 0; i < 11; i++) {
            this.setScheduleDay(lastScheduleDay, 10);
            lastScheduleDay++;
        }
    }

    @Override
    public Integer getAvailableItems(Integer day) {
        if (day >= scheduleMap.size()) {
            extendSchedule(); // TODO: maybe have Facility or FacilityManager do this
        }
        Integer availableItemsForDay = scheduleMap.get(day);
        return availableItemsForDay;

    }

    @Override
    public Integer getFirstAvailableDay() throws NoAvailableDaysException {

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

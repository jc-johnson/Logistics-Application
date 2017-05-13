package src.main.java.interfaces.impl;

import src.main.java.exceptions.NoAvailableDaysException;
import src.main.java.interfaces.Schedule;

import java.util.HashMap;
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

    @Override
    public Integer getAvailableItems(Integer day) {
        return scheduleMap.get(day);

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

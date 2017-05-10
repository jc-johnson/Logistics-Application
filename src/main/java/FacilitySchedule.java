package src.main.java;

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

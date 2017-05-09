package src.main.java.interfaces.impl;

import src.main.java.interfaces.SchedulePrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordan on 5/2/2017.
 */
public class SchedulePrinterImpl implements SchedulePrinter {

    @Override
    public void print(HashMap<Integer, Integer> schedule) {
        System.out.println("Schedule: ");
        System.out.print("Day: \t\t\t");
        for (Map.Entry<Integer, Integer> entry : schedule.entrySet()) {
            System.out.print(entry.getKey()+"\t");
        }
        System.out.println("");
        System.out.print("Available: \t\t");
        for (Map.Entry<Integer, Integer> entry : schedule.entrySet()) {
            System.out.print(entry.getValue()+"\t");
        }
        System.out.println("");
        System.out.println("");
    }
}

package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.SchedulePrinter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jordan on 5/2/2017.
 */
public class SchedulePrinterImpl implements SchedulePrinter {

    @Override
    public void print(HashMap<Integer, Integer> schedule) {
        System.out.println("Schedule: ");
        System.out.print("Day: \t\t");
        Iterator iterator = schedule.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.println(pair.getKey().toString());
            iterator.remove(); // avoids a ConcurrentModificationException
        }
        System.out.println("");

        System.out.print("Available: \t\t");
        iterator = schedule.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.println(pair.getValue().toString());
            iterator.remove(); // avoids a ConcurrentModificationException
        }

    }
}

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
        Iterator iterator = schedule.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.println(pair.getKey().toString() + " = " + pair.getValue().toString());
            iterator.remove(); // avoids a ConcurrentModificationException
        }
    }
}

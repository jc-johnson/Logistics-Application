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
        System.out.print("Day:" + " \t\t\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\t16\t17\t18\t19\t20");
        Iterator iterator = schedule.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.println(pair.getKey().toString());
            iterator.remove(); // avoids a ConcurrentModificationException
        }
        System.out.println("");

        System.out.println("Available: \t\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10\t10");
        iterator = schedule.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.println(pair.getValue().toString());
            iterator.remove(); // avoids a ConcurrentModificationException
        }
        System.out.println("");

    }
}

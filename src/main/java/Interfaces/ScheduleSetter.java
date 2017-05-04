package src.main.java.Interfaces;

import java.util.HashMap;

/**
 * Created by Jordan on 5/4/2017.
 */
public interface ScheduleSetter {

    void addDay(Integer day, Integer value, HashMap<Integer, Integer> schedule);
}

package src.main.java.Interfaces.Impl;

import src.main.java.Interfaces.ScheduleSetter;

import java.util.HashMap;

/**
 * Created by Jordan on 5/4/2017.
 */
public class ScheduleSetterImpl implements ScheduleSetter{

    @Override
    public void addDay(Integer day, Integer value, HashMap<Integer, Integer> schedule) {
        schedule.put(day, value);
    }
}

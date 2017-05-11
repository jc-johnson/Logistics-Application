package src.main.java.interfaces;

import src.main.java.exceptions.NoAvailableDaysException;

/**
 * Created by Jordan on 5/9/2017.
 */
public interface Schedule {

    void setScheduleDay(Integer day, Integer itemNumber);

    Integer getAvailableItems(Integer day);

    Integer getFirstAvailableDay() throws NoAvailableDaysException;

    void printOutput();


}

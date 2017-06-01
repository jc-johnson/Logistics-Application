package src.main.java.interfaces;

import src.main.java.exceptions.NegativeQuantityException;
import src.main.java.exceptions.NoAvailableDaysException;
import src.main.java.exceptions.NullParameterException;

/**
 * Created by Jordan on 5/9/2017.
 */
public interface Schedule {

    void setScheduleDay(Integer day, Integer itemNumber) throws NegativeQuantityException;

    Integer getAvailableItems(Integer day) throws NegativeQuantityException;

    Integer getFirstAvailableDay() throws NoAvailableDaysException;

    void printOutput();

    Integer getLastScheduleDay();

    void extendSchedule();

    void addScheduleDay(Integer day, Integer itemsAvailable);

    Integer getNextAvailableDay(Integer startDay) throws NullParameterException, NegativeQuantityException;

    void processOrderSchedule(Integer arrivalDay, Integer totalItemsProcessed) throws NegativeQuantityException, NullParameterException;



}

package src.main.java.interfaces;

/**
 * Created by Jordan on 5/13/2017.
 */
public interface ItemArrival {

    Integer getPercentOfTotal();

    void setPercentOfTotal(Integer totalQuantity);

    Integer getArrivalDay();

    void setArrivalDay(Integer day);

    Integer getItemsProcessed();

    void setItemsProcessed(Integer quantity);

    Integer getPercentOfItemArrivals();

    void setPercentOfItemArrivals(Integer quantity);

    void print();
}

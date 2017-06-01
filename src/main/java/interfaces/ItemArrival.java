package src.main.java.interfaces;

/**
 * Created by Jordan on 5/13/2017.
 */
public interface ItemArrival {

    Double getPercentOfTotal();

    void setPercentOfTotal(Double totalQuantity);

    Integer getArrivalDay();

    void setArrivalDay(Integer day);

    Integer getItemsProcessed();

    void setItemsProcessed(Integer quantity);

    Double getPercentOfItemArrivals();

    void setPercentOfItemArrivals(Double quantity);

    void print();
}

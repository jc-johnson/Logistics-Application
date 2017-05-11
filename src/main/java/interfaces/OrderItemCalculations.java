package src.main.java.interfaces;

/**
 * Created by Jordan on 5/11/2017.
 */
public interface OrderItemCalculations {

    String getItemId();

    void setItemId(String itemId);

    Integer getQuantity();

    void setQuantity(Integer quantity);

    Integer getCost();

    void setCost(Integer cost);

    Integer getNumberOfSources();

    void setNumberOfSources(Integer sources);

    Integer getFirstDay();

    void setFirstDay(Integer day);

    Integer getLastDay();

    void setLastDay(Integer day);
}

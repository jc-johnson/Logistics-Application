package src.main.java.interfaces;

/**
 * Created by Jordan on 5/15/2017.
 */
public interface BackOrder {

    String getItemId();
    void setItemId(String itemId);
    Integer getBackOrderQuanity();
    void setBackOrderQuanity(Integer quanity);
    void print();

}

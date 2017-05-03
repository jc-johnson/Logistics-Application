package src.main.java;

/**
 * Created by Jordan on 4/30/2017.
 */
public class Item {

    private String id;
    private Integer price;

    public Item(String id) {
        this.id = id;
    }

    public Item(String id, Integer price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

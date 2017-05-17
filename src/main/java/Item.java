package src.main.java;

import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.FacilityRecord;
import src.main.java.interfaces.Solution;
import src.main.java.interfaces.impl.SolutionImpl;

/**
 * Created by Jordan on 4/30/2017.
 */
public class Item {

    private String id;
    private Integer price;
    private Solution solution = new SolutionImpl();

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


    // TODO: make into interface method for Item
    public void addSolution(FacilityRecord facilityRecord) throws NullParameterException {
        solution.addFacilityRecord(facilityRecord);
    }
}

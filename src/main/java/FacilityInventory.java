package src.main.java;

import src.main.java.exceptions.DataValidationException;
import src.main.java.exceptions.NullParameterException;
import src.main.java.interfaces.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jordan on 5/9/2017.
 */
public class FacilityInventory implements Inventory {

    private Map<String, Integer> activeInventory = new HashMap<>();
    private Map<String, Integer> depletedInventory = new HashMap<>();

    public void initializeInventory() {
        for (Map.Entry<String,Integer> entry : activeInventory.entrySet()) {
            entry.setValue(0);
        }
    }

    public void setInventoryItem(Item item, Integer quantity) throws DataValidationException {
        if (item == null || quantity < 0) throw new DataValidationException("Null parameter or Negative Quantity");

        activeInventory.put(item.getId(), quantity);

    }

    @Override
    public void updateInventoryItem(Item item, Integer newQuantity) throws DataValidationException, NullParameterException {
        if (item == null) throw new NullParameterException();

        Integer originalQuantity = activeInventory.get(item.getId());

        // Need to update depleted inventory
        if (newQuantity < originalQuantity) {
            Integer numberOfDepleted = originalQuantity - newQuantity;
            depletedInventory.put(item.getId(), numberOfDepleted);
        }

        activeInventory.put(item.getId(), newQuantity);
    }

    @Override
    public void printInventory() {
        printActiveInventory();
        printDepletedInventory();
    }

    @Override
    public boolean containsItem(Item item) {
        for (Map.Entry<String, Integer> entry : activeInventory.entrySet()) {
            if (item.getId().equals(entry.getKey()) && entry.getValue() > 0) {
                return true;
            }
        }

        return false;
    }


    @Override
    public Integer getItemQuantity(Item item) {
        return activeInventory.get(item.getId());

    }


    private void printActiveInventory() {
        System.out.println("Active Inventory: ");
        System.out.println("\tItem ID\t\tQuantity");
        for (Map.Entry<String, Integer> entry : activeInventory.entrySet()) {
            System.out.println("\t" + entry.getKey() + "\t\t" + entry.getValue() );
        }
        System.out.println("");
    }


    private void printDepletedInventory() {
        if (depletedInventory.size() == 0) {
            System.out.println("\tDepleted (Used-Up) Inventory: None");
            return;
        }

        System.out.println("\tDepleted (Used-Up) Inventory: ");
        System.out.println("\tItem ID\tQuantity");
        for (Map.Entry<String, Integer> entry : activeInventory.entrySet()) {
            System.out.println("\t" + entry.getKey() + "\t" + entry.getValue() );
        }
        System.out.println("");
        System.out.println("");
    }


}

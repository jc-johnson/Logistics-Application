package src.main.java.Interfaces.Impl;

import src.main.java.Facilities.ChicagoFacility;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.OutputPrinter;

/**
 * Created by Jordan on 4/29/2017.
 */
public class FacilityOutputImpl implements OutputPrinter {

    Facility facility = null;

    FacilityOutputImpl(Facility facility) {
        this.facility = facility;
    }

    @Override
    public void printOutput() {
        System.out.println("----------------------------------------------------------");
        facility.getCity();
        System.out.println("Rate per day: " + facility.getRatePerDay());
        System.out.println("Cost per day: " + facility.getCostPerDay());
        System.out.println("");
        System.out.println("Direct Links: ");
        facility.printNeighbors();
        System.out.println("");
        System.out.println("Active Inventory: ");
        facility.printActiveInventory();
        System.out.println("");
        System.out.println("Depleted (Used-up) Inventory: ");
        facility.printDepletedInventory();
        System.out.println("");
        System.out.println("Schedule: ");
        System.out.println("Day: \t");
        System.out.println("Available: \t");
    }

}

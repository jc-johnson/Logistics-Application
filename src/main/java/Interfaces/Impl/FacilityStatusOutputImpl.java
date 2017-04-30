package src.main.java.Interfaces.Impl;

import src.main.java.Facilities.ChicagoFacility;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.FacilityOutput;

/**
 * Created by Jordan on 4/29/2017.
 */
public class FacilityStatusOutputImpl implements FacilityOutput{

    Facility facility = null; // TODO: maybe needs to be a null impl

    FacilityStatusOutputImpl(Facility facility) {
        this.facility = facility;
    }

    @Override
    public void printOutput() {
        System.out.println("----------------------------------------------------------");
        System.out.println(facility.getLocation()); // TODO: Tweak to just output the city without the state
        System.out.println("Rate per day: " + facility.getRatePerDay());
        System.out.println("Cost per day: " + facility.getCostPerDay());
        System.out.println("");
        System.out.println("Direct Links: ");
        // System.out.println(facility.printDirectLinks); // TODO: implement
        System.out.println("");
        System.out.println("Active Inventory: ");
        // System.out.println(facility.printInventory); // TODO: implement
        System.out.println("");
        System.out.println("Depleted (Used-up) Inventory: ");
        // System.out.println(facility.printDepletedInventory); // TODO: implement
        System.out.println("");
        System.out.println("Schedule: ");
        System.out.println("Day: \t");
        System.out.println("Available: \t");
    }

    /**
     * TODO: Remove Test
     */
    public static void main(String[] args) {
        Facility facility = new ChicagoFacility();
        FacilityStatusOutputImpl facilityStatusOutput = new FacilityStatusOutputImpl(facility);
        facilityStatusOutput.printOutput();
    }
}

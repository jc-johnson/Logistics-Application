package src.main.java.Interfaces.Impl;

import src.main.java.Facilities.ChicagoFacility;
import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.FacilityOutput;

/**
 * Created by Jordan on 4/29/2017.
 */
public class FacilityStatusOutputImpl implements FacilityOutput{

    Facility facility = null;

    FacilityStatusOutputImpl(Facility facility) {
        this.facility = facility;
    }

    @Override
    public void printOutput() {
        System.out.println("----------------------------------------------------------");
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

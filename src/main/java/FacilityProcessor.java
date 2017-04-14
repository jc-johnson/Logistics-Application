package src.main.java;

import src.main.java.Interfaces.Facility;
import src.main.java.Interfaces.Processor;

/**
 * Created by Jordan on 4/14/2017.
 */
public class FacilityProcessor implements Processor {

    @Override
    public void process(String type) {
        Facility chicagoFacility = FacilityFactory.createFacility("Chicago, IL");

    }
}

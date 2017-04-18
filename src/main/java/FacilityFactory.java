package src.main.java;

import src.main.java.Facilities.*;
import src.main.java.Interfaces.Facility;

/**
 * Created by Jordan on 4/14/2017.
 */
public class FacilityFactory {

    private FacilityFactory(){} // static methods, no reason to "new"

    public static Facility createFacility(String type) {

        switch (type) {
            case "Seattle, WA":
                return new SeattleFacility();
            case "San Francisco, CA":
                return new SanFranciscoFacility();
            case "Los Angeles, CA":
                return new LosAngelesFacility();
            case "Phoenix, AZ":
                return new PhoenixFacility();
            case "Denver, CO":
                return new DenverFacility();
            case "Santa Fe, NM":
                return new SantaFeFacility();
            case "Fargo, ND":
                return new FargoFacility();
            case "Austin, TX":
                return new AustinFacility();
            case "St. Louis, MO":
                return new StLouisFacility();
            case "Chicago, IL":
                return new ChicagoFacility();
            case "New Orleans, LA":
                return new NewOrleansFacility();
            case "Nashville, TN":
                return new NashvilleFacility();
            case "Detroit, MI":
                return new DetroitFacility();
            case "Boston, MA":
                return new BostonFacility();
            case "New York City, NY":
                return new NewYorkCityFacility();
            case "Norfolk, VA":
                return new NorfolkFacility();
            case "Atlanta, GA":
                return new AtlantaFacility();
            case "Miami, FL":
                return new MiamiFacility();
            default:
                return null;
        }
    }
}

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
                return LosAngelesFacility.getInstance();
            case "Phoenix, AZ":
                return new PhoenixFacility();
            case "Denver, CO":
                return DenverFacility.getInstance();
            case "Santa Fe, NM":
                return new SantaFeFacility();
            case "Fargo, ND":
                return FargoFacility.getInstance();
            case "Austin, TX":
                return AustinFacility.getInstance();
            case "St. Louis, MO":
                return new StLouisFacility();
            case "Chicago, IL":
                return ChicagoFacility.getInstance();
            case "New Orleans, LA":
                return NewOrleansFacility.getInstance();
            case "Nashville, TN":
                return NashvilleFacility.getInstance();
            case "Detroit, MI":
                return DetroitFacility.getInstance();
            case "Boston, MA":
                return BostonFacility.getInstance();
            case "New York City, NY":
                return new NewYorkCityFacility();
            case "Norfolk, VA":
                return new NorfolkFacility();
            case "Atlanta, GA":
                return AtlantaFacility.getInstance();
            case "Miami, FL":
                return MiamiFacility.getInstance();
            default:
                return null;
        }
    }
}

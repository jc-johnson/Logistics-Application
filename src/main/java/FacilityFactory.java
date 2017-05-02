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
                return SeattleFacility.getInstance();
            case "San Francisco, CA":
                return SanFranciscoFacility.getInstance();
            case "Los Angeles, CA":
                return LosAngelesFacility.getInstance();
            case "Phoenix, AZ":
                return PhoenixFacility.getInstance();
            case "Denver, CO":
                return DenverFacility.getInstance();
            case "Santa Fe, NM":
                return SantaFeFacility.getInstance();
            case "Fargo, ND":
                return FargoFacility.getInstance();
            case "Austin, TX":
                return AustinFacility.getInstance();
            case "St. Louis, MO":
                return StLouisFacility.getInstance();
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
                return NewYorkCityFacility.getInstance();
            case "Norfolk, VA":
                return NorfolkFacility.getInstance();
            case "Atlanta, GA":
                return AtlantaFacility.getInstance();
            case "Miami, FL":
                return MiamiFacility.getInstance();
            default:
                return null;
        }
    }
}

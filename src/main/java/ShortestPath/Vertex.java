package src.main.java.ShortestPath;

import src.main.java.FacilityFactory;
import src.main.java.Interfaces.Facility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Vertex implements Comparable<Vertex>{


    private String name;   // may need to change to location for FacilityVertex
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex previous = null;
    private Facility facility;
    private ArrayList<Edge> adjacencies = new ArrayList<>();

    public Vertex (String name) {
        this.name =  name;
    }

    /*public Vertex (String facilityLocation) {
        this.facility = FacilityFactory.createFacility(facilityLocation);
    }*/


    public void setFacility (String facilityLocation) {
        this.facility = FacilityFactory.createFacility(facilityLocation);
    }

    public void setFacility (Facility facility) {
        this.facility = facility;
    }

    public String toString() {
        return name;
    }


    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double distance) {
        minDistance = distance;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex vertex) {
        this.previous = vertex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAdjacency(Edge edge) {
        adjacencies.add(edge);
    }

    public ArrayList<Edge> getAdjacenciesList() {
        // make a copy of adjacencies
        ArrayList<Edge> adjaceneiesListCopy = new ArrayList<>();
        adjaceneiesListCopy = adjacencies;
        return adjaceneiesListCopy;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(minDistance, otherVertex.minDistance);
    }
}

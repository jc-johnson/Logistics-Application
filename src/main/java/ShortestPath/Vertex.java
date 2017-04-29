package src.main.java.ShortestPath;

import src.main.java.Interfaces.Facility;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Vertex implements Comparable<Vertex>{


    public final String name;   // may need to change to location for FacilityVertex
    public ArrayList<Edge> adjacencies = new ArrayList<>();
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous = null;
    private Facility facility;

    public Vertex (String name) {
        this.name =  name;
    }

    public Vertex (Facility facility) {
        this.name = facility.getLocation();
    }

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(minDistance, otherVertex.minDistance);
    }
}

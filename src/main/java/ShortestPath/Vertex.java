package src.main.java.ShortestPath;

import java.util.Comparator;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Vertex implements Comparable<Vertex>{


    public final String name;   // may need to change to location for FacilityVertex
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex (String name) {
        this.name =  name;
    }

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(minDistance, otherVertex.minDistance);
    }
}

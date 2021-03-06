package src.main.java.shortestpath;

import src.main.java.interfaces.Facility;

import java.util.ArrayList;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Vertex implements Comparable<Vertex>{


    private String name;   // may need to change to location for FacilityVertex
    private Integer minDistance = Integer.MAX_VALUE;
    private Vertex previous = null;

    private Facility facility;
    private ArrayList<Edge> adjacencies = new ArrayList<>();

    public Vertex (String name) {
        this.name =  name;
    }

    public String toString() {
        return name;
    }


    public Integer getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(Integer distance) {
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
        return Integer.compare(minDistance, otherVertex.minDistance);
    }

}

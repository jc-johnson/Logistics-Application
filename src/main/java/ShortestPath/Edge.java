package src.main.java.ShortestPath;

import src.main.java.FacilityFactory;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Edge {

    public Vertex target;
    public double weight = 0;

    public Edge(Vertex targetVertex, double edgeWeight) {
        target = targetVertex;
        this.weight = edgeWeight;
    }
}

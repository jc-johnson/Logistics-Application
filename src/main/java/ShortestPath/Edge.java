package src.main.java.ShortestPath;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Edge {

    public final Vertex target;
    public final double weight;
    public Edge(Vertex targetVertex, double edgeWeight) {
        target = targetVertex;
        weight = edgeWeight;
    }
}

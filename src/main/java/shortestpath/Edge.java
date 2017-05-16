package src.main.java.shortestpath;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Edge {

    public Vertex target;
    public Integer weight = 0;

    public Edge(Vertex targetVertex, Integer edgeWeight) {
        target = targetVertex;
        this.weight = edgeWeight;
    }
}

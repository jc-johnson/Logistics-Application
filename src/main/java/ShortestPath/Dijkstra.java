package src.main.java.ShortestPath;

import java.util.*;

/**
 * Created by Jordan on 4/20/2017.
 */
public class Dijkstra {

    // Builds graph. Only need to do this once
    public static void computePaths(Vertex source) {

        // Set up priority queue to hold our final path
        source.setMinDistance(0);    // you will update this variable as the program keeps track of the shortest path
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source); // Source vertex will be the beginning of the queue

        // headVertex will constantly be getting updated
        while (!vertexQueue.isEmpty()) {
            Vertex headVertex = vertexQueue.poll(); // returns the head of the queue or null if the queue is empty
                                                    // updates to the latest vertex added to the Queue??
            System.out.println("Head Vertex: " + headVertex.getName());

                if (headVertex.getAdjacenciesList() == null) {
                    return; // TODO: Throw exception here
                }

                // Visit each edge exiting the headVertex
                for (Edge edge : headVertex.getAdjacenciesList()) {
                    Vertex edgeVertex = edge.target;
                    double edgeWeight = edge.weight;    // Note: temp variable to hold weight. Not changing vertex.weight;
                    double distanceFromHeadVertex = headVertex.getMinDistance() + edgeWeight;  // will constantly be updating the head vertex weight as we travel along the graph
                    System.out.println("Distance from head vertex: " + distanceFromHeadVertex);
                    System.out.println("Source Edge Vertex: " + edgeVertex.getName());
                    System.out.println("Source Edge Weight: " + edgeWeight);

                    // keeping the latest path if it's shorter than what we already have stored (Infinity is initially stored for minDistance)
                    if (distanceFromHeadVertex < edgeVertex.getMinDistance()) {
                        vertexQueue.remove(edgeVertex); // might not need - might be redundant. Might just be clean up

                        edgeVertex.setMinDistance(distanceFromHeadVertex); // we care about the last vertex in the priority queue's minDistance for our final shortest path distance
                        System.out.println("Edge Vertex min distance: " + edgeVertex.getMinDistance());
                        edgeVertex.setPrevious(headVertex);
                        vertexQueue.add(edgeVertex); // then iterates to next edge in the headverte

                    }
                }

        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null ; vertex = vertex.getPrevious()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    public static List<Vertex> shortestPath(Vertex source, Vertex target) {
        computePaths(source); // run Dijkstra
        System.out.println("Distance to " + target + target.getMinDistance());
        List<Vertex> path = getShortestPathTo(target);
        System.out.println("Path: " + path);

        return path;
    }

    public static void main(String[] args) {

        // mark all the vertices
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");

      /*
        Vertex K = new Vertex("K");
        Vertex J = new Vertex("J");
        Vertex M = new Vertex("M");
        Vertex O = new Vertex("O");
        Vertex P = new Vertex("P");
        Vertex R = new Vertex("R");
        Vertex Z = new Vertex("Z");

    */

        // set the edges and weight
        A.addAdjacency(new Edge(B, 8));
        B.addAdjacency(new Edge(D, 1));
        B.addAdjacency(new Edge(C, 5));
        C.addAdjacency(new Edge(E, 3));
        D.addAdjacency(new Edge(E, 7));


        /*
        K.adjacencies = new Edge[] {new Edge(M, 40)};
        J.adjacencies = new Edge[] {new Edge(M, 25)};
        M.adjacencies = new Edge[] {new Edge(M, 8)};
        O.adjacencies = new Edge[] {new Edge(M, 40)};
        P.adjacencies = new Edge[] {new Edge(M, 18)};
        R.adjacencies = new Edge[] {new Edge(M, 15)};
        Z.adjacencies = new Edge[] {new Edge(M, 18)};
        */

        /*
        computePaths(A); // run Dijkstra
        System.out.println("Distance to " + E + E.minDistance);
        List<Vertex> path = getShortestPathTo(E);
        System.out.println("Path: " + path); */

        shortestPath(A, E);

    }
}

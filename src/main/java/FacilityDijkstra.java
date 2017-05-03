package src.main.java;

import src.main.java.Exceptions.NullVertexException;
import src.main.java.Interfaces.Facility;
import src.main.java.ShortestPath.Edge;
import src.main.java.ShortestPath.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Jordan on 5/1/2017.
 */
public class FacilityDijkstra {

    private List<Vertex> shortestPath;
    private List<Facility> shortestPathFacilities;

    // Builds graph. Only need to do this once
    public static void computePaths(Vertex source) throws NullVertexException {
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
                throw new NullVertexException();
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

    public static List<Vertex> shortestPath(Vertex source, Vertex target) throws NullVertexException {
        computePaths(source); // run Dijkstra
        System.out.println("Distance to " + target + target.getMinDistance());
        List<Vertex> path = getShortestPathTo(target);
        System.out.println("Path: " + path);

        return path;
    }

    /*// Create two verticies based on input facilities
    public void computeShortestPath(Facility facilityOne, Facility facilityTwo) throws NullVertexException {
        Vertex source = new Vertex(facilityOne);
        Vertex target = new Vertex(facilityTwo);
        shortestPath(source, target);
    }

    public void computeShortestPath(String startFacility, String endFacility) throws NullVertexException {
        Facility start = FacilityFactory.createFacility(startFacility);
        Facility end = FacilityFactory.createFacility(endFacility);

        System.out.println(startFacility + " to " + endFacility + ":");
        computeShortestPath(start, end);
    }*/

    public void printShortestPath() {

    }

    public static void main(String[] args) {

    }
}

package src.main.java;

import src.main.java.Exceptions.*;
import src.main.java.Facilities.ChicagoFacility;
import src.main.java.Interfaces.Facility;
import src.main.java.ShortestPath.Edge;
import src.main.java.ShortestPath.FacilityEdge;
import src.main.java.ShortestPath.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Jordan on 5/1/2017.
 */
public class FacilityDijkstra {

    public static void computePaths(Facility source) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
        source.setMinDistance(0);
        PriorityQueue<Facility> facilityQueue = new PriorityQueue<>();
        facilityQueue.add(source);

        while (!facilityQueue.isEmpty()) {
            Facility headFacility = facilityQueue.poll(); // returns the head of the queue or null if queue is empty
            System.out.println("Head Facility " + headFacility.getLocation());

            if (headFacility.getNeighborList() == null) {
                throw new NullNeighborListException();
            }

            /*if (headFacility.getNeighborList().size() == 0) {
                throw new EmptyNeighborListException();
            }*/

            // Visit each edge exiting the headFacility
            for (FacilityEdge facilityEdge : headFacility.getNeighborList()) {
                Facility edgeFacility = facilityEdge.getTarget();
                double edgeWeight = facilityEdge.getWeight();
                double distanceFromHeadFacility = headFacility.getMinDistance() + edgeWeight;
                System.out.println("Distance from head facility: " + distanceFromHeadFacility);
                System.out.println("Source Edge Vertex: " + edgeFacility.getLocation());
                System.out.println("Source Edge Weight: " + edgeWeight);

                if (distanceFromHeadFacility < edgeFacility.getMinDistance()) {
                    facilityQueue.remove(edgeFacility);
                    edgeFacility.setMinDistance(distanceFromHeadFacility);
                    System.out.println("Edge facility min distance: " + edgeFacility.getMinDistance());
                    edgeFacility.setPrevious(headFacility);
                    facilityQueue.add(edgeFacility);
                }

            }
        }
    }

    public static List<Facility> getShortestPathTo(Facility target) {
        List<Facility> path = new ArrayList<>();
        for (Facility facility = target; facility != null; facility = facility.getPrevious()) {
            path.add(facility);
        }
        Collections.reverse(path);
        return path;
    }

    public static List<Facility> shortestPath(Facility source, Facility target) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
        computePaths(source);
        System.out.println("Distance to " + target.getLocation() + ". Shortest distance: " + target.getMinDistance());
        List<Facility> path = getShortestPathTo(target);
        System.out.println("Path: ");
        printFacilityPath(path);

        return path;
    }

    public static void printFacilityPath(List<Facility> facilityPath) {
        for (Facility facility : facilityPath) {
            System.out.print(facility.getLocation() + " -> ");
        }


    }

    /*// Builds graph
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
    }*/

    /*public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null ; vertex = vertex.getPrevious()) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }*/

    /*public static List<Vertex> shortestPath(Vertex source, Vertex target) throws NullVertexException {
        computePaths(source); // run Dijkstra
        System.out.println("Distance to " + target + target.getMinDistance());
        List<Vertex> path = getShortestPathTo(target);
        System.out.println("Path: " + path);

        return path;
    }*/

    public static void main(String[] args) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
        Facility santaFeFacility = FacilityFactory.createFacility("Santa Fe, NM");
        Facility chicagoFacility = FacilityFactory.createFacility("Chicago, IL");
        Facility newYorkFacility = FacilityFactory.createFacility("New York City, NY");
        Facility stLouisFacility = FacilityFactory.createFacility("St. Louis, MO");
        Facility fargoFacility = FacilityFactory.createFacility("Fargo, ND");

        chicagoFacility.addNeighbor(new FacilityEdge(newYorkFacility, 8));
        newYorkFacility.addNeighbor(new FacilityEdge(stLouisFacility, 10));
        stLouisFacility.addNeighbor(new FacilityEdge(santaFeFacility, 9));
        chicagoFacility.addNeighbor(new FacilityEdge(fargoFacility, 2));
        fargoFacility.addNeighbor(new FacilityEdge(santaFeFacility, 1));

        computePaths(chicagoFacility);
        shortestPath(chicagoFacility, santaFeFacility);

    }
}

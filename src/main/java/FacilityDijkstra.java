package src.main.java;

import src.main.java.Exceptions.*;
import src.main.java.Facilities.ChicagoFacility;
import src.main.java.Interfaces.Facility;
import src.main.java.ShortestPath.Edge;
import src.main.java.ShortestPath.FacilityEdge;
import src.main.java.ShortestPath.Vertex;

import java.util.*;

/**
 * Created by Jordan on 5/1/2017.
 */
public class FacilityDijkstra {

    public void computePaths(Facility source) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
        source.setMinDistance(0);
        PriorityQueue<Facility> facilityQueue = new PriorityQueue<>();
        facilityQueue.add(source);

        while (!facilityQueue.isEmpty()) {
            Facility headFacility = facilityQueue.poll(); // returns the head of the queue or null if queue is empty
            System.out.println("Head Facility " + headFacility.getLocation());

            if (headFacility.getNeighborList() == null) {
                throw new NullNeighborListException();
            }

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

    public List<Facility> getShortestPathTo(Facility target) {
        List<Facility> path = new ArrayList<>();
        for (Facility facility = target; facility != null; facility = facility.getPrevious()) {
            path.add(facility);
        }
        Collections.reverse(path);
        return path;
    }

    public List<Facility> shortestPath(Facility source, Facility target) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
        computePaths(source);
        System.out.println("Distance to " + target.getLocation() + ". Shortest distance: " + target.getMinDistance());
        List<Facility> path = getShortestPathTo(target);
        System.out.println("Path: ");
        printFacilityPath(path);

        return path;
    }

    public void printFacilityPath(List<Facility> facilityPath) {
        for (Facility facility : facilityPath) {
            System.out.print(facility.getLocation() + " -> ");
        }
        getTotalMiles(facilityPath);
    }

    public void getTotalMiles(List<Facility> facilityPath) {
        double totalDistance = 0;

        for (int i = 0; i < facilityPath.size(); i++) {
            Facility facility = facilityPath.get(i);
            System.out.println("Facility: " + facility.getLocation());
            for (FacilityEdge facilityEdge : facility.getNeighborList()) {
                System.out.println("Facility Edge Target: " + facilityEdge.getTarget().getLocation());
                if (facilityEdge.getTarget().getLocation() == facilityPath.get(i+1).getLocation()) {
                    totalDistance += facilityEdge.getWeight();
                }
            }

        }

        System.out.println("Total distance: " + totalDistance);
    }

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

        FacilityDijkstra facilityDijkstra = new FacilityDijkstra();

        facilityDijkstra.computePaths(chicagoFacility);
        facilityDijkstra.shortestPath(chicagoFacility, santaFeFacility);

    }
}

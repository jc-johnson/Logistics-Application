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


    public static void run(String startFacility, String endFacility) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException {
        Facility source = FacilityFactory.createFacility(startFacility);
        Facility target = FacilityFactory.createFacility(endFacility);

        System.out.println(source.getLocation() + " to " + target.getLocation() + ":");
        computePaths(source);
        shortestPath(source, target);

    }

    // each Facility in shortest path gets a previous
    public static void computePaths(Facility source) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
        source.setMinDistance(0);
        PriorityQueue<Facility> facilityQueue = new PriorityQueue<>();
        facilityQueue.add(source);

        while (!facilityQueue.isEmpty()) {
            Facility headFacility = facilityQueue.poll(); // returns the head of the queue or null if queue is empty
            // System.out.println("Head Facility: " + headFacility.getLocation());
            // System.out.println("");
            if (headFacility.getNeighborList() == null) {
                throw new NullNeighborListException();
            }
            if (headFacility.getNeighborList().size() == 0) {
                throw new EmptyNeighborListException();
            }
            // Visit each edge exiting the headFacility
            for (FacilityEdge facilityEdge : headFacility.getNeighborList()) {
                Facility edgeFacility = facilityEdge.getTarget();
                double edgeWeight = facilityEdge.getWeight();
                double distanceFromHeadFacility = headFacility.getMinDistance() + edgeWeight;
                // System.out.println("Distance from head facility: " + distanceFromHeadFacility);
                // System.out.println("Edge Vertex: " + edgeFacility.getLocation());
                // System.out.println("Edge Weight: " + edgeWeight);
                // System.out.println("Edge Facility Min Distance: " + edgeFacility.getMinDistance());
                // System.out.println("");
                // all connecting facilities get added to queue to parse all thier neighbors next
                if (distanceFromHeadFacility < edgeFacility.getMinDistance()) {

                    facilityQueue.remove(edgeFacility);
                    edgeFacility.setMinDistance(distanceFromHeadFacility);
                    // System.out.println("New edge facility min distance: " + edgeFacility.getMinDistance());
                    // System.out.println("Newly added edge facility: " + edgeFacility.getLocation());
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
        List<Facility> path = getShortestPathTo(target);
        // System.out.println("Path: ");
        printFacilityPath(path);

        return path;
    }

    public static void printFacilityPath(List<Facility> facilityPath) {
        /*for (Facility facility : facilityPath) {
            System.out.print(facility.getLocation() + " -> ");
        }*/

        for (int i = 0; i < facilityPath.size(); i++) {
            Facility facility = facilityPath.get(i);
            System.out.print(facility.getLocation() + " -> ");
        }

        Double totalMiles = getTotalMiles(facilityPath);
        Double totalDays = getTotalDays(totalMiles);

        System.out.println(totalMiles + " mi");
        System.out.println(totalMiles + " mi / (8 hours per day * 50 mph) = " + totalDays + " days");
    }

    public static double getTotalMiles(List<Facility> facilityPath) {
        double totalDistance = 0;

        for (int i = 0; i < facilityPath.size()-1; i++) {
            Facility facility = facilityPath.get(i);
            // System.out.println("Facility: " + facility.getLocation());
            for (FacilityEdge facilityEdge : facility.getNeighborList()) {
                // System.out.println("Facility Edge Target: " + facilityEdge.getTarget().getLocation());
                if (facilityEdge.getTarget().getLocation() == facilityPath.get(i+1).getLocation()) {
                    totalDistance += facilityEdge.getWeight();
                }
            }
        }

        return totalDistance;
    }

    public static double getTotalDays(double totalMiles) {
        return totalMiles / 400;
    }

    public static void main(String[] args) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
        /*Facility santaFeFacility = FacilityFactory.createFacility("Santa Fe, NM");
        Facility chicagoFacility = FacilityFactory.createFacility("Chicago, IL");
        Facility newYorkFacility = FacilityFactory.createFacility("New York City, NY");
        Facility stLouisFacility = FacilityFactory.createFacility("St. Louis, MO");
        Facility fargoFacility = FacilityFactory.createFacility("Fargo, ND");

        chicagoFacility.addNeighbor(new FacilityEdge(newYorkFacility, 8));
        newYorkFacility.addNeighbor(new FacilityEdge(stLouisFacility, 10));
        stLouisFacility.addNeighbor(new FacilityEdge(santaFeFacility, 9));
        chicagoFacility.addNeighbor(new FacilityEdge(fargoFacility, 2));
        fargoFacility.addNeighbor(new FacilityEdge(santaFeFacility, 1));*/

        //FacilityDijkstra facilityDijkstra = new FacilityDijkstra();

        //facilityDijkstra.computePaths(chicagoFacility);
        //facilityDijkstra.shortestPath(chicagoFacility, santaFeFacility);

    }
}

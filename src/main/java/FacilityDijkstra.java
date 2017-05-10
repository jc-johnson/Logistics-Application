package src.main.java;

import src.main.java.exceptions.EmptyNeighborListException;
import src.main.java.exceptions.NullFacilityException;
import src.main.java.exceptions.NullNeighborListException;
import src.main.java.exceptions.NullPriorityQueueException;
import src.main.java.interfaces.Facility;
import src.main.java.shortestpath.FacilityEdge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Jordan on 5/10/2017.
 */
public class FacilityDijkstra {

    private List<Facility> facilityList = null;

    public FacilityDijkstra(List<Facility> facilities) {
        this.facilityList = facilities;
    }

    private Facility getFacility(String facilityLocation) {
        for (Facility facility : facilityList) {
            if(facility.getLocation().equals(facilityLocation)) {
                return facility;
            }
        }
        return null;
    }

    public void run(String startFacility, String endFacility) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException {
        Facility source = getFacility(startFacility);
        Facility target = getFacility(endFacility);

        if (source != null && target != null) {
            System.out.println(source.getLocation() + " to " + target.getLocation() + ":");
            // computePaths(source);
            shortestPath(source, target);
        }
    }

    // each Facility in shortest path gets a previous
    public void computePaths(Facility source) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException {
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
                String edgeFacilityString = facilityEdge.getTarget();
                double edgeWeight = facilityEdge.getWeight();
                double distanceFromHeadFacility = headFacility.getMinDistance() + edgeWeight;
                Facility edgeFacility = getFacility(edgeFacilityString);

                // System.out.println("Distance from head facility: " + distanceFromHeadFacility);
                // System.out.println("Edge Vertex: " + edgeFacility.getLocation());
                // System.out.println("Edge Weight: " + edgeWeight);
                // System.out.println("Edge Facility Min Distance: " + edgeFacility.getMinDistance());
                // System.out.println("");

                // all connecting facilities get added to queue to parse all their neighbors next
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
        List<Facility> path = getShortestPathTo(target);
        // System.out.println("Path: ");
        printFacilityPath(path);

        return path;
    }

    public  void printFacilityPath(List<Facility> facilityPath) {

        System.out.print(facilityPath.get(0).getLocation());

        for (int i = 1; i < facilityPath.size(); i++) {
            Facility facility = facilityPath.get(i);
            System.out.print(" -> " + facility.getLocation());
        }

        Double totalMiles = getTotalMiles(facilityPath);
        Double totalDays = getTotalDays(totalMiles);

        System.out.println(" = " + totalMiles + " mi");
        System.out.println(totalMiles + " mi / (8 hours per day * 50 mph) = " + totalDays + " days");
        System.out.println("");
    }

    public double getTotalMiles(List<Facility> facilityPath) {
        double totalDistance = 0;

        for (int i = 0; i < facilityPath.size()-1; i++) {
            Facility facility = facilityPath.get(i);
            // System.out.println("Facility: " + facility.getLocation());
            for (FacilityEdge facilityEdge : facility.getNeighborList()) {
                // System.out.println("Facility Edge Target: " + facilityEdge.getTarget().getLocation());
                if (facilityEdge.getTarget() == facilityPath.get(i+1).getLocation()) {
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

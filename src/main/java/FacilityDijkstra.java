package src.main.java;

import src.main.java.exceptions.*;
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
    private Integer totalMiles = 0;
    private Integer totalDays = 0;

    public FacilityDijkstra(List<Facility> facilities) {
        this.facilityList = facilities;
    }

    private Facility getFacility(String facilityLocation) throws DataValidationException {
        if (facilityLocation.equals("") || facilityLocation.isEmpty()) {
            throw new DataValidationException("Empty string parameter in FacilityDijkstra.getFacility()");
        }
        for (Facility facility : facilityList) {
            if(facility.getLocation().equals(facilityLocation)) {
                return facility;
            }
        }
        return null;
    }

    public void run(String startFacility, String endFacility) throws EmptyNeighborListException, NullNeighborListException, NullPriorityQueueException, NullFacilityException, DataValidationException, NullParameterException, NegativeQuantityException {
        if (startFacility.equals("") || startFacility.isEmpty() || endFacility.equals("") || endFacility.isEmpty()) {
            throw new DataValidationException("Empty string parameter in FacilityDijkstra.getFacility()");
        }

        Facility source = getFacility(startFacility);
        Facility target = getFacility(endFacility);

        if (source != null && target != null) {
            System.out.println(source.getLocation() + " to " + target.getLocation() + ":");
            shortestPath(source, target);
        }
    }

    public List<Facility> shortestPath(Facility source, Facility target) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException, DataValidationException, NullParameterException, NegativeQuantityException {
        if (source == null || target == null) throw new DataValidationException("Empty Facility parameter in FacilityDijkstra.shortestPath()");

        computePaths(source);
        List<Facility> path = getShortestPathTo(target);
        setTotalMiles(path);
        setTotalDays(totalMiles);

        // printFacilityPath(path);

        return path;
    }

    // each Facility in shortest path gets a previous
    public void computePaths(Facility source) throws NullFacilityException, NullNeighborListException, NullPriorityQueueException, EmptyNeighborListException, DataValidationException {
        if (source == null) throw new DataValidationException("Empty Facility parameter in FacilityDijkstra.computePaths()");

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
                Integer edgeWeight = facilityEdge.getWeight();
                Integer distanceFromHeadFacility = headFacility.getMinDistance() + edgeWeight;
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

    public List<Facility> getShortestPathTo(Facility target) throws NullParameterException {
        if (target == null) throw new NullParameterException("Null Facility parameter in FacilityDijkstra.getShortestPathTo(Facility)");

        List<Facility> path = new ArrayList<>();
        for (Facility facility = target; facility != null; facility = facility.getPrevious()) {
            path.add(facility);
        }
        Collections.reverse(path);
        return path;
    }

    public void printFacilityPath(List<Facility> facilityPath) throws NullParameterException {
        if (facilityPath == null) throw new NullParameterException("Null facilityPath list in FacilityDijkstra.printFacilityPath");

        System.out.print(facilityPath.get(0).getLocation());

        for (int i = 1; i < facilityPath.size(); i++) {
            Facility facility = facilityPath.get(i);
            System.out.print(" -> " + facility.getLocation());
        }

        // setTotalMiles(facilityPath);
        Integer totalMiles = getTotalMiles();

        // setTotalDays(totalMiles);
        Integer totalDays = getTotalDays();

        System.out.println(" = " + totalMiles + " mi");
        System.out.println(totalMiles + " mi / (8 hours per day * 50 mph) = " + totalDays + " days");
        System.out.println("");
    }

    public void setTotalMiles(List<Facility> facilityPath) throws NullParameterException {
        if (facilityPath == null) throw new NullParameterException("Null facilityPath list in FacilityDijkstra.setTotalMiles");

        Integer totalDistance = 0;

        for (int i = 0; i < facilityPath.size()-1; i++) {
            Facility facility = facilityPath.get(i);
            // System.out.println("Facility: " + facility.getLocation());
            for (FacilityEdge facilityEdge : facility.getNeighborList()) {
                // System.out.println("Facility Edge Target: " + facilityEdge.getTarget().getLocation());
                String nextFacility = facilityPath.get(i+1).getLocation();
                if (facilityEdge.getTarget().equals(nextFacility)) {
                    totalDistance += facilityEdge.getWeight();
                }
            }
        }

        totalMiles = totalDistance;
    }

    public Integer getTotalMiles() { return totalMiles; }

    public void setTotalDays(Integer totalMiles) throws NegativeQuantityException {
        if (totalMiles < 0 ) throw new NegativeQuantityException("Negative parameter in FacilityDijkstra.setTotalDay()");

        totalDays = totalMiles / 400;
    }

    public Integer getTotalDays() { return totalDays; }

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

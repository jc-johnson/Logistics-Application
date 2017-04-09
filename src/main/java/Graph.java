package src.main.java;

import java.util.ArrayList;

public class Graph {

    private final int verticies;
    private final int edges;
    private ArrayList<Integer>[] adjacencyList;

    public Graph(int vertecies) {
        this.verticies = vertecies;
        this.edges = 0;
        adjacencyList = (ArrayList<Integer>[]) new ArrayList[vertecies];

    }

}
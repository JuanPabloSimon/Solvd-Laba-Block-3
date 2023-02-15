package helpers;

import java.util.*;

public class Graph<T> {
    private Map<String, Set<T>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    public Map<String, Set<T>> getAdjVertices() {
        return adjVertices;
    }

    public void setAdjVertices(Map<String, Set<T>> adjVertices) {
        this.adjVertices = adjVertices;
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(label, new HashSet<T>());
    }

    public void removeVertex(String label) {
        adjVertices.values().stream().forEach(e -> e.remove(label));
        adjVertices.remove(label);
    }

    public void addEdge(String start, T destination) {
        adjVertices.get(start).add(destination);
    }

    public void removeEdge(String start, T destination) {
        Set<T> eV1 = adjVertices.get(start);
        if (eV1 != null)
            eV1.remove(destination);
    }

    public Set<T> getAdjVertices(String label) {
        return adjVertices.get(label);
    }

}

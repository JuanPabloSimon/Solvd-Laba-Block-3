package helpers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<String, Set<String>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<>();
    }

    public Map<String, Set<String>> getAdjVertices() {
        return adjVertices;
    }

    public void setAdjVertices(Map<String, Set<String>> adjVertices) {
        this.adjVertices = adjVertices;
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(label, new HashSet<String>());
    }

    public void removeVertex(String label) {
        adjVertices.values().stream().forEach(e -> e.remove(label));
        adjVertices.remove(label);
    }

    public void addEdge(String start, String destination) {
        adjVertices.get(start).add(destination);
    }

    public void removeEdge(String start, String destination) {
        Set<String> eV1 = adjVertices.get(start);
        if (eV1 != null)
            eV1.remove(destination);
    }

    Set<String> getAdjVertices(String label) {
        return adjVertices.get(label);
    }

}

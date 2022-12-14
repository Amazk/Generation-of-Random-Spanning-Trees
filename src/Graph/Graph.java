package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Graph {
    public int order;
    public int edgeCardinality;
    private final Random random = new Random();

    public final ArrayList<LinkedList<Edge>> adjacency = new ArrayList<>();      // Non oriente
    public final ArrayList<LinkedList<Arc>> inAdjacency = new ArrayList<>();    // oriente
    public final ArrayList<LinkedList<Arc>> outAdjacency = new ArrayList<>();   // oriente

    public Graph(int upperBound) {
        order=upperBound;
        edgeCardinality=0;
        for(int i=0;i<order;i++) {
            adjacency.add(new LinkedList<>());
            inAdjacency.add(new LinkedList<>());
            outAdjacency.add(new LinkedList<>());
        }
    }
    public boolean isVertex(int index) {
        return index<order;
    }
    public void addVertex(int indexVertex) {
        if(isVertex(indexVertex)) {
            adjacency.add(new LinkedList<>());
        }
    }
    public void ensureVertex(int indexVertex) {}
    public void setRandomWeight() {
        for(LinkedList<Edge> list : adjacency)
            for(Edge e : list) e.weight = random.nextDouble();
    }
    public void addArc(Arc arc) {
        if(isVertex(arc.getSource()) && isVertex(arc.getDest())) {
            outAdjacency.get(arc.getSource()).add(arc);
            inAdjacency.get(arc.getDest()).add(arc);
        }
    }
    public void addEdge(Edge e) {
        if(isVertex(e.source) && isVertex(e.dest)) {
            adjacency.get(e.source).add(e);
            addArc(new Arc(e,false));
            addArc(new Arc(e,true));
        }
    }
    public LinkedList<Arc> outNeighbours(int sommet) {
        if(!isVertex(sommet)) return new LinkedList<>();
        return outAdjacency.get(sommet);
   }
}

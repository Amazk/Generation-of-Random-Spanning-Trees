package RandomTreeAlgos;
import Graph.Arc;
import Graph.Edge;
import Graph.Graph;

import java.util.*;
public class MinTree {
    private final Graph graph;
    private final ArrayList<Arc> tree;
    private final ArrayList<Arc> frontier;
    private final BitSet reached;
    private int currentVertex;
    private void push(int vertex) {
        frontier.addAll(graph.outNeighbours(vertex));
    }
    public MinTree(Graph graph) {
        tree = new ArrayList<>();
        this.graph=graph;
        frontier = new ArrayList<>();
        reached = new BitSet(graph.order);
    }
    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        MinTree algo = new MinTree(graph);
        algo.create(root);
        return algo.tree;
    }
    private void create(int root) {
        graph.setRandomWeight();
        reached.set(root);
        push(root);
        currentVertex = root;
        while (!frontier.isEmpty() && !allReached()) explore(getMinWeight(currentVertex));
    }
    private boolean allReached() {
        for(int i=0;i<graph.order;i++) if(!reached.get(i)) return false;
        return true;
    }
    private void explore(Arc arc) {
        if(reached.get(arc.getDest())) return;
        reached.set(arc.getDest());
        tree.add(arc);
        push(arc.getDest());
        currentVertex = arc.getDest();
    }
    private Arc getMinWeight(int vertex) {
        double minWeight = 2;
        Arc minArc = null;
        for (Arc arc : frontier) {
            if(minWeight>=arc.getWeight() && arc.getSource()==vertex) {
                minWeight=arc.getWeight();
                minArc=arc;
            }
        }
        frontier.remove(minArc);
        return minArc;
    }
}
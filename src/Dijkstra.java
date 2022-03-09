import dir.DiGraphe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra<T> {
    DiGraphe<T> g;
    T source;
    Set<T> unvisited;
    Map<T, Double> distances;
    boolean isComputed = false;

    public Dijkstra(DiGraphe<T> graphe, T source){
        g = graphe;
        this.source = source;
        unvisited = g.getNoeuds();
        distances = //TODO
    }

    public void run(){
        while (getMin() != null) {
            //TODO
        }
    }

    private T getMin(){
        T argMin = null;
        double min = Double.POSITIVE_INFINITY;
        //TODO
        return argMin;
    }

    public Map<T, Double> getDistances() {
        if (!isComputed)
            run();
        return distances;
    }
}

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
        distances = new HashMap<>();
        for (T node : unvisited){
            if (!node.equals(source))
                distances.put(node, Double.POSITIVE_INFINITY);
            else
                distances.put(source, 0d);
        }
    }

    public void run(){
        while (unvisited.size() > 0 && getMin() != null) {
            T current = getMin();
            double currentDist = distances.get(current);
            for (T suivant : g.getSuccesseurs(current)) {
                if (unvisited.contains(suivant)) {
                    double dist = currentDist + g.getValeurArc(current, suivant);
                    if (dist < distances.get(suivant))
                        distances.put(suivant, dist);
                }
            }
            unvisited.remove(current);
        }
    }

    private T getMin(){
        T argMin = null;
        double min = Double.POSITIVE_INFINITY;
        for (T noeud : unvisited){
            if (distances.get(noeud) < min){
                argMin = noeud;
                min = distances.get(noeud);
            }
        }
        return argMin;
    }

    public Map<T, Double> getDistances() {
        if (!isComputed)
            run();
        return distances;
    }
}

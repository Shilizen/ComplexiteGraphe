package dir;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatriceDiGraphe<T> implements DiGraphe<T> {

    private double[][] adj;
    private T[] ids;
    private int n;

    private MatriceDiGraphe(){
        //pointless
    }

    private MatriceDiGraphe(DiGraphe<T> source){
        //TODO
    }

    @Override
    public int compterArcs() {
        int cpt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!Double.isNaN(adj[i][j]))
                    cpt++;
            }
        }
        return cpt;
    }

    @Override
    public int compterNoeuds() {
        return n;
    }

    @Override
    public Set<T> getNoeuds() {
        return Stream.of(ids).collect(Collectors.toSet());
    }

    @Override
    public Set<Arc<T>> getArcs() {
        Set<Arc<T>> arcs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!Double.isNaN(adj[i][j]))
                    arcs.add(new Arc<>(ids[i], ids[j], adj[i][j]));
            }
        }
        return arcs;
    }

    @Override
    public boolean supprimerNoeud(T v) {
        throw new UnsupportedOperationException("Immutable");
    }

    @Override
    public boolean ajouterArc(T src, T dest, double val) {
        throw new UnsupportedOperationException("Immutable");
    }

    @Override
    public boolean supprimerArc(T v, T w) {
        throw new UnsupportedOperationException("Immutable");
    }
}

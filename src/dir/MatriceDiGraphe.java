package dir;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatriceDiGraphe<T> implements DiGraphe<T> {

    private double[][] adj;
    private ArrayList<T> ids;
    private int n;

    public MatriceDiGraphe(int size){
        n = size;
        ids = new ArrayList<>(n);
        // init the array to nan everywhere (no arcs)
        adj = new double[n][n];
        for (int i = 0; i <n; i++)
            Arrays.fill(adj[i], Double.NaN);
    }

    public MatriceDiGraphe(DiGraphe<T> source){
        n = source.compterNoeuds();
        ids = new ArrayList<>(source.getNoeuds());

        if (source instanceof ListeDiGraphe){
            // init the array to nan everywhere (no arcs)
            adj = new double[n][];
            for (int i = 0; i <n; i++)
                Arrays.fill(adj[i], Double.NaN);
            // add the arcs replacing the NaN where they exit
            for (Arc<T> a : source.getArcs()) {
                adj[ids.indexOf(a.source)][ids.indexOf(a.destination)] = a.valeur;
            }
        }
        else if (source instanceof MatriceDiGraphe){
            MatriceDiGraphe<T> s = (MatriceDiGraphe<T>) source;
            for (int i = 0; i <n; i++)
                System.arraycopy(s.adj[i], 0, adj[i], 0, n); // We can array copy this is way faster
        }
        else {
            throw new UnsupportedOperationException("Unsupported type for deep copy operation");
        }
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
        return new HashSet<>(ids);
    }

    @Override
    public Set<Arc<T>> getArcs() {
        Set<Arc<T>> arcs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!Double.isNaN(adj[i][j]))
                    arcs.add(new Arc<>(ids.get(i), ids.get(j), adj[i][j]));
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
        int idx = ids.indexOf(src);
        int idx2 = ids.indexOf(dest);

        if (idx == -1)
            throw new IllegalArgumentException("Node '"+src.toString()+"' must be in graph");
        if (idx2 == -1)
            throw new IllegalArgumentException("Node '"+dest.toString()+"' must be in graph");

        adj[idx][idx2] = val;
        return true;
    }

    @Override
    public boolean supprimerArc(T v, T w) {
        int idx = ids.indexOf(v);
        int idx2 = ids.indexOf(w);

        if (idx == -1)
            throw new IllegalArgumentException("Node '"+v.toString()+"' must be in graph");
        if (idx2 == -1)
            throw new IllegalArgumentException("Node '"+w.toString()+"' must be in graph");

        adj[idx][idx2] = Double.NaN;
        return true;
    }

    @Override
    public Set<T> getSuccesseurs(T v) {
        int idx = -1;
        for (int i = 0; i < ids.size(); i++) {
            if (v.equals(ids.get(i))) {
                idx = i;
                break;
            }
        }
        if (idx == -1)
            throw new IllegalArgumentException("Node '"+v.toString()+"' must be in graph");
        Set<T> res = new HashSet<>();
        for (int i = 0; i < ids.size(); i++) {
            if (!Double.isNaN(adj[idx][i]) && i != idx)
                res.add(ids.get(i));
        }
        return res;
    }

    @Override
    public double getValeurArc(T u, T v) {
        int idx = ids.indexOf(u);
        int idx2 = ids.indexOf(v);

        if (idx == -1)
            throw new IllegalArgumentException("Node '"+u.toString()+"' must be in graph");
        if (idx2 == -1)
            throw new IllegalArgumentException("Node '"+v.toString()+"' must be in graph");

        return adj[idx][idx2];
    }

    void setIds(ArrayList<T> ids) {
        this.ids = ids;
    }

    ArrayList<T> getIds() {
        return ids;
    }

    double[][] getAdj() {
        return adj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("--- MatriceDiGraphe ---").append("\n");
        sb.append(ids).append("\n");
        for (int i = 0; i < n; i++) {
            sb.append(ids.get(i)).append(": ").append(Arrays.toString(adj[i])).append("\n");
        }
        return sb.toString();
    }
}

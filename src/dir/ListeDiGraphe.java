package dir;

import com.sun.javaws.exceptions.InvalidArgumentException;
import nonDir.Noeud;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListeDiGraphe<T> implements DiGraphe<T> {

    private Set<Arc<T>> arcs;

    private ListeDiGraphe(){
        arcs = new HashSet<>();
    }

    private ListeDiGraphe(DiGraphe<T> source){
        arcs = new HashSet<>();
        if (source instanceof ListeDiGraphe){
            ListeDiGraphe<T> s = (ListeDiGraphe<T>) source;
            s.arcs.forEach(a -> arcs.add(new Arc<>(a.source, a.destination, a.valeur)));
        }
        else if (source instanceof MatriceDiGraphe){
            arcs.addAll( source.getArcs() );
        }
        else {
            throw new UnsupportedOperationException("Unsupported type for deep copy operation");
        }
    }

    @Override
    public int compterArcs() {
        return arcs.size();
    }

    @Override
    public int compterNoeuds() {
        return arcs.stream().flatMap(a -> Stream.of(a.destination, a.source)).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<T> getNoeuds() {
        return arcs.stream().flatMap(a -> Stream.of(a.destination, a.source)).collect(Collectors.toSet());
    }

    @Override
    public Set<Arc<T>> getArcs() {
        return arcs;
    }

    @Override
    public boolean supprimerNoeud(T v) {
        return false;
    }

    @Override
    public boolean ajouterArc(T src, T dest, double val) {
        return arcs.add(new Arc<>(src, dest, val));
    }

    @Override
    public boolean supprimerArc(T v, T w) {
        Arc<T> victime = null;
        for (Arc<T> a : arcs){
            if (a.destination.equals(w) && a.source.equals(v))
                victime = a;
        }
        if (victime == null)
            return false;
        return arcs.remove(victime);
    }
}

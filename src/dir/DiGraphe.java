package dir;

import nonDir.Noeud;
import utils.Pair;

import java.util.Set;

public interface DiGraphe<T> {
    public int compterArcs();
    public int compterNoeuds();

    public Set<T> getNoeuds();
    public Set<Arc<T>> getArcs();

    public boolean supprimerNoeud(T v);
    public boolean ajouterArc(T src, T dest, double val);
    public boolean supprimerArc(T v, T w);
}

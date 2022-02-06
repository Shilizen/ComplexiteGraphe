package nonDir;

import java.util.Set;

public interface Graphe<T> {

    public int compterArcs();
    public int compterNoeuds();
    public int degreMax();

    public Set<Noeud<T>> getNoeuds();
    public Noeud<T> getNoeud(T label);

    public boolean ajouterNoeud(Noeud<T> v);
    public boolean supprimerNoeud(Noeud<T> v);
    public boolean ajouterArc(Noeud<T> v, Noeud<T> w);
    public boolean supprimerArc(Noeud<T> v, Noeud<T> w);

    public boolean ajouterNoeud(T label);
    public boolean supprimerNoeud(T label);
    public boolean ajouterArc(T vLabel, T wLabel);
    public boolean supprimerArc(T vLabel, T wLabel);

}

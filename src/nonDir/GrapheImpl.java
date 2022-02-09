package nonDir;

import java.util.HashSet;
import java.util.Set;

public class GrapheImpl<T> implements Graphe<T>{

    Set<Noeud<T>> noeuds;

    public GrapheImpl() {
        noeuds = new HashSet<>();
    }

    @Override
    public int compterArcs() {
        int compteur = 0;
        Set<Noeud<T>> tmp = new HashSet<>();
        for (Noeud<T> v : noeuds) {
            for (Noeud<T> w : v.getVoisins()){
                if (!tmp.contains(w))
                    compteur++;
            }
            tmp.add(v);
        }

        return compteur;
    }

    @Override
    public int compterNoeuds() {
        return this.noeuds.size();
    }

    @Override
    public Noeud<T> getNoeud(T label) {
        return noeuds.stream().filter(n -> n.label.equals(label)).findFirst().orElse(null);
    }

    @Override
    public Set<Noeud<T>> getNoeuds() {
        return noeuds;
    }

    @Override
    public boolean ajouterNoeud(Noeud<T> v) {
        return noeuds.add(v);
    }

    @Override
    public boolean ajouterNoeud(T label) {
        if (getNoeud(label) == null)
            noeuds.add(new NoeudImpl<>(label));
        return true;
    }

    @Override
    public boolean supprimerNoeud(Noeud<T> v) {
        return noeuds.remove(v);
    }

    @Override
    public boolean supprimerNoeud(T label) {
        return noeuds.remove(getNoeud(label));
    }

    @Override
    public boolean ajouterArc(Noeud<T> v, Noeud<T> w) {
        noeuds.add(v);
        noeuds.add(w);
        getNoeud(v.getLabel()).getVoisins().add(getNoeud(w.getLabel()));
        getNoeud(w.getLabel()).getVoisins().add(getNoeud(v.getLabel()));
        return true;
    }

    @Override
    public boolean ajouterArc(T vLabel, T wLabel) {
        if (getNoeud(vLabel) == null)
            noeuds.add(new NoeudImpl<>(vLabel));
        if (getNoeud(wLabel) == null)
            noeuds.add(new NoeudImpl<>(wLabel));
        getNoeud(vLabel).getVoisins().add(getNoeud(wLabel));
        getNoeud(wLabel).getVoisins().add(getNoeud(vLabel));
        return true;
    }

    @Override
    public boolean supprimerArc(Noeud<T> v, Noeud<T> w) {
        return false;
    }

    @Override
    public boolean supprimerArc(T vLabel, T wLabel) {
        return false;
    }

    @Override
    public int degreMax() {
        return noeuds.stream().mapToInt(Noeud::degre).max().orElse(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Noeud n :
                noeuds) {
            sb.append(n.toString()).append("\n");
        }
        return sb.toString();
    }
}

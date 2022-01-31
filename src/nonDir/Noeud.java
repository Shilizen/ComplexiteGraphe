package nonDir;

import java.util.Set;

public abstract class Noeud<T> {
    T label;
    protected Set<T> voisins;

    public abstract int degre();

    public Set<T> getVoisins() {
        return voisins;
    }
}

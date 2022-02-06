package nonDir;

import java.util.Objects;
import java.util.Set;

public abstract class Noeud<T> {
    T label;
    protected Set<Noeud<T>> voisins;

    public abstract int degre();

    public Set<Noeud<T>> getVoisins() {
        return voisins;
    }

    public T getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Noeud)) return false;
        Noeud<?> noeud = (Noeud<?>) o;
        return label.equals(noeud.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}

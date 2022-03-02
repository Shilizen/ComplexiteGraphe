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
    public int hashCode() {
        return Objects.hash(label);
    }
}

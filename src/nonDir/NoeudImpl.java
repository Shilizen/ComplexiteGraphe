package nonDir;

import java.util.HashSet;
import java.util.stream.Collectors;

public class NoeudImpl<T> extends Noeud<T>{

    public NoeudImpl(T label) {
        this.label = label;
        this.voisins = new HashSet<>();
    }

    @Override
    public int degre() {
        return this.voisins.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NoeudImpl<?>)
            return label.equals(((NoeudImpl<?>)obj).label);
        else
            return false;
    }

    @Override
    public String toString() {
        return label.toString() + ": " + voisins.stream().map(n -> n.label.toString()).collect(Collectors.joining("; "));
    }
}

package nonDir;

import java.util.HashSet;

public class NoeudImpl<T> extends Noeud<T>{

    public NoeudImpl(T label) {
        this.label = label;
        this.voisins = new HashSet<>();
    }

    @Override
    public int degre() {
        return this.voisins.size();
    }


}

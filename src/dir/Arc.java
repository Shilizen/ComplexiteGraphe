package dir;

import java.util.Objects;

public class Arc <T>{
    double valeur;
    T source, destination;

    public Arc(T source, T destination, double valeur) {
        this.valeur = valeur;
        this.source = source;
        this.destination = destination;
    }

    public boolean isSelfLoop(){
        return source.equals(destination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc<?> arc = (Arc<?>) o;
        return  source.equals(arc.source) && destination.equals(arc.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Override
    public String toString() {
        return source.toString() + "-(" + valeur + ")->" + destination.toString();
    }

    public double getValeur() {
        return valeur;
    }
}

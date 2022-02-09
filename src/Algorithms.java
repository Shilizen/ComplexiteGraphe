import nonDir.Graphe;
import nonDir.Noeud;
import utils.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithms {

    private static <T> List<Noeud<T>> arbreProfondeur(Graphe<T> g, Noeud<T> depart) {
        return null;
    }

    private static <T> List<Noeud<T>> arbreLargeur(Graphe<T> g, Noeud<T> depart) {
        return null;
    }

    private static <T> List<Set<Noeud<T>>> composantesConnexes(Graphe<T> g) {
        return null;
    }

    /**
     * Produit une coupe aléatoire par fusion successive de nœuds
     * @param g le graphe à couper
     * @param <T> Type des labels du graphe
     * @return une paire contenant l'ensemble S et le nombre d'arcs coupés
     */
    private static <T> Pair<Set<Noeud<T>>, Integer> coupeAlea(Graphe<T> g){
        return new Pair<>(null, 0);
    }
}

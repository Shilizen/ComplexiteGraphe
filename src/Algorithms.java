import nonDir.Graphe;
import nonDir.Noeud;
import utils.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithms {

    public static <T> List<Noeud<T>> arbreProfondeur(Graphe<T> g, Noeud<T> depart) {
        DepthVisitor<T> visitor = new DepthVisitor<>();
        return visitor.parcours(depart);
    }

    private static class DepthVisitor<E> {
        public Set<Noeud<E>> visited = new HashSet<>();
        public List<Noeud<E>> parcours(Noeud<E> depart){
            visited.add(depart);
            return depart.getVoisins().stream()
                    .filter(n -> !visited.contains(n))
                    .map(this::parcours)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
    }

    public static <T> List<Noeud<T>> arbreLargeur(Graphe<T> g, Noeud<T> depart) {
        return null;
    }

    public static <T> List<Set<Noeud<T>>> composantesConnexes(Graphe<T> g) {
        Noeud<T> aNode = g.getNoeuds().iterator().next();
        List<Noeud<T>> parcours = arbreLargeur(g, aNode);

        if (parcours.size() == g.compterNoeuds()){
            ArrayList<Set<Noeud<T>>> tmp = new ArrayList<>();
            tmp.add(new HashSet<>(g.getNoeuds()));
            return tmp;
        }

        HashSet<Noeud<T>> nodesLeft = new HashSet<>(g.getNoeuds());
        nodesLeft.removeAll(parcours);
        ArrayList<Set<Noeud<T>>> composantes = new ArrayList<>();
        composantes.add(new HashSet<>(parcours));

        while (nodesLeft.size() > 0){
            aNode = nodesLeft.iterator().next();
            parcours = arbreLargeur(g, aNode);
            composantes.add(new HashSet<>(parcours));
            nodesLeft.removeAll(parcours);
        }

        return composantes;
    }

    /**
     * Produit une coupe aléatoire par fusion successive de nœuds
     * @param g le graphe à couper
     * @param <T> Type des labels du graphe
     * @return une paire contenant l'ensemble S et le nombre d'arcs coupés
     */
    public static <T> Pair<Set<Noeud<T>>, Integer> coupeAlea(Graphe<T> g){
        return new Pair<>(null, 0);
    }
}

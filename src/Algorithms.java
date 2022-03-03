import nonDir.Graphe;
import nonDir.GrapheImpl;
import nonDir.Noeud;
import nonDir.NoeudImpl;
import utils.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Algorithms {

    public static <T> List<Noeud<T>> arbreProfondeur(Graphe<T> g, Noeud<T> depart) {
        DepthVisitor<T> visitor = new DepthVisitor<>();
        return visitor.parcours(depart);
    }

    private static class DepthVisitor<E> {
        public Set<Noeud<E>> visited = new HashSet<>();
        public List<Noeud<E>> parcours(Noeud<E> depart){
            visited.add(depart);
            return Stream.concat(Stream.of(depart),
                            depart.getVoisins().stream()
                            .filter(n -> !visited.contains(n))
                    .map(this::parcours)
                    .flatMap(Collection::stream))
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
        Graphe<List<T>> g_ = toList(g);
        Set<Pair<Noeud<List<T>>, Noeud<List<T>>>> allArcs = g_.getArcs();

        while (allArcs.size() > 1){
            Pair<Noeud<List<T>>, Noeud<List<T>>> unArc = randomElement(allArcs);
            g_ = fusioner(g_, unArc.getA(), unArc.getB());
            allArcs = g_.getArcs();
        }

        //un cote de la coupe
        List<T> cutLabels = g_.getNoeuds().stream().findFirst().orElse(null).getLabel();
        //recup des noeuds d'origine
        Set<Noeud<T>> cutNodes = cutLabels.stream().map(g::getNoeud).collect(Collectors.toSet());

        int nbArcsCoupes = 0;
        for (Noeud<T> u : cutNodes){
            for (Noeud<T> v: u.getVoisins()){
                if (!cutNodes.contains(v))
                    nbArcsCoupes++;
            }
        }

        return new Pair<>(cutNodes, nbArcsCoupes);
    }

    static <T> Graphe<List<T>> fusioner(Graphe<List<T>> in, Noeud<List<T>> a, Noeud<List<T>> b){
        Graphe<List<T>> out = new GrapheImpl<>();
        //copie des noeuds sauf a et b
        for (Noeud<List<T>> n : in.getNoeuds()){
            if (n != a && n != b)
                out.ajouterNoeud(n.getLabel());
        }

        // de même copie des arcs sauf ceux qui concernent a et b
        Set<Noeud<List<T>>> visites = new HashSet<>();
        for (Noeud<List<T>> n : in.getNoeuds()){
            if (n != a && n != b){
                for (Noeud<List<T>> autre : n.getVoisins()){
                    if (!visites.contains(autre) && autre != a && autre != b){
                        out.ajouterArc(n.getLabel(), autre.getLabel());
                    }
                }
            }
            visites.add(n);
        }

        // creation du noeud a+b
        Noeud<List<T>> fusione = new NoeudImpl<List<T>>(a.getLabel());
        out.ajouterNoeud(fusione);

        //creation des arcs du noeud a+b
        Set<Noeud<List<T>>> tousVoisins = new HashSet<>(a.getVoisins());
        tousVoisins.addAll(b.getVoisins());
        tousVoisins.remove(a);
        tousVoisins.remove(b);
        a.getLabel().addAll(b.getLabel());

        // ajout des acrs au nouveau graphe
        for (Noeud<List<T>> voisin : tousVoisins){
            out.ajouterArc(fusione, out.getNoeud(voisin.getLabel()));
        }

        return out;
    }

    // changement de type pour supporter les labels de noeuds fusionés
    static <T> Graphe<List<T>> toList(Graphe<T> in){
        Graphe<List<T>> out = new GrapheImpl<>();
        in.getNoeuds().forEach(n -> {
            List<T> l = new ArrayList<>();
            l.add(n.getLabel());
            out.ajouterNoeud(l);
        });
        out.getNoeuds().forEach(u -> {
            in.getNoeud(u.getLabel().get(0)).getVoisins().forEach(v -> {
                List<T> l = new ArrayList<>();
                l.add(v.getLabel());
                out.ajouterArc(u, out.getNoeud(l));
            });
        });

        return out;
    }

    // prend un element au hasard dans un set
    private static <T> T randomElement(Set<T> set) {
        int cpt = (int) (Math.random() * set.size());
        for(T el: set)
            if (--cpt < 0)
                return el;
        return null;
    }
    
    // changement de type pour supporter les labels de noeuds fusionés
    static <T> Graphe<List<T>> toList(Graphe<T> in){
        Graphe<List<T>> out = new GrapheImpl<>();
        in.getNoeuds().forEach(n -> {
            List<T> l = new ArrayList<>();
            l.add(n.getLabel());
            out.ajouterNoeud(l);
        });
        out.getNoeuds().forEach(u -> {
            in.getNoeud(u.getLabel().get(0)).getVoisins().forEach(v -> {
                List<T> l = new ArrayList<>();
                l.add(v.getLabel());
                out.ajouterArc(u, out.getNoeud(l));
            });
        });

        return out;
    }
}

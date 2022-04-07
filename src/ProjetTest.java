import dir.DiGrapheLoader;
import dir.MatriceDiGraphe;

public class ProjetTest {
    //Valeurs obj optimales http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/STSP.html
    //documentation http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp95.pdf
    public static void main(String[] args) {
        //Verification of the distance function see doc 2.7
        MatriceDiGraphe<String> pcb442 = DiGrapheLoader.fromTSPLibFile("./data/pcb442.tsp");
        System.out.println(objectiveValue(pcb442, IntStream.range(1,443).boxed().map(Object::toString).collect(Collectors.toList())));
        
        MatriceDiGraphe<String> g = DiGrapheLoader.fromTSPLibFile("./data/berlin52.tsp");
        List<String> tour = nnAlgorithmAllStarts(g);
        System.out.println("Afficher le tour");
        System.out.println(tour);
        System.out.println("Afficher la vérification");
        System.out.println(objectiveValue(g, tour)); // 8181 about 8% from optimal not bad for naive/fast algorithm 
    }
    
        public static List<String> nnAlgorithmAllStarts(MatriceDiGraphe<String> g){
        double minZ = Double.POSITIVE_INFINITY;
        List<String> minTour = null;

        for (String start : g.getNoeuds()) {
            List<String> tour = new ArrayList<>();
            String arcN = start;
            while (tour.size() < g.compterNoeuds()) {
                tour.add(arcN);
                String sNoeud = null; //initialisation de la chaîne de caractère qui contiendra le label du noeud étudié.
                double mini = Double.POSITIVE_INFINITY; //le mini est l'infini positif.
                for (String unNoeud : g.getSuccesseurs(arcN)){
                    if (g.getValeurArc(arcN, unNoeud) < mini){ //si la valeur du noeud de départ et du noeud étudié est inférieur à la valeur du mini (soit non +infini),
                        // on vérifie que ce noeud n'a pas été regardé.
                        if (!tour.contains(unNoeud)){ //si le tour ne contient pas le noeud en question
                            sNoeud = unNoeud;
                            mini = g.getValeurArc(arcN, unNoeud); // le minimum devient le cout de l'arc a,b
                        }
                    }
                }
                arcN = sNoeud; // en sortie de boucle, le prochain arc est l'arc étudié
            }
            if (objectiveValue(g, tour) < minZ){
                minZ = objectiveValue(g, tour);
                minTour = tour; //si l'objectif est validé alors le minimum du graphe deviendra le "tour" étudié
            }
        }
        return minTour; //retourne le chemin minimal du graphe.
    }

//début de l'algorithme de Lin-Kernighan
    //j'ai bidouillé un truc mais j'suis sûre que mon algo est nul et n'a aucun sens
    public static List<String> algorithmLinKernighan(MatriceDiGraphe<String> g, List<String> tour){
        double max = Double.POSITIVE_INFINITY;
        List<String> listG = new ArrayList<>(); //list G
        List<String> listA = new ArrayList<>();
        List<String> listB = new ArrayList<>();

        for (String noeudDepart : g.getNoeuds()){
            for (Arc arcA : g.getArcs()){
                for (Arc arcB : g.getArcs()){
                    double distanceA = arcA.getValeur();
                    double distanceB = arcB.getValeur();
                    max = Double.max(distanceA, distanceB);
                    String noeudArcA = arcA.toString();
                    String noeudArcB = arcB.toString();
                    String gVal = String.valueOf(distanceA + distanceB - 2 * g.getValeurArc(noeudArcA, noeudArcB));
                    if (distanceA + distanceB - 2 * g.getValeurArc(noeudArcA, noeudArcB) == max){
                        listG.add(gVal);
                        listA.add(noeudArcA);
                        listB.add(noeudArcB);
                    }
                }
            }
        }

        return null;
    }
    
    public static double objectiveValue(DiGraphe<String> g, List<String> tour){
        double d = g.getValeurArc(tour.get(g.compterNoeuds()-1), tour.get(0));
        for (int i = 0; i < tour.size() - 1; i++) {
            d += g.getValeurArc(tour.get(i), tour.get(i+1));
        }
        return d;
    }
}

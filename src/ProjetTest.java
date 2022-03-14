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
        System.out.println(tour);
        System.out.println(objectiveValue(g, tour)); // 8181 about 8% from optimal not bad for naive/fast algorithm 
    }
    
        public static List<String> nnAlgorithmAllStarts(MatriceDiGraphe<String> g){
        double minZ = Double.POSITIVE_INFINITY;
        List<String> minTour = null;

        for (String start : g.getNoeuds()) {
            List<String> tour = new ArrayList<>();
            //TODO
            while (tour.size() < g.compterNoeuds()) {
                //TODO
            }
            if (objectiveValue(g, tour) < minZ){
                //TODO
            }
        }
        return minTour;
    }
    
    public static double objectiveValue(DiGraphe<String> g, List<String> tour){
        double d = g.getValeurArc(tour.get(g.compterNoeuds()-1), tour.get(0));
        for (int i = 0; i < tour.size() - 1; i++) {
            d += g.getValeurArc(tour.get(i), tour.get(i+1));
        }
        return d;
    }
}

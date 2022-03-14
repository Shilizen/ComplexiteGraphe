import dir.DiGrapheLoader;
import dir.MatriceDiGraphe;

public class ProjetTest {
    public static void main(String[] args) {
        MatriceDiGraphe<String> g = DiGrapheLoader.fromTSPLibFile("./data/berlin52.tsp");
        System.out.println(g);
        //Valeurs obj optimales http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/STSP.html
        //documentation http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp95.pdf
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

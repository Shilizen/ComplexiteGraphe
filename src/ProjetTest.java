import dir.DiGrapheLoader;
import dir.MatriceDiGraphe;

public class ProjetTest {
    public static void main(String[] args) {
        MatriceDiGraphe<String> g = DiGrapheLoader.fromTSPLibFile("./data/kroA200.tsp");
        System.out.println(g);
        //Valeurs obj optimales http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/STSP.html
        //documentation http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp95.pdf
    }
}

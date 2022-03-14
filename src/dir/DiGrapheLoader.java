package dir;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiGrapheLoader {
    public static MatriceDiGraphe<String> fromTSPLibFile(String path){
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String name = br.readLine();
            String type = br.readLine().replace("TYPE","").replace(":","").replace(" ", "");
            String comment = br.readLine();
            int size = Integer.parseInt(br.readLine().replace("DIMENSION", "").replace(":","").replace(" ", ""));
            String metric = br.readLine().replace("EDGE_WEIGHT_TYPE","").replace(":","").replace(" ", "");

            if (metric.equals("EUC_2D")){
                int[] ids = new int[size];
                double[][] points = new double[size][2];
                boolean markers_check = br.readLine().contains("COORD");
                for (int i = 0; i < size; i++) {
                    String[] tmp = br.readLine().split(" ");
                    ids[i] = Integer.parseInt(tmp[0]);
                    points[i] = new double[]{Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2])};
                }
                markers_check = markers_check && br.readLine().contains("EOF");
                System.out.printf("Anomaly reading coordinates ? %s %n", !markers_check);

                // Init the graph with ids of nodes
                MatriceDiGraphe<String> g = new MatriceDiGraphe<>(size);
                for (int i = 0; i < size; i++) {
                    g.getIds().add(String.valueOf(ids[i]));
                }

                //fill the distance matrix
                double[][] adjptr = g.getAdj();
                for (int i = 0; i < size; i++) {
                    for (int j = i+1; j < size; j++) {
                        // complies with http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp95.pdf
                        double xd = points[i][0] - points[j][0];
                        double yd = points[i][1] - points[j][1];
                        adjptr[i][j] = Math.round(Math.sqrt(xd*xd + yd*yd));
                        adjptr[j][i] = adjptr[i][j];
                    }
                }

                return g;
            }
            else {
                System.err.println("Unsupported file format");
                return null;//TODO
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DiGraphe<String> fromTSV(String path){
        try {
            List<String> lines =  Files.readAllLines(Paths.get(path));
            DiGraphe<String> g = new ListeDiGraphe<>();
            for (String l : lines){
                String[] tmp = l.split("\t");
                g.ajouterArc(tmp[0], tmp[1], Double.parseDouble(tmp[2]));
            }
            return g;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

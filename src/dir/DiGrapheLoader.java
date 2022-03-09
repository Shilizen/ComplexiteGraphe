package dir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DiGrapheLoader {
    public static DiGraphe<String> fromTSPLibFile(String path){
        //TODO
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

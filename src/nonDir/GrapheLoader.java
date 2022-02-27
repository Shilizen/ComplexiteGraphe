package nonDir;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrapheLoader {
    public static Graphe<String> chargerChaine(String chemin){
        try {
            List<String> lines =  Files.readAllLines(Paths.get(chemin));
            Graphe<String> g = new GrapheImpl<>();
            for (String l : lines){
                String[] tmp = l.split("\t");
                g.ajouterArc(tmp[0], tmp[1]);
            }
            return g;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        public static Graphe<Integer> chargerEntier(String chemin){
        try {
            List<String> lines =  Files.readAllLines(Paths.get(chemin));
            Graphe<Integer> g = new GrapheImpl<>();
            for (String l : lines){
                String[] tmp = l.split("\t");
                g.ajouterArc(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
            }
            return g;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean enregistrer(Graphe g, String chemin){
        try (FileOutputStream fos = new FileOutputStream(chemin)){
            Set<Noeud> tmp = new HashSet<>();
            Set<Noeud> allNodes = g.getNoeuds();
            for (Noeud v : allNodes) {
                Set<Noeud> voisins =  v.getVoisins();
                for (Noeud w : voisins){
                    if (!tmp.contains(w))
                        fos.write((v.label.toString() + "\t" + w.label.toString() + "\n").getBytes(StandardCharsets.UTF_8) );
                }
                tmp.add(v);
            }

            fos.flush();
            return true;
        } catch (IOException e){
            System.err.println("Erreur de sauvegarde");
            return false;
        }
    }
}

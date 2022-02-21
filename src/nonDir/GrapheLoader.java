package nonDir;

public class GrapheLoader {
    public static Graphe<String> chargerChaine(String chemin){
        return null; // TODO charger le fichier vers une structure Graphe du bon type
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
        return false; //TODO
    }
}

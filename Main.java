import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Etudiant> etudiants = GestionNotes.lireCSV("C:\\notes.csv");
        for (Etudiant e : etudiants) e.calculerMoyenne();
        etudiants.sort(Comparator.comparingDouble(e -> -e.moyenne));
        GestionNotes.ecrireCSV(etudiants, "resultats.csv");
        System.out.println("Fini ✅ Résultats dans resultats.csv");
    }
}
 
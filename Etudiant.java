import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    String id, nom;
    List<Double> notes = new ArrayList<>();
    double moyenne;

    Etudiant(String id, String nom) { this.id = id; this.nom = nom; }

    void ajouterNote(double n) { notes.add(n); }
    void calculerMoyenne() {
        moyenne = notes.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }
}
 
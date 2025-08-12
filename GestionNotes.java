import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GestionNotes {

    // ✅ Reads CSV in either "wide" or "long" format, ignores text in numeric columns
    static List<Etudiant> lireCSV(String fichier) throws IOException {
        List<String> lignes = Files.readAllLines(Paths.get(fichier));
        List<Etudiant> etudiants = new ArrayList<>();
        if (lignes.isEmpty()) return etudiants;

        for (int i = 1; i < lignes.size(); i++) { // skip header
            String line = lignes.get(i).trim();
            if (line.isEmpty()) continue;

            String[] p = line.split(",", -1);
            if (p.length < 2) continue;

            Etudiant e = new Etudiant(p[0].trim(), p[1].trim());

            // Format long: id,nom,matiere,note (note = last column numeric)
            if (p.length >= 4 && isNumeric(p[p.length - 1])) {
                e.ajouterNote(parseNum(p[p.length - 1]));
            } else {
                // Format wide: id,nom,note1,note2,...
                for (int j = 2; j < p.length; j++) {
                    if (isNumeric(p[j])) e.ajouterNote(parseNum(p[j]));
                }
            }
            etudiants.add(e);
        }
        return etudiants;
    }

    // ✅ Calculates averages
    static void calculerMoyennes(List<Etudiant> etudiants) {
        for (Etudiant e : etudiants) e.calculerMoyenne();
    }

    // ✅ Writes results to CSV
    static void ecrireCSV(List<Etudiant> etudiants, String fichier) throws IOException {
        try (PrintWriter pw = new PrintWriter(fichier)) {
            pw.println("Id,Nom,Moyenne");
            for (Etudiant e : etudiants) {
                pw.println(e.id + "," + e.nom + "," + String.format(Locale.US, "%.2f", e.moyenne));
            }
        }
    }

    // ✅ Helper: check if value is numeric
    private static boolean isNumeric(String s) {
        if (s == null) return false;
        s = s.trim().replace(',', '.');
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Helper: parse number safely
    private static double parseNum(String s) {
        return Double.parseDouble(s.trim().replace(',', '.'));
    }
}
 
package tests;

import static org.junit.jupiter.api.Assertions.*;
import logique.Recette;
import logique.Alchimiste;
import org.junit.jupiter.api.Test;

class AlchimisteTest {
    private Alchimiste alchimiste = null;
    private String nom;
    private int niveau;
    private int experience;

    String NomTropCourt(){
        nom = "";
        for (int i = 1; i <= Alchimiste.LONGUEUR_NOM_MINIMAL-1; i++){
            nom = nom + "X";
        }
        return nom;
    }
    String NomOK(){
        nom = "";
        for (int i = 1; i <= Alchimiste.LONGUEUR_NOM_MINIMAL; i++){
            nom = nom + "X";
        }
        return nom;
    }

    @Test
    void setNomNonNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste = new Alchimiste(null);
        });
        assertEquals(Alchimiste.ERREUR_VALEUR_NULL, ex.getMessage());
    }

    @Test
    void setNomNomTailleMinimum() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste = new Alchimiste(NomTropCourt());
        });
        assertEquals(Alchimiste.ERREUR_NOM_TROP_COURT, ex.getMessage());
    }

    @Test
    void setNomOK(){
        String nomOK = NomOK();
        alchimiste = new Alchimiste(nomOK);
        assertEquals(alchimiste.getNom(), nomOK);
    }

    @Test
    void fairePotionRecetteNull(){
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste.fairePotion(null);
        });
        assertEquals(Alchimiste.ERREUR_VALEUR_NULL, ex.getMessage());
    }
}
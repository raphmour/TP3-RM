package tests;

import static org.junit.jupiter.api.Assertions.*;

import logique.Alchimiste;
import logique.Ingredient;
import logique.MessagesErreur;
import logique.Recette;
import org.junit.jupiter.api.Test;

class AlchimisteTest {
    private Alchimiste alchimiste = null;
    private String nom;
    private int niveau;
    private int experience;
    Ingredient ing1 = new Ingredient("A", 1);
    Ingredient ing2 = new Ingredient("B", 1);
    Ingredient ing3 = new Ingredient("C", 1);
    Recette recetteDifficile = new Recette(ing1, ing2, ing3, "Difficile", 5, 500);
    Recette recetteFacile = new Recette(ing1, ing2, ing3, "Facile", 1, 100);

    public String NomTropCourt() {
        nom = "";
        for (int i = 1; i <= Alchimiste.LONGUEUR_NOM_MINIMAL - 1; i++) {
            nom = nom + "X";
        }
        return nom;
    }

    public static String NomOK() {
        String nom;
        nom = "";
        for (int i = 1; i <= Alchimiste.LONGUEUR_NOM_MINIMAL; i++) {
            nom = nom + "X";
        }
        return nom;
    }

    @Test
    void setNomNonNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste = new Alchimiste(null);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRE_NULL, ex.getMessage());
    }

    @Test
    void setNomInferieurTailleMinimum() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste = new Alchimiste(NomTropCourt());
        });
        assertEquals(MessagesErreur.ERREUR_NOM_ALCHIMISTE_TROP_COURT, ex.getMessage());
    }

    @Test
    void setNomVide() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste = new Alchimiste("");
        });
        assertEquals(MessagesErreur.ERREUR_STRING_VIDE, ex.getMessage());
    }

    @Test
    void setNomOK() {
        String nomOK = NomOK();
        alchimiste = new Alchimiste(nomOK);
        assertEquals(alchimiste.getNom(), nomOK);
    }

    @Test
    void fairePotionRecetteNull() {
        alchimiste = new Alchimiste(NomOK());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste.fairePotion(null);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRE_NULL, ex.getMessage());
    }

    @Test
    void fairePotionReussite100Pourcent() {
        alchimiste = new Alchimiste("Végéta", 9000);
        boolean reussite = alchimiste.fairePotion(recetteFacile);
        assertTrue(reussite);
    }

    @Test
    void fairePotionEchec100Pourcent() {
        alchimiste = new Alchimiste(NomOK(), 1);
        boolean reussite = alchimiste.fairePotion(recetteDifficile);
        assertFalse(reussite);
    }

    @Test
    void fairePotionResultatIncertain() {
        alchimiste = new Alchimiste(NomOK(), 1);
        int reussite = 0;
        int echec = 0;
        for (int i = 0; i < 100; i++) {
            if (alchimiste.fairePotion(recetteFacile)) {
                reussite++;
            } else {
                echec++;
            }
        }
        assertTrue(reussite > 0 && echec > 0);
    }

    @Test
    void fairePotionMonteDeNiveau() {
    Recette recetteLevelUP = new Recette(ing1, ing2, ing3, "Level UP", 1, Alchimiste.EXPERIENCE_POUR_NIVEAU_SUIVANT+10);
    alchimiste = new Alchimiste(NomOK(), 5);
    int niveauInitial = alchimiste.getNiveau();
    alchimiste.fairePotion(recetteLevelUP);
    assertTrue(alchimiste.getNiveau() > niveauInitial && alchimiste.getExperience()==10);
    }

    @Test
    void fairePotionGainXP() {
        alchimiste = new Alchimiste(NomOK(), 5);
        alchimiste.fairePotion(recetteFacile);
        assertEquals(alchimiste.getExperience(), recetteFacile.getPointExperience());
    }


}
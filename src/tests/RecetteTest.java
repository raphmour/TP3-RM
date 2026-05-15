package tests;

import logique.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetteTest {

    Ingredient ing1;
    Ingredient ing2;
    Ingredient ing3;
    Laboratoire laboratoire;
    Alchimiste alchimiste;

    String nomOK(){
        String nom;
        nom = "";
        for (int i = 1; i <= Recette.LONGUEUR_NOM_MINIMALE; i++) {
            nom = nom + "X";
        }
        return nom;
    }

    @BeforeEach
    void setUp() {
        alchimiste = new Alchimiste(AlchimisteTest.NomOK());
        laboratoire = new Laboratoire(alchimiste);
        ing1 = laboratoire.getIngredients().get(0);
        ing2 = laboratoire.getIngredients().get(1);
        ing3 = laboratoire.getIngredients().get(2);
    }


    @Test
    void constructeurIngredientNull() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Recette(null, ing1, ing2, nomOK(), 1, 10);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRE_NULL, exception.getMessage());
    }

    @Test
    void constructeurNomTropCourt() {
        String nom = "";
        for (int i = 0; i < Recette.LONGUEUR_NOM_MINIMALE - 1; i++) {
            nom = nom + "X";
        }
        String finalNom = nom;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Recette(ing1, ing2, ing3, finalNom, 1, 10);
        });
        assertEquals(MessagesErreur.ERREUR_NOM_RECETTE_TROP_COURT, exception.getMessage());
    }

    @Test
    void constructeurDiffulteTropBasse() {
        int difficulte = Recette.DIFFICULTE_MIN - 1;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Recette(ing1, ing2, ing3, nomOK(), difficulte, 10);
        });
        assertEquals(MessagesErreur.ERREUR_DIFFICULTE_HORS_LIMITES, exception.getMessage());
    }

    @Test
    void constructeurDifficulteTropHaute() {
        int difficulte = Recette.DIFFICULTE_MAX + 1;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Recette(ing1, ing2, ing3, nomOK(), difficulte, 10);
        });
        assertEquals(MessagesErreur.ERREUR_DIFFICULTE_HORS_LIMITES, exception.getMessage());
    }

    @Test
    void constructeurXP0ouNeg() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Recette(ing1, ing2, ing3, nomOK(), 1, 0);
        });
        assertEquals(MessagesErreur.ERREUR_VALEUR_NEGATIVE, exception.getMessage());
    }

    @Test
    void constructeurIngredientsIdentiques(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Recette(ing1, ing1, ing3, nomOK(), 1, 10);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRES_IDENTIQUES, exception.getMessage());
    }

    @Test
    void contientIngredientNull() {
        Recette recette = new Recette(ing1, ing2, ing3, nomOK(), 1, 10);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            recette.contientIngredient(null);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRE_NULL, exception.getMessage());
    }

    @Test
    void contientIngredientVide() {
        Recette recette = new Recette(ing1, ing2, ing3, nomOK(), 1, 10);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            recette.contientIngredient("");
        });
        assertEquals(MessagesErreur.ERREUR_STRING_VIDE, exception.getMessage());
    }
}
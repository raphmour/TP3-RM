package tests;

import com.sun.source.tree.AssertTree;
import logique.Ingredient;
import logique.MessagesErreur;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    String nomOK() {
        String nom = "";
        for (int i = 0; i < Ingredient.LONGUEUR_NOM_MINIMALE; i++) {
            nom = nom + "X";
        }
        return nom;
    }


    @Test
    void constructeurNomNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(null, 10);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRE_NULL, exception.getMessage());
    }

    @Test
    void constructeurNomTropCourt() {
        String nomTropCourt = "";
        for (int i = 0; i < Ingredient.LONGUEUR_NOM_MINIMALE - 1; i++) {
            nomTropCourt = nomTropCourt + "X";
        }
        String finalNomTropCourt = nomTropCourt;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(finalNomTropCourt, 10);
        });
        assertEquals(MessagesErreur.ERREUR_NOM_INGREDIENT_TROP_COURT, exception.getMessage());
    }

    @Test
    void constructeurPrixInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ingredient(nomOK(), 0);
        });
        assertEquals(MessagesErreur.ERREUR_VALEUR_NEGATIVE, exception.getMessage());
    }
}
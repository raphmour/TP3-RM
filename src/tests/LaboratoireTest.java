package tests;

import logique.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaboratoireTest {
    Alchimiste alchimiste;
    Laboratoire laboratoire;

    @BeforeEach
    void setUp() {
        alchimiste = new Alchimiste(AlchimisteTest.NomOK());
        laboratoire = new Laboratoire(alchimiste);
    }

    @Test
    void TestConstructeurInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Laboratoire laboratoire = new Laboratoire(null);
        });
        assertEquals(MessagesErreur.ERREUR_ALCHIMISTE_NULL, exception.getMessage());
    }

    @Test
    void TestConstructeurValide() {
        assertEquals(laboratoire.getProprietaire(), alchimiste);
    }

    @Test
    void trouverIngredientValide() {
        Ingredient ingredientValide = laboratoire.getIngredients().get(0);
        assertEquals(ingredientValide, laboratoire.trouverIngredient(ingredientValide.getNom()));
    }

    @Test
    void trouverIngredientNomNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.trouverIngredient(null);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRE_NULL, exception.getMessage());
    }

    @Test
    void trouverIngredientInexistant() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.trouverIngredient("Ingredient Invalide");
        });
        assertEquals(MessagesErreur.ERREUR_INGREDIENT_INEXISTANT, exception.getMessage());
    }

    @Test
    void trouverRecetteIngredientNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.trouverRecette(null, null, null);
        });
        assertEquals(MessagesErreur.ERREUR_PARAMETRE_NULL, exception.getMessage());
    }

    @Test
    void trouverRecetteValide() {
        Recette recette = laboratoire.getRecettes().get(0);
        Recette trouvee = laboratoire.trouverRecette(
                recette.getIngredient().get(0).getNom(),
                recette.getIngredient().get(1).getNom(),
                recette.getIngredient().get(2).getNom());
        assertEquals(recette.getNom(), trouvee.getNom());
    }

    @Test
    void fairePotionSucces() {
        Recette recette = laboratoire.getRecettes().get(0);
        String ing1 = recette.getIngredient().get(0).getNom();
        String ing2 = recette.getIngredient().get(1).getNom();
        String ing3 = recette.getIngredient().get(2).getNom();

        Alchimiste alchimiste = new Alchimiste(AlchimisteTest.NomOK(), 100);
        Laboratoire laboratoire = new Laboratoire(alchimiste);
        ResultatExperience resultat = laboratoire.fairePotion(ing1, ing2, ing3);
        assertTrue(resultat.getExiste());
        assertTrue(resultat.getSuccess());
    }

    @Test
    void fairePotionInexistant() {
        ResultatExperience resultat = laboratoire.fairePotion("X", "X", "X");
        assertFalse(resultat.getExiste());
        assertFalse(resultat.getSuccess());
    }

    @Test
    void fairePotionEchec() {
        Recette recette = laboratoire.getRecettes().get(0);
        String ing1 = recette.getIngredient().get(0).getNom();
        String ing2 = recette.getIngredient().get(1).getNom();
        String ing3 = recette.getIngredient().get(2).getNom();

        Alchimiste alchimiste = new Alchimiste(AlchimisteTest.NomOK(), 1);
        Laboratoire laboratoire = new Laboratoire(alchimiste);
        ResultatExperience resultat;

        do {
            resultat = laboratoire.fairePotion(ing1, ing2, ing3);
        } while (resultat.getSuccess());
        assertTrue(resultat.getExiste());
        assertFalse(resultat.getSuccess());
    }
    @Test
    void CreerPotionDejaExistante() {
        Recette recette = laboratoire.getRecettes().get(0);
        int nbRecettesInitial = laboratoire.getRecettes().size();
        ResultatExperience resultat = laboratoire.creerNouvellePotion(
                recette.getIngredient().get(0).getNom(),
                recette.getIngredient().get(1).getNom(),
                recette.getIngredient().get(2).getNom(),
                "NomTest",1,100);
        assertTrue(resultat.getExiste());
        assertEquals(nbRecettesInitial, laboratoire.getRecettes().size());
    }
    @Test
    void creerNouvellePotionInedite() {
        int nbRecettesInitial = laboratoire.getRecettes().size();
        String ing1 = laboratoire.getIngredients().get(0).getNom();
        String ing2 = laboratoire.getIngredients().get(1).getNom();
        String ing3 = laboratoire.getIngredients().get(2).getNom();
        ResultatExperience resultat = laboratoire.creerNouvellePotion(ing1,ing2,ing3,"Nouvelle Potion",1,100);
        assertFalse(resultat.getExiste());
        assertTrue(resultat.getSuccess());
        assertEquals(nbRecettesInitial + 1, laboratoire.getRecettes().size());
    }
}
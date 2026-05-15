package logique;

import java.util.ArrayList;

/**
 * Author : Mathieu Bourgoin
 * Ordre de conception : 3e
 */
public class Recette {
    private ArrayList<Ingredient> ingredients;
    private String nom;
    private int difficulte;
    private int pointExperience;
    public static final int LONGUEUR_NOM_MINIMALE = 10;
    public static final int DIFFICULTE_MAX = 5;
    public static final int DIFFICULTE_MIN = 1;
    public Recette(Ingredient ing1, Ingredient ing2, Ingredient ing3, String nom, int difficulte, int pointExperience) {
        if (ing1 == null || ing2 == null || ing3 == null) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_PARAMETRE_NULL);
        }
        if (ing1.equals(ing2) || ing2.equals(ing3) || ing3.equals(ing1)) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_PARAMETRES_IDENTIQUES);
        }
        if (nom.length() < LONGUEUR_NOM_MINIMALE) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_NOM_RECETTE_TROP_COURT);

        }
        if (difficulte < DIFFICULTE_MIN || difficulte > DIFFICULTE_MAX) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_DIFFICULTE_HORS_LIMITES);
        }
        if (pointExperience <= 0) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_VALEUR_NEGATIVE);
        }
        this.ingredients = new ArrayList<Ingredient>();
        this.ingredients.add(ing1);
        this.ingredients.add(ing2);
        this.ingredients.add(ing3);
        this.setDifficulte(difficulte);
        this.setNom(nom);
        this.setPointExperience(pointExperience);
    }


    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public int getDifficulte() {
        return difficulte;
    }

    private void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public int getPointExperience() {
        return pointExperience;
    }

    private void setPointExperience(int pointExperience) {
        this.pointExperience = pointExperience;
    }

    public ArrayList<Ingredient> getIngredient() {
        return this.ingredients;
    }

    public int obtenirPrix() {
        int prixTotal = 0;

        for (Ingredient ing : this.ingredients)
            prixTotal += ing.getPrix();

        return prixTotal;
    }

    public boolean contientIngredient(String nom) {
        boolean estContenu = false;
        if (nom == null) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_PARAMETRE_NULL);
        }
        if (nom.isEmpty()) {
            throw new IllegalArgumentException(MessagesErreur.ERREUR_STRING_VIDE);
        }
        for (Ingredient ing : this.ingredients) {
            if (ing.getNom().equals(nom)) {
                estContenu = true;
                break;
            }
        }

        return estContenu;
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s", this.getNom(), this.ingredients.get(0).getNom(), this.ingredients.get(1).getNom(), this.ingredients.get(2).getNom(), this.getDifficulte(), this.getPointExperience());
    }


}

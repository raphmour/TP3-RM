package logique;/*
420-266-H26 – POO II
TP3 – Fichiers
Nom : Mourlon
Prénom: Raphaël
*/

public class MessagesErreur {
    public static final String ERREUR_PARAMETRE_NULL = "Paramètre null non permis.";
    public static final String ERREUR_NOM_ALCHIMISTE_TROP_COURT = "Ce nom est trop court, longueur minimale = " + Alchimiste.LONGUEUR_NOM_MINIMAL + " caractères.";
    public static final String ERREUR_ALCHIMISTE_NULL = "Aucun alchimiste passé en paramètre.";
    public static final String ERREUR_INGREDIENT_INEXISTANT = "Cet ingrédient n'existe pas.";
    public static final String ERREUR_STRING_VIDE = "Le String ne peut être vide.";
    public static final String ERREUR_RECETTE_INEXISTANTE = "Cette recette n'existe pas.";
    public static final String ERREUR_VALEUR_NEGATIVE = "La valeur reçue est négative (ou 0).";
    public static final String ERREUR_PARAMETRES_IDENTIQUES = "Plusieurs paramètres sont identiques";
    public static final String ERREUR_NOM_RECETTE_TROP_COURT = "Ce nom est trop court, longueur minimale = " + Recette.LONGUEUR_NOM_MINIMALE + " caractères.";
    public static final String ERREUR_DIFFICULTE_HORS_LIMITES = "La difficulté doit être comprise entre "+Recette.DIFFICULTE_MIN+" et "+Recette.DIFFICULTE_MAX;
    public static final String ERREUR_NOM_INGREDIENT_TROP_COURT = "Ce nom est trop court, longueur minimale = " + Ingredient.LONGUEUR_NOM_MINIMALE + " caractères.";


}

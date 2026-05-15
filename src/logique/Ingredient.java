package logique;

public class Ingredient
{
    private String nom;
    private int prix;
    public static final int LONGUEUR_NOM_MINIMALE = 6;

    public Ingredient(String nom, int prix)
    {
        if(nom == null){
            throw new IllegalArgumentException(MessagesErreur.ERREUR_PARAMETRE_NULL);
        }
        if(nom.length() < LONGUEUR_NOM_MINIMALE){
            throw new IllegalArgumentException(MessagesErreur.ERREUR_NOM_INGREDIENT_TROP_COURT);
        }
        if(prix < 0){
            throw new IllegalArgumentException(MessagesErreur.ERREUR_VALEUR_NEGATIVE);
        }
        this.setNom(nom);
        this.setPrix(prix);
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    private void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString()
    {
        return "Ingredient{" + "nom=" + nom + ", prix=" + prix + '}';
    }
}
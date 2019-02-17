package register.model;

/** Classe Article 
 * @author Mehdi, Flavien Coçu
 * @version 1.0 
*/

public class Article {
    private int identifiant;
    private int idVendeur;
    private String nom;
    private String courteDescription;
    private String longueDescription;
    private double prix;
    private String etat;
    private String categorie;
    private String img;
    private String lieu;

    public Article(){}

    public int getIdentifiant() { return this.identifiant; }

    public void setIdentifiant(int identifiant) { this.identifiant = identifiant; }

    public int getIdVendeur() { return this.idVendeur; }

    public void setIdVendeur(int idVendeur){ this.idVendeur = idVendeur;}

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getCourteDescription() { return this.courteDescription; }

    public void setCourteDescription(String courteDescription) { this.courteDescription = courteDescription; }

    public String getLongueDescription() { return this.longueDescription; }

    public void setLongueDescription(String longueDescription) { this.longueDescription = longueDescription; }

    public double getPrix() { return this.prix; }

    public void setPrix(double prix) { this.prix = prix; }

    public String getEtat(){ return this.etat; }

    public void setEtat(String etat) { this.etat=etat; }

    public String getCategorie() { return this.categorie; }

    public void setCategorie(String categorie) { this.categorie=categorie; }

    public String getImg() { return this.img; }

    public void setImg(String img) { this.img=img;}

    public String getLieu() { return this.lieu; }

    public void setLieu(String lieu) { this.lieu=lieu; }

    @Override
    public String toString() { 
        StringBuilder sb = new StringBuilder();
        sb.append(this.getIdentifiant());
        sb.append(" - ");
        sb.append(this.getNom());
        sb.append(" : ");
        sb.append(this.getCourteDescription());
        sb.append(" : ");
        sb.append(this.getPrix());
        return sb.toString();
    }
}

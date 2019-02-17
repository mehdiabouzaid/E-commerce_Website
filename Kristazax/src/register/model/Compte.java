package register.model;

/** Classe Compte
 *  @author Pierre Bernard, Flavien Coçu
 *  @version 1.0
*/

public class Compte{
    private int idCompte;
    private String identifiant;
    private String motDePasse;
    private String email;
    private String role;
    private int nbNotes;
    private double noteMoyenne;

    //Obligatoire
    public Compte() {}

    public int getIdCompte() {
        return this.idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNbNotes() {
        return this.nbNotes;
    }

    public void setNbNotes(int nbNotes) {
        this.nbNotes = nbNotes;
    }

    public double getNoteMoyenne() {
        return this.noteMoyenne;
    }

    public void setNoteMoyenne(double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
        sb.append("id : ");
        sb.append(this.getIdentifiant());
        sb.append(" mdp : ");
        sb.append(this.getMotDePasse());
        sb.append(" email : ");
        sb.append(this.getEmail());
        sb.append(" role : ");
        sb.append(this.getRole());
        sb.append(" nbNotes : ");
        sb.append(this.getNbNotes());
        sb.append(" noteMoyenne : ");
        sb.append(this.getNoteMoyenne());
        return sb.toString();
    }
}

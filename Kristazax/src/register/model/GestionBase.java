package register.model;

import java.sql.*;
import java.util.Scanner;
import java.util.Date;


/**
* @author Flavien Coçu
*/

public class GestionBase {
    private Connection conn;

    private PreparedStatement ajoutArticle;
    private PreparedStatement supprArticle;
    private PreparedStatement listeArticles;
    private PreparedStatement listeArticlesValide;
    private PreparedStatement listeArticlesNonValide;
    private PreparedStatement rechercheArticleAvancee;

    private PreparedStatement ajoutCompte;
    private PreparedStatement supprCompte;
    private PreparedStatement rechercherCompteIdentifiant;
    private PreparedStatement rechercherCompteDebutIdentifiant;
    private PreparedStatement rechercherTousLesCompteDebutIdentifiant;
    private PreparedStatement changerRoleCompteToAdmin;
    private PreparedStatement changerEtatArticleToValide;
    private PreparedStatement changerMdpCompte;
    private PreparedStatement rechercherCompteID;


    private PreparedStatement ajoutNote;
    private PreparedStatement supprNote;

    private PreparedStatement ajoutPanier;
    private PreparedStatement supprPanier;
    private PreparedStatement supprPanierIdArticle;

    private PreparedStatement rechercheidArticlePanieridCompte;
    private PreparedStatement rechercheArticleIdArticle;
    private PreparedStatement recherchePanier;

    private PreparedStatement ajoutMessage;
    private PreparedStatement rechercheMessagesAvecUtilisateurs;
    

    /**
    * @param args the command line arguments
    */

    public GestionBase()throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");

        this.conn = DriverManager.getConnection("jdbc:postgresql://localhost/kristazax", "banane", "jaune");

        this.ajoutArticle = this.conn.prepareStatement("INSERT INTO ARTICLE(idVendeur,nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (?,?,?,?,?,?,?,?,?);");
        this.supprArticle = this.conn.prepareStatement("DELETE FROM ARTICLE WHERE idArticle = ?;");
        this.listeArticles = this.conn.prepareStatement("SELECT * FROM ARTICLE");
        this.listeArticlesValide = this.conn.prepareStatement("SELECT * FROM ARTICLE WHERE etat = 'Valide'");
        this.listeArticlesNonValide = this.conn.prepareStatement("SELECT * FROM ARTICLE WHERE etat = 'NonValide'");

        this.rechercheArticleAvancee = this.conn.prepareStatement("SELECT * FROM article WHERE (lower(nomarticle) LIKE ? OR lower(descriptionCourteArticle) LIKE ?) AND lower(categorie) LIKE ? AND lower(lieu) LIKE ? AND prixarticle <= ? AND etat = 'Valide'");

        this.ajoutCompte = this.conn.prepareStatement("INSERT INTO COMPTE(nomDeCompte,mdpCompte,roleCompte,email) VALUES (?,?,?,?);");
        // Faire trigger pour remplir les notes dès qu'une note est ajouté
        this.supprCompte = this.conn.prepareStatement("DELETE FROM COMPTE WHERE idCompte = ?;");
        this.rechercherCompteIdentifiant = this.conn.prepareStatement("SELECT * FROM COMPTE WHERE nomDeCompte = ? ");
        this.rechercherTousLesCompteDebutIdentifiant = this.conn.prepareStatement("SELECT idCompte,nomDeCompte,roleCompte,email,noteMoyenneCompte,nbNotes FROM COMPTE WHERE nomDeCompte LIKE ?");
        this.rechercherCompteID = this.conn.prepareStatement("SELECT * FROM COMPTE WHERE idCompte = ?");
        this.rechercherTousLesCompteDebutIdentifiant = this.conn.prepareStatement("SELECT * FROM COMPTE WHERE nomDeCompte LIKE ?");
        this.rechercherCompteDebutIdentifiant = this.conn.prepareStatement("SELECT * FROM COMPTE WHERE nomDeCompte LIKE ? AND roleCompte != 'Administrateur'");
        this.changerRoleCompteToAdmin = this.conn.prepareStatement("UPDATE COMPTE SET roleCompte = 'Administrateur' where nomDeCompte= ?");
        this.changerEtatArticleToValide = this.conn.prepareStatement("UPDATE ARTICLE SET etat = 'Valide' where idArticle= ?");
        this.changerMdpCompte = this.conn.prepareStatement("UPDATE COMPTE SET mdpCompte = ? where nomDeCompte= ?");

        this.ajoutNote = this.conn.prepareStatement("INSERT INTO NOTE(idVendeur,idAcheteur,note) VALUES (?,?,?);");
        this.supprNote = this.conn.prepareStatement("DELETE FROM NOTE WHERE idVendeur = ? AND idAcheteur = ? ;");

        this.ajoutPanier = this.conn.prepareStatement("INSERT INTO PANIER(idCompte,idArticle) VALUES (?,?);");
        this.supprPanier = this.conn.prepareStatement("DELETE FROM article WHERE idArticle IN (SELECT idArticle FROM panier WHERE idCompte = ?)");
        this.supprPanierIdArticle = this.conn.prepareStatement("DELETE FROM PANIER WHERE idArticle = ?;");

        this.rechercheidArticlePanieridCompte = this.conn.prepareStatement("select idArticle from panier where idCompte = (select idCompte from compte where nomDeCompte = ?);");
        this.rechercheArticleIdArticle =  this.conn.prepareStatement("SELECT * FROM ARTICLE where idArticle = ?");
        this.rechercheMessagesAvecUtilisateurs =  this.conn.prepareStatement("SELECT idMessage,c1.nomDeCompte AS expediteur,c2.nomDeCompte AS destinataire,contenu,dateEnvoi FROM MESSAGE, COMPTE c1, COMPTE c2 WHERE c1.idCompte = idExpediteur AND c2.idCompte=idDestinataire AND ((idExpediteur = ? AND idDestinataire = ?) OR (idExpediteur = ? AND idDestinataire = ?)) ORDER BY dateEnvoi ASC;");
        this.recherchePanier = this.conn.prepareStatement("SELECT * FROM PANIER WHERE idCompte = ? and idArticle=?");
        this.ajoutMessage = this.conn.prepareStatement("INSERT INTO MESSAGE(idExpediteur,idDestinataire,contenu,dateEnvoi) VALUES (?,?,?,?);");
    }

    public void deconnexion() throws SQLException {
        this.conn.close();
    }

    public ResultSet rechercheidArticlePanierParidCompte(String nomDeCompte) throws SQLException{
        this.rechercheidArticlePanieridCompte.setString(1,nomDeCompte);
        return this.rechercheidArticlePanieridCompte.executeQuery();
    }

    public ResultSet rechercheArticleIdParArticle(int idArticle) throws SQLException{
        this.rechercheArticleIdArticle.setInt(1,idArticle);
        return this.rechercheArticleIdArticle.executeQuery();
    }

    public ResultSet recherchePanier(int idCompte, int idArticle) throws SQLException{
        this.recherchePanier.setInt(1,idCompte);
        this.recherchePanier.setInt(2,idArticle);
        return this.recherchePanier.executeQuery();
    }

    public void ajouterArticle(int idVendeur, String nomArticle, double prixArticle, String descriptionCourteArticle, String descriptionLongueArticle, String categorie, String img, String lieu) throws SQLException {
        this.ajoutArticle.setInt(1, idVendeur);
        this.ajoutArticle.setString(2, nomArticle);
        this.ajoutArticle.setDouble(3, prixArticle);
        this.ajoutArticle.setString(4, descriptionCourteArticle);
        this.ajoutArticle.setString(5, descriptionLongueArticle);
        this.ajoutArticle.setString(6, "NonValide");
        this.ajoutArticle.setString(7, categorie);
        this.ajoutArticle.setString(8, img);
        this.ajoutArticle.setString(9, lieu);

        this.ajoutArticle.executeUpdate();
    }

    public ResultSet listeDArticles() throws SQLException{
        return this.listeArticles.executeQuery();
    }

    public ResultSet listeArticlesValide() throws SQLException{
        return this.listeArticlesValide.executeQuery();
    }

    public ResultSet listeArticlesNonValide() throws SQLException{
        return this.listeArticlesNonValide.executeQuery();
    }

    //
    // RECHERCHE AVANCEE
    //

    public ResultSet getRechercheAvancee(String texte, String categorie, String lieu, Double prix) throws SQLException {
        if (prix == null) {
            ResultSet resultat = this.conn.createStatement().executeQuery("SELECT max(prixarticle) FROM article");
            resultat.next();

            prix = resultat.getDouble(1);
        }

        this.rechercheArticleAvancee.setString(1, "%" + texte.toLowerCase() + "%");
        this.rechercheArticleAvancee.setString(2, "%" + texte.toLowerCase() + "%");
        this.rechercheArticleAvancee.setString(3, "%" + categorie.toLowerCase() + "%");
        this.rechercheArticleAvancee.setString(4, "%" + lieu.toLowerCase() + "%");
        this.rechercheArticleAvancee.setDouble(5, prix);

        return this.rechercheArticleAvancee.executeQuery();
    }

    //
    // -----------------
    //

    public ResultSet afficherArticle() throws SQLException {
        Statement st = this.conn.createStatement();
        return st.executeQuery("SELECT * FROM ARTICLE");
    }

    public void supprimerArticle(int idArticle) throws SQLException {
        this.supprArticle.setInt(1, idArticle);

        this.supprArticle.executeUpdate();
    }

    public void ajouterCompte(String nomDeCompte, String mdpCompte, String roleCompte, String email) throws SQLException {
        this.ajoutCompte.setString(1,nomDeCompte);
        this.ajoutCompte.setString(2, mdpCompte);
        this.ajoutCompte.setString(3, roleCompte);
        this.ajoutCompte.setString(4, email);

        this.ajoutCompte.executeUpdate();
    }

    public void changerRoleCompteAdmin(String nomDeCompte) throws SQLException {
        this.changerRoleCompteToAdmin.setString(1,nomDeCompte);
        this.changerRoleCompteToAdmin.executeUpdate();
    }

    public void changerEtatArticleValide(int idArticle) throws SQLException {
        this.changerEtatArticleToValide.setInt(1,idArticle);
        this.changerEtatArticleToValide.executeUpdate();
    }

    public void changerMdpCompte(String mdpCompte, String nomDeCompte) throws SQLException {
        this.changerMdpCompte.setString(1,mdpCompte);
        this.changerMdpCompte.setString(2,nomDeCompte);
        this.changerMdpCompte.executeUpdate();
    }

    public ResultSet rechercherCompteParIdentifiant(String nomDeCompte) throws SQLException {
        this.rechercherCompteIdentifiant.setString(1,nomDeCompte);
        return this.rechercherCompteIdentifiant.executeQuery();
    } 

    public ResultSet rechercherCompteID(int idCompte) throws SQLException {
        this.rechercherCompteID.setInt(1,idCompte);
        return this.rechercherCompteID.executeQuery();
    } 
    
    public ResultSet rechercherTousLesCompteParDebutIdentifiant(String nomDeCompte) throws SQLException{
        this.rechercherTousLesCompteDebutIdentifiant.setString(1,nomDeCompte+"%");
        return this.rechercherTousLesCompteDebutIdentifiant.executeQuery();
    } 
    
    public ResultSet rechercherCompteParDebutIdentifiant(String nomDeCompte) throws SQLException{
        this.rechercherCompteDebutIdentifiant.setString(1,nomDeCompte+"%");
        return this.rechercherCompteDebutIdentifiant.executeQuery();
    } 

    public ResultSet afficherCompte() throws SQLException {
        Statement st = this.conn.createStatement();
        return st.executeQuery("SELECT * FROM COMPTE");
    }

    public void supprimerCompte(int idCompte) throws SQLException {
        this.supprCompte.setInt(1, idCompte);

        this.supprCompte.executeUpdate();
    }

    public void ajouterNote(int idVendeur, int idAcheteur, int note) throws SQLException {
        this.ajoutNote.setInt(1, idVendeur);
        this.ajoutNote.setInt(2, idAcheteur);
        this.ajoutNote.setInt(3, note);

        this.ajoutNote.executeUpdate();
    }

    public ResultSet afficherNote() throws SQLException {
        Statement st = this.conn.createStatement();
        return st.executeQuery("SELECT * FROM NOTE");
    }

    public void supprimerNote(int idVendeur, int idAcheteur) throws SQLException {
        this.supprNote.setInt(1, idVendeur);
        this.supprNote.setInt(2, idAcheteur);

        this.supprNote.executeUpdate();
    }

    public void ajouterPanier(int idCompte, int idArticle) throws SQLException {
        this.ajoutPanier.setInt(1, idCompte);
        this.ajoutPanier.setInt(2, idArticle);

        this.ajoutPanier.executeUpdate();
    }

    public ResultSet afficherPanier() throws SQLException {
        Statement st = this.conn.createStatement();
        return st.executeQuery("SELECT * FROM PANIER");
    }

    public void supprimerPanier(int idCompte) throws SQLException {
        this.supprPanier.setInt(1, idCompte);

        this.supprPanier.executeUpdate();
    }

    public void supprPanierIdArticle(int idArticle) throws SQLException {
        this.supprPanierIdArticle.setInt(1, idArticle);

        this.supprPanierIdArticle.executeUpdate();
    }

    public void ajouterMessage(int idExpediteur, int idDestinataire, String contenu) throws SQLException {
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        this.ajoutMessage.setInt(1, idExpediteur);
        this.ajoutMessage.setInt(2, idDestinataire);
        this.ajoutMessage.setString(3, contenu);
        this.ajoutMessage.setTimestamp(4,ts);

        this.ajoutMessage.executeUpdate();
    }

    public ResultSet rechercherMessagesAvecUtilisateurs(int idUtilisateur1, int idUtilisateur2) throws SQLException {
        this.rechercheMessagesAvecUtilisateurs.setInt(1,idUtilisateur1);
        this.rechercheMessagesAvecUtilisateurs.setInt(2,idUtilisateur2);
        this.rechercheMessagesAvecUtilisateurs.setInt(3,idUtilisateur2);
        this.rechercheMessagesAvecUtilisateurs.setInt(4,idUtilisateur1);

        return this.rechercheMessagesAvecUtilisateurs.executeQuery();
    }
}

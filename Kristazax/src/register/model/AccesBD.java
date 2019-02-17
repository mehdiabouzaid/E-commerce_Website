package register.model;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
* @author Flavien Coçu
*/

public class AccesBD{

  private GestionBase gestionBase;
  
  public AccesBD()throws ClassNotFoundException, SQLException{
    this.gestionBase = new GestionBase();
  }

  public void ajouterArticle(int idVendeur, String nomArticle, double prixArticle, String descriptionCourteArticle, String descriptionLongueArticle, String categorie, String img, String lieu) throws SQLException{
    this.gestionBase.ajouterArticle(idVendeur,nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle, categorie, img, lieu);
  }

  public void supprimerArticle(int idArticle) throws SQLException{
    this.gestionBase.supprimerArticle(idArticle);
  }

  public void ajouterCompte(String nomDeCompte, String mdpCompte, String roleCompte, String email) throws SQLException{
    this.gestionBase.ajouterCompte(nomDeCompte, mdpCompte, roleCompte, email);
  }

  public void modifierRoleToAdmin(String nomDeCompte) throws SQLException{
    this.gestionBase.changerRoleCompteAdmin(nomDeCompte);
  }

  public void modifierEtatArticleToValide(int idArticle) throws SQLException{
    this.gestionBase.changerEtatArticleValide(idArticle);
  }

  public void modifierMdpCompte(String mdpCompte, String nomDeCompte) throws SQLException{
    this.gestionBase.changerMdpCompte(mdpCompte, nomDeCompte);
  }

  public void ajouterNote(int idVendeur, int idAcheteur, int note) throws SQLException{
    this.gestionBase.ajouterNote(idVendeur,idAcheteur,note);
  }

  public void ajouterPanier(int idCompte, int idArticle) throws SQLException{
    this.gestionBase.ajouterPanier(idCompte,idArticle);
  }

  public String obtenirPanier(int idCompte, int idArticle) throws SQLException{
    ResultSet resultat = this.gestionBase.recherchePanier(idCompte,idArticle);
    if (resultat.next() == false){
      return "non";
    }else{ return "ok";}
  }

  public void supprimerPanierIdArticle(int idArticle) throws SQLException{
    this.gestionBase.supprPanierIdArticle(idArticle);
  }

  public void supprimerPanier(int idCompte) throws SQLException {
    this.gestionBase.supprimerPanier(idCompte);
  }

  public Article obtenirArticleParId(int idArticle) throws SQLException{
    ResultSet resultat = this.gestionBase.rechercheArticleIdParArticle(idArticle);
    resultat.next();
    Article article = creerArticle(resultat);
    return article;
  }

  public void ajouterMessage(int idExpediteur, int idDestinataire, String contenu) throws SQLException{
    this.gestionBase.ajouterMessage(idExpediteur, idDestinataire, contenu);
  }

  public List<Article> obtenirArticles() throws SQLException{
    ResultSet resultat  = this.gestionBase.listeDArticles();
    int cpt=0;
    
    List<Article> articles = new ArrayList<Article>();

    while(resultat.next()){
      Article article = creerArticle(resultat);

      articles.add(article);
      cpt++;
    }

    if (cpt==0){
      return null;
    }

    return articles;
  }

  public List<Article> obtenirArticlesValide() throws SQLException{
    ResultSet resultat  = this.gestionBase.listeArticlesValide();
    int cpt=0;
    
    List<Article> articles = new ArrayList<Article>();

    while(resultat.next()){
      Article article = creerArticle(resultat);

      articles.add(article);
      cpt++;
    }

    if (cpt==0){
      return null;
    }

    return articles;
  }

  public List<Article> obtenirArticlesNonValide() throws SQLException{
    ResultSet resultat  = this.gestionBase.listeArticlesNonValide();
    int cpt=0;
    
    List<Article> articles = new ArrayList<Article>();

    while(resultat.next()){
      Article article = creerArticle(resultat);

      articles.add(article);
      cpt++;
    }

    if (cpt==0){
      return null;
    }

    return articles;
  }

  public ArrayList ObtenirArticleIdParArticle(String nomDeCompte) throws SQLException{
    ArrayList<Integer> idArticles = ObteniridArticlePanierParidCompte(nomDeCompte);
    ArrayList articles = new ArrayList();
    for (Integer idArticle : idArticles){
      ResultSet resultat  = this.gestionBase.rechercheArticleIdParArticle(idArticle.intValue());
      resultat.next();
      articles.add(creerArticle(resultat));
    }
    return articles;
  }

  public ArrayList<Integer> ObteniridArticlePanierParidCompte(String nomDeCompte) throws SQLException{
    ResultSet resultat  = this.gestionBase.rechercheidArticlePanierParidCompte(nomDeCompte);
    
    ArrayList idArticles = new ArrayList();
    while(resultat.next()){
      idArticles.add(resultat.getInt("idArticle"));
    }
    return idArticles;
  }

  public Compte obtenirCompteParNom(String nomDeCompte) throws SQLException{
    ResultSet resultat  = this.gestionBase.rechercherCompteParIdentifiant(nomDeCompte);
    if (!resultat.next()){
      return null;
    }
    Compte compte = creerCompte(resultat);

    return compte;
  }
  
  public Compte obtenirCompteParID(int idCompte) throws SQLException{
    ResultSet resultat  = this.gestionBase.rechercherCompteID(idCompte);
    if (!resultat.next()){
      return null;
    }
    Compte compte = creerCompte(resultat);

    return compte;
  }

  public List<Compte> obtenirTousLesComptesParNom(String nomDeCompte) throws SQLException{
    ResultSet resultat  = this.gestionBase.rechercherTousLesCompteParDebutIdentifiant(nomDeCompte);
    List<Compte> comptes = new ArrayList<Compte>();
    while(resultat.next()){
      comptes.add(creerCompteAnonyme(resultat));
      
    }
    
    return comptes;
  }

  public List<Compte> obtenirPlusieursComptesParNom(String nomDeCompte) throws SQLException{
    ResultSet resultat  = this.gestionBase.rechercherCompteParDebutIdentifiant(nomDeCompte);


    List<Compte> comptes = new ArrayList<Compte>();
    while(resultat.next()){
      comptes.add(creerCompte(resultat));
      
    }
    
    return comptes;
  }

  public List<Message> rechercherMessagesAvecUtilisateurs(String utilisateur1, String utilisateur2) throws SQLException{
    Compte c1 = obtenirCompteParNom(utilisateur1);
    Compte c2 = obtenirCompteParNom(utilisateur2);

    ResultSet resultat  = this.gestionBase.rechercherMessagesAvecUtilisateurs(c1.getIdCompte(), c2.getIdCompte());


    List<Message> messages = new ArrayList<Message>();
    while(resultat.next()){
      messages.add(creerMessage(resultat));
    }
    
    return messages;
  }

  private Compte creerCompte(ResultSet resultat) throws SQLException {
    Compte compte = new Compte();

    compte.setIdCompte(resultat.getInt("idCompte"));
    compte.setIdentifiant(resultat.getString("nomDeCompte"));
    compte.setMotDePasse(resultat.getString("mdpCompte"));
    compte.setEmail(resultat.getString("email"));
    compte.setRole(resultat.getString("roleCompte"));
    compte.setNbNotes(resultat.getInt("nbNotes"));
    compte.setNoteMoyenne(resultat.getDouble("noteMoyenneCompte"));

    return compte;
  }

  private Compte creerCompteAnonyme(ResultSet resultat) throws SQLException {
    Compte compte = new Compte();

    compte.setIdCompte(resultat.getInt("idCompte"));
    compte.setIdentifiant(resultat.getString("nomDeCompte"));
    compte.setMotDePasse("");
    compte.setEmail(resultat.getString("email"));
    compte.setRole(resultat.getString("roleCompte"));
    compte.setNbNotes(resultat.getInt("nbNotes"));
    compte.setNoteMoyenne(resultat.getDouble("noteMoyenneCompte"));

    return compte;
  }

  private Article creerArticle(ResultSet resultat) throws SQLException {
    Article article = new Article();

    article.setIdentifiant(resultat.getInt("idArticle"));
    article.setIdVendeur(resultat.getInt("idVendeur"));
    article.setNom(resultat.getString("nomArticle"));
    article.setPrix(resultat.getDouble("prixArticle"));
    article.setCourteDescription(resultat.getString("descriptionCourteArticle"));
    article.setLongueDescription(resultat.getString("descriptionLongueArticle"));
    article.setEtat(resultat.getString("etat"));
    article.setCategorie(resultat.getString("categorie"));
    article.setImg(resultat.getString("img"));
    article.setLieu(resultat.getString("lieu"));

    return article;
  }

  public List<Article> rechercheArticleAvancee(String texte, String categorie, String lieu, Double prix) throws SQLException {
    ResultSet resultat = this.gestionBase.getRechercheAvancee(texte, categorie, lieu, prix);

    List<Article> articles = new ArrayList<Article>();
    while (resultat.next()) {
      Article article = creerArticle(resultat);

      articles.add(article);
    }

    return articles;
  }

  private Message creerMessage(ResultSet resultat) throws SQLException {
    Message message = new Message();

    message.setIdMessage(resultat.getInt("idMessage"));
    message.setExpediteur(resultat.getString("expediteur"));
    message.setDestinataire(resultat.getString("destinataire"));
    message.setContenu(resultat.getString("contenu"));
    message.setDateEnvoi(resultat.getTimestamp("dateEnvoi"));

    return message;
  }

  public void deconnexion()throws SQLException{
    this.gestionBase.deconnexion();
  }
}
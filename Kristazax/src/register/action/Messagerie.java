/** 
 *  @author Pierre Bernard
 *  @version 1.0
*/
package register.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import register.model.AccesBD; 
import register.model.Compte;
import register.model.Message;
import java.util.*;
import java.sql.SQLException;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import java.text.SimpleDateFormat;


public class Messagerie extends ActionSupport implements SessionAware{

    // ----- Attributs -----
    private AccesBD BD;
    private List<Compte> listeComptes;
    private String debutUtilisateur;
    private String utilisateur1;
    private SessionMap<String,Object> sessionMap;
    private List<Message> listeMessages;
    private String messageAEnvoyer;
    private String utilisateurAEnvoyer;

    // ----- Getters/Setters -----
    public List<Compte> getListeComptes() {
        return listeComptes;
    }

    public void setListeComptes(List<Compte> listeComptes) {
        this.listeComptes = listeComptes;
    }

     public List<Message> getListeMessages() {
        return listeMessages;
    }

    public void setListeMessages(List<Message> listeMessages) {
        this.listeMessages = listeMessages;
    }


    // ----- Méthodes -----
    public String register() throws Exception {
        return ActionSupport.SUCCESS;
    }


    /**
     * 
     * @return success
     */
    public String doAjaxObtenirUtilisateurs() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        System.out.println("nom utilisateur");
        System.out.println(this.debutUtilisateur);
        listeComptes = BD.obtenirTousLesComptesParNom(this.debutUtilisateur);
        
        System.out.println(listeComptes);
        BD.deconnexion();
        return ActionSupport.SUCCESS;

    }

    public String doAjaxObtenirMessages() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        String utilisateur2 = (String)sessionMap.get("utilisateur");
        System.out.println("nom utilisateur1");
        System.out.println(utilisateur1);
        System.out.println("nom utilisateur2");
        System.out.println(utilisateur2);
        listeMessages = BD.rechercherMessagesAvecUtilisateurs(utilisateur2,utilisateur1);

        BD.deconnexion();
        return ActionSupport.SUCCESS;

    }

     public String doAjaxEnvoyerMessage() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        String expediteur = (String)sessionMap.get("utilisateur");
        System.out.println("Utilisateur a envoyer");
        System.out.println(utilisateurAEnvoyer);
        System.out.println("Message a envoyer");
        System.out.println(messageAEnvoyer);
        Compte compte1 = BD.obtenirCompteParNom(expediteur);
        Compte compte2 = BD.obtenirCompteParNom(utilisateurAEnvoyer);
        BD.ajouterMessage(compte1.getIdCompte(),compte2.getIdCompte(),messageAEnvoyer);
        BD.deconnexion();
        return ActionSupport.SUCCESS;

    }

    public String getDebutUtilisateur(){
        return this.debutUtilisateur;
    }

    public void setDebutUtilisateur(String debutUtilisateur){
        this.debutUtilisateur = debutUtilisateur;
    }

    public String getUtilisateur1(){
        return this.utilisateur1;
    }

    public void setUtilisateur1(String utilisateur1){
        this.utilisateur1 = utilisateur1;
    }

    public String getMessageAEnvoyer(){
        return this.messageAEnvoyer;
    }

    public void setMessageAEnvoyer(String messageAEnvoyer){
        this.messageAEnvoyer = messageAEnvoyer;
    }
    
    public String getUtilisateurAEnvoyer(){
        return this.utilisateurAEnvoyer;
    }

    public void setUtilisateurAEnvoyer(String utilisateurAEnvoyer){
        this.utilisateurAEnvoyer = utilisateurAEnvoyer;
    }

    // public String getUtilisateur2(){
    //     return this.utilisateur2;
    // }

    // public void setUtilisateur2(String utilisateur2){
    //     this.utilisateur2 = utilisateur2;
    // }
    @Override
    public void setSession(Map<String, Object> map) {  
        sessionMap=(SessionMap)map;  
    }  

    
}

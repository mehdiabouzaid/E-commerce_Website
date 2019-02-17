/**
* @author Flavien Coçu
*/
package register.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import register.model.*;
import java.io.*;


public class VerifierCompteExiste extends ActionSupport implements SessionAware{
    private Compte compte;
    private String utilisateur;
    private String typeCompte;
    private SessionMap<String,Object> sessionMap;
    private AccesBD accesBD;

    @Override  
    public void setSession(Map<String, Object> map) {  
        sessionMap=(SessionMap)map;  
    }  

    public String execute() throws Exception {
        sessionMap.put("utilisateur",this.utilisateur);
        sessionMap.put("typeCompte", this.typeCompte); 
        // session.setAttribute("utilisateur",this.utilisateur);
        // session.setAttribute("typeCompte",this.typeCompte);
        return SUCCESS;
    }

    public String input() throws Exception { return INPUT; }

    public void validate(){
        if (compte.getIdentifiant().length() == 0 ){
            addFieldError( "compte.identifiant", "Identifiant requis." );
        }
    
        if (compte.getMotDePasse().length() == 0){
            addFieldError( "compte.motDePasse", "Mot de passe requis." );
        }

        try{
            accesBD = new AccesBD();
            if (compte.getIdentifiant().length() != 0 ){
               Compte compteTest = accesBD.obtenirCompteParNom(compte.getIdentifiant());
                if (compteTest == null){
                    addFieldError( "compte.identifiant", "Identifiant inconnu." );
                }else{
                    if (compte.getMotDePasse().length() != 0){
                        if(!compteTest.getMotDePasse().equals(SHA_256.getSHA(compte.getMotDePasse()))){
                            addFieldError( "compte.motDePasse", "Mot de passe invalide." );
                        }else{
                            this.utilisateur = compteTest.getIdentifiant();
                            this.typeCompte = compteTest.getRole();
                        }
                    }
                }
            }
            accesBD.deconnexion();
        }catch(Exception e){}
    }

    public String deconnexion(){
        if(sessionMap!=null){  
            sessionMap.invalidate();  
        }  
        return "success";  
    }

    public Compte getCompte() { return this.compte; }
    public void setCompte(Compte compte) { this.compte = compte; }
}
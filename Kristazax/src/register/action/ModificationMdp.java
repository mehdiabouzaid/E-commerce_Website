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


public class ModificationMdp extends ActionSupport implements SessionAware{
    private String motDePasseOld;
    private String motDePasseNew;
    private SessionMap<String,Object> sessionMap;
    private AccesBD accesBD;
    
    @Override  
    public void setSession(Map<String, Object> map) {  
        sessionMap=(SessionMap)map;  
    } 

    public String getMotDePasseOld() { return this.motDePasseOld; }
    public void setMotDePasseOld(String motDePasseOld) { this.motDePasseOld = SHA_256.getSHA(motDePasseOld); }
    public String getMotDePasseNew() { return this.motDePasseNew; }
    public void setMotDePasseNew(String motDePasseNew) { this.motDePasseNew = SHA_256.getSHA(motDePasseNew); }


    public String execute() throws Exception {
        return SUCCESS;
    }

    public String input() throws Exception { return INPUT; }

    public void validate(){
        if (getMotDePasseOld().length() == 0 ){
            addFieldError( "motDePasseOld", "Mot de passe actuel requis." );
        }
    
        if (getMotDePasseNew().length() == 0){
            addFieldError( "motDePasseNew", "Nouveau mot de passe requis." );
        }

        try{
            accesBD = new AccesBD();
            String nomDeCompte = (String)sessionMap.get("utilisateur");
            if (getMotDePasseOld().length() != 0 ){
               Compte compteTest = accesBD.obtenirCompteParNom(nomDeCompte);
                if (!compteTest.getMotDePasse().equals(getMotDePasseOld())){
                    addFieldError( "motDePasseOld", "Mot de passe actuel incorrect." );
                }else{
                    accesBD.modifierMdpCompte(getMotDePasseNew(),nomDeCompte);                    
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
}
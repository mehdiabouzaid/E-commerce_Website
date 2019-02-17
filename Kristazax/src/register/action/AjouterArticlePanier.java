/** Classe AjouterArticlePanier
 *  @author Flavien Coçu
 *  @version 1.0
*/
package register.action;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import register.model.AccesBD; 
import register.model.Compte;

import javax.servlet.*;
import javax.servlet.http.*;

public class AjouterArticlePanier extends ActionSupport implements SessionAware{

    // ----- Attributs -----
    private AccesBD BD;
    private int idArticle;
    private String message;
    private SessionMap<String,Object> sessionMap;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message){
        this.message=message;
    }

    /**
     * Action "AJAX" renvoyant la liste des articles de l'utilisateur pour le panier
     * 
     * @return success
     */
    public String doAjaxAjoutArticlePanier() throws ClassNotFoundException, SQLException {
        String nomDeCompte = (String)sessionMap.get("utilisateur");
        if(nomDeCompte == null){
            this.message="Veuillez vous connecté pour ajouté un article à votre panier";
        }else{
            this.message="Article déjà dans le panier";
            if (nomDeCompte != null){
                BD = new AccesBD();
                Compte compte = BD.obtenirCompteParNom(nomDeCompte);
                String test = BD.obtenirPanier(compte.getIdCompte(),getIdArticle());
                if(test.equals("non")){
                    BD.ajouterPanier(compte.getIdCompte(), getIdArticle());
                    this.message="Article ajouté";
                }
                BD.deconnexion(); 
            }
        }
        
        return ActionSupport.SUCCESS;
    }

    @Override  
    public void setSession(Map<String, Object> map) {  
        sessionMap=(SessionMap)map;  
    } 

    public int getIdArticle(){
        return this.idArticle;
    }

    public void setIdArticle(int idArticle){
        this.idArticle=idArticle;
    } 
}

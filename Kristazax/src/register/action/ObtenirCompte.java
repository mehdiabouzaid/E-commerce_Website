/** Classe ObtenirCompte
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

public class ObtenirCompte extends ActionSupport implements SessionAware{

    // ----- Attributs -----
    private AccesBD BD;
    private Compte compte;
    private int idCompte;
    private SessionMap<String,Object> sessionMap;


    // ----- Getters/Setters -----
    public Compte getCompte() {
        return this.compte;
    }

    public void setCompte(Compte compte){
        this.compte=compte;
    }

    /**
     * Action "AJAX" renvoyant la liste des articles de l'utilisateur pour le panier
     * 
     * @return success
     */
    public String doAjaxObtenirCompte() throws ClassNotFoundException, SQLException {
        String nomDeCompte = (String)sessionMap.get("utilisateur");
        if (nomDeCompte != null){
            BD = new AccesBD();
            compte = BD.obtenirCompteParNom(nomDeCompte);
            BD.deconnexion();
        }
        return ActionSupport.SUCCESS;
    }

    public String doAjaxObtenirCompteParId() throws ClassNotFoundException, SQLException{
        BD = new AccesBD();
        compte = BD.obtenirCompteParID(getIdCompte());
        BD.deconnexion();
        return ActionSupport.SUCCESS;
    }

    @Override  
    public void setSession(Map<String, Object> map) {  
        sessionMap=(SessionMap)map;  
    }

    public int getIdCompte(){ return this.idCompte;}

    public void setIdCompte(int idCompte){this.idCompte=idCompte;}
}

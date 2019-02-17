/** Classe ObtenirListeArticles
 *  @author Quentin Enjalbert, Flavien Coçu
 *  @version 1.0
*/
package register.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import register.model.AccesBD; 
import register.model.Article;

import javax.servlet.*;
import javax.servlet.http.*;

public class ObtenirListeArticles extends ActionSupport implements SessionAware{

    // ----- Attributs -----
    private AccesBD BD;
    private List<Article> listeArticles;
    private SessionMap<String,Object> sessionMap;


    // ----- Getters/Setters -----
    public List<Article> getListeArticles() {
        return listeArticles;
    }


    // ----- Méthodes -----
    public String register() throws Exception {
        return ActionSupport.SUCCESS;
    }


    /**
     * Action "AJAX" renvoyant la liste des articles
     * 
     * @return success
     */
    public String doAjaxGetListeArticles() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        listeArticles = BD.obtenirArticlesValide();
        BD.deconnexion();
        return ActionSupport.SUCCESS;
    }

    /**
     * Action "AJAX" renvoyant la liste des articles de l'utilisateur pour le panier
     * 
     * @return success
     */
    public String doAjaxGetListeArticlesPourPanier() throws ClassNotFoundException, SQLException {
        String nomDeCompte = (String)sessionMap.get("utilisateur");
        if (nomDeCompte != null){
            BD = new AccesBD();
            listeArticles = BD.ObtenirArticleIdParArticle(nomDeCompte);
            BD.deconnexion();
        }
        return ActionSupport.SUCCESS;
    }

    @Override  
    public void setSession(Map<String, Object> map) {  
        sessionMap=(SessionMap)map;  
    }  
}

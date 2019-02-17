/** Classe SuppressionTuple
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
import register.model.Article;

public class SuppressionTuple extends ActionSupport implements SessionAware {

    // ----- Attributs -----
    private AccesBD BD;
    private int idArticle;
    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = (SessionMap) map;
    }

    /**
     * Action "AJAX" renvoyant la liste des articles
     * 
     * @return success
     */
    public String doAjaxSupprimerArticle() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        BD.supprimerPanierIdArticle(getIdArticle());
        BD.deconnexion();
        return ActionSupport.SUCCESS;
    }

    public int getIdArticle(){
        return this.idArticle;
    }

    public void setIdArticle(int idArticle){
        this.idArticle=idArticle;
    }

    public String doAjaxSupprimerPanier() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();

        int idCompte = BD.obtenirCompteParNom((String) sessionMap.get("utilisateur")).getIdCompte();
        BD.supprimerPanier(idCompte);

        BD.deconnexion();

        return ActionSupport.SUCCESS;
    }
}

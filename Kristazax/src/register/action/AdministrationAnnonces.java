/** 
 *  @author Pierre Bernard
 *  @version 1.0
*/
package register.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import register.model.AccesBD; 
import register.model.Article;
import java.util.*;
import java.sql.SQLException;


public class AdministrationAnnonces extends ActionSupport {

    // ----- Attributs -----
    private AccesBD BD;
    private List<Article> listeAnnonces;
    private int annonceValidee;



    // ----- Getters/Setters -----
    public List<Article> getListeAnnonces() {
        return listeAnnonces;
    }

    public void setListeAnnonces(List<Article> listeAnnonces) {
        this.listeAnnonces = listeAnnonces;
    }


    // ----- Méthodes -----
    public String register() throws Exception {
        return ActionSupport.SUCCESS;
    }

    /**
     * 
     * @return success
     */
    public String doAjaxObtenirAnnonces() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        listeAnnonces = BD.obtenirArticlesNonValide();
        BD.deconnexion();
        return ActionSupport.SUCCESS;
    }

    public String doAjaxValiderAnnonce() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        BD.modifierEtatArticleToValide(this.annonceValidee);
        BD.deconnexion();
        return ActionSupport.SUCCESS;
    }

    public String doAjaxSupprimerArticle() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        BD.supprimerArticle(this.annonceValidee);
        BD.deconnexion();
        return ActionSupport.SUCCESS;
    }

    public void setAnnonceValidee(int annonceValidee){
        this.annonceValidee = annonceValidee;
    }

    public int getAnnonceValidee(){
        return this.annonceValidee;
    }
}

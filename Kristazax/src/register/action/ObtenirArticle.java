/** Classe ObtenirArticle
 *  @author Flavien Coçu
 *  @version 1.0
*/
package register.action;

import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Action;

import register.model.AccesBD; 
import register.model.Article;

import javax.servlet.*;
import javax.servlet.http.*;

public class ObtenirArticle extends ActionSupport{

    // ----- Attributs -----
    private AccesBD BD;
    private Article article;
    private int idArticle;


    // ----- Getters/Setters -----
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article){
        this.article=article;
    }

    /**
     * Action "AJAX" renvoyant la liste des articles de l'utilisateur pour le panier
     * 
     * @return success
     */
    public String doAjaxObtenirArticle() throws ClassNotFoundException, SQLException {
        if (getIdArticle() > 0){
            BD = new AccesBD();
            article = BD.obtenirArticleParId(getIdArticle());
            BD.deconnexion();
        }
        return ActionSupport.SUCCESS;
    }

    public int getIdArticle(){
        return this.idArticle;
    }

    public void setIdArticle(int idArticle){
        this.idArticle=idArticle;
    }

}

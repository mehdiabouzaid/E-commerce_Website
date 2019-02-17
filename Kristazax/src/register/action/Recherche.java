/** 
  * @author Thomas Di Gregorio
  * @version 1.0
  */
package register.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import register.model.AccesBD;
import register.model.Article;
import java.util.List;
import java.sql.SQLException;

public class Recherche extends ActionSupport {
    private Double prix;
    private String categorie;
    private String lieu;
    private String texte;

    private List<Article> articles;

    public String doAjaxRechercheArticleAvancee() throws ClassNotFoundException, SQLException {
      AccesBD BD = new AccesBD();

      this.articles = BD.rechercheArticleAvancee(texte, categorie, lieu, prix);

      BD.deconnexion();


      return SUCCESS;
    }

    public void setPrix(String prix) { try { this.prix = new Double(prix); } catch (NumberFormatException e) {} }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public void setTexte(String texte) { this.texte = texte; }

    public List<Article> getArticles() { return this.articles; }
}

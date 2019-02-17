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
import java.util.Collections;

public class Recommandes extends ActionSupport {
    private List<Article> recommandes;
    private AccesBD BD;

    public String doAjaxRecommandes() throws ClassNotFoundException, SQLException {
      BD = new AccesBD();

      this.recommandes = BD.rechercheArticleAvancee("", "", "", null);
      Collections.shuffle(this.recommandes);
      this.recommandes = this.recommandes.subList(0, Math.min(6, this.recommandes.size()));

      BD.deconnexion();


      return SUCCESS;
    }

    public List<Article> getRecommandes() { return this.recommandes; }
}

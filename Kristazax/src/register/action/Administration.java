/** 
 *  @author Pierre Bernard
 *  @version 1.0
*/
package register.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import register.model.AccesBD; 
import register.model.Compte;
import java.util.*;
import java.sql.SQLException;


public class Administration extends ActionSupport {

    // ----- Attributs -----
    private AccesBD BD;
    private List<Compte> listeComptes;
    private String debutUtilisateur;
    private String utilisateurPromotion;


    // ----- Getters/Setters -----
    public List<Compte> getListeComptes() {
        return listeComptes;
    }

    public void setListeComptes(List<Compte> listeComptes) {
        this.listeComptes = listeComptes;
    }


    // ----- Méthodes -----
    public String register() throws Exception {
        return ActionSupport.SUCCESS;
    }


    /**
     * 
     * @return success
     */
    public String doAjaxGetListeComptes() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        System.out.println("nom utilisateur");
        System.out.println(this.debutUtilisateur);
        listeComptes = BD.obtenirPlusieursComptesParNom(this.debutUtilisateur);
        
        System.out.println(listeComptes);
        BD.deconnexion();
        return ActionSupport.SUCCESS;

    }

    public String doAjaxPasserAdmin() throws ClassNotFoundException, SQLException {
        BD = new AccesBD();
        System.out.println("user à promouvoir");
        System.out.println(this.utilisateurPromotion);
        BD.modifierRoleToAdmin(this.utilisateurPromotion);
        BD.deconnexion();
        return ActionSupport.SUCCESS;

    }

    public String getDebutUtilisateur(){
        return this.debutUtilisateur;
    }

    public void setDebutUtilisateur(String debutUtilisateur){
        this.debutUtilisateur = debutUtilisateur;
    }

    public String getUtilisateurPromotion(){
        return this.utilisateurPromotion;
    }

    public void setUtilisateurPromotion(String utilisateurPromotion){
        this.utilisateurPromotion = utilisateurPromotion;
    }
}

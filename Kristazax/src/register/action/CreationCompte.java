/** Classe Compte
 *  @author Pierre Bernard
 *  @version 1.1
*/
package register.action;

import register.model.Compte;
import com.opensymphony.xwork2.ActionSupport;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import register.model.AccesBD; 

public class CreationCompte extends ActionSupport {
    private Compte compteBean;
    private static final String regexMotDePasse = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,30})";
    private static final String regexIdentifiant = "^[A-Za-z0-9_*#@$]{4,20}$";
    private static final String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static Matcher matcher;
    private AccesBD BD;

    public String register() throws Exception {
        BD = new AccesBD();
        this.BD.ajouterCompte(compteBean.getIdentifiant(), SHA_256.getSHA(compteBean.getMotDePasse()) , "Normal", compteBean.getEmail());
        BD.deconnexion();
        return SUCCESS;
    }

    public String input() throws Exception { return INPUT; }

    public void validate(){
        
        Pattern patternIdentifiant = Pattern.compile(regexIdentifiant);
        Pattern patternMotDePasse = Pattern.compile(regexMotDePasse);
        Pattern patternEmail = Pattern.compile(regexEmail);
        boolean compteExiste = false; 

        try{
            AccesBD accesBD = new AccesBD();
            if (compteBean.getIdentifiant().length() != 0 ){
               Compte compteTest = accesBD.obtenirCompteParNom(compteBean.getIdentifiant());
                if (compteTest != null){
                    compteExiste = true;
                }
            }
            accesBD.deconnexion();
        }catch(Exception e){}

        if ( compteBean.getIdentifiant().length() == 0 || !(patternIdentifiant.matcher(compteBean.getIdentifiant()).matches()) ){
            addFieldError( "compteBean.identifiant", "Identifiant non valide." );
        }
        if (compteExiste) {
            addFieldError( "compteBean.identifiant", "Compte existant." );
        }
        if ( compteBean.getEmail().length() == 0 || !(patternEmail.matcher(compteBean.getEmail()).matches()) ) {
            addFieldError( "compteBean.email", "Adresse mail non valide." );
        }
        if ( compteBean.getMotDePasse().length() <= 6 || !(patternMotDePasse.matcher(compteBean.getMotDePasse()).matches())  ) {
            addFieldError( "compteBean.motDePasse", "Mot de passe non valide. Un mot de passe doit contenir un chiffre, une lettre et une majuscule." );
        }
        
    }

    public Compte getCompteBean() { return this.compteBean; }
    public void setCompteBean(Compte compte) { this.compteBean = compte; }
}

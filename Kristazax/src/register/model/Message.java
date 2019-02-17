package register.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/** Classe Message
 * @author Pierre Bernard
 * @version 1.0 
*/

public class Message {
    private int idMessage;
    private String expediteur;
    private String destinataire;
    private String contenu;
    private Date dateEnvoi;
    

    public Message(){}

    public int getIdMessage() { return this.idMessage; }

    public void setIdMessage(int idMessage) { this.idMessage = idMessage; }

    public String getExpediteur() { return expediteur; }

    public void setExpediteur(String expediteur) { this.expediteur = expediteur; }

    public String getDestinataire() { return this.destinataire; }

    public void setDestinataire(String destinataire) { this.destinataire = destinataire; }

    public String getContenu() { return this.contenu; }

    public void setContenu(String contenu) { this.contenu = contenu; }

    public Date getDateEnvoi() { return this.dateEnvoi; }

    public void setDateEnvoi(Date dateEnvoi) { this.dateEnvoi=dateEnvoi; }

    @Override
    public String toString() { 
        StringBuilder sb = new StringBuilder();
        sb.append(this.getIdMessage());
        sb.append(" - ");
        sb.append(this.getExpediteur());
        sb.append(" : ");
        sb.append(this.getDestinataire());
        sb.append(" : ");
        sb.append(this.getContenu());
        return sb.toString();
    }
}

/** 
 * @author Mehdi
 */

package register.action;

import register.model.Article;
import register.model.Compte;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import java.util.Map;
 
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import register.model.AccesBD;

public class MiseEnVente extends ActionSupport implements SessionAware{
  private Compte compteBean;
  private Article articleBean;
  private File upload;
  private String uploadContentType;
  private String uploadFileName;
  private AccesBD BD;
  private SessionMap<String,Object> sessionMap;

  public String execute() throws Exception{
    String nomDeCompte = (String)sessionMap.get("utilisateur");

    ServletContext context = ServletActionContext.getServletContext();
    String absolutePathToIndexJSP = context.getRealPath("/image");

	  String fullFileName = absolutePathToIndexJSP + "/" + this.uploadFileName;
	  File theFile = new File(fullFileName);
    FileUtils.copyFile(upload, theFile);
    
    articleBean.setImg("image/" + this.uploadFileName);

    BD = new AccesBD();
    Compte compte = BD.obtenirCompteParNom(nomDeCompte);
    this.BD.ajouterArticle(compte.getIdCompte(),articleBean.getNom(), articleBean.getPrix(), articleBean.getCourteDescription(), articleBean.getLongueDescription(), articleBean.getCategorie(), articleBean.getImg(), articleBean.getLieu());
    BD.deconnexion();
    return SUCCESS;
  }

  public String input() throws Exception {
    return INPUT;
  }

  public void validate() {
    if (articleBean.getNom().length() == 0) {
      addFieldError("articleBean.nom", "Nom requis");
    }
    if (articleBean.getCourteDescription().length() == 0) {
      addFieldError("articleBean.courteDescription", "Courte description requise");
    }
    if (articleBean.getLongueDescription().length() == 0) {
      addFieldError("articleBean.longueDescription", "Longue description requise");
    }
    if (articleBean.getPrix() <= 0) {
      addFieldError("articleBean.prix", "Un prix supérieur à 0 est requis");
    }
    if (upload == null || uploadFileName.length() == 0 || !(uploadContentType.startsWith("image"))) {
      addFieldError("articleBean.img", "Une image est requise");
    }
    if (articleBean.getLieu().equals("-1")) {
      addFieldError("articleBean.lieu", "Lieu requis");
    }
    if (articleBean.getCategorie().equals("-1")) {
      addFieldError("articleBean.categorie", "Catégorie requise");
    }
  }

  public Article getArticleBean() {
    return this.articleBean;
  }

  public void setArticleBean(Article article) {
    this.articleBean = article;
  }

  @Override  
  public void setSession(Map<String, Object> map) {  
      sessionMap=(SessionMap)map;  
  }

  public File getUpload() {
    return this.upload;
  }
  
  public void setUpload(File upload) {
    this.upload = upload;
  }

  public String getUploadContentType() {
    return uploadContentType;
  }

  public void setUploadContentType(String uploadContentType) {
    this.uploadContentType = uploadContentType;
  }

  public String getUploadFileName() {
    return uploadFileName;
  }

  public void setUploadFileName(String uploadFileName) {
    this.uploadFileName = uploadFileName;
  }
}

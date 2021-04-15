package biblio.formbean;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InscriptionFormBean {
	private String nom="";
	private String prenom="";
	private String pwd="";
	private String pseudonyme="";
	private String datenaiss="";
	private String sexe="";
	private String cat="";
	private String tel="";
	private Map<String,String> errors = new HashMap<>();
	
	public InscriptionFormBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public String getDatenaiss() {
		return datenaiss;
	}

	public void setDatenaiss(String datenaiss) {
		this.datenaiss = datenaiss;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
public void validate() {
		
		if(this.getNom().isEmpty()) errors.put("nom", "  le champs nom ne doit pas être vide ");
		if(this.getPrenom().isEmpty()) errors.put("prenom", "  le champs nom ne doit pas être vide ");
		if(this.getPwd().isEmpty()) errors.put("pwd", "  le champs nom ne doit pas être vide ");
		if(this.getPseudonyme().isEmpty()) errors.put("pseudo", "  le champs nom ne doit pas être vide ");
		if(this.getDatenaiss().isEmpty()) errors.put("datenaiss", "  le champs nom ne doit pas être vide ");		
		boolean b=true;
        try {
            Float f = Float.parseFloat(this.getTel());           
        } catch (NumberFormatException e) {
        	b=false;
        	if(b==false) errors.put("tel", "  le champs doit comporter uniquement des chiffres !!!");
        }
        
		for(Entry<String, String> o : errors.entrySet()) {
			System.out.println("il y a : "+errors.size()+" erreurs. L'erreur : "+o.toString());
		}	
	}
	
	public boolean isValid() {		
		if (errors==null || errors.size()==0) return true;
		getErrors().clear();
		return false;
	}


	public Map<String, String> getErrors() {
		return errors;
	}


	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	
	

}

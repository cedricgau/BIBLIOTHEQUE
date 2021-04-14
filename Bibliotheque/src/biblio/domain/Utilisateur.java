package biblio.domain;

 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Utilisateur extends Personne
{
	@Id @GeneratedValue ( strategy =GenerationType.SEQUENCE)
	private int idUtilisateur;
	@Column
	private String pwd;
	@Column
	private String pseudonyme;
	public static ArrayList<EmpruntEnCours> emprunt = new ArrayList<>();
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy",Locale.FRANCE);
	@Column
	public EmpruntArchive stock;
	@Column
	private EnumCategorieEmploye categorieEmploye;
	@Column
	private String categorieUtilisateur = " EMPLOYE";



	public Utilisateur(int idUtilisateur,String nom, String prenom, String dateNaissance, String sexe, String pwd, String pseudonyme)
	{
		super(nom,prenom, dateNaissance, sexe);
		setidUtilisateur(idUtilisateur);
		setpwd(pwd);
		setpseudonyme(pseudonyme);

	}
	


	public Utilisateur() {
		
	}

  


	@Override
	public String toString() {
		return super.toString()+", Catégorie d'utilisateur ="+getCategorieUtilisateur()+", idUtilisateur =" + getidUtilisateur() + ", pwd =" + getpwd()
				+ ", pseudonyme =" + getpseudonyme();
	}	


	public static ArrayList<EmpruntEnCours> getEmprunt() {
		return emprunt;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return super.getNom();
	}


	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		super.setNom(nom);
	}


	@Override
	public String getPrenom() {
		// TODO Auto-generated method stub
		return super.getPrenom();
	}


	@Override
	public void setPrenom(String prenom) {
		// TODO Auto-generated method stub
		super.setPrenom(prenom);
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	public void addEmpruntEnCours (EmpruntEnCours ep) throws Exception
	{
		
			emprunt.add(ep);
			 
	}

	
	public ArrayList<EmpruntEnCours> getEmpruntenCours()
	{
		return emprunt;
	}

	public int getNbEmpruntsEnCours()
	{
		int j=0;
		for (int i=0; i<emprunt.size(); i++)
		{
		if(getEmpruntenCours().get(i).getUtilisateur().getidUtilisateur()==(this.getidUtilisateur()))j++;
		}
	 return j;
	}
	
	public static void setEmprunt(ArrayList<EmpruntEnCours> emprunt) {
		Utilisateur.emprunt = emprunt;
	}

	public void removeEmprunt(int id) {
		int j= 0;
		for(EmpruntEnCours o : emprunt) {
            if(o.getExemplaire().getIdExemplaire()==id) {
            	j =emprunt.indexOf(o);
            	o.getExemplaire().setStatus(EnumStatusExemplaire.DISPONIBLE);
            	 stock = new EmpruntArchive(emprunt.get(j), LocalDate.now().format(df));
            }
            
        }
		
        System.out.println("Archive crée : "+stock.getExemplaire().getIdExemplaire()+" a été emprunté le : "+stock.getDateEmprunt().format(df)+ " , et restitué le :"+stock.getDateRestitutionEff().format(df));
       
        emprunt.remove(j);
        
    }
	
	public int getidUtilisateur() {return idUtilisateur;}
	public String getpwd() {return pwd;}
	public String getpseudonyme() {return pseudonyme;}
	
	public void setidUtilisateur(int idUtilisateur) {this.idUtilisateur = idUtilisateur;}
	public void setpwd(String pwd) {this.pwd = pwd;}
	public void setpseudonyme(String pseudonyme) {this.pseudonyme = pseudonyme;}
	
	public EnumCategorieEmploye getCategorieEmploye() {
		return categorieEmploye;
	}



	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}



	public String getCategorieUtilisateur() {
		return categorieUtilisateur;
	}



	public void setCategorieUtilisateur(String categorieUtilisateur) {
		this.categorieUtilisateur = categorieUtilisateur;
	}



	public static void main(String[] args) {
		
	}




}
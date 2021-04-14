package biblio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employe extends Utilisateur
{
	@Column
	private String codeMatricule;
	@Column
	private EnumCategorieEmploye categorieEmploye;
	
	
	public Employe(int idUtilisateur,String nom, String prenom,String dateNaissance,String sexe, String pwd, String pseudonyme, String codeMatricule, EnumCategorieEmploye categorieEmploye)
	{
		super(idUtilisateur,nom, prenom, dateNaissance,sexe,pwd, pseudonyme);
		setCodeMatricule(codeMatricule);
		setCategorieEmploye(categorieEmploye);
	}


	@Override
	public String toString() {
		return super.toString()+", Categorie d'employé = "+categorieEmploye + ", Matricule = " + codeMatricule + ".";
	}


	public String getCodeMatricule() {
		return codeMatricule;
	}


	public void setCodeMatricule(String codeMatricule) {
		this.codeMatricule = codeMatricule;
	}


	public EnumCategorieEmploye getCategorieEmploye() {
		return categorieEmploye;
	}


	public void setCategorieEmploye(EnumCategorieEmploye categorieEmploye) {
		this.categorieEmploye = categorieEmploye;
	}
}
//Source file: C:\\Users\\Cedric\\Desktop\\UML-SQL\\Bibliotheque_V2\\Schemas\\Personne.java

package biblio.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Personne 
{
	@Column
   private String nom;
	@Column
   private String prenom;
   protected static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.FRENCH);
   protected static DateTimeFormatter dfn = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   @Column
   private String dateNaissance;
   @Column
   private String sexe;
   
   /**
    * @roseuid 604209D101D5
    */
   public Personne(String nom, String prenom, String dateNaissance, String sexe) 
   {
    
	   this.nom = nom;
	   this.prenom = prenom;
	   this.dateNaissance=LocalDate.parse(dateNaissance,df).format(dfn);
	   this.sexe = sexe;
   }
   
   public Personne (){
		//this("nom inconnu", "prenom inconnu",  "dateNaissance", "Sexe inconnu");
	}
	
	
	
	public String getDateNaissance() {
	return dateNaissance;
}

public String setDateNaissance(String dateNaissance) {
	return dateNaissance;
}

public String getSexe() {
	return sexe;
}

public void setSexe(String sexe) {
	this.sexe = sexe;
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



	@Override
	public String toString() {
		return "Nom=" + nom + ", Prenom=" + prenom + ", Date de naissance=" + dateNaissance + ", Sexe=" + sexe;
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

}

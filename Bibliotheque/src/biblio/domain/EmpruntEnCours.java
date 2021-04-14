//Source file: C:\\Users\\Cedric\\Desktop\\UML-SQL\\Bibliotheque_V2\\Schemas\\EmpruntEnCours.java

package biblio.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class EmpruntEnCours
{
	@OneToOne
   private Utilisateur utilisateur;
   @Column
   private LocalDate dateEmprunt;
   private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   @OneToMany
   private Exemplaire exemplaire;
   
   
   public EmpruntEnCours(Utilisateur ut,Exemplaire exemp) 
   {
	   setEmprunteur(ut);
	   setExemplaire(exemp);
	   setDateEmprunt(LocalDate.now().format(df));
   }
   
   @Override
public String toString() {
	return "Emprunt En Cours : nom = " + utilisateur.getNom() + ", date Emprunt=" + dateEmprunt + ", exemplaire=" + exemplaire + ".";
}

private void setEmprunteur(Utilisateur ut) {
	   this.utilisateur=ut;
   }
   
   private void setExemplaire(Exemplaire ex) {
	    ex.setStatus(EnumStatusExemplaire.PRETE);
	    this.exemplaire=ex;
	  
   }
   
   public Exemplaire getExemplaire() {
	return exemplaire;
}

private void setDateEmprunt(String d) {
	   
	   this.dateEmprunt=LocalDate.parse(d,df);
   }

   public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
public LocalDate getDateEmprunt() {
	return dateEmprunt;
}
   
public boolean insertEmpruntEnCours(EmpruntEnCours emprunt) {
	return true;
}

   
}

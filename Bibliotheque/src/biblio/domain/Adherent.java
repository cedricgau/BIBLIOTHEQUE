package biblio.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Adherent extends Utilisateur
{
	@Column
	private String telephone;
	@Column
	private String cat;
	@Column
	public static int nbMaxPrets = 3;
	@Column
	public static int dureeMaxPrets =15;	
		
	public Adherent(int idUtilisateur,String nom, String prenom,String pwd,String pseudonyme,String dateNaissance,String sexe,String cat, String telephone)
	{
		super(idUtilisateur,nom, prenom, dateNaissance,sexe, pwd, pseudonyme);
		setCategorieUtilisateur(cat);
		setTelephone(telephone);
		setCat(cat);
		
	}

	
	public Adherent() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return super.toString()+ " numero de telephone = " + telephone +".";
	}


	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getNbMaxPrets() {
		return nbMaxPrets;
	}

	public void setNbMaxPrets(int nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}

	public int getDureeMaxPrets() {
		return dureeMaxPrets;
	}

	public void setDureeMaxPrets(int dureeMaxPrets) {
		Adherent.dureeMaxPrets = dureeMaxPrets;
	}

	public boolean isConditionsPretAcceptees()
	{
		
		if((getNbEmpruntsEnCours()>=nbMaxPrets) )
		{
			System.out.println("Vous avez dépassé les 3 emprunts autorisés !!");
			return false; 
		}
		else if(( this.getNbRetards()>dureeMaxPrets ))
		{
			System.out.println("Vous ne pouvez emprunter de nouveau, car un de vos emprunt est en retard");
			return false; 
		}
		return true;
	}

	public int getNbRetards()
	{	if( getNbEmpruntsEnCours()>0 ) {
		for(int i=0 ;  i<getEmpruntenCours().size() ; i++) {
		long dif = ChronoUnit.DAYS.between(LocalDate.parse(getEmpruntenCours().get(i).getDateEmprunt().toString()), LocalDate.now());		
		if( (int) dif > 15 && getEmpruntenCours().get(i).getUtilisateur().getidUtilisateur()==this.getidUtilisateur()) return (int) dif;
		}
	}
		return 0;
		
	}
	
	@Override
	public void addEmpruntEnCours (EmpruntEnCours ep) throws Exception
	{
		if(isConditionsPretAcceptees()==true)
		{
			emprunt.add(ep);
			System.out.println("emprunt autorisé");
		}
		else System.out.println("REFUSE");
		
	}
	
	public String getCat() {
		return cat;
	}


	public void setCat(String cat) {
		this.cat = cat;
	}


	public void validate() {
		// TODO Auto-generated method stub
		
	}


	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}


	
}
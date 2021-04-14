package biblio.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.EmpruntEnCoursDb;
import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.dao.UtilisateursDao;
import biblio.domain.EmpruntEnCours;


public class EmprunterCtl {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {

				
		System.out.println("\n-------------Test 2.3 :Cr�ation d'un emprunt en cours pour un Employ� ou un Adh�rent avec r�gles m�tier-----------------------");
		
		
		int z=0;
		
		while(z==0) {
		
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous faire l'emprunt d'un exemplaire ? ", "Emprunt d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
			
		UtilisateursDao utilisateur2 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		ExemplairesDao exemplaire2 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		String a = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (ex Employe = 2,3,6 ou Adherent = 1,4,5,7,8) : ","R�alisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		String b = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire emprunt� ( de 1 � 8 ): ","R�alisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd.insertEmpruntEnCours(new EmpruntEnCours(utilisateur2.findByKey(Integer.parseInt(a)),exemplaire2.findByKey(Integer.parseInt(b))));
		EmpruntEnCoursDao eecd3 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		System.out.println("\nListe des emprunts en cours de l'emprunteur "+a+" : \n");
		for(EmpruntEnCoursDb v : eecd3.findByUtilisateur(utilisateur2.findByKey(Integer.parseInt(a)))) {
			System.out.println("Exemplaire id : "+v.getIdUtil()+"\n");
		}
		
		ExemplairesDao eecd6 = new ExemplairesDao(PingJdbc.getConnectionByProperties());

		System.out.println("\nEtat de l'exemplaire emprunt� : " + eecd6.findByKey(Integer.parseInt(b)).toString());
					
		}
		
		
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bient�t !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);
		

	}
	
	public static String creaemprunt(String b,String a) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if (!(b.isBlank() || a.isBlank())) {
		UtilisateursDao utilisateur2 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		ExemplairesDao exemplaire2 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		EmpruntEnCoursDao eecd = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd.insertEmpruntEnCours(new EmpruntEnCours(utilisateur2.findByKey(Integer.parseInt(a)),exemplaire2.findByKey(Integer.parseInt(b))));
		
		UtilisateursDao utilisateur6 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		EmpruntEnCoursDao eecd3 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		String resultat = "\nListe des emprunts en cours de l'emprunteur "+a+" : \n";
		for(EmpruntEnCoursDb v : eecd3.findByUtilisateur(utilisateur6.findByKey(Integer.parseInt(a)))) {
			resultat = resultat +"Exemplaire id : "+v.getIdUtil()+"\n";
		}
		return resultat;
		}
		return "Renseignez les champs ID de l'exemplaire et de l'ID de l'utilisateur s.v.p. !";
	}

}

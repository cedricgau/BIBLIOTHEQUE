package biblio.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import biblio.dao.EmpruntArchiveDao;
import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.domain.EmpruntArchive;
import biblio.domain.Exemplaire;

public class RetourCtl {

	public static void main(String[] args) throws NumberFormatException, SQLException, FileNotFoundException, IOException {
		
		System.out.println("\n-------------Test 2.4 :Retour d'un emprunt en cours pourun Employé ou un Adhérent-----------------------");
		
		int z=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous faire la restitution d'un exemplaire ? ", "Restitution d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
		
		new ExemplairesDao(PingJdbc.getConnectionByProperties());
		String c = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire restitué ( de 1 à 8 ): ","Retour d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
		EmpruntEnCoursDao eecd4 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd4.removeEmpruntEnCours(Integer.parseInt(c));

		ExemplairesDao eecd5 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		System.out.println("\nListe des exemplaires disponibles : \n");
		for(Exemplaire w : eecd5.findAll()) {
			if ( w.getStatus().toString().equalsIgnoreCase("DISPONIBLE")) System.out.println("Exemplaire id : "+w+"\n");
		}
		
		z=JOptionPane.showConfirmDialog(null, "Voulez-vous continuer la restitution d'exemplaire ? ", "Restitution d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		}
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bientôt !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static String retour(String c) throws NumberFormatException, SQLException, FileNotFoundException, IOException {
		if (! c.isBlank()) {
		new ExemplairesDao(PingJdbc.getConnectionByProperties());
		EmpruntEnCoursDao eecd4 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
		eecd4.removeEmpruntEnCours(Integer.parseInt(c));

		ExemplairesDao eecd5 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		String result = "\nListe des exemplaires disponibles : \n\n";
		for(Exemplaire w : eecd5.findAll()) {
			if ( w.getStatus().toString().equalsIgnoreCase("DISPONIBLE")) result = result + "Exemplaire id : "+w+"\n";
		}
		
		EmpruntArchiveDao eecd9 = new EmpruntArchiveDao(PingJdbc.getConnectionByProperties());
		String result2 = "\nListe des exemplaires archivés de cet utilisateur : \n\n";
		for(EmpruntArchive x : eecd9.findAll()) {
			result2 = result2 + "Exemplaires archivés : "+x+"\n";
		}
		result = result+result2;
		return result;
		}
		return "Renseignez le champs ID Exemplaire, s.v.p. !";
	}

}

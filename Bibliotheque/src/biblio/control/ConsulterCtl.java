package biblio.control;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.EmpruntEnCoursDb;
import biblio.dao.ExemplairesDao;
import biblio.dao.PingJdbc;
import biblio.dao.UtilisateursDao;
import biblio.domain.Adherent;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;


public class ConsulterCtl {

	public static void main(String[] args) throws HeadlessException, IOException, NumberFormatException, SQLException, ParseException {
		
		
		System.out.println("\n\n-------------Test  : Persistence à la base de données avec le user Bibliothecaire-----------------------\n\n");
		
		EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("bibliothecaire");
            entityManager = entityManagerFactory.createEntityManager();
            
            System.out.println("\nLISTE DES Adherents :");
    		System.out.println("-----------------------");
    		
    		Adherent a1 = new Adherent(1,"Bichon","Simone","tit","gilou",new SimpleDateFormat("dd/mm/yyyy").parse("04/03/1981").toString(),"F","ADHERENT","0678587896");
    		Adherent a2 =new Adherent(2,"Abicol","Nicole","hyydd","tylo",new SimpleDateFormat("dd/mm/yyyy").parse("15/12/1951").toString(),"F","ADHERENT","06785814496");
    		Adherent a3 =new Adherent(3,"Dupuis","Gary","efzrf","okpokpok",new SimpleDateFormat("dd/mm/yyyy").parse("17/10/2000").toString(),"M","ADHERENT","067865487496");
    		
    		EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist(a1);
            entityManager.persist(a2);
            entityManager.persist(a3);
            trans.commit();
        
            System.out.println( "- Lecture de tous les Adherents -----------" );
            
            List<Adherent> Adherents = entityManager.createQuery( "from Adherent", Adherent.class )
                                   .getResultList();
            for (Adherent Adherent : Adherents) {
                System.out.println( Adherent );
            }
        } finally {
            if ( entityManager != null ) entityManager.close();
            if ( entityManagerFactory != null ) entityManagerFactory.close();
        }
		
		System.out.println("\n\n-------------Test 2.1 : Connection à la base avec le user Bibliothecaire-----------------------\n\n");
		int z=0, y=0;
		Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("src\\biblio\\dao\\jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
		if(PingJdbc.getConnectionByProperties() != null) JOptionPane.showMessageDialog(null, "Vous êtes connecté(e) à la base de données de la bibliothèque avec le user : "+properties.getProperty("user"), "Etat de la connection à la base de données" , JOptionPane.INFORMATION_MESSAGE);
		
		
		System.out.println("\n\n-------------Test 2.2 : Demande des objets aux DAO-----------------------\n\n");
		
		z=0;
		y=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous consulter un exemplaire ? ", "Consultation d'exemplaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
		
		ExemplairesDao exemplaire1 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		
		String h = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire ( de 1 à 8 ): ","Recherche d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("\nDemande d'exemplaire n°"+(++y)+" : "+exemplaire1.findByKey(Integer.parseInt(h)));
		
		}
		
		z=0;
		y=0;
		
		while(z==0) {
			
			z=JOptionPane.showConfirmDialog(null, "Voulez-vous consulter un utilisateur ? ", "Consultation d'utilisateur", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (z==1 ) continue;
			
			UtilisateursDao utilisateur1 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
			String k = JOptionPane.showInputDialog(null, "Entrez l'ID de l'utilisateur que vous souhaitez consulter (ex Employe = 2,3,6 ou Adherent = 1,4,5,7,8,9) : ","Recherche d'un utilisateur", JOptionPane.INFORMATION_MESSAGE); 
			System.out.println("\nDemande d'utilisateur n°"+(++y)+" : "+utilisateur1.findByKey(Integer.parseInt(k)).toString());
		
		
		}
		
		JOptionPane.showMessageDialog(null, "Au Revoir et a bientôt !", "Fin de session", JOptionPane.INFORMATION_MESSAGE);

	}
	
	public static String connectbase() throws IOException {
		Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("src\\biblio\\DAO\\jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
	      return properties.getProperty("user");
		
	}
	
	public static String consultexemplaire(String h) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if(!h.isBlank()) {
		ExemplairesDao exemplaire1 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		if(exemplaire1.findByKey(Integer.parseInt(h))==null ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
				return exemplaire1.findByKey(Integer.parseInt(h)).toString();
		}
		return "Renseignez le champs ID Exemplaire, s.v.p. !";
	}
	
	public static String consultutilisateur(String k) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		if(!k.isBlank()) {
		String o="";
		UtilisateursDao utilisateur1 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		if(utilisateur1.findByKey(Integer.parseInt(k))==null ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		o=utilisateur1.findByKey(Integer.parseInt(k)).toString();
		if(utilisateur1.findByKey(Integer.parseInt(k)).getCategorieUtilisateur().equalsIgnoreCase("ADHERENT")) {
		
			EmpruntEnCoursDao eecd6 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
			for(EmpruntEnCoursDb s : eecd6.findByUtilisateur(utilisateur1.findByKey(Integer.parseInt(k)))){
				
				o=o+"\n\nExemplaire(s) en retard à rendre au plus vite :\n\n ";
				for (Exemplaire g : eecd6.controlRetard(Integer.parseInt(k))){
					o=o+g+"\n";
				}
			}
		}
		return o;
		}
		return "Renseignez le champs ID Utilisateur, s.v.p. !";
		}
	
	public static String nbE() throws IOException {
		String h="";
		UtilisateursDao utilisateur7 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		if(utilisateur7.findAll()==null) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		for(Utilisateur u : utilisateur7.findAll()){
			if ( u.getCategorieUtilisateur().contains("EMPLOYE")) h=h+u.getidUtilisateur()+" ";
		}
		
		return h;
	}
	public static String nbA() throws IOException {
		String i="";
		UtilisateursDao utilisateur6 = new UtilisateursDao(PingJdbc.getConnectionByProperties());
		if(utilisateur6.findAll()==null) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		for(Utilisateur w : utilisateur6.findAll()){
			if ( w.getCategorieUtilisateur().equalsIgnoreCase("ADHERENT")) i=i+w.getidUtilisateur()+" ";
		}
		if(i.equals("") ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		return i;
	}
	public static String nbL() throws IOException, SQLException {
		String j="";
		ExemplairesDao ex6 = new ExemplairesDao(PingJdbc.getConnectionByProperties());
		if(ex6.findAll()==null) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		for(Exemplaire v : ex6.findAll()){
			j=j+v.getIdExemplaire()+" ";
		}
		if(j.equals("") ) return "Aucune réponse, choisissez parmi les réponses affichées ci-jointes";
		return j;
	}
}



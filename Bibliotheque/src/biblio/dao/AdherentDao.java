package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import biblio.domain.Adherent;

public class AdherentDao {
	private String driverName="";
	private String url="";
	private String usr="";
	private String pwd="";
	
	public AdherentDao(String driver,String url, String usr, String pwd) {
		setDriverName(driver);
		setPwd(pwd);
		setUrl(url);
		setUsr(usr);
	}
	
	
	public String insertAdherent(Adherent adh) {
	
	try {
		Class.forName(driverName);
		
	} catch (ClassNotFoundException e) {
		// on loggue l'erreur dans les fichiers de tomcat
		return "ERROR loading driver : " + e.getMessage();
	}
	
	Connection cnx = null;
	Statement statement = null;
	ResultSet rs = null;
	
		try { //ouverture de la connexion
			cnx = DriverManager.getConnection( url, usr, pwd );
		} catch (SQLException e) {
			return	"User:"+ usr + " Pwd:" + pwd + " " + e.getMessage();
		}
		try { //Création d'un énoncé et exécution
			statement = cnx.createStatement();
			cnx.setAutoCommit(true);
			char sexo='H';
			if(adh.getSexe().equalsIgnoreCase("feminin")) sexo='F';
			int nnadh=0;
			
			int nbLignesImpactees = statement.executeUpdate("INSERT INTO UTILISATEUR(IDUTILISATEUR,NOM, PRENOM,PWD,PSEUDONYME,DATENAISSANCE,SEXE,CATEGORIEUTILISATEUR) VALUES (seq_utilisateur.nextval,'"+adh.getNom().toUpperCase()+"','"+adh.getPrenom()+"','"+adh.getpwd()+"','"+adh.getpseudonyme()+"','"+adh.getDateNaissance()+"','"+sexo+"','"+adh.getCat()+"')");
			int nbLignesImpactees2 = statement.executeUpdate("INSERT INTO ADHERENT(IDUTILISATEUR,TELEPHONE) VALUES (seq_utilisateur.currval,'"+adh.getTelephone()+"')");
				
			rs = statement.executeQuery("select utilisateur.IDUTILISATEUR,nom,prenom,pwd,pseudonyme,datenaissance,sexe,categorieutilisateur,telephone from adherent , utilisateur where utilisateur.idutilisateur= adherent.idutilisateur ");
			String result = "<br><br><head></head><body><br><br><H3>Voici la liste des ADHERENTS</H3><br>";
			result+="<p><ul>";
			while (rs.next()) { nnadh=rs.getInt(1); result += "<li> n°adhérent : " + rs.getInt(1) +"    "+ rs.getString(2) +"  "+ rs.getString(3) +"  "+ rs.getString(4) +"  "+ rs.getString(5) +"  "+ rs.getString(6) +"  "+ rs.getString(7) +"  "+ rs.getString(8) +"  "+ rs.getString(9) +"</li>"; }
			result+="</ul></p><p>L'inscription du nouvel adhérent "+ adh.getPrenom().toUpperCase()+" "+adh.getNom().toUpperCase()+ " a bien été pris en compte et il lui a été attribué le nouveau numéro d'adhérent : "+nnadh+"</p></Body>";
			
					
			rs.close();
			cnx.close();
			
			return result;
				
			

		} catch (SQLException e) {
			return e.getMessage();
		}
		
	
	}
		public String getDriverName() {
			return driverName;
		}
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUsr() {
			return usr;
		}
		public void setUsr(String usr) {
			this.usr = usr;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		

}

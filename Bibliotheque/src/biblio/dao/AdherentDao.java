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
	
	
	public String insertAdherent() {
	
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
			
			int nbLignesImpactees = statement.executeUpdate("INSERT INTO UTILISATEUR(IDUTILISATEUR,NOM, PRENOM,PWD,PSEUDONYME,DATENAISSANCE,SEXE,CATEGORIEUTILISATEUR) VALUES (seq_utilisateur"+"')");
			
			String result = "<br><br><head>JDBC & Driver Manager</head><body><br><br><H3>Liste des ADHERENTS</H3><br>";
			result+="<ul>";
			while (rs.next()) result += "<li>" + rs.getString(1) + "</li>";
			result+="</ul></Body>";
			
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

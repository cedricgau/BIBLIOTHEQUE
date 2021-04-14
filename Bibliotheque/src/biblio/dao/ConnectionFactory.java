package biblio.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection (String nomPilote, String URLBD, String authorizationID, String password) throws ClassNotFoundException,SQLException {
	     
	         try {
	        	Class.forName(nomPilote);
	 			
	 		}catch (ClassNotFoundException e) {
	 			System.out.println("Driver non présent dans le CLASSPATH  -  " + e.getMessage());
	 			System.exit(1);
	 		}

	 		//Ouvrir une connexion 

	 		try( Connection cnx = DriverManager.getConnection(URLBD , authorizationID , password)) {
	 			cnx.setAutoCommit(true);
	 			return cnx;	
	 		} catch (SQLException e1) {
	 			System.out.println("Pb pour atteindre la BD  -  " + e1.getMessage());
	 			System.exit(2);
	 		}
	 		return null;
	   }
	
	public Connection getConnectionSansAutoCommit (String nomPilote, String URLBD, String authorizationID, String password) throws ClassNotFoundException,SQLException {
	     
        try {
       	Class.forName(nomPilote);
			
		}catch (ClassNotFoundException e) {
			System.out.println("Driver non présent dans le CLASSPATH  -  " + e.getMessage());
			System.exit(1);
		}

		//Ouvrir une connexion 

		try( Connection cnx = DriverManager.getConnection(URLBD , authorizationID , password)) {
			cnx.setAutoCommit(false);
			return cnx;	
		} catch (SQLException e1) {
			System.out.println("Pb pour atteindre la BD  -  " + e1.getMessage());
			System.exit(2);
		}
		return null;
  } 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

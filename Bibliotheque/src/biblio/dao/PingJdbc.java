package biblio.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.Properties;



public class PingJdbc {
	static ConnectionFactory connectionfactory;
	
		
	public static ConnectionFactory getConnectionfactory() {
		return connectionfactory;}


		public static Connection getConnectionByProperties() throws IOException, FileNotFoundException{
	      Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("src\\biblio\\DAO\\jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
	        
	         try {
	        	Class.forName(properties.getProperty("driver"));
	 			
	 		}catch (ClassNotFoundException e) {
	 			System.out.println("Driver non présent dans le CLASSPATH  -  " + e.getMessage());
	 			System.exit(1);
	 		}

	 		//Ouvrir une connexion 

	 		try {
	 			Connection cnx = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("pwd"));
	 			cnx.setAutoCommit(true);
	 			
	 			return cnx;
	 			
	 		} catch (SQLException e1) {
	 			System.out.println("Problème pour atteindre la BD actuelle 'bibiliothécaire' ( modifiez le jdbc.properties )  -  " + e1.getMessage());
	 			System.exit(2);
	 		}
	 		return null;
	   } 
		
		
	
}



package biblio.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import biblio.domain.EnumStatusExemplaire;
import biblio.domain.Exemplaire;


public class ExemplairesDao {

	Connection cnx1 = null;
	
public ExemplairesDao(Connection cnx1) {
		this.cnx1=cnx1;
	}

public ExemplairesDao() {
	// TODO Auto-generated constructor stub
}

public Exemplaire findByKey(int idExemplaire) throws SQLException
{
	Statement stmt1 = cnx1.createStatement();
	ResultSet rs2 = stmt1.executeQuery(
			"select idexemplaire,status,dateachat,isbn "+
			" FROM exemplaire where idexemplaire = " + idExemplaire);			
	Exemplaire ex = null;
	
	boolean next = rs2.next();
	//while( rs2.next()){} //pas nécessaire
	/*String idexemplaire = rs2.getString(1);
	String status = rs2.getString(3);
	Date dateachat=rs2.getDate(2);*/

	if( next ) {
		int idexemplaire = rs2.getInt("idexemplaire"); // corrigé !!!
		String status = rs2.getString("status");
		String dateachat=rs2.getDate("dateachat").toString(); // corrigé !!!
		String isbn = rs2.getString("isbn");
		EnumStatusExemplaire enumStatus = EnumStatusExemplaire.valueOf(status);
		//Livre livre = null; // Lazy-loading //chargement tardif
		ex = new Exemplaire( idexemplaire, dateachat, enumStatus, isbn); //ici, mapping Objet Relationel
	}
	else {
		ex = null;
	}
	
	
	return ex;
		
}
	
public ArrayList<Exemplaire> findAll() throws SQLException
{
	Statement stmt1 = cnx1.createStatement();
	ArrayList <Exemplaire> listeExemplaire= new ArrayList<Exemplaire>();
	ResultSet rs3 = stmt1.executeQuery("select * FROM exemplaire");			
	while( rs3.next()){
		
		int idexemplaire = rs3.getInt(1); //  corrigé
		String dateachat=rs3.getDate(2).toString(); // corrigé !!!
		String isbn = rs3.getString(4);
		String status = rs3.getString(3);
		EnumStatusExemplaire enstex = EnumStatusExemplaire.valueOf(status);
		
		//System.out.println("ID Exemplaire : " + idexemplaire +" Status : "+ status );
		//Livre livre=new Livre();
		Exemplaire ex = new Exemplaire(idexemplaire,dateachat,enstex,isbn);//mapping Objet Relationel
		listeExemplaire.add(ex);
		
	}
	
	return listeExemplaire;
}
	
}

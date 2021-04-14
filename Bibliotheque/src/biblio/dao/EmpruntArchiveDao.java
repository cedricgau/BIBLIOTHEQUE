package biblio.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


import biblio.domain.EmpruntArchive;


public class EmpruntArchiveDao {

	Connection cnx1 = null;
	
	public EmpruntArchiveDao(Connection cnx1) {
			this.cnx1=cnx1;
		}

	public ArrayList<EmpruntArchive> findByKey(int idUser) throws FileNotFoundException, IOException {
		ArrayList<EmpruntArchive> listarchives = new ArrayList<>();
		PreparedStatement pstm;
		EmpruntArchive arch = null;
		int id = 0,	ide = 0, idu=0;
		String dte,dte2;

		try {
			pstm = cnx1
					.prepareStatement("select * from EMPRUNTENCOURS, EMPRUNTARCHIVE where EMPRUNTENCOURS.idutilisateur=EMPRUNTARCHIVE.idutilisateur (+) and EMPRUNTENCOURS.idutilisateur=?");
			pstm.setInt(1, idUser);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				id = result.getInt(4);
				dte = result.getDate(3).toString();
				dte2 = result.getDate(6).toString();
				ide = result.getInt(7);
				idu = result.getInt(8);
				
				arch = new EmpruntArchive(id,dte, dte2, ide,idu);
				listarchives.add(arch);
				}
			pstm.close();
				return listarchives;
				
			} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<EmpruntArchive> findAll() throws FileNotFoundException, IOException {
		ArrayList<EmpruntArchive> listarchives = new ArrayList<>();
		PreparedStatement pstm;
		EmpruntArchive arch = null;
		int id = 0,	ide = 0, idu=0;
		String dte,dte2;

		try {
			pstm = cnx1
					.prepareStatement("select IDEMPRUNTARCHIVE,DATEEMPRUNT,DATERESTITUTIONEFF,IDEXEMPLAIRE,IDUTILISATEUR from EMPRUNTARCHIVE");

			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				id = result.getInt(1);
				dte = result.getDate(2).toString();
				dte2 = result.getDate(3).toString();
				ide = result.getInt(4);
				idu = result.getInt(5);
				
				arch = new EmpruntArchive(id,dte, dte2, ide,idu);
				listarchives.add(arch);
				}
			pstm.close();
				return listarchives;
				
			} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import biblio.domain.Adherent;
import biblio.domain.Employe;
import biblio.domain.EnumCategorieEmploye;
import biblio.domain.Utilisateur;

public class UtilisateursDao {

	Connection con = null;

	public UtilisateursDao(Connection con) {
		this.con = con;
	}

	public UtilisateursDao() {
		// TODO Auto-generated constructor stub
	}

	public Utilisateur findByKey(int idUser) {
		PreparedStatement pstm,pstm2;
		Utilisateur user = null;
		int id = 0;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		String pseudo = "";
		String dn;
		String sex ="";
		try {
			
			pstm2 = con.prepareStatement("SELECT * FROM ADHERENTGENERAL");
			ResultSet ag = pstm2.executeQuery();
			ag.next();
			Adherent.nbMaxPrets= ag.getInt(1);
			Adherent.dureeMaxPrets = ag.getInt(2);
			pstm = con
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, utilisateur.pseudonyme, utilisateur.datenaissance, utilisateur.sexe, categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
			pstm.setInt(1, idUser);
			ResultSet result = pstm.executeQuery();

			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(8);
				pseudo = result.getString(5);
				dn = result.getDate(6).toString();
				sex = result.getString(7);
				
				if (cat.equals("ADHERENT")) {
					tel = result.getString(9);
					
					user = new Adherent(id,nom, prenom, pwd,pseudo,dn,sex,cat, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(10);
					cat_employe = result.getString(11);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe);
					user = new Employe(id,nom, prenom, dn,sex, pwd, pseudo,code, cat2);
				}
				return user;
			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Utilisateur> findAll() {
		ArrayList<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		int id = 0;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String pseudo = "";
		String dn;
		String sex ="";
		String code = "";
		String cat_employe = "";
		try {
			Statement stm = con.createStatement();
			ResultSet result = stm
					.executeQuery("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, utilisateur.pseudonyme, utilisateur.datenaissance, utilisateur.sexe, categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+)");
			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(8);
				pseudo = result.getString(5);
				dn = result.getDate(6).toString();
				sex = result.getString(7);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(9);
					user = new Adherent(id,nom, prenom,  pwd,pseudo,dn,sex,cat, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(10);
					cat_employe = result.getString(11);					
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe);
					user = new Employe(id,nom, prenom, dn,sex, pwd, pseudo,code, cat2);
				}

				listUtilisateur.add(user);
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUtilisateur;
	}

}

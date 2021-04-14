package biblio.servlet;
/**
 * Mise en application 12
 *         Simple connection à une base de données
 * @author AFPA
 */

import java.sql.*;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biblio.dao.AdherentDao;
import biblio.domain.Adherent;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/gogogo", initParams={@WebInitParam(name="database-driver-class", value="oracle.jdbc.driver.OracleDriver"),@WebInitParam(name="database-url", value="jdbc:oracle:thin:@localhost:1521/xepdb1")} )
public class InscrireAdherent extends HttpServlet {
static String driverName;
static String url;

public void init(ServletConfig config) throws ServletException {
	//	récupération des paramètres de servlet et chargement du driver au start up
	super.init(config);
	
	driverName = config.getInitParameter("database-driver-class");
	url = config.getInitParameter("database-url");

	
};

public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	if (request.getParameter("page").equalsIgnoreCase("login")) {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//récupération des paramètres d'identification
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
	
		//HttpSession session = request.getSession(true);
	
		if(usr.equals("biblio") && pwd.equals("biblio")) {
			
			request.getRequestDispatcher("/formulaireInscription.jsp").forward(request, response);
		}else {
			//usr+=" , votre identifiant ou mot de passe est faux";
			Cookie cook = new Cookie("one",usr);
			response.addCookie(cook);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST , " pas de paramètres usr et pwd");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}else if (request.getParameter("page").equalsIgnoreCase("formulaireInscription")) {
		Adherent ad = new Adherent();
		ad.setNom(request.getParameter("nom"));
		ad.setPrenom(request.getParameter("prenom"));
		ad.setpwd(request.getParameter("pwd"));
		ad.setpseudonyme(request.getParameter("pseudo"));
		ad.setDateNaissance(request.getParameter("datenaiss"));
		ad.setSexe(request.getParameter("sexe"));
		ad.setCat("ADHERENT");
		ad.setTelephone(request.getParameter("tel"));		
				
		ad.validate();
		
		if(ad.isValid()) {
			AdherentDao ado = new AdherentDao();
			request.getRequestDispatcher("/confirmInscription.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/formulaireInscription.jsp").forward(request, response);
		}
	}else {
		Cookie cook = new Cookie("one","inconnu");
		response.addCookie(cook);
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}
	
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
	doGet(request, response);
}
	
}

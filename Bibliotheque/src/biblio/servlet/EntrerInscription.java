package biblio.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EntrerInscription
 */
@WebServlet(urlPatterns="/formulaireInscription")
public class EntrerInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntrerInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Connection cnx = null;
		Statement statement = null;
		ResultSet rs = null;

		if ((request.getParameter("usr") == null) || (request.getParameter("pwd") == null)) {//non renseigné redirection page erreur
			response.sendError(HttpServletResponse.SC_BAD_REQUEST , " pas de paramètres usr et pwd");
		} else {
			try { //ouverture de la connexion
				cnx = DriverManager.getConnection( request.getParameter("pwd"), request.getParameter("usr"), request.getParameter("pwd") );
			} catch (SQLException e) {
				response.sendError(	HttpServletResponse.SC_FORBIDDEN, "User:"
									+ request.getParameter("usr") + " Pwd:" + request.getParameter("pwd") + " " + e.getMessage());
			}
			try { //Création d'un énoncé et exécution
				statement = cnx.createStatement();
				rs = statement.executeQuery("SELECT * FROM "+tb);

				String result = "<br><br><head>JDBC & Driver Manager</head><body><br><br><H3>Liste des "+tb+"S</H3><br>";
				while (rs.next()) 
					result += "<li>" + rs.getString(2) + "</li>";
				
					session.setAttribute("req",result); // stockage du résultat de la requête dans la session
					
				
					
					request.getRequestDispatcher("/Login.jsp").forward(request, response); // rappel de la page Login
					
					

				rs.close();
				cnx.close();

			} catch (SQLException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			}
			out.println("</Body>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

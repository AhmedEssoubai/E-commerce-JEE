package controllers.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.beans.Administrateur;
import modeles.beans.Utilisateur;
import modeles.services.ClientService;
import modeles.services.FournisseurService;
import modeles.services.UtilisateurService;

/**
 * Servlet implementation class Connecter
 */
@WebServlet("/Connecter")
public class ConnecterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnecterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/connecter.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = null;
		UtilisateurService utilisateurService = null;
		boolean valid = false;
		String email = request.getParameter("email");
		String motPasse = request.getParameter("mot_passe");

		if (email != null && motPasse != null)
			try {
				if (ClientService.verifierEmail(email))
					utilisateurService = new ClientService();
				else if (FournisseurService.verifierEmail(email))
					utilisateurService = new FournisseurService();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		
		if (utilisateurService != null)
		{
			utilisateur = (Utilisateur)utilisateurService.verifier(email, motPasse);
			if (utilisateur != null)
				valid = true;
		}
		else
			if (getServletContext().getInitParameter("admin-email").equals(email) && getServletContext().getInitParameter("admin-mot-passe").equals(motPasse))
			{
				utilisateur = new Administrateur(email);
				valid = true;
			}
		if (valid)
		{
			request.getSession().setAttribute("utilisateur", utilisateur);
			response.sendRedirect("Articles");
		}
		else
		{
			request.setAttribute("err", 1);
			doGet(request, response);
		}
	}

}

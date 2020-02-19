package controllers.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.beans.Client;
import modeles.beans.Fournisseur;
import modeles.beans.Utilisateur;
import modeles.services.ClientService;
import modeles.services.FournisseurService;
import modeles.services.UtilisateurService;

/**
 * Servlet implementation class Inscrire
 */
@WebServlet("/Inscrire")
public class InscrireController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscrireController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/inscrire.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur;
		UtilisateurService utilisateurService;
		
		if (request.getParameter("utilisateur").equals("f"))
		{
			utilisateur = new Fournisseur();
			try {
				utilisateurService = new FournisseurService();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
		else
		{
			utilisateur = new Client();
			try {
				utilisateurService = new ClientService();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
		
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setAdresse(request.getParameter("adresse"));
		utilisateur.setCodePostal(Integer.parseInt(request.getParameter("code_postale")));
		utilisateur.setVille(request.getParameter("ville"));
		utilisateur.setTel(request.getParameter("tel"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setMotPasse(request.getParameter("mot_passe"));
		
		if (utilisateurService.ajouter(utilisateur))
			response.sendRedirect(request.getContextPath() + "/Connecter");
		else
		{
			request.setAttribute("err", 1);
			doGet(request, response);
		}
	}

}

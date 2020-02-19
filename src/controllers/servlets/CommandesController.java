package controllers.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modeles.beans.Client;
import modeles.beans.Commande;
import modeles.beans.Utilisateur;
import modeles.services.CommandesService;

/**
 * Servlet implementation class CommandesController
 */
@WebServlet("/Commandes")
public class CommandesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adresse = "mes-commandes.jsp";
		boolean valid = false;
		HttpSession session = request.getSession();
		
		if (session.getAttribute("utilisateur") != null)
		{
			switch (((Utilisateur)session.getAttribute("utilisateur")).getType()) 
			{
				case 'c':
					List<Commande> commandes = new ArrayList<Commande>();
					CommandesService commandesService = null;
					
					try {
						commandesService = new CommandesService();
					} catch (Exception e) {
						e.printStackTrace();
						throw new ServletException();
					}
					commandes = commandesService.getParClient((Client)request.getSession().getAttribute("utilisateur"));
					double prixTotal = 0;
					for(Commande commande : commandes)
						prixTotal += commande.getPrixTotal();
					request.setAttribute("prixTotal", prixTotal);
					request.setAttribute("commandes", commandes);
					valid = true;
					break;
				case 'a':
					adresse = "admin/commandes.jsp";
					valid = true;
					break;
			}
		}
		if (valid)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/" + adresse);
			dispatcher.forward(request, response);
		}
		else
			response.sendRedirect("Articles");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

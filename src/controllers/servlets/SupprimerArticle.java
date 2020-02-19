package controllers.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.beans.Utilisateur;
import modeles.services.ArticlesService;
import modeles.services.PanierService;

/**
 * Servlet implementation class SupprimerArticle
 */
@WebServlet("/SupprimerArticle")
public class SupprimerArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try
		{
			id = Integer.parseInt(request.getParameter("id"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (id > 0)
			try
			{
				ArticlesService articlesService = new ArticlesService();
				Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
				//if (utilisateur.getType() == 'a' || (utilisateur.getType() == 'f' && utilisateur.getId() == articlesService.getProprietaireId(id)))
					articlesService.supprimer(id);
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ServletException();
			};
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

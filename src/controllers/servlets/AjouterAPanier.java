package controllers.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeles.beans.Article;
import modeles.beans.LigneCommande;
import modeles.services.ArticlesService;
import modeles.services.PanierService;

/**
 * Servlet implementation class AjouterAPanier
 */
@WebServlet("/AjouterAPanier")
public class AjouterAPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterAPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PanierService panierService = new PanierService(request.getSession());
		if (request.getParameter("id") != null && request.getParameter("quantite") != null)
		{
			int id = Integer.parseInt(request.getParameter("id")),
					quantite = Integer.parseInt(request.getParameter("quantite"));
			LigneCommande ligneCommande = null;
			try
			{
				ArticlesService articlesService = new ArticlesService();
				Article article = articlesService.getParId(id);
				if (article != null)
					ligneCommande = new LigneCommande(article, quantite);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			if (ligneCommande != null)
				panierService.ajouter(ligneCommande);
		}
		response.sendRedirect("Panier");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

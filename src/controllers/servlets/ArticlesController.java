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

import modeles.beans.Article;
import modeles.beans.Categorie;
import modeles.beans.Fournisseur;
import modeles.beans.Utilisateur;
import modeles.services.ArticlesService;
import modeles.services.CategoriesService;
import modeles.services.FournisseurService;

/**
 * Servlet implementation class Articles
 */
@WebServlet("/Articles")
public class ArticlesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticlesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adresse = "articles.jsp";
		int articleId = 0;
		try
		{
			articleId = Integer.parseInt(request.getParameter("id"));
		}
		catch (Exception e) {
		}
		
		if (request.getParameter("id") != null)
		{
			adresse = "article.jsp";
			try {
				ArticlesService articlesService = new ArticlesService();
				Article article = articlesService.getParId(articleId);
				if (article != null)
					request.setAttribute("article", article);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
		else
		{
			int fournisseurId = 0;
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			if (utilisateur != null && utilisateur.getType() == 'f')
				fournisseurId = utilisateur.getId();
			else
			{
				try
				{
					fournisseurId = Integer.parseInt(request.getParameter("fournisseur"));
				}
				catch (Exception e) {
				}
			}
			
			int categorieId = 0;
			try
			{
				categorieId = Integer.parseInt(request.getParameter("categorie"));
			}
			catch (Exception e) {
			}
			
			List<Categorie> categories = new ArrayList<Categorie>();
			List<Fournisseur> fournisseurs = null;
			List<Article> articles = new ArrayList<Article>();
			try {
				CategoriesService categoriesService = new CategoriesService();
				categories = categoriesService.getTout();
				if (utilisateur.getType() == 'f')
				{
					FournisseurService fournisseurService = new FournisseurService();
					fournisseurs = fournisseurService.getTout();
				}
				categories = categoriesService.getTout();
				ArticlesService articlesService = new ArticlesService();
				if (categorieId > 0 && fournisseurId > 0)
					articles = articlesService.getParCategorieEtFournisseur(categorieId, fournisseurId);
				else if (categorieId > 0)
					articles = articlesService.getParCategorie(categorieId);
				else if (fournisseurId > 0)
					articles = articlesService.getParFournisseur(fournisseurId);
				else
					articles = articlesService.getTout();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
			if (categorieId > 0)
				request.setAttribute("categorie", categorieId);
			if (fournisseurId > 0)
				request.setAttribute("fournisseur", fournisseurId);
			if (fournisseurs != null)
				request.setAttribute("fournisseurs", fournisseurs);
			request.setAttribute("categories", categories);
			request.setAttribute("articles", articles);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/articles/" + adresse);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

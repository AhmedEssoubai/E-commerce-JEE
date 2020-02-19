package controllers.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import modeles.beans.Article;
import modeles.beans.Categorie;
import modeles.beans.Fournisseur;
import modeles.services.ArticlesService;
import modeles.services.CategoriesService;

/**
 * Servlet implementation class GestionArticle
 */
@WebServlet("/Ajouter-Article")
@MultipartConfig
public class AjouterArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			CategoriesService categoriesService = new CategoriesService();
			categories = categoriesService.getTout();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		request.setAttribute("categories", categories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/articles/ajouter-article.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String applicationPath = request.getServletContext().getRealPath("");
	        String uploadFilePath = applicationPath + File.separator + "img" + File.separator + "articles";
			Part part = request.getPart("photo");
			String filePath = uploadFilePath + File.separator + getFileName(part);
            part.write(filePath);
            OutputStream  out = null;
            InputStream filecontent = null;
            try {
            	out = new FileOutputStream(new File(filePath));
            	filecontent = part.getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
            finally {
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }
            }
			Article article = new Article(1, 
					Integer.parseInt(request.getParameter("stock")), 
					request.getParameter("titre"), 
					request.getParameter("description"), 
					"img/articles/ar_0.jpg", 
					Double.parseDouble(request.getParameter("prix")), 
					new Categorie(Integer.parseInt(request.getParameter("categorie")), ""), 
					(Fournisseur) request.getSession().getAttribute("utilisateur")
					);
			ArticlesService articlesService = new ArticlesService();
			articlesService.ajouter(article);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
		
		response.sendRedirect("Articles");
	}
	
	private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

}

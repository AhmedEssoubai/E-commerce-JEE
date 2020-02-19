package modeles.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modeles.beans.Article;
import modeles.beans.Categorie;
import modeles.beans.Fournisseur;
import modeles.dao.DaOService;
import modeles.database.BaseDonneesAssistant;

public class ArticlesService implements DaOService<Article>{
	private BaseDonneesAssistant assistant;
	private PreparedStatement preparedStatement;
	
	public ArticlesService(BaseDonneesAssistant baseDonneesAssistant) {
		assistant = baseDonneesAssistant;
	}
	
	public ArticlesService() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		assistant = BaseDonneesAssistant.getAssistant();
	}

	@Override
	public boolean ajouter(Article article) {
		try
		{
			preparedStatement = assistant.prepareStatement("INSERT INTO articles(titre, prix, stock, categorie, fournisseur, description, photo) VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			preparedStatement.setString(1, article.getTitre());
			preparedStatement.setDouble(2, article.getPrix());
			preparedStatement.setInt(3, article.getStock());
			preparedStatement.setInt(4, article.getCategorie().getId());
			preparedStatement.setInt(5, article.getFournisseur().getId());
			preparedStatement.setString(6, article.getDescription());
			preparedStatement.setString(7, article.getPhoto());
			
			preparedStatement.execute();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(int id, Article objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(int id) {
		try
		{
			preparedStatement = assistant.prepareStatement("DELETE FROM articles WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Article getParId(int id) {
		Article article = null;
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT * FROM articles WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
			{
				article = new Article(result.getInt(1), 
						result.getInt(4), 
						result.getString(2), 
						result.getString(7), 
						result.getString(8), 
						result.getDouble(3), 
						new Categorie(result.getInt(5), null), 
						new Fournisseur(result.getInt(6))
					);
				CategoriesService categoriesService = new CategoriesService(assistant);
				Categorie categorie = categoriesService.getParId(article.getCategorie().getId());
				
				FournisseurService fournisseurService = new FournisseurService(assistant);
				Fournisseur fournisseur = fournisseurService.getParId(article.getFournisseur().getId());
				article.setCategorie(categorie);
				article.setFournisseur(fournisseur);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public List<Article> getTout() {
		List<Article> articles = new ArrayList<Article>();
		try
		{

			ResultSet result = assistant.createStatement().executeQuery("SELECT * FROM articles");
			
			while (result.next())
				articles.add(new Article(result.getInt(1), 
						result.getInt(4), 
						result.getString(2), 
						result.getString(7), 
						result.getString(8), 
						result.getDouble(3), 
						new Categorie(result.getInt(5), null), 
						new Fournisseur(result.getInt(6))
						)
				);
			
			if (articles.size() > 0)
			{
				CategoriesService categoriesService = new CategoriesService(assistant);
				List<Categorie> categories = categoriesService.getTout();
				
				FournisseurService fournisseurService = new FournisseurService(assistant);
				List<Fournisseur> fournisseurs = fournisseurService.getTout();
				
				for(Article article : articles)
				{
					for(Categorie categorie : categories)
						if (article.getCategorie().getId() == categorie.getId())
						{
							article.setCategorie(categorie);
							break;
						}
					for(Fournisseur fournisseur : fournisseurs)
						if (article.getFournisseur().getId() == fournisseur.getId())
						{
							article.setFournisseur(fournisseur);
							break;
						}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public List<Article> getParCategorie(int categorieId) {
		List<Article> articles = new ArrayList<Article>();
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT * FROM articles WHERE categorie = ?");
			
			preparedStatement.setInt(1, categorieId);
			
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next())
				articles.add(new Article(result.getInt(1), 
						result.getInt(4), 
						result.getString(2), 
						result.getString(7), 
						result.getString(8), 
						result.getDouble(3), 
						new Categorie(categorieId, null), 
						new Fournisseur(result.getInt(6))
						)
				);
			
			if (articles.size() > 0)
			{
				CategoriesService categoriesService = new CategoriesService(assistant);
				Categorie categorie = categoriesService.getParId(categorieId);
				
				FournisseurService fournisseurService = new FournisseurService(assistant);
				List<Fournisseur> fournisseurs = fournisseurService.getTout();
				
				for(Article article : articles)
				{
					article.setCategorie(categorie);
					for(Fournisseur fournisseur : fournisseurs)
						if (article.getFournisseur().getId() == fournisseur.getId())
						{
							article.setFournisseur(fournisseur);
							break;
						}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public List<Article> getParFournisseur(int fournisseurId) {
		List<Article> articles = new ArrayList<Article>();
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT * FROM articles WHERE fournisseur = ?");
			
			preparedStatement.setInt(1, fournisseurId);
			
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next())
				articles.add(new Article(result.getInt(1), 
						result.getInt(4), 
						result.getString(2), 
						result.getString(7), 
						result.getString(8), 
						result.getDouble(3), 
						new Categorie(result.getInt(5), null), 
						new Fournisseur(fournisseurId)
						)
				);
			
			if (articles.size() > 0)
			{
				CategoriesService categoriesService = new CategoriesService(assistant);
				List<Categorie> categories = categoriesService.getTout();
				
				FournisseurService fournisseurService = new FournisseurService(assistant);
				Fournisseur fournisseur = fournisseurService.getParId(fournisseurId);
				
				for(Article article : articles)
				{
					for(Categorie categorie : categories)
						if (article.getCategorie().getId() == categorie.getId())
						{
							article.setCategorie(categorie);
							break;
						}
					article.setFournisseur(fournisseur);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public List<Article> getParCategorieEtFournisseur(int categorieId, int fournisseurId) {
		List<Article> articles = new ArrayList<Article>();
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT * FROM articles WHERE categorie = ? AND fournisseur = ?");
			
			preparedStatement.setInt(1, categorieId);
			preparedStatement.setInt(2, fournisseurId);
			
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next())
				articles.add(new Article(result.getInt(1), 
						result.getInt(4), 
						result.getString(2), 
						result.getString(7), 
						result.getString(8), 
						result.getDouble(3), 
						new Categorie(categorieId, null), 
						new Fournisseur(result.getInt(6))
						)
				);
			
			if (articles.size() > 0)
			{
				CategoriesService categoriesService = new CategoriesService(assistant);
				Categorie categorie = categoriesService.getParId(categorieId);
				
				FournisseurService fournisseurService = new FournisseurService(assistant);
				Fournisseur fournisseur = fournisseurService.getParId(fournisseurId);
				
				for(Article article : articles)
				{
					article.setCategorie(categorie);
					article.setFournisseur(fournisseur);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}
	
	public int getProprietaireId(int id)
	{
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT fournisseur FROM articles WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
				return result.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}

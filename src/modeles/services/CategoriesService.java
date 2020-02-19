package modeles.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modeles.beans.Categorie;
import modeles.dao.DaOService;
import modeles.database.BaseDonneesAssistant;

public class CategoriesService implements DaOService<Categorie> {
	private BaseDonneesAssistant assistant;
	private PreparedStatement preparedStatement;

	public CategoriesService(BaseDonneesAssistant baseDonneesAssistant) {
		assistant = baseDonneesAssistant;
	}
	
	public CategoriesService() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		assistant = BaseDonneesAssistant.getAssistant();
	}

	@Override
	public boolean ajouter(Categorie categorie) {
		try
		{
			preparedStatement = assistant.prepareStatement("INSERT INTO categories(label) VALUES(?)");
			
			preparedStatement.setString(1, categorie.getLabel());
			
			preparedStatement.execute();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifier(int id, Categorie objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(int id) {
		try
		{
			preparedStatement = assistant.prepareStatement("DELETE FROM categories WHERE id = ?");
			
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
	public Categorie getParId(int id) {
		Categorie categorie = null;
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT label FROM categories WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
				categorie = new Categorie(id, result.getString(1));
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	@Override
	public List<Categorie> getTout() {
		List<Categorie> categories = new ArrayList<Categorie>();
		try
		{
			
			ResultSet result = assistant.createStatement().executeQuery("SELECT * FROM categories");
			
			while (result.next())
				categories.add(new Categorie(result.getInt(1), result.getString(2)));
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

}

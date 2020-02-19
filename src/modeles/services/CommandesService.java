package modeles.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;

import modeles.beans.Article;
import modeles.beans.Client;
import modeles.beans.Commande;
import modeles.beans.LigneCommande;
import modeles.dao.DaOService;
import modeles.database.BaseDonneesAssistant;

public class CommandesService implements DaOService<Commande>{
	private BaseDonneesAssistant assistant;
	private PreparedStatement preparedStatement;

	public CommandesService(BaseDonneesAssistant baseDonneesAssistant) {
		assistant = baseDonneesAssistant;
	}
	
	public CommandesService() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		assistant = BaseDonneesAssistant.getAssistant();
	}

	@Override
	public boolean ajouter(Commande commande) {
		try
		{
			preparedStatement = assistant.prepareStatement("INSERT INTO commandes(client, dateCommande) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, commande.getClient().getId());
			preparedStatement.setDate(2, new java.sql.Date(new Date().getTime()));
			
			preparedStatement.executeUpdate();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next())
			{
				commande.setId((int)generatedKeys.getLong(1));
				for(LigneCommande ligneCommande : commande.getLignesCommande())
				{
					preparedStatement = assistant.prepareStatement("INSERT INTO lignesCommande(commande, article, quantite) VALUES(?, ?, ?)");
					
					preparedStatement.setInt(1, commande.getId());
					preparedStatement.setInt(2, ligneCommande.getArticle().getId());
					preparedStatement.setInt(3, ligneCommande.getQuantite());
					
					preparedStatement.execute();
				}
				return true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean modifier(int id, Commande objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Commande getParId(int id) {
		Commande commande = null;
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT client, dateCommande FROM commandes WHERE commande = ?");
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
			{
				ClientService clientService = new ClientService(assistant);
				Client client = clientService.getParId(result.getInt(1));
				commande = new Commande(id, 
					client, 
					result.getDate(3)
					);
				
				ArticlesService articlesService = new ArticlesService(assistant);
				List<Article> articles = articlesService.getTout();

				preparedStatement = assistant.prepareStatement("SELECT article, quantite FROM lignesCommande WHERE commande = ?");
				preparedStatement.setInt(1, commande.getId());
				ResultSet lignesCommande = preparedStatement.executeQuery();
				
				while(lignesCommande.next())
				{
					int articleId = lignesCommande.getInt(1);
					for(Article article : articles)
						if (article.getFournisseur().getId() == articleId)
						{
							commande.ajouterLigneCommande(new LigneCommande(article, lignesCommande.getInt(3)));
							break;
						}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return commande;
	}

	public List<Commande> getParClient(Client client) {
		List<Commande> commandes = new ArrayList<Commande>();
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT * FROM commandes WHERE client = ?");
			preparedStatement.setInt(1, client.getId());
			ResultSet result = preparedStatement.executeQuery();
			
			while (result.next())
				commandes.add(new Commande(result.getInt(1), 
						client, 
						result.getDate(3)
						)
				);
			
			if (commandes.size() > 0)
			{
				ArticlesService articlesService = new ArticlesService(assistant);
				List<Article> articles = articlesService.getTout();
				
				for(Commande commande : commandes)
				{
					preparedStatement = assistant.prepareStatement("SELECT article, quantite FROM lignesCommande WHERE commande = ?");
					preparedStatement.setInt(1, commande.getId());
					ResultSet lignesCommande = preparedStatement.executeQuery();
					
					while(lignesCommande.next())
					{
						int articleId = lignesCommande.getInt(1);
						for(Article article : articles)
							if (article.getId() == articleId)
							{
								commande.ajouterLigneCommande(new LigneCommande(article, lignesCommande.getInt(2)));
								break;
							}
					}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return commandes;
	}

	@Override
	public List<Commande> getTout() {
		List<Commande> commandes = new ArrayList<Commande>();
		try
		{
			ResultSet result = assistant.createStatement().executeQuery("SELECT * FROM commandes");
			
			while (result.next())
				commandes.add(new Commande(result.getInt(1), 
						new Client(result.getInt(2)), 
						result.getDate(3)
						)
				);
			
			if (commandes.size() > 0)
			{
				ClientService clientService = new ClientService(assistant);
				List<Client> clients = clientService.getTout();
				ArticlesService articlesService = new ArticlesService(assistant);
				List<Article> articles = articlesService.getTout();
				
				for(Commande commande : commandes)
				{
					for(Client client : clients)
						if (commande.getClient().getId() == client.getId())
						{
							commande.setClient(client);
							break;
						}
					preparedStatement = assistant.prepareStatement("SELECT article, quantite FROM lignesCommande WHERE commande = ?");
					preparedStatement.setInt(1, commande.getId());
					ResultSet lignesCommande = preparedStatement.executeQuery();
					
					while(lignesCommande.next())
					{
						int articleId = lignesCommande.getInt(1);
						for(Article article : articles)
							if (article.getFournisseur().getId() == articleId)
							{
								commande.ajouterLigneCommande(new LigneCommande(article, lignesCommande.getInt(3)));
								break;
							}
					}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return commandes;
	}

}

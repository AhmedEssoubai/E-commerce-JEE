package modeles.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modeles.beans.Client;
import modeles.database.BaseDonneesAssistant;

public class ClientService implements UtilisateurService<Client> {
	private BaseDonneesAssistant assistant;
	private PreparedStatement preparedStatement;
	private static final String emailEnd = ".c@isil.este";

	public ClientService(BaseDonneesAssistant baseDonneesAssistant) {
		assistant = baseDonneesAssistant;
	}
	
	public ClientService() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		assistant = BaseDonneesAssistant.getAssistant();
	}

	@Override
	public boolean ajouter(Client utilisateur) {
		boolean valid = false;
		if (!utilisateur.getEmail().endsWith(emailEnd))
			utilisateur.setEmail(utilisateur.getEmail() + emailEnd);
		if (verifierEmail(utilisateur.getEmail()))
			try
			{
				preparedStatement = assistant.prepareStatement("INSERT INTO clients(nom, prenom, email, motPasse, addresse, codePostal, ville, tele) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
				
				preparedStatement.setString(1, utilisateur.getNom());
				preparedStatement.setString(2, utilisateur.getPrenom());
				preparedStatement.setString(3, utilisateur.getEmail());
				preparedStatement.setString(4, utilisateur.getMotPasse());
				preparedStatement.setString(5, utilisateur.getAdresse());
				preparedStatement.setInt(6, utilisateur.getCodePostal());
				preparedStatement.setString(7, utilisateur.getVille());
				preparedStatement.setString(8, utilisateur.getTel());
				
				preparedStatement.execute();
				valid = true;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		return valid;
	}

	@Override
	public boolean modifier(int id, Client utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client verifier(String email, String motPasse) {
		Client client = null;
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT * FROM clients WHERE email = ? AND motPasse = ?");
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, motPasse);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
				client = new Client(result.getInt(1), 
						result.getInt(7), 
						result.getString(4), 
						result.getString(2), 
						result.getString(3), 
						result.getString(6), 
						result.getString(8), 
						result.getString(5), 
						result.getString(9));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public char getType() {
		return 'c';
	}
	
	public static boolean verifierEmail(String email) {
		return email.matches("[a-zA-Z0-9_.]+" + emailEnd.replaceFirst(".", "[.]"));
	}

	@Override
	public Client getParId(int id) {
		Client client = null;
		try
		{

			preparedStatement = assistant.prepareStatement("SELECT * FROM clients WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
				client = new Client(result.getInt(1), 
						result.getInt(7), 
						result.getString(4), 
						result.getString(2), 
						result.getString(3), 
						result.getString(6), 
						result.getString(8), 
						result.getString(5), 
						result.getString(9));
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public List<Client> getTout() {
		List<Client> clients = new ArrayList<Client>();
		try
		{
			
			ResultSet result = assistant.createStatement().executeQuery("SELECT * FROM clients");
			
			while (result.next())
				clients.add(
					new Client(result.getInt(1), 
						result.getInt(7), 
						result.getString(4), 
						result.getString(2), 
						result.getString(3), 
						result.getString(6), 
						result.getString(8), 
						result.getString(5), 
						result.getString(9)
					)
				);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

	@Override
	public boolean supprimer(int id) {
		try
		{
			preparedStatement = assistant.prepareStatement("DELETE FROM clients WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}

package modeles.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modeles.beans.Fournisseur;
import modeles.database.BaseDonneesAssistant;

public class FournisseurService implements UtilisateurService<Fournisseur> {
	private BaseDonneesAssistant assistant;
	private PreparedStatement preparedStatement;
	private static final String emailEnd = ".f@isil.este"; 

	public FournisseurService(BaseDonneesAssistant baseDonneesAssistant) {
		assistant = baseDonneesAssistant;
	}
	
	public FournisseurService() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		assistant = BaseDonneesAssistant.getAssistant();
	}

	@Override
	public boolean ajouter(Fournisseur utilisateur) {
		if (!utilisateur.getEmail().endsWith(emailEnd))
			utilisateur.setEmail(utilisateur.getEmail() + emailEnd);
		if (verifierEmail(utilisateur.getEmail()))
			try
			{
				preparedStatement = assistant.prepareStatement("INSERT INTO fournisseurs(nom, prenom, email, motPasse, addresse, codePostal, ville, tele) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
				
				preparedStatement.setString(1, utilisateur.getNom());
				preparedStatement.setString(2, utilisateur.getPrenom());
				preparedStatement.setString(3, utilisateur.getEmail());
				preparedStatement.setString(4, utilisateur.getMotPasse());
				preparedStatement.setString(5, utilisateur.getAdresse());
				preparedStatement.setInt(6, utilisateur.getCodePostal());
				preparedStatement.setString(7, utilisateur.getVille());
				preparedStatement.setString(8, utilisateur.getTel());
				
				preparedStatement.execute();
				return true;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}

	@Override
	public boolean modifier(int id, Fournisseur utilisateur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fournisseur verifier(String email, String motPasse) {
		Fournisseur fournisseur = null;
		try
		{
			preparedStatement = assistant.prepareStatement("SELECT * FROM fournisseurs WHERE email = ? AND motPasse = ?");
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, motPasse);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
				fournisseur = new Fournisseur(result.getInt(1), 
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
		return fournisseur;
	}

	@Override
	public char getType() {
		return 'f';
	}
	
	public static boolean verifierEmail(String email) {
		return email.matches("[a-zA-Z0-9_.]+" + emailEnd.replaceFirst(".", "[.]"));
	}

	@Override
	public Fournisseur getParId(int id) {
		Fournisseur fournisseur = null;
		try
		{

			preparedStatement = assistant.prepareStatement("SELECT * FROM fournisseurs WHERE id = ?");
			
			preparedStatement.setInt(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if (result.next())
				fournisseur = new Fournisseur(result.getInt(1), 
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
		return fournisseur;
	}

	@Override
	public List<Fournisseur> getTout() {
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
		try
		{
			
			ResultSet result = assistant.createStatement().executeQuery("SELECT * FROM fournisseurs");
			
			while (result.next())
				fournisseurs.add(
					new Fournisseur(result.getInt(1), 
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
		return fournisseurs;
	}

	@Override
	public boolean supprimer(int id) {
		try
		{
			preparedStatement = assistant.prepareStatement("DELETE FROM fournisseurs WHERE id = ?");
			
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

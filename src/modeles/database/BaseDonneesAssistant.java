package modeles.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDonneesAssistant {
	private Connection connexion;
	
	private static BaseDonneesAssistant assistant;

	private BaseDonneesAssistant() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connexion = DriverManager.getConnection("jdbc:mysql://localhost/e_commerce","root","");
	}
	
	public static BaseDonneesAssistant getAssistant() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
	{
		if (assistant == null)
			assistant = new BaseDonneesAssistant();
		return assistant;
	}
	
	public PreparedStatement prepareStatement(String sql) throws SQLException
	{
		return connexion.prepareStatement(sql);
	}
	
	public PreparedStatement prepareStatement(String sql, int statement) throws SQLException
	{
		return connexion.prepareStatement(sql, statement);
	}
	
	public Statement createStatement() throws SQLException
	{
		return connexion.createStatement();
	}
	
	
}

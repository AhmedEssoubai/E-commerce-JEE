package modeles.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Commande {
	private int id;
	private Client client;
	protected List<LigneCommande> lignesCommande;
	private Date date;

	public Commande(int id, Client client, Date date) {
		this.id = id;
		this.client = client;
		this.date = date;
		this.lignesCommande = new ArrayList<LigneCommande>();
	}
	public Commande(int id, Client client) {
		this.id = id;
		this.client = client;
		this.lignesCommande = new ArrayList<LigneCommande>();
	}
	public Commande(int id, Client client, List<LigneCommande> lignesCommande) {
		this.id = id;
		this.client = client;
		this.lignesCommande = lignesCommande;
	}
	
	public void ajouterLigneCommande(LigneCommande ligneCommande)
	{
		lignesCommande.add(ligneCommande);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<LigneCommande> getLignesCommande() {
		return lignesCommande;
	}
	public void setLignesCommande(List<LigneCommande> lignesCommande) {
		this.lignesCommande = lignesCommande;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getPrixTotal() {
		double prix = 0;
		for(LigneCommande ligneCommande : lignesCommande)
			prix += ligneCommande.getPrix();
		return prix;
	}
}

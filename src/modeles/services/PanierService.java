package modeles.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import modeles.beans.Client;
import modeles.beans.LigneCommande;
import modeles.beans.Panier;
import modeles.dao.DaOService;

public class PanierService implements DaOService<LigneCommande>{
	HttpSession session;
	Panier panier;
	
	public PanierService(HttpSession session)
	{
		this.session = session;
	}

	@Override
	public boolean ajouter(LigneCommande ligneCommande) {
		load();
		boolean ajouter = true;
		for(LigneCommande lc : panier.getLignesCommande())
			if (lc.getArticle().getId() == ligneCommande.getArticle().getId())
			{
				lc.setQuantite(lc.getQuantite() + ligneCommande.getQuantite());
				ajouter = false;
				break;
			}
		if (ajouter)
			panier.ajouterLigneCommande(ligneCommande);
		save();
		return true;
	}

	@Override
	public boolean modifier(int id, LigneCommande objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(int id) {
		load();
		for(LigneCommande ligneCommande : panier.getLignesCommande())
			if (ligneCommande.getArticle().getId() == id)
			{
				panier.supprimerLigneCommande(ligneCommande);
				save();
				break;
			}
		return false;
	}

	public void vider() {
		load();
		panier.vider();
		save();
	}

	@Override
	public LigneCommande getParId(int id) {
		load();
		for(LigneCommande ligneCommande : panier.getLignesCommande())
			if (ligneCommande.getArticle().getId() == id)
				return ligneCommande;
		return null;
	}

	@Override
	public List<LigneCommande> getTout() {
		load();
		return panier.getLignesCommande();
	}
	
	private void load()
	{
		panier = (Panier) session.getAttribute("panier");
		Client client = (Client)session.getAttribute("utilisateur");
		System.out.println(session.getAttribute("utilisateur") == null);
		System.out.println(client == null);
		int id = client.getId();
		System.out.println(panier == null);
		if (panier == null || panier.getClient().getId() != client.getId())
			panier = new Panier(client);
	}
	
	private void save()
	{
		session.setAttribute("panier", panier);
	}
	
	public Panier getPanier()
	{
		load();
		return panier;
	}
	

}

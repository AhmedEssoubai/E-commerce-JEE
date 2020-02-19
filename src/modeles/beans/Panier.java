package modeles.beans;

import java.util.ArrayList;

public class Panier extends Commande{

	public Panier(Client client) {
		super(1, client, new ArrayList<LigneCommande>());
	}

	public LigneCommande getLigneCommande(int index) {
		return lignesCommande.get(index);
	}
	
	public void supprimerLigneCommande(LigneCommande ligneCommande)
	{
		lignesCommande.remove(ligneCommande);
	}
	
	public void vider()
	{
		lignesCommande = new ArrayList<LigneCommande>();
	}

}

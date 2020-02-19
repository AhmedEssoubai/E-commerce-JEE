package modeles.beans;

public class Fournisseur extends Utilisateur{

	public Fournisseur() {
		super();
	}

	public Fournisseur(int id) {
		super(id, 0, null, null, null, null, null, null, null);
	}

	public Fournisseur(int id, int codePostal, String email, String nom, String prenom, String adresse, String ville,
			String tel, String motPasse) {
		super(id, codePostal, email, nom, prenom, adresse, ville, tel, motPasse);
	}

	@Override
	public char getType() {
		return 'f';
	}

}

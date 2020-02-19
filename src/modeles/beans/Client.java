package modeles.beans;

public class Client extends Utilisateur{

	public Client() {
		super();
	}

	public Client(int id) {
		super(id, 0, null, null, null, null, null, null, null);
	}

	public Client(int id, int codePostal, String email, String nom, String prenom, String adresse, String ville,
			String tel, String motPasse) {
		super(id, codePostal, email, nom, prenom, adresse, ville, tel, motPasse);
	}

	@Override
	public char getType() {
		return 'c';
	}

	
}

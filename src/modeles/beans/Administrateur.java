package modeles.beans;

public class Administrateur extends Utilisateur {

	public Administrateur(String email) {
		super(0, 0, null, email, null, null, null, null, null);
	}

	@Override
	public char getType() {
		return 'a';
	}

}

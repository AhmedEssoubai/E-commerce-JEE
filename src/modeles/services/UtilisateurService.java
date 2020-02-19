package modeles.services;

import modeles.dao.DaOService;

public interface UtilisateurService<T> extends DaOService<T>{
	
	T verifier(String email, String motPasse);
	
	char getType();
	
}

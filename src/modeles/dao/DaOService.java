package modeles.dao;

import java.util.List;

public interface DaOService<T> {
	
	boolean ajouter(T objet);
	
	boolean modifier(int id, T objet);
	
	boolean supprimer(int id);
	
	T getParId(int id);
	
	List<T> getTout();

}

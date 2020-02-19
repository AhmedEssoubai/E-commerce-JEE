package modeles.beans;

public class Categorie {
	private int id;
	private String label;

	public Categorie(int id, String label) {
		super();
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}

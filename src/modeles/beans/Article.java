package modeles.beans;

public class Article {
	private int id, stock;
	private String titre, description, photo;
	private double prix;
	private Categorie categorie;
	private Fournisseur fournisseur;
	
	public Article(int id, int stock, String titre, double prix, Categorie categorie,
			Fournisseur fournisseur) {
		super();
		this.id = id;
		this.stock = stock;
		this.titre = titre;
		this.prix = prix;
		this.categorie = categorie;
		this.fournisseur = fournisseur;
	}
	
	public Article(int id, int stock, String titre, String description, String photo, double prix, Categorie categorie,
			Fournisseur fournisseur) {
		super();
		this.id = id;
		this.stock = stock;
		this.titre = titre;
		this.description = description;
		this.photo = photo;
		this.prix = prix;
		this.categorie = categorie;
		this.fournisseur = fournisseur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	
	
}

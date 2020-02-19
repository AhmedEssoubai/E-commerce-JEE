package modeles.beans;

public class LigneCommande {
	private Article article;
	private int quantite;

	public LigneCommande() {
		super();
	}
	public LigneCommande(Article article, int quantite) {
		super();
		this.article = article;
		this.quantite = quantite;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getPrix() {
		return article.getPrix() * quantite;
	}

	
}

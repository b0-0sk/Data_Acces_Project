package model;

public class Article_Components {
	
	public int id_article;
	public int id_components;
	
	
	public Article_Components(int id_article, int id_components) {
		
		this.id_article = id_article;
		this.id_components = id_components;
	}
	
	
	public int getId_article() {
		return id_article;
	}


	public void setId_article(int id_article) {
		this.id_article = id_article;
	}


	public int getId_components() {
		return id_components;
	}


	public void setId_components(int id_components) {
		this.id_components = id_components;
	}


	public void addArt(int id_article) {}
	public void delArt(int id_article) {}
	public void addComp(int id_components) {}
	public void delComp(int id_components) {}
	
}


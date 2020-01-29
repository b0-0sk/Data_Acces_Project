package model;

public class Article {
	public int id_article;
	public String name;
	
	public Article(int id_article, String name) {
		
		this.id_article = id_article;
		this.name = name;
	}

	public int getId_article() {
		return id_article;
	}

	public void setId_article(int id_article) {
		this.id_article = id_article;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}


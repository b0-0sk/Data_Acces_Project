package model;

public class Movements_WareHouse {
	
	public int id_article;
	public int id_moviments;
	public int id_components;
	public int id_wh;
	public char movementType;
	public int quantity;
	public int rack;
	public int depth;
	public int level;
	
	
	public Movements_WareHouse(int id_article, int id_moviments, int id_components, int id_wh, char movementType, int quantity, int rack, int depth, int level) {
		
		this.id_article = id_article;
		this.id_moviments = id_moviments;
		this.id_components = id_components;
		this.id_wh = id_wh;
		this.movementType = movementType;
		this.quantity = quantity;
		this.rack = rack;
		this.depth = depth;
		this.level = level;
	}
	
	
	public int getId_article() {
		return id_article;
	}


	public void setId_article(int id_article) {
		this.id_article = id_article;
	}


	public int getId_moviments() {
		return id_moviments;
	}


	public void setId_moviments(int id_moviments) {
		this.id_moviments = id_moviments;
	}


	public int getId_components() {
		return id_components;
	}


	public void setId_components(int id_components) {
		this.id_components = id_components;
	}


	public int getId_wh() {
		return id_wh;
	}


	public void setId_wh(int id_wh) {
		this.id_wh = id_wh;
	}


	public char getMovementType() {
		return movementType;
	}


	public void setMovementType(char movementType) {
		this.movementType = movementType;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getRack() {
		return rack;
	}


	public void setRack(int rack) {
		this.rack = rack;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int stockTotal() {
		return 0;
	}
	public int[][][] checkLocationComponents(int id_components){
		return null;
	}
	public int[][][] checkLocationArticle(int id_article){
		return null;
	}
	
	
}


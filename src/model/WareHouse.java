package model;;

public class WareHouse {

	public int id_wh;
	public String name;
	
	
	public WareHouse(int id_wh, String name) {
		
		this.id_wh = id_wh;
		this.name = name;
	}

	public int getId_wh() {
		return id_wh;
	}

	public void setId_wh(int id_wh) {
		this.id_wh = id_wh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addWH(String name){}
}


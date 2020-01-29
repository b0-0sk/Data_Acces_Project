package model;;

public class Components {
	
	public int id_components;
	public int id_provider;
	public String name;
	public String description;
	
	
	public Components(int id_components, int id_provider, String name, String description) {
		
		this.id_components = id_components;
		this.id_provider = id_provider;
		this.name = name;
		this.description = description;
	}
	


	public int getId_components() {
		return id_components;
	}



	public void setId_components(int id_components) {
		this.id_components = id_components;
	}



	public int getId_provider() {
		return id_provider;
	}



	public void setId_provider(int id_provider) {
		this.id_provider = id_provider;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public void addComponent(String name,String description) {}
}


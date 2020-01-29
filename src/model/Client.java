package model;

public class Client {
	
	public String id_client;
	public String cif;
	public String name;
	public String surname;
	public String email;
	public String province;
	public String iban;
	public String cp;
	public String telf;
	public String goodpayer;

	public Client(String cif, String name, String surname, String telf, String email, String cp, String province,String iban,String goodpayer ) {
		this.cif = cif;
		this.name = name;
		this.surname = surname;
		this.cif = cif;
		this.telf = telf;
		this.email = email;
		this.cp = cp;
		this.province = province;
		this.iban = iban;
		this.goodpayer = goodpayer;

	}
	/*
	 * 
	 * DELETE SQL
	 * 
	 */
	
	public Client(String cif) {
		this.cif = cif;
	}
	
	///// SQL CLIENTS
	
	
	
	public String getGoodpayer() {
		return goodpayer;
	}

	public void setGoodpayer(String goodpayer) {
		this.goodpayer = goodpayer;
	}

	public String getId_Client() {
			return id_client;
	}

	public void setId_Client(String id_client) {
		this.id_client = id_client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	
	public String[] goodPayers(boolean goodPayer) {
		return null;
	}

	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", name=" + name + ", surname=" + surname + ", telf=" + telf
				+ ", email=" + email + ", cp=" + cp + ", province=" + province + ", cif=" + cif + ", iban=" + iban
				+ ", goodpayer=" + goodpayer + "]";
	}
	
}
















package data;
import java.sql.*;


import java.sql.Connection;
import model.Client;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;


public class SQLClients {

	Connection c = null;

	Statement sentencia = null;

	String nombreTabla;

	String name, surname;

	
	ArrayList<Client> clients = new ArrayList<Client>();



	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");

			
			//c= DriverManager.getConnection("jdbc:sqlite:/home/abosch/git/Data_access/ProjectBenet/Servidor/muntatge.db");
			c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\b0_0sk\\Java\\ProjectMuntatgeBenet\\Servidor\\muntatge.db");
			
			//c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\b0_0sk\\git\\Data_Acces_Project\\Servidor\\muntatge.db");

			System.out.println("Exito en la primera conexion con la base de datos");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos");

		}
		return c;

	}



	public void insertaClients(Client client) throws SQLException {

	
		
		
		String sqlInsert = "INSERT INTO clients" 
				+ " VALUES ("+"\""+client.getCif()+ "\""+","
				+"\""+client.getName()+ "\""+","
				+"\""+client.getSurname()+ "\""+","
				+"\""+client.getTelf()+ "\""+","
				+"\""+client.getEmail()+ "\""+","
				+"\""+client.getCp()+ "\""+","
				+"\""+client.getProvince()+ "\""+","
				+"\""+client.getIban()+ "\""+","
				+"\""+client.getGoodpayer()+"\")";
		
		
		

		try {

			conectar();

			sentencia = c.createStatement();
			
			sentencia.executeUpdate(sqlInsert); 
			

			sentencia.close();

			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}
	
	public void updateClient(Client client) throws SQLException{
		
		
		
		String sqlUpdate ="UPDATE clients "
				+ "SET "
				+ "name ='" + client.getName()
				+ "', surname ='" + client.getSurname()
				+ "', telf ='" + client.getTelf()
				+ "', email ='" + client.getEmail()
				+ "', cp ='" + client.getCp()
				+ "', province='" + client.getProvince()
				+ "', iban ='" + client.getIban()
				+ "', goodPayer ='" + client.getGoodpayer()
				+ "' WHERE cif ='" + client.getCif() + "';";
		
		
		try{
			
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlUpdate); 



			sentencia.close();

			c.close();

			System.out.println("Datos actualizados");
			  
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());

		}
	}

	
	public void deleteClients(Client client) throws SQLException {
					
		String sqlDelete = "DELETE FROM clients " + " WHERE cif = "+"\""+client.getCif()+"\""+";";
		
		try {
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete); 
			
			sentencia.close();

			c.close();

			System.out.println("Datos eliminados");
	
				
	
		} catch (Exception e) {
	
			System.out.println(e.getMessage());
	
		}
	
	}




	public ArrayList<Client> queryClients(String nombreTabla) throws SQLException {

		conectar();

		sentencia = c.createStatement();

		String consultaSql = "SELECT * FROM " + nombreTabla + ";";

		try {
			ResultSet rs = sentencia.executeQuery(consultaSql);
			while (rs.next()) {

				
				this.clients.add(new Client(
						rs.getString("cif"),
						rs.getString("name"),
						rs.getString("surname"),
						rs.getString("telf"),
						rs.getString("email"),
						rs.getString("cp"),
						rs.getString("province"),
						rs.getString("iban"),
						rs.getString("goodPayer")));
				

			}	
			

			rs.close();

			sentencia.close();

			c.close();
			


		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		
		return clients;




	}

}

package data;
import java.sql.*;



import java.sql.Connection;
import model.CommandLine;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class SQLCommandLine {

	Connection c = null;
	Statement sentencia = null;	
	ArrayList<CommandLine> commandLine = new ArrayList<CommandLine>();
	

	public Connection conectar() {

		try {

			Class.forName("org.sqlite.JDBC");

			
			//c= DriverManager.getConnection("jdbc:sqlite:/home/abosch/git/Data_access/ProjectBenet/Servidor/muntatge.db");
			
			//c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\b0_0sk\\Java\\ProjectMuntatgeBenet\\Servidor\\muntatge.db");
			
			c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\b0_0sk\\git\\Data_Acces_Project\\Servidor\\muntatge.db");

			System.out.println("Exito en la primera conexion con la base de datos");

		} catch (Exception e) {

			System.out.println("Error al conectar con base de datos");

		}
		return c;

	}



	public void insertaCommandLine(CommandLine cl) throws SQLException {


		//TODO cambiar variable command y commandLine
		
		String sqlInsert = "INSERT INTO commandLine" 
				+ " VALUES ("
				+"\""+cl.getId_command_line()+ "\""+","
				+"\""+cl.getId_command()+ "\""+","
				+"\""+cl.getUnitsToDo()+ "\""+","
				+"\""+cl.getUnitsMade()+ "\""+","
				+"\""+cl.getStatus()+ "\""+","
				+"\""+cl.getLineCommandPrice()+ "\")";
		
		
		

		try {

			conectar();

			sentencia = c.createStatement();
			
			sentencia.executeUpdate(sqlInsert); 
			

			sentencia.close();

			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			JOptionPane.showConfirmDialog(null, "ERROR BD COMMANDLINE: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void updateCommandLine(CommandLine commandLine) throws SQLException{
		
		
		
		String sqlUpdate ="UPDATE commandLine "
				+ "SET "
				+ "   id_command = '" + commandLine.getId_command()
				+ "', id_command_line = '" + commandLine.getId_command_line()
				+ "', unitToDo = '" + commandLine.getUnitsToDo()
				+ "', unitMade = unitMade +'" + commandLine.getUnitsMade()
				+ "', lineCommandPrice = '" + commandLine.getLineCommandPrice()
				+ "', status = '" + commandLine.getStatus()
				+ "' WHERE id_command_line ='" + commandLine.getId_command_line() + "';";
		
		
		
		
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
			JOptionPane.showConfirmDialog(null, "ERROR BD COMMANDLINE: UPDATE "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
	}

	
	public void deleteCommandLine(CommandLine commandLine) throws SQLException {
					
		String sqlDelete = "DELETE FROM commandLine " + " WHERE id_command_line = "+"\""+commandLine.getId_command_line()+"\""+";";
		
		try {
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete); 
			
			sentencia.close();

			c.close();

			System.out.println("Datos eliminados");
	
				
	
		} catch (Exception e) {
	
			JOptionPane.showConfirmDialog(null, "ERROR BD COMMANDLINE: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	public ArrayList<CommandLine> queryCommandLine(String nombreTabla, String id_command) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		String consultaSql = "SELECT * FROM " + nombreTabla + " WHERE id_command ='" + id_command +"';";
		
		try {
			
			ResultSet rs = sentencia.executeQuery(consultaSql);
			
			while (rs.next()) {
				
					this.commandLine.add(new CommandLine(
							rs.getString("id_command"),
							rs.getString("id_command_line"),
							rs.getInt("unitToDo"),
							rs.getInt("unitMade"),
							rs.getDouble("lineCommandPrice"),
							rs.getString("status")
					));
			}	
			

			rs.close();

			sentencia.close();

			c.close();
			


		} catch (Exception e) {

			JOptionPane.showConfirmDialog(null, "ERROR BD COMMANDLINE: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
		
		return commandLine;




	}

//	public ArrayList<CommandLine> queryCommandLine(String nombreTabla, String id_command, String status) throws SQLException {
//
//		conectar();
//
//		sentencia = c.createStatement();
//		String consultaSql;
//		if (status == "") { consultaSql = "SELECT * FROM " + nombreTabla + " WHERE id_command ='" + id_command +"';";}
//		else { consultaSql = "SELECT * FROM " + nombreTabla + " WHERE id_command ='" + id_command + "' AND status ='" + status +"';";}
//		
//		try {
//			ResultSet rs = sentencia.executeQuery(consultaSql);
//			
//			while (rs.next()) {
//				
//					this.commandLine.add(new CommandLine(
//							rs.getString("id_command"),
//							rs.getString("id_command_line"),
//							rs.getInt("unitToDo"),
//							rs.getDouble("unitPrice"),
//							rs.getString("status")
//					));
//			}	
//			
//
//			rs.close();
//
//			sentencia.close();
//
//			c.close();
//			
//
//
//		} catch (Exception e) {
//
//			JOptionPane.showConfirmDialog(null, "ERROR BD COMMANDLINE: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
//		}
//		
//		return commandLine;
//
//
//
//
//	}
	
	
	

}

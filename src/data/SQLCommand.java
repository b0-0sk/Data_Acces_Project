package data;
import java.sql.Connection;
import model.Command;
import model.CommandLine;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;


public class SQLCommand {

	Connection c = null;
	Statement sentencia = null;
	ArrayList<Command> command = new ArrayList<Command>();
	ArrayList<CommandLine> commandLine = new ArrayList<CommandLine>();

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



	public void insertaCommand(Command command) throws SQLException {


		//TODO cambiar variable command y commandLine
		
		String sqlInsert = "INSERT INTO command" 
				+ " VALUES ("
				+"\""+command.getId_command()+ "\""+","
				+"\""+command.getId_client()+ "\""+","
				+"\""+command.getOvertureDate()+ "\""+","
				+"\""+command.getConcludeDate()+ "\""+","
				+"\""+command.getStatus()+ "\""+","
				+"\""+command.getTotalPrice()+ "\")";
		
		
		

		try {

			conectar();

			sentencia = c.createStatement();
			
			sentencia.executeUpdate(sqlInsert); 
			

			sentencia.close();

			c.close();

			System.out.println("Datos insertados");

		} catch (Exception e) {

			JOptionPane.showConfirmDialog(null, "ERROR BD INSERTAR COMMAND: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void updateCommand(Command command) throws SQLException{
		
		
		
		String sqlUpdate ="UPDATE command "
				+ "SET "
				+ "   concludeDate ='" + command.getConcludeDate()
				+ "' WHERE id_command ='" + command.getId_command() + "';";
		
		
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
			JOptionPane.showConfirmDialog(null, "ERROR BD COMMAND: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
	}

	
	public void deleteCommand(Command command) throws SQLException {
					
		String sqlDelete = "DELETE FROM command " + " WHERE id_command = "+"\""+command.getId_command()+"\""+";";
		
		try {
			conectar();

			sentencia = c.createStatement();
			sentencia.executeUpdate(sqlDelete); 
			
			sentencia.close();

			c.close();

			System.out.println("Datos eliminados");

		} catch (Exception e) {
	
			JOptionPane.showConfirmDialog(null, "ERROR BD COMMAND: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public ArrayList<Command> queryCommand(String commandTable,String status) throws SQLException {

		conectar();

		sentencia = c.createStatement();
		
		String consultaCommandSql;
		if (status == "ALL") {consultaCommandSql = "SELECT * FROM " +  commandTable + " ;";}	
		else { consultaCommandSql = "SELECT * FROM " +commandTable + " WHERE status = "+"\""+status+"\""+";";}
		

		try {
					
			ResultSet rs = sentencia.executeQuery(consultaCommandSql);
			while (rs.next()) {

				this.command.add(new Command(
						rs.getString("id_command"),
						rs.getString("id_client"),
						rs.getString("status")
						
				));
			}			

			rs.close();

			sentencia.close();

			c.close();
			


		} catch (Exception e) {

			JOptionPane.showConfirmDialog(null, "ERROR BD CONSULTA COMMAND: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
		
		return command;

	}


	/*public ArrayList<Command> queryCommand(String commandTable) throws SQLException {

		conectar();

		sentencia = c.createStatement();

		String consultaCommandSql = "SELECT * FROM " + commandTable + " ;";
		//String consultaCommandLineSql = "SELECT * FROM commandLine;";


		try {
					
			ResultSet rs = sentencia.executeQuery(consultaCommandSql);
			while (rs.next()) {

				this.command.add(new Command(
						rs.getString("id_command"),
						rs.getString("id_client"),
						rs.getString("status")
						
				));
			}			

			rs.close();

			sentencia.close();

			c.close();
			


		} catch (Exception e) {

			JOptionPane.showConfirmDialog(null, "ERROR BD CONSULTA COMMAND: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
		
		return command;

	}*/
}

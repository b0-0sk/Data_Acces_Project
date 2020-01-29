package controler;

import model.Command;
import model.CommandLine;
import model.Client;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import data.LectorXML;
import data.SQLClients;
import data.SQLCommandLine;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		
 
		SQLClients connector = new SQLClients();
		Client client1 = new Client("12345672S","nomUsuari","CognomUsurai","316289564","usarinoucorreu@corejuk.cat","8740","barcelona en flames","E12343535","0");
		System.out.println(client1.toString());
		connector.queryClients("clients");
		
		connector.insertaClients(client1);
		System.out.println("\n\n\n\n");
		connector.queryClients("clients");
		Client client1actualitzat = new Client("12345672S","nomUsuariActualitzat","CognomUsurai","316289564","asdadsadasd@corejuk.cat","8740","barcelona en flames","E12343535","0");
		connector.updateClient(client1actualitzat);
		System.out.println("\n\n\n\n");
		connector.queryClients("clients");
		
		connector.deleteClients(client1);
		System.out.println("\n\n\n\n");
		connector.queryClients("clients");
		
		
		ArrayList<Command> commands = new ArrayList<Command>();

		LectorXML controlador1 = new LectorXML("command.xml");
		
		commands = controlador1.getCommands();
		
		
		for (int i = 0; i < commands.size(); i++) {
			
			System.out.println("Commanda  "+(i+1)+" "+
					commands.get(i).getId_command()+ ", "+
					commands.get(i).getId_client()+ ", "+
					commands.get(i).getOvertureDate()+ ", "+
					commands.get(i).getConcludeDate()+", " +
					commands.get(i).getStatus()+", "+
					commands.get(i).getTotalPrice()
					
					
					
					//commands.get(i).getListCommandLine()
					);


			

			
		}
		
		System.out.println("Command Objects " + controlador1.getCommands());
		
		
		
		//SQLCommandLine con = new SQLCommandLine();
		
	}
}

package model;
import java.util.*;
import java.text.*;
import java.sql.*;

public class Command {
	
	public String id_client; 
	public String id_command;
	public String status; 
	public double totalPrice;
	public String overtureDate;
	public String concludeDate;
	public ArrayList<CommandLine> ListCommandLine = new ArrayList<CommandLine>() ;
	
	/*
	 * 
	 * CREATE COMMAND
	 * 
	 */
	
	public Command(String id_command, String id_client, String status, ArrayList<CommandLine> ListCommandLine) {
		
		this.id_command = id_command; 
		this.id_client = id_client;
		this.overtureDate = dateNow();
		this.concludeDate = "00";
		this.status = status;
		this.totalPrice = totalPriceCommand();		
		this.ListCommandLine = ListCommandLine;
	
	}
	
	public Command(String id_command, String id_client, String status) {
		
		this.id_command = id_command; 
		this.id_client = id_client;
		this.overtureDate = dateNow();
		this.concludeDate = "00";
		this.status = status;
		this.totalPrice = totalPriceCommand();		
	
	}
	
	/*
	 * 
	 * DELETE SQL  || UPDATE CONCLUDE DATE COMMAND
	 * 
	 */
	
	public Command(String id_command,String concludeDate) {
		
		this.id_command = id_command; 
		this.concludeDate = concludeDate; 	
	
	}


	
	public String dateNow(){
		
		String PATTERN="dd/MM/yyy";
		SimpleDateFormat dateFormat=new SimpleDateFormat();
		dateFormat.applyPattern(PATTERN);
		String dateString=dateFormat.format(Calendar.getInstance().getTime());
		
		return dateString;
	}
	
	// TOTAL PRICE COMMAND
	public double totalPriceCommand() {
		double totalPrice= 0.0;
		
		for (CommandLine command_Line : ListCommandLine) {
			
			totalPrice += command_Line.lineCommandPrice;
		}
		
		return totalPrice;
	}	
	
	// ID_CLIENT
	public String getId_client() {
		return id_client;
	}


	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	//ID_COMMAND
	public String getId_command() {
		return id_command;
	}

	public void setId_command(String id_command) {
		this.id_command = id_command;
	}

	//STATUS
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//TOTAL PRICE
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	

	//OVERTUREDATE
	public String getOvertureDate() {
		return overtureDate;
	}

	public void setOvertureDate(String overtureDate) {
		this.overtureDate = overtureDate;
	}

	//CONCLUDE DATE
	public String getConcludeDate() {
		return concludeDate;
	}

	public void setConcludeDate(String concludeDate) {
		this.concludeDate = concludeDate;
	}


	//LIST COMMAND LINE
	public ArrayList<CommandLine> getListCommandLine() {
		return ListCommandLine;
	}
	
	public void setListCommandLine(ArrayList<CommandLine> listCommandLine) {
		ListCommandLine = listCommandLine;
	}


	
	public int[] articlesLinesCommand(int id_command) {
		return null;
	}
	public void newCommand(int id_client, int overtureDate, String description) {
		
	}
	public void paidCommandTrue(int id_command) {
		
	}


	@Override
	public String toString() {
		return "Command [id_client=" + id_client + ", id_command=" + id_command + ", status=" + status + ", totalPrice="
				+ totalPrice + ", overtureDate=" + overtureDate + ", concludeDate=" + concludeDate
				+ ", ListCommandLine=" + ListCommandLine + "]";
	}


	

	
	
	
	
}

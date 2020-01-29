package model;

public class CommandLine {
	
	public String id_command_line;
	public String id_command;
	public String status; 
	public int unitsToDo;
	public int unitsMade;
	public double unitPrice;
	public double lineCommandPrice;
	
	
	public CommandLine(String id_command, String id_command_line, int unitsToDo,int unitsMade, double unitPrice, String status) {

		this.id_command = id_command;
		this.id_command_line = id_command_line; 
		this.unitsToDo = unitsToDo;
		this.unitsMade = unitsMade;
		this.status = status;
		this.lineCommandPrice = unitPrice ;	//unitPrice * unitsMade
	}
	/**
	 * SQL UPDATE 
	 * 
	 */
	public CommandLine(String id_command_line, String status) {
		
		this.id_command_line = id_command_line;
		this.status = status;
		
	}
	public String getId_command_line() {
		return id_command_line;
	}
	public void setId_command_line(String id_command_line) {
		this.id_command_line = id_command_line;
	}
	public String getId_command() {
		return id_command;
	}
	public void setId_command(String id_command) {
		this.id_command = id_command;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getUnitsToDo() {
		return unitsToDo;
	}
	public void setUnitsToDo(int unitsToDo) {
		this.unitsToDo = unitsToDo;
	}
	public int getUnitsMade() {
		return unitsMade;
	}
	public void setUnitsMade(int unitsMade) {
		this.unitsMade = unitsMade;
	}
	public double getLineCommandPrice() {
		return lineCommandPrice;
	}
	public void setLineCommandPrice(double lineCommandPrice) {
		this.lineCommandPrice = lineCommandPrice;
	}
	
	@Override
	public String toString() {
		return "CommandLine [id_command_line=" + id_command_line + ", id_command=" + id_command + ", status=" + status
				+ ", unitsToDo=" + unitsToDo + ", unitsMade=" + unitsMade + ", lineCommandPrice=" + lineCommandPrice
				+ "]";
	}
	
	
}


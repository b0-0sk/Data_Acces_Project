package model;

public class OF {
	public int id_of;
	public int id_command;
	public int id_line_command;
	public String status;
	
	public OF(int id_of, int id_command, int id_line_command, String status) {
		
		this.id_of = id_of;
		this.id_command = id_command;
		this.id_line_command = id_line_command;
		this.status = status;
	}

	public int getId_of() {
		return id_of;
	}

	public void setId_of(int id_of) {
		this.id_of = id_of;
	}

	public int getId_command() {
		return id_command;
	}

	public void setId_command(int id_command) {
		this.id_command = id_command;
	}

	public int getId_line_command() {
		return id_line_command;
	}

	public void setId_line_command(int id_line_command) {
		this.id_line_command = id_line_command;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void startOF(int id_of) {}
	
	public void changeStatusOF(String status) {}
	
	
	
}


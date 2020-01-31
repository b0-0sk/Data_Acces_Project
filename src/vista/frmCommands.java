package vista;
import java.awt.Color;


import java.util.regex.*; 

import java.awt.Font;

import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import data.LectorXML;
import data.SQLCommand;
import data.SQLCommandLine;
import javafx.scene.control.Slider;
import model.Client;
import model.Command;
import model.CommandLine;
import java.awt.TextArea;
import java.awt.Component;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.List;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Toolkit;


/*
 * TODO Validation fields
 * TODO Fields with wrong info background red and the correct info background green
 * TODO 
 */
 
@SuppressWarnings("serial")
public class frmCommands extends JFrame{

	private JScrollPane scrollCommand;
	private JScrollPane scrollCommandLine;
	private JTable commandTable;
	private JTable commandLineTable;
	
	private ArrayList<Command> commands = new ArrayList<Command>();
	private static ArrayList<Command> command = new ArrayList<Command>();
	private static ArrayList<CommandLine> commandLine = new ArrayList<CommandLine>();
		
	private static DefaultTableModel modelCommand;
	private static DefaultTableModel modelCommandLine;

	private DefaultTableCellRenderer centerRenderer;

	private SQLCommand sqlCommand = new SQLCommand();
	private SQLCommandLine sqlCommandLine = new SQLCommandLine();
	
	private String nameXML = "command.xml";
	private LectorXML readerXML ;
	
	private JButton btnInsertarXml;
	
	private ButtonGroup btngroupCommand;
	private String id_Command_Table;
	private JRadioButton btnCommandOpen;
	private JRadioButton btnCommandInProgress;
	private JRadioButton btnCommandPayed;
	private JRadioButton btnCommandClosed;
	private JRadioButton btnCommandALL;
    
	private JButton btnSave;
	private JLabel lblSelectCommandFor;
	
	private int unitToDoCHANGE,unitMadeCHANGE;
	
	//PRUEVA
	
	private CommandLine commandLineSQL;
	
	private  JSlider slider;
	private JLabel lblUnitsMades;
	private JLabel lblRest;
	private JLabel lblPlus;
	private JTextField unitMade,unitsToDo;
	private JLabel lblUnitToDo;
	
	public frmCommands() throws SQLException  {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmCommands.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Background-Color-Yellow@2x.png")));
		
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setBackground(Color.GRAY);
		setBackground(Color.GRAY);
		
		getContentPane().setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		getContentPane().setLayout(null);
		
		// Titulos
		addTitlesTable();
		
		//Añadir Tabla
	    addTable();

	    //Añadir Botones
	    addBottons();
	    
	    //update tables
	    update("ALL");
	    

	}
	    
	
	public void addTitlesTable() {
		
		scrollCommand = new JScrollPane();
		scrollCommand.setViewportBorder(null);
		scrollCommand.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCommand.setToolTipText("");
		String[] dataCommand = new String[] {"Command ID","Client ID","Overture Date","Conclude Date","Status","Total Price"};
	    modelCommand = new DefaultTableModel(new Object[][] {},dataCommand ) {
	    
	    	public boolean isCellEditable(int r, int c){
	    		return false;
	    	}
	    };
	    
	    scrollCommandLine = new JScrollPane();
	    scrollCommandLine.setViewportBorder(null);
		scrollCommandLine.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCommandLine.setToolTipText("");
	    String[] dataCommandLine = new String[] {"Command ID","Command Line ID","Unit to Do","Unit Made","Command Line Price","Status"};
	    modelCommandLine = new DefaultTableModel(new Object[][] {},dataCommandLine ) {
	    
	    	public boolean isCellEditable(int r, int c){
	    		return false;
	    	}
	    };
	    
	}
	
	
	public void addTable() throws SQLException {
		
		/*
		 * 
		 * COMMAND TABLE
		 * 
		 */
		
		commandTable = new JTable(modelCommand);
	    scrollCommand.setBounds(265,58,1374,396);
	    commandTable.getTableHeader().setReorderingAllowed(false); // columns drawing

	    scrollCommand.setViewportView(commandTable); //Scrolls table
	    getContentPane().add(scrollCommand);
	    
	 	centerRenderer = new DefaultTableCellRenderer(); // Centre les dades del camp
	  	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	  	commandTable.setDefaultRenderer(Object.class, centerRenderer);
	  	
	  	/*
	  	 * 
	  	 * COMMAND LINE TABLE
	  	 * 
	  	 */
	  	
	  	commandLineTable = new JTable(modelCommandLine);
	    scrollCommandLine.setBounds(535,480,834,427);
	    commandLineTable.getTableHeader().setReorderingAllowed(false); // columns drawing
	    commandLineTable.setBackground(Color.WHITE);
	    
	    scrollCommandLine.setViewportView(commandLineTable); //Scrolls table
	    getContentPane().add(scrollCommandLine);

	 	centerRenderer = new DefaultTableCellRenderer(); // Centre les dades del camp
	  	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	  	commandLineTable.setDefaultRenderer(Object.class, centerRenderer);
	    


	}
	

	
	public void addBottons() {
		
		
		
		 /*
		 * 
		 * INSERT XML FILE
		 * 
		 */
		
		 btnInsertarXml = new JButton("Insertar XML");
		    btnInsertarXml.setBackground(Color.LIGHT_GRAY);
			btnInsertarXml.setIcon(new ImageIcon(frmCommands.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		    btnInsertarXml.setBounds(1669, 58, 209, 53);
		    getContentPane().add(btnInsertarXml);
			btnInsertarXml.addMouseListener(new MouseAdapter() {
				public void mouseClicked(final MouseEvent e) {
						
						try {				

							insertXML();	
							update("ALL");
							
						} catch (Exception e2) {
							// TODO: handle exception			
						}
					}
				});
		
		
	    /*
	     * 
	     * Button group Command Table
	     * 
	     */
	    btnCommandOpen = new JRadioButton("Open");
	    btnCommandInProgress = new JRadioButton("In Progress");
	    btnCommandPayed = new JRadioButton("Payed");
	    btnCommandClosed = new JRadioButton("Closed");
	    btnCommandALL = new JRadioButton("ALL");


	    btngroupCommand = new ButtonGroup();
	    btngroupCommand.add(btnCommandOpen);
	    btngroupCommand.add(btnCommandInProgress);
	    btngroupCommand.add(btnCommandPayed);
	    btngroupCommand.add(btnCommandClosed);
	    btngroupCommand.add(btnCommandALL);



	    /*
	     * 
	     * LISTENERS COMMAND TABLE BUTTONS
	     * 
	     */
        
	    lblSelectCommandFor = new JLabel("Select Command for his STATUS\r\n");
	    lblSelectCommandFor.setForeground(Color.WHITE);
	    lblSelectCommandFor.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblSelectCommandFor.setBounds(1669, 137, 209, 14);
	    getContentPane().add(lblSelectCommandFor);
        
        btnCommandOpen.setSelected(true);
	    btnCommandOpen.setForeground(Color.WHITE);
	    btnCommandOpen.setBackground(Color.GRAY);
	    btnCommandOpen.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    btnCommandOpen.setBounds(1684, 172, 71, 23);
	    getContentPane().add(btnCommandOpen);
	    btnCommandOpen.addMouseListener(new MouseAdapter() {
		public void mouseClicked(final MouseEvent e) {
				
				try {				
					update("O");				
				} catch (Exception e2) {
					// TODO: handle exception			
				}
			}
		});
	    
	    
	    btnCommandClosed.setSelected(true);
	    btnCommandClosed.setForeground(Color.WHITE);
	    btnCommandClosed.setBackground(Color.GRAY);
	    btnCommandClosed.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    btnCommandClosed.setBounds(1757, 198, 156, 23);
	    getContentPane().add(btnCommandClosed);
	    btnCommandClosed.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
				
				try {

					update("C");

				} catch (Exception e2) {
					// TODO: handle exception
					
				}
				
			}
		});
	    btnCommandInProgress.setForeground(Color.WHITE);
	    btnCommandInProgress.setBackground(Color.GRAY);
	    btnCommandInProgress.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    btnCommandInProgress.setBounds(1757, 172, 156, 23);
	    getContentPane().add(btnCommandInProgress);
	    btnCommandInProgress.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
				
				try {

					update("IP");

					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
				
			}
		});
	    btnCommandPayed.setForeground(Color.WHITE);
	    btnCommandPayed.setBackground(Color.GRAY);
	    btnCommandPayed.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    btnCommandPayed.setBounds(1684, 198, 65, 23);
	    getContentPane().add(btnCommandPayed);
	    btnCommandPayed.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
				
				try {
					
					update("P");

					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
				
			}
		});
	    
	    btnCommandALL.setSelected(true);
	    btnCommandALL.setBackground(Color.GRAY);
	    btnCommandALL.setForeground(Color.WHITE);
	    btnCommandALL.setFont(new Font("Tahoma", Font.PLAIN, 10));
	    btnCommandALL.setBounds(1728, 224, 65, 23);
	    getContentPane().add(btnCommandALL);
	    btnCommandALL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {	
					
					update("ALL");
					
				} catch (Exception e2) {
				// TODO: handle exception
					JOptionPane.showConfirmDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS: "+e2.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	    
	    btnSave = new JButton("Save");
	    btnSave.setForeground(Color.WHITE);
	    btnSave.setEnabled(false);
	    btnSave.setBackground(Color.LIGHT_GRAY);
	    btnSave.setBounds(1669, 830, 209, 62);
	    getContentPane().add(btnSave);
	    btnSave.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//actualitza flags
				if(btnSave.isEnabled()) {
					String err = "";
					boolean ok = false;
			
					if (commandLineSQL.getUnitsToDo() != 0) {					
						
						int reply = JOptionPane.showConfirmDialog(null, "Estàs segur que vols actualitzar Unitats Servides?",
								"Suprimir Client", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION)
						try {
							
							commandLineSQL.setUnitsToDo(unitToDoCHANGE);
							commandLineSQL.setUnitsMade(unitMadeCHANGE);
							
							updateUnitMadeAndUnitToDo();
							updateTabelCommandLine(commandLineSQL.getId_command());
							
							
							ok = true;
							
							unitsToDo.setText("0");
							unitMade.setText("0");
							setEnableOrNotBTCommandLine("false");
							


						} catch (Exception e1) {
							err+= " \n - ERROR UPDATING COMMAND LINE";
						}
												
					}else {
					
						
						err += " \n - ERROR WITH THE NUMBER UNIT MADE: "+ "[Unit Made es superior a Unit to do o es igual a 0.]";

					}
						
					if (ok == false ) {
						
						JOptionPane.showConfirmDialog(null, "ERROR SAVING COMMAND LINE DATA: "+err, "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);

					}			
				}

			}
		});
	    
	    slider = new JSlider();
	    slider.setEnabled(false);
	    slider.setValue(0);
	    slider.setBackground(Color.GRAY);
	    slider.setBounds(1684, 706, 173, 26);
	    getContentPane().add(slider);
	    slider.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				try {
					
				
						unitToDoCHANGE = commandLineSQL.getUnitsToDo() - Integer.parseInt(String.valueOf(slider.getValue()));
						unitMadeCHANGE = slider.getValue();
	
						
					
					if (unitToDoCHANGE >= 0 && unitMadeCHANGE <= commandLineSQL.getUnitsToDo()) {
						
						unitsToDo.setText(String.valueOf(unitToDoCHANGE));
						unitMade.setText(String.valueOf(unitMadeCHANGE));
						
					}else {
						
						JOptionPane.showConfirmDialog(null, "Linia de comanda servida ", "Completed!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);

					}
						
			} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
		});
	    
	    lblUnitsMades = new JLabel("Units mades");
	    lblUnitsMades.setEnabled(false);
	    lblUnitsMades.setForeground(Color.WHITE);
	    lblUnitsMades.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblUnitsMades.setBounds(1671, 616, 101, 14);
	    getContentPane().add(lblUnitsMades);
	    
	   
	    
	    lblRest = new JLabel("-");
	    lblRest.setEnabled(false);
	    lblRest.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblRest.setForeground(Color.WHITE);
	    lblRest.setBounds(1667, 693, 7, 40);
	    getContentPane().add(lblRest);
	    
	    lblPlus = new JLabel("+");
	    lblPlus.setEnabled(false);
	    lblPlus.setForeground(Color.WHITE);
	    lblPlus.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblPlus.setBounds(1867, 706, 13, 14);
	    getContentPane().add(lblPlus);
	    
	    unitMade = new JTextField();
	    unitMade.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    unitMade.setHorizontalAlignment(JTextField.CENTER);
	    unitMade.setEnabled(false);
	    unitMade.setEditable(false);
	    unitMade.setBounds(1728, 649, 86, 20);
	    getContentPane().add(unitMade);
	    unitMade.setColumns(10);
	    
	    unitsToDo = new JTextField();
	    unitsToDo.setHorizontalAlignment(JTextField.CENTER);
	    unitsToDo.setEnabled(false);
	    unitsToDo.setEditable(false);
	    unitsToDo.setBounds(1728, 574, 86, 20);
	    getContentPane().add(unitsToDo);
	    unitsToDo.setColumns(10);
	    
	    lblUnitToDo = new JLabel("Units to do");
	    lblUnitToDo.setForeground(Color.WHITE);
	    lblUnitToDo.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblUnitToDo.setEnabled(false);
	    lblUnitToDo.setBounds(1669, 538, 101, 14);
	    getContentPane().add(lblUnitToDo);
		
	    /*
		 * 
		 * LISTENER TABLE COMMAND
		 * 
		 */ 

		commandTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
					
					try {		
			
						id_Command_Table = modelCommand.getValueAt(commandTable.getSelectedRow(), 0).toString();
						
						btnCommandOpen.setEnabled(true);
						btnCommandInProgress.setEnabled(true);
						btnCommandClosed.setEnabled(true);
						
						updateTabelCommandLine(id_Command_Table);
						setEnableOrNotBTCommandLine("false");

						checkCommandLine();
					} catch (Exception e2) {
						// TODO: handle exception			
					}
				}
			});
		
		/*
		 * 
		 * LISTENER TABLE COMMAND
		 * 
		 */ 

		commandLineTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
					
					try {	
					
						fillCommandLineSQL();

						if (commandLineSQL.getUnitsToDo() != 0) {
							
							
							setValorsInCommandLine();			
							setEnableOrNotBTCommandLine("true");
							
						}else {
							
							JOptionPane.showConfirmDialog(null, "Linia de comanda servida ", "Completed!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
							setEnableOrNotBTCommandLine("false");

						}
						
					
						
						checkUnitMade();
						
					} catch (Exception e2) {
						// TODO: handle exception	
						JOptionPane.showConfirmDialog(null, "ERROR COMMAND LINE TABLE MOUSE LISTENER: FALLO", "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);

					}
				}
			});
	}
	
	public void checkCommandLine() {
		int count = commandLine.size();
		for (int j = 0; j < commandLine.size() ; j++) {			
				
			if (commandLine.get(j).getStatus().toString() == "C") {		
				count--;
				if (count == 0) {
					
					/**
					 * UPDATE STATUS COMMAND  AND CONCLUDE DATE
					 */
				}
			}
		}
		
		
		
	}
	
	public void checkUnitMade() {
		
		if (String.valueOf(commandLineSQL.getUnitsToDo()) == "0") {
			/**
			 * CHANGE STATUS COMMANDLINE
			 */
		}
	}

	public void setValorsInCommandLine() {
		
		slider.setMaximum(commandLineSQL.getUnitsToDo());
		
		//unitMade.setText(String.valueOf(commandLineSQL.getUnitsMade()));
		//slider.setValue(Integer.parseInt(unitMade.getText()));
		unitMade.setText("0");
		slider.setValue(0);
		unitsToDo.setText(String.valueOf(commandLineSQL.getUnitsToDo()));
		
	}
	
	public void fillCommandLineSQL() {
		
		commandLineSQL = new CommandLine(
				modelCommandLine.getValueAt(commandLineTable.getSelectedRow(),0).toString(),
				modelCommandLine.getValueAt(commandLineTable.getSelectedRow(),1).toString(),
				Integer.parseInt(modelCommandLine.getValueAt(commandLineTable.getSelectedRow(),2).toString()),
				Integer.parseInt(modelCommandLine.getValueAt(commandLineTable.getSelectedRow(),3).toString()),
				Double.parseDouble(modelCommandLine.getValueAt(commandLineTable.getSelectedRow(),4).toString()),
				modelCommandLine.getValueAt(commandLineTable.getSelectedRow(),5).toString()
				);
		
	}
	
	public void setEnableOrNotBTCommandLine(String view) {
		
		lblUnitsMades.setEnabled((view == "true" )? true:false);
		lblUnitToDo.setEnabled((view == "true" )? true:false);
		unitMade.setEnabled((view == "true" )? true:false);
		unitsToDo.setEnabled((view == "true" )? true:false);
		slider.setEnabled((view == "true" )? true:false);
		btnSave.setEnabled((view == "true" )? true:false);
		lblPlus.setEnabled((view == "true" )? true:false);
		lblRest.setEnabled((view == "true" )? true:false);
		
	}
	
	/*
	 * 
	 * Change name xml file
	 * 
	 */
	
	public void setDocumentXML(String n) {
		
		this.nameXML = n; 
	}
	
	public void insertXML() throws SQLException {
		
		
		try {
			readerXML= new LectorXML(nameXML);
			commands = readerXML.getCommands();
			for (int i = 0; i < commands.size(); i++) {
				
				sqlCommand.insertaCommand(commands.get(i));
				
				for (int j = 0; j < commands.get(i).getListCommandLine().size(); j++) {
					
					sqlCommandLine.insertaCommandLine(commands.get(i).getListCommandLine().get(j));
				}
				
			}			
			JOptionPane.showConfirmDialog(null, "INSERCIÓN FICHERO XML: " + "CORRECTO", id_Command_Table, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showConfirmDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void deleteCommand(String n) throws SQLException {
		
		try {
			
			commands = readerXML.getCommands();
			Command c = new Command(n,"");
			sqlCommand.deleteCommand(c);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void updateCommand(String concludeDate) {
		
		try {
			
			commands = readerXML.getCommands();
			Command c= new Command("",concludeDate);
			sqlCommand.updateCommand(c);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void update(String status) throws SQLException{
		
		modelCommand.setRowCount(0);

		sqlCommand = new SQLCommand();
		command = sqlCommand.queryCommand("command",status);
		
	  	/*
	  	 * 
	  	 * COMANDES
	  	 * 
	  	 */

	  	String concludeDate;
	  	
	  	for (int i = 0; i < command.size(); i++) {
	  		
		  	if (command.get(i).getConcludeDate().equals("00")) {concludeDate = "Unspecified date";}  		
		  	else {concludeDate = command.get(i).getConcludeDate();}
		  
		  	modelCommand.addRow(new Object[]{
	  				command.get(i).getId_command(),
	  				command.get(i).getId_client(),
	  				command.get(i).getOvertureDate(),
	  				concludeDate,
	  				command.get(i).getStatus(),
	  				command.get(i).getTotalPrice()	
	  		});
		  }
	  	
	  	for (int i = 0; i < 50; i++) {
	  		modelCommand.addRow(new Object[] {null,null,null,null,null,null,null,null,null});
	  		modelCommandLine.addRow(new Object[] {null,null,null,null,null,null,null,null,null});
		}		  	
	 }
	
	public void updateTabelCommandLine(String id_command) {
		
		try {
			modelCommandLine.setRowCount(0);
		  	sqlCommandLine = new SQLCommandLine();
			
			commandLine = sqlCommandLine.queryCommandLine("commandLine" , id_command);
			
		  	modelCommandLine.setRowCount(0);
			
			/**
			 * 
			 * Possibilad de crear un objecto CommandLine para poder hacer el update correctamente
			 * 
			 */
			
			
			for (int j = 0; j < commandLine.size() ; j++) {
				modelCommandLine.addRow(new Object[] {
							commandLine.get(j).getId_command(),
							commandLine.get(j).getId_command_line(),
							commandLine.get(j).getUnitsToDo(),
							commandLine.get(j).getUnitsMade(),
							commandLine.get(j).getLineCommandPrice() * commandLine.get(j).getUnitsMade(),
							commandLine.get(j).getStatus()
				});
			}
			
			for (int i = 0; i < 50; i++) {
		  		modelCommandLine.addRow(new Object[] {null,null,null,null,null,null,null,null,null});
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showConfirmDialog(null, "ERROR BD FRCOMMAND COMMANDLINE: "+e.getMessage(), "Warning!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);

		}
	}
	
	/*
	 * 
	 * ACTUALIZAR TABLA COMMAND LINE DATOS 
	 * 
	 */
	
	public void updateUnitMadeAndUnitToDo() {
		
		try {
			
			sqlCommandLine.updateCommandLine(commandLineSQL);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

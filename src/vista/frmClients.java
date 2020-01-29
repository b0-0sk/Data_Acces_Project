package vista;
import java.awt.Color;



import java.awt.Font;
import java.util.regex.Pattern; 
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import data.SQLClients;
import model.Client;


/*
 * TODO Validation fields
 * TODO Fields with wrong info background red and the correct info background green
 * TODO 
 */
 
public class frmClients extends JFrame {
	
	private JScrollPane scroll;
	private JTable clientTable;
		
	private JButton btnNewClient = new JButton("New client");
	private JButton btnDelete = new JButton("Delete");
	private JButton btnEdit = new JButton("Edit");
	private JButton btnSave = new JButton("Save");
		
	private static ArrayList<Client> clients = new ArrayList<Client>();
	private static DefaultTableModel model;
	private DefaultTableCellRenderer centerRenderer;

	private SQLClients con = new SQLClients();

	private JTextField cif;
	private JTextField name;
	private JTextField surname;
	private JTextField telf;
	private JTextField email;
	private JTextField cp;
	private JTextField province;
	private JTextField iban;
	private JCheckBox goodPayer;
	
	boolean save = false;
	boolean edit = false;
	boolean delete = false;
	
	private JTextField txtSampleDesign,txtSampleDesign1,txtSampleDesign2,txtSampleDesign3,txtSampleDesign4,txtSampleDesign5,txtSampleDesign6,txtSampleDesign7;


 
	public frmClients() throws SQLException  {
		
		getContentPane().setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		
		getContentPane().setLayout(null);
		
		// Titulos
		addTitlesTable();
		
		//Añadir Tabla
	    addTable();

	    //Añadir Botones
	    
	    addBottons();
	    
	    // Añadir campos
	    addTextFields();
		
	    update();

	}
	    
	public void contextFieldExample(String n) {

		if (n == "null") {
			fieldsNull();
			fieldsSampleColorAndFont();
		}	
		txtSampleDesignVisible("true");

		
	}
	public void fieldsNull() {
		
			
			cif.setText(null);
			name.setText(null);
			surname.setText(null);
			email.setText(null);
			telf.setText(null);
			cp.setText(null);
			province.setText(null);
			iban.setText(null);
			goodPayer.setSelected(false);
		
		
	}
	public void fieldsSampleColorAndFont() {
	  
		cif.setBackground(Color.WHITE);
		name.setBackground(Color.WHITE);
		surname.setBackground(Color.WHITE);
		email.setBackground(Color.WHITE);
		telf.setBackground(Color.WHITE);
		cp.setBackground(Color.WHITE);
		province.setBackground(Color.WHITE);
		iban.setBackground(Color.WHITE);
		
		cif.setForeground(Color.BLACK);
		name.setForeground(Color.BLACK);
		surname.setForeground(Color.BLACK);
		email.setForeground(Color.BLACK);
		cif.setForeground(Color.BLACK);
		telf.setForeground(Color.BLACK);
		cp.setForeground(Color.BLACK);
		province.setForeground(Color.BLACK);
		iban.setForeground(Color.BLACK);

	}
	
	public void txtSampleDesignVisible(String c) {
		
		txtSampleDesign.setVisible((c == "true")? true:false);
		txtSampleDesign1.setVisible((c == "true")? true:false);
		txtSampleDesign2.setVisible((c == "true")? true:false);
		txtSampleDesign3.setVisible((c == "true")? true:false);
		txtSampleDesign4.setVisible((c == "true")? true:false);
		txtSampleDesign5.setVisible((c == "true")? true:false);
		txtSampleDesign6.setVisible((c == "true")? true:false);
		txtSampleDesign7.setVisible((c == "true")? true:false);
		//txtSampleDesign8.setVisible((c == "true")? true:false);

	}
	

	@SuppressWarnings("serial")
	public void addTitlesTable() {
		scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setToolTipText("");
		String[] dataClient = new String[] {"CIF","Name","Surname","Telephone","Email","CP","Province","Iban","Good Payer"};
	    model = new DefaultTableModel(new Object[][] {},dataClient ) {
	    
	    	public boolean isCellEditable(int r, int c){
	    		return false;
	    	}
	    };
	    
	}
	
	
	public void addTable() throws SQLException {
		
		clientTable = new JTable(model);
	    scroll.setBounds(10,453,1891,629);
	    clientTable.getTableHeader().setReorderingAllowed(false); // columns drawing
	    clientTable.setBackground(Color.WHITE);
	    
	    scroll.setViewportView(clientTable); //Scrolls table
	    getContentPane().add(scroll);
	    
	 	centerRenderer = new DefaultTableCellRenderer(); // Centre les dades del camp
	  	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	  	clientTable.setDefaultRenderer(Object.class, centerRenderer);

	}
	

	
	public void addBottons() {
		clientTable.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(final MouseEvent e) {
				
				try {
					
					if (e.getClickCount() == 1) {
						
						contextFieldExample("null");

						final JTable table = (JTable) e.getSource();
						final int row = table.getSelectedRow();
						
						cif.setText(table.getValueAt(row, 0).toString());
						name.setText(table.getValueAt(row, 1).toString());
						surname.setText(table.getValueAt(row, 2).toString());	
						telf.setText(table.getValueAt(row, 3).toString());
						email.setText(table.getValueAt(row, 4).toString());
						cp.setText(table.getValueAt(row, 5).toString());
						province.setText(table.getValueAt(row, 6).toString());
						iban.setText(table.getValueAt(row, 7).toString());	
											
						if (clientTable.getValueAt(clientTable.getSelectedRow(), 8).toString().equals("No")) {						
							goodPayer.setSelected(false);						
						}else {						
							goodPayer.setSelected(true);
						}
						
						cif.setEditable(false);
						name.setEditable(false);
						surname.setEditable(false);
						email.setEditable(false);
						telf.setEditable(false);
						cp.setEditable(false);
						province.setEditable(false);
						iban.setEditable(false);
						

						btnNewClient.setEnabled(true);
						btnDelete.setEnabled(true);
						btnEdit.setEnabled(true);
						btnSave.setEnabled(false);

						save = false;
						

					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
				
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(1340, 190, 177, 74);
	    getContentPane().add(btnSave);
	    btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Boolean ok = false;
				try {					
					if (save) {
						
						//EDITAR TODOS LOS CAMPOS						
						ok = checkFields(); // Checkers
						contextFieldExample("noNull");
						if(ok) {	
							insertClient(
									cif.getText(), 
									name.getText(), 
									surname.getText(),
									telf.getText(),
									email.getText(),
									cp.getText(),
									province.getText(),
									iban.getText(),
									goodPayer.isSelected()? "Yes": "No"
									);
							
							fieldsNull();
							fieldsSampleColorAndFont();
							JOptionPane.showMessageDialog(null, "S'han desat correctament les dades");

						}
					
					} else if (edit) {
						
						ok = checkFields();
						//EDITAR TODOS LOS CAMPOS
						contextFieldExample("noNull");
						if(ok) {
														
							editClient(
									cif.getText(), 
									name.getText(), 
									surname.getText(),
									telf.getText(),
									email.getText(),
									cp.getText(),
									province.getText(),
									iban.getText(),
									goodPayer.isSelected()? "Yes": "No"
									);
							
							fieldsNull();
							fieldsSampleColorAndFont();
							JOptionPane.showMessageDialog(null, "S'han desat correctament les dades");


						}
						
						
					}
					
					if(ok) {
						update();
						cif.setEditable(false);
						name.setEditable(false);
						surname.setEditable(false);
						email.setEditable(false);
						telf.setEditable(false);
						cp.setEditable(false);
						province.setEditable(false);
						iban.setEditable(false);
						goodPayer.setEnabled(false);
						
						fieldsNull();	// not-default

						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						
						
						save = false;
						edit = false;
						delete = false;
					}
					


				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//btn New Client
	    btnNewClient.setBounds(1588,159,137,61);
	    getContentPane().add(btnNewClient);
		btnNewClient.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//actualitza flags
				save = true;
				edit = false;
				delete = false;
				try {
					//buida els panells de text i permet editar
					
			    	contextFieldExample("null"); // null | false
					JOptionPane.showConfirmDialog(null, "És entrat en el mode \"New client\"",	"Client Creation", JOptionPane.DEFAULT_OPTION);
					
					cif.setEditable(true);
					name.setEditable(true);
					surname.setEditable(true);
					email.setEditable(true);
					telf.setEditable(true);
					cp.setEditable(true);
					province.setEditable(true);
					iban.setEditable(true);
					goodPayer.setEnabled(true);
	
					
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
					btnSave.setEnabled(true);

					cif.requestFocus(); // non-editable 


				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
		//btn Delete
		
		btnDelete.setEnabled(false);
		btnDelete.setBounds(1735,159,137,61);
	    getContentPane().add(btnDelete);
		btnDelete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//actualitza flags
				if(btnDelete.isEnabled()) {
					save = false;
					edit = false;
					int reply = JOptionPane.showConfirmDialog(null, "Estàs segur que vols suprimir aquest client?",
							"Suprimir Client", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION)
					try {
						Client client = new Client((String) model.getValueAt(clientTable.getSelectedRow(), 0));
						removeClient(client);
						contextFieldExample("null");


						update();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				}

			}
		});
		//btn Eedit
		
		btnEdit.setEnabled(false);
		btnEdit.setBounds(1662,238,137,61);
	    getContentPane().add(btnEdit);
		btnEdit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(btnEdit.isEnabled()) {
					//actualitza flags
					save = false;
					edit = true;
					delete = false;
					try {

						JOptionPane.showConfirmDialog(null, "És entrat en el mode \"Editar client\"",	"Client Edit", JOptionPane.DEFAULT_OPTION);

						cif.setEditable(false);
						name.setEditable(true);
						surname.setEditable(true);
						email.setEditable(true);
						telf.setEditable(true);
						cp.setEditable(true);
						province.setEditable(true);
						iban.setEditable(true);
						goodPayer.setEnabled(true);
						
						btnSave.setEnabled(true);
						
						name.requestFocus();
						
						txtSampleDesignVisible("true");

						
						update();

					} catch (Exception e1) {
						// TODO: handle exception
					}
				}
				
			}
		});
		 
		    
	}
	
	public void insertClient(String cif, String name, String surname, String telf, String email, String cp, String province, String iban,String goodPayer) throws SQLException {
		Client client = new Client(cif.toUpperCase(),  name, surname, telf, email.toLowerCase(), cp, province, iban.toUpperCase(), goodPayer);
		con.insertaClients(client);
	}
	public void editClient(String cif, String name, String surname, String telf, String email, String cp, String province, String iban,String goodPayer) throws SQLException {
		Client client = new Client(cif.toUpperCase(),  name, surname, telf, email.toLowerCase(), cp, province, iban.toUpperCase(), goodPayer);
		con.updateClient(client);
	}
	public void removeClient(Client client) throws SQLException {
		
		con.deleteClients(client);
		
	}
	public void addTextFields() {
	    
	    JLabel lblCif = new JLabel("CIF");
	    lblCif.setBounds(431, 201, 56, 16);
	    getContentPane().add(lblCif);
	    
	    cif = new JTextField();
	    cif.setHorizontalAlignment(SwingConstants.CENTER);
	    cif.setBounds(431, 230, 116, 22);
	    getContentPane().add(cif);
	    cif.setColumns(10);
	    cif.setEditable(false);
	    
	    JLabel lblNewLabel = new JLabel("Name");
	    lblNewLabel.setBounds(589, 159, 56, 16);
	    getContentPane().add(lblNewLabel);
	    
	    name = new JTextField();
	    name.setHorizontalAlignment(SwingConstants.CENTER);
	    name.setBounds(589, 186, 116, 22);
	    getContentPane().add(name);
	    name.setColumns(10);
	    name.setEditable(false);
	    
	    JLabel lblSurname = new JLabel("Surname");
	    lblSurname.setBounds(736, 159, 56, 16);
	    getContentPane().add(lblSurname);
	    
	    JLabel lblTelf = new JLabel("Telephone");
	    lblTelf.setBounds(889, 159, 80, 16);
	    getContentPane().add(lblTelf);
	    
	    surname = new JTextField();
	    surname.setHorizontalAlignment(SwingConstants.CENTER);
	    surname.setBounds(736, 186, 116, 22);
	    getContentPane().add(surname);
	    surname.setColumns(10);
	    surname.setEditable(false);
	    
	    telf = new JTextField();
	    telf.setHorizontalAlignment(SwingConstants.CENTER);
	    telf.setBounds(889, 186, 193, 22);
	    getContentPane().add(telf);
	    telf.setColumns(10);
	    telf.setEditable(false);
	    
	    JLabel lblEmail = new JLabel("Email");
	    lblEmail.setBounds(1113, 159, 56, 16);
	    getContentPane().add(lblEmail);
	    
	    email = new JTextField();
	    email.setHorizontalAlignment(SwingConstants.CENTER);
	    email.setBounds(1113, 186, 152, 22);
	    getContentPane().add(email);
	    email.setColumns(10);
	    email.setEditable(false);
	    
	    JLabel lblCp = new JLabel("CP");
	    lblCp.setBounds(589, 248, 56, 16);
	    getContentPane().add(lblCp);
	    
	    cp = new JTextField();
	    cp.setHorizontalAlignment(SwingConstants.CENTER);
	    cp.setBounds(589, 277, 116, 22);
	    getContentPane().add(cp);
	    cp.setColumns(10);
	    cp.setEditable(false);
	    
	    JLabel lblProvince = new JLabel("Province");
	    lblProvince.setBounds(736, 248, 56, 16);
	    getContentPane().add(lblProvince);
	    
	    province = new JTextField();
	    province.setHorizontalAlignment(SwingConstants.CENTER);
	    province.setBounds(736, 277, 116, 22);
	    getContentPane().add(province);
	    province.setColumns(10);
	    province.setEditable(false);
	    
	    JLabel lblNewLabel_1 = new JLabel("Iban");
	    lblNewLabel_1.setBounds(889, 248, 56, 16);
	    getContentPane().add(lblNewLabel_1);
	    
	    iban = new JTextField();
	    iban.setHorizontalAlignment(SwingConstants.CENTER);
	    iban.setBounds(889, 277, 193, 22);
	    getContentPane().add(iban);
	    iban.setColumns(10);
	    iban.setEditable(false);
	    
	    JLabel lblGoodpayer = new JLabel("Good Payer");
	    lblGoodpayer.setBounds(1113, 248, 90, 16);
	    getContentPane().add(lblGoodpayer);
	    
	    goodPayer = new JCheckBox("");
	    goodPayer.setEnabled(false);
	    goodPayer.setBounds(1113, 277, 97, 23);
	    getContentPane().add(goodPayer);
	    
	    txtSampleDesign = new JTextField();
	    txtSampleDesign.setEditable(false);
	    txtSampleDesign.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign.setText("E12345678");
	    txtSampleDesign.setBounds(431, 256, 116, 20);
	    getContentPane().add(txtSampleDesign);
	    txtSampleDesign.setColumns(10);
	    
	    txtSampleDesign1 = new JTextField();
	    txtSampleDesign1.setEditable(false);
	    txtSampleDesign1.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign1.setText("Juan");
	    txtSampleDesign1.setColumns(10);
	    txtSampleDesign1.setBounds(589, 217, 116, 20);
	    getContentPane().add(txtSampleDesign1);
	    
	    txtSampleDesign2 = new JTextField();
	    txtSampleDesign2.setEditable(false);
	    txtSampleDesign2.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign2.setText("Pedrosa");
	    txtSampleDesign2.setColumns(10);
	    txtSampleDesign2.setBounds(736, 217, 116, 20);
	    getContentPane().add(txtSampleDesign2);
	    
	    txtSampleDesign3 = new JTextField();
	    txtSampleDesign3.setEditable(false);
	    txtSampleDesign3.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign3.setText("678 912 345");
	    txtSampleDesign3.setColumns(10);
	    txtSampleDesign3.setBounds(889, 217, 193, 20);
	    getContentPane().add(txtSampleDesign3);
	    
	    txtSampleDesign4 = new JTextField();
	    txtSampleDesign4.setEditable(false);
	    txtSampleDesign4.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign4.setText("email@server.com");
	    txtSampleDesign4.setColumns(10);
	    txtSampleDesign4.setBounds(1113, 217, 152, 20);
	    getContentPane().add(txtSampleDesign4);
	    
	    txtSampleDesign5 = new JTextField();
	    txtSampleDesign5.setEditable(false);
	    txtSampleDesign5.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign5.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign5.setText("ES00 0000 0000 0000 0000 0000");
	    txtSampleDesign5.setColumns(10);
	    txtSampleDesign5.setBounds(889, 310, 193, 20);
	    getContentPane().add(txtSampleDesign5);
	    
	    txtSampleDesign6 = new JTextField();
	    txtSampleDesign6.setEditable(false);
	    txtSampleDesign6.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign6.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign6.setText("Barcelona");
	    txtSampleDesign6.setColumns(10);
	    txtSampleDesign6.setBounds(736, 310, 116, 20);
	    getContentPane().add(txtSampleDesign6);
	    
	    txtSampleDesign7 = new JTextField();
	    txtSampleDesign7.setEditable(false);
	    txtSampleDesign7.setHorizontalAlignment(SwingConstants.CENTER);
	    txtSampleDesign7.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
	    txtSampleDesign7.setText("08640");
	    txtSampleDesign7.setColumns(10);
	    txtSampleDesign7.setBounds(589, 310, 116, 20);
	    getContentPane().add(txtSampleDesign7);
	    
	  
	}
	
	
	public static void update() throws SQLException{
		
		model.setRowCount(0);
				
		SQLClients con = new SQLClients();
	  	clients = con.queryClients("clients");
	  	
	  	for (int i = 0; i < clients.size(); i++) {
	  		model.addRow(new Object[]{
	  				clients.get(i).getCif(),
	  				clients.get(i).getName(),
	  				clients.get(i).getSurname(),
	  				clients.get(i).getTelf(),
	  				clients.get(i).getEmail(),
	  				clients.get(i).getCp(),
	  				clients.get(i).getProvince(),
	  				clients.get(i).getIban(),
	  				clients.get(i).getGoodpayer() 				
	  		});
	  	}
	  	
	  	/*
	  	 * Ompli taula amb camps buits
	  	 */
	  	for (int i = 0; i < 50; i++) {
	  		model.addRow(new Object[] {null,null,null,null,null,null,null,null,null});
		}
	  		
	}
	
	public boolean checkFields() {
		
		String errMessage="";
		boolean ok = false;
		boolean okCheckers = false;
		if(!checkCIF(cif.getText()) && cif.getText().equals("")) {
			
			errMessage += " \n - CIF no valid";
			cif.setBackground(new Color(217, 80, 48));
			cif.setForeground(Color.WHITE);
			okCheckers = true;
		}
		
		if (name.getText().equals("")) {
																
			errMessage += " \n - Nom no valid.";
			name.setBackground(new Color(217, 80, 48));
			name.setForeground(Color.WHITE);
			okCheckers = true;
		
		}
		
		if (surname.getText().equals("")) {
			
			errMessage += " \n - Cognom no valid.";
			surname.setBackground(new Color(217, 80, 48));
			surname.setForeground(Color.WHITE);
			okCheckers = true;
		
		}
		
		if (!checkTelfANDcp(telf.getText()) || telf.getText().equals("")) {
			
			errMessage += " \n - Telefon no valid.";
			telf.setBackground(new Color(217, 80, 48));
			telf.setForeground(Color.WHITE);
			okCheckers = true;
		
		}						
		
		if (!checkEmail(email.getText()) || email.getText().equals("")) {
			
			errMessage += " \n - Email no valid.";
			email.setBackground(new Color(217, 80, 48));
			email.setForeground(Color.WHITE);
			okCheckers = true;

		}
		
		if (province.getText().equals("")) {
					
			errMessage += " \n - Provincia no valida.";
			province.setBackground(new Color(217, 80, 48));
			province.setForeground(Color.WHITE);
			okCheckers = true;
		
		}
		
		if(!checkTelfANDcp(cp.getText()) || cp.getText().equals("")) {								

			errMessage += " \n - CP no valid.";
			cp.setBackground(new Color(217, 80, 48));
			cp.setForeground(Color.WHITE);
			okCheckers = true;

		}
		
		if (!checkIban(iban.getText())) {
			
			errMessage += " \n - Iban no valid.";
			iban.setBackground(new Color(217, 80, 48));
			iban.setForeground(Color.WHITE);
			okCheckers = true;

		}
		
		if (ok == false && okCheckers == true) {
			JOptionPane.showConfirmDialog(null, errMessage, "Input Error", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}
		
		if (errMessage.equals("")) {
			ok= true;
		}
		
		return ok;
		
		
		
		
	}
	 
	
	/*
	 * Checks Inputs
	 */
	
	
	
	public boolean checkTelfANDcp(String n){
		
		if(n.length() != 11 && n.length() != 5) return false;
		
		else if(n.length() == 11) {
			for(int i=0;i<n.length();++i) {
				System.out.println("i: " + i +" n: " + n.charAt(i));
				if(i == 3 || i == 7 ) {}
				else if(n.charAt(i)< 48 || n.charAt(i)> 57) return false;
			}
		}
		
		else {
			for(int i=0;i<n.length();++i) {
				if(n.charAt(i)< 48 || n.charAt(i)> 57) return false;
			}
		}
		return true;
	}
	
	public boolean checkCIF(String cif) {
		
		cif.toUpperCase();
		if(cif.length() != 9) return false;
		if(cif.charAt(0) > 90 && cif.charAt(0) < 65) return false;
		for(int i=1;i<cif.length();++i) {
			if(cif.charAt(i)< 48 || cif.charAt(i)> 57) return false;
		}
		return true;
	}
	
	public boolean checkIban(String iban) {
		
		iban.toUpperCase();
		if(iban.length() != 29) return false; 
		if(iban.charAt(0) > 90 && iban.charAt(0) < 65 && iban.charAt(1) > 90 && iban.charAt(1) < 65) return false; // los dos primeros valores que sean letras de la [A-Z]
		
		for(int i=2;i<iban.length();++i) {
			if (i == 4 || i == 9 || i==14 || i== 19 || i==24) {}
			else if(iban.charAt(i)< 48 || iban.charAt(i)> 57 ) return false; // del 0 al 9
		}
		return true;
	}
	
	
	public static boolean checkEmail(String email)  {
		
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	
}

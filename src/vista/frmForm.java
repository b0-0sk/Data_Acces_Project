package vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
public class frmForm extends JFrame implements ActionListener{

	private JMenuBar toolbar;
    private JMenu option;
    private JMenuItem client,command;
    public frmForm() {
    	
    	setLayout(null);
    	
        toolbar=new JMenuBar();
        setJMenuBar(toolbar);
        
        option=new JMenu("Options");
        toolbar.add(option);
        
        client=new JMenuItem("Clients");
        client.addActionListener(this);
        option.add(client);
        
        command=new JMenuItem("Commands");
        command.addActionListener(this);
        option.add(command);
        
        
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == client) {
			try {
				frmClients n= new frmClients(); //esto es para manejar la ventana se hace la variable que es la clase 
				//n.setSize(1920,1080); //tamaño de la ventana
				//n.setResizable(false);
				//n.setLocationRelativeTo(null); //hacer que este centrada en la pantalla
				//n.setVisible(true); 	
				
				n.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				n.setUndecorated(true);
				n.setVisible(true);
				n.setResizable(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
		}else if (e.getSource() == command ) {
			
			try {
				frmCommands n= new frmCommands(); //esto es para manejar la ventana se hace la variable que es la clase 
				
				//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				n.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				
				//n.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				//n.setUndecorated(false);
				n.setState(Frame.NORMAL);

				
				n.setVisible(true);
				//n.setResizable(true);

				//n.setSize(1920,1080); //tamaño de la ventana
				//n.setLocationRelativeTo(null); //hacer que este centrada en la pantalla
				//n.setVisible(true); 	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
         
    }
}
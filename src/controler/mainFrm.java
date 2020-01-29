package controler;

import javax.swing.JFrame;

import vista.frmForm;

public class mainFrm {
	
    public static void main(String[] ar) {
    	
    	frmForm formulario1 = new frmForm();
        formulario1.setSize(800,600); //tamaño de la ventana
        formulario1.setVisible(true); //que se visible
        formulario1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //instruccion de cierre        
        formulario1.setLocationRelativeTo(null); //hacer que este centrada en la pantalla
        
    } 

}

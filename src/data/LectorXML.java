package data;

import model.CommandLine;
import model.Command;

import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class LectorXML {
	
	public Integer units;
	
	public ArrayList<Command> commands = new ArrayList<Command>();

	
	
   public LectorXML(String nDocument) {

      try {
    	      	 
         File inputFile = new File(nDocument);
         
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);         
         doc.getDocumentElement().normalize();	
		
         
         NodeList nListCommands = doc.getElementsByTagName("comanda");

         for (int temp1 = 0; temp1 < nListCommands.getLength(); temp1++) {
           ArrayList<CommandLine> commandLine = new ArrayList<CommandLine>();
        	 
           Node node = nListCommands.item(temp1);
           
           NodeList nListCommand = node.getChildNodes();
           
           for (int temp2 = 0; temp2 < node.getChildNodes().getLength(); temp2++) {
        	   if (nListCommand.item(temp2).getNodeType() == Node.ELEMENT_NODE) {
        		   Element eElement = (Element) nListCommand.item(temp2);
        		   String n= "";
        		   n= Integer.toString((temp2+1)/2);
        		   
				commandLine.add(new CommandLine(
						   (node.getAttributes().item(1).getTextContent() + n)
        				   ,node.getAttributes().item(1).getTextContent()
        				   ,Integer.parseInt(eElement.getElementsByTagName("units").item(0).getTextContent())
        				   ,0
        				   ,Double.parseDouble(eElement.getElementsByTagName("unitPrice").item(0).getTextContent())
        				   ,eElement.getElementsByTagName("status").item(0).getTextContent()));
        		   units = temp2;
        		   
      
        	   }
           }
           
           if (node.getAttributes().item(2).getTextContent().charAt(0) == '0') { // NO READ COMMANDS
        	  
        	   commands.add(new Command(
        			   node.getAttributes().item(1).getTextContent() // id_client
        			   ,node.getAttributes().item(0).getTextContent() // id_command
        			   ,node.getAttributes().item(2).getTextContent() //status
        			   ,commandLine 
        			   ));
        	   
           }else {
        	   commands.add(new Command(
        			   node.getAttributes().item(1).getTextContent() // id_client
        			   ,node.getAttributes().item(0).getTextContent() // id_command
        			   ,node.getAttributes().item(2).getTextContent() //status
        			   ,commandLine 
        			   ));
        	   //System.out.println("COMMANDA YA LEIDA");
        	   
        	   
        	 
        	   

           }
		}	  
         
      } catch (Exception e) {
 			JOptionPane.showConfirmDialog(null, "ERROR: READED COMMAND | COMMANDS FILE NAME: "+ nDocument+" "+e.getMessage() , "Warning!" , JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
      }
   }
	   
   //LISTA COMANDAdate
 	public ArrayList<Command> getCommands() {
 	return commands;
 	}
 	
 	public void setCommands(ArrayList<Command> commands) {
 		this.commands = commands;
 	}
    
	
	
	



   

}
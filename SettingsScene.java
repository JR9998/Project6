//John R. & Matthew I.
import java.io.*;
import javafx.stage.FileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SettingsScene{
	private final String name = "Settings.xml";
	private TextField word = new TextField();
	private TextField color = new TextField();
	private File editFile;
	
	public void set(Stage stage) { 
		VBox root = new VBox();
		Label title = new Label("Settings");
		word.setPromptText("Enter your word");
		color.setPromptText("Set your color");
		Button save = new Button("Save");
		save.setOnAction(e -> saveSettings());
		Button load = new Button("Load");
		load.setOnAction(e -> loadSettings());
		Button back = new Button("Back"); 
		back.setOnAction(e -> { 
			 StartingScene startingScene = new StartingScene();
	         startingScene.start(stage);
		});
		
		root.getChildren().addAll(title, word, color, save,load, back);
		Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
	}//end of set method 
	
	public void saveSettings() { 
		FileChooser fileChooser = new FileChooser();
		if (editFile == null) { 
			fileChooser.setInitialFileName(name);
		}
		else { 
			fileChooser.setInitialFileName(name);
		}
		try (PrintWriter outgoing = new PrintWriter(new FileWriter(name))) {
			outgoing.println("<PICTIONARY>");
			outgoing.println("   <SETTINGS>");
			outgoing.println("      <WORD>" + word.getText() + "</WORD>");
			outgoing.println("      <COLOR>" + color.getText() + "</COLOR>");
			outgoing.println("   </SETTINGS>");
			outgoing.println("</PICTIONARY>");
		}
		catch(IOException e) { 
			System.out.println("ERROR: " + e);
		}
		/*
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 try { 
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 Document doc = db.newDocument();
			 Element root = doc.createElement("PICTIONARY");//creates the <PICTIONARY> root 
			 doc.appendChild(root);
			 Element settings = doc.createElement("SETTINGS"); //creates the <SETTINGS> part 
			 root.appendChild(settings);
			 Element currentWord = doc.createElement("WORD");//creates the <WORD> part
			 currentWord.setTextContent(word.getText()); //gets the word from the field and write it in the file 
			 settings.appendChild(currentWord);
			 Element currentColor = doc.createElement("COLOR");//creates the <COLOR> part 
			 currentColor.setTextContent(color.getText());//gets the color from the field and writes it in the file
			 settings.appendChild(currentColor);
			 //Writes the actual XML file:
			 TransformerFactory tf = TransformerFactory.newInstance();
			 Transformer t = tf.newTransformer();
			 DOMSource source = new DOMSource(doc);
			 StreamResult result = new StreamResult(new File(name));
			 t.transform(source, result);
		 }
		 catch(Exception e) { 
			 e.printStackTrace();
		 }
		 */
	}//end of saveSettings method 
	
	public void loadSettings() { 
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      try {
	          DocumentBuilder db = dbf.newDocumentBuilder();
	          Document doc = db.parse(new File(name));
	          NodeList list = doc.getElementsByTagName("SETTINGS");
	          for (int temp = 0; temp < list.getLength(); temp++) {
	              Node node = list.item(temp);
	              if (node.getNodeType() == Node.ELEMENT_NODE) {
	                  Element element = (Element) node;
	                  String currentword = element.getElementsByTagName("WORD").item(0).getTextContent().trim();
	                  String currentcolor = element.getElementsByTagName("COLOR").item(0).getTextContent().trim();
	                  word.setText(currentword);
	                  color.setText(currentcolor);
	              }
	          }
	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }
	}//end of loadSettings method
	
	public String getWord(String currentword) { 
		return currentword;
	}
	
	public String getColor(String currentcolor) { 
		return currentcolor;
	}
	
}//end of class

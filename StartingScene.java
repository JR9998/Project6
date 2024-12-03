//John R. & Matthew I.
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;

public class StartingScene extends Application {
	SettingsScene setting = new SettingsScene();
	BufferedReader incoming; 
	PrintWriter outgoing; 
	private static final int LISTENING_PORT = 32007;
	public static void main(String[] args) { 
		launch(args);
	}//end of main method 
	
	public void start(Stage stage) { 
		HBox root = new HBox();
		Button start = new Button("Start");
		Button settings = new Button("Settings");
		settings.setOnAction(e -> setting.set(stage));
		start.setOnAction(e -> connect(stage));
		root.getChildren().addAll(start, settings);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 300,400);
		stage.setScene(scene); 
		stage.setTitle("Pictionary");
		stage.show();
	}//end of start method
	public void connect(Stage stage) { 
		try { 
		String host = "localhost";
		Socket connection = new Socket(host, LISTENING_PORT);
		outgoing = new PrintWriter(connection.getOutputStream(), true);
		incoming  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = incoming.readLine();
		boolean isPlayer1 = response.contains("Player 1");
		PrimaryScene primaryScene = new PrimaryScene();
		primaryScene.set(stage, isPlayer1, isPlayer1 ? "Horse" : null , incoming, outgoing);
		System.out.println("Recieved: " + response);
		} 
		catch(IOException e) { 
			System.out.println("ERROR:" + e);
		}
	}//end of connect method
	
}//end of class

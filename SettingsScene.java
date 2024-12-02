//John R. & Matthew I.
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SettingsScene{
	public void set(Stage stage) { 
		VBox root = new VBox();
		Label title = new Label("Settings");
		TextField word = new TextField();
		word.setPromptText("Enter your word");
		TextField color = new TextField();
		color.setPromptText("Set your color");
		Button save = new Button("Save");
		save.setOnAction(e -> nothing());
		Button load = new Button("Load");
		load.setOnAction(e -> nothing());
		Button back = new Button("Back"); 
		back.setOnAction(e -> { 
			 StartingScene startingScene = new StartingScene();
	         startingScene.start(stage);
		});
		
		root.getChildren().addAll(title, word, color, save,load, back);
		Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
	}
	
	public void nothing() { 
		System.out.println("Nothing");
	}
}

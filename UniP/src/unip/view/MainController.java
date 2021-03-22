package unip.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import unip.Main;
import unip.controller.Reiter;
import unip.controller.ToDoReiter;

public class MainController extends Application {
	private int aktuellerReiter;
	private Node[] reiter;
	
	public static void run(String[] args) {
		launch(args);
	}
	
	public void navigationListener(ActionEvent event) {
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Main.mainController = this;
			reiter = new Node[5];
			reiter[4] = FXMLLoader.load(getClass().getResource("ToDoGUI.fxml"));
			
			BorderPane border = (BorderPane)FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
			HBox navigation = (HBox)FXMLLoader.load(getClass().getResource("Navigation.fxml"));
			border.setCenter(reiter[4]);
			border.setTop(navigation);
			Scene scene = new Scene(border,1280,720);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

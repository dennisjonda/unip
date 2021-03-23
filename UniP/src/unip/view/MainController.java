package unip.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import unip.Main;

public class MainController extends Application {
	private int aktuellerReiter;
	private Node[] reiter;
	
	@FXML
	private HBox navigation;
	
	@FXML
	private void initialize() {
			reiter = new Node[5];
			try {		
			reiter[2]= (Node) FXMLLoader.load(getClass().getResource("StundenplanGUI.fxml"));
			reiter[4]= (Node) FXMLLoader.load(getClass().getResource("ToDoGUI.fxml"));
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void run(String[] args) {
		launch(args);
	}
	
	public void navigationListener(ActionEvent event) {
		Button button = (Button) event.getSource();
		switch (button.getId()) {
		case "stundenplan":
			aktuellerReiter=2;
			break;
		case "todo":
			aktuellerReiter=4;
			break;
		}
		((BorderPane) button.getScene().getRoot()).setCenter(reiter[aktuellerReiter]);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Main.mainController = this;
			
			BorderPane border = (BorderPane)FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
			HBox navigation = (HBox)FXMLLoader.load(getClass().getResource("Navigation.fxml"));
			border.setTop(navigation);
			Scene scene = new Scene(border,1280,720);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

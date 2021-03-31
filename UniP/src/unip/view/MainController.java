package unip.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import unip.UniP;
import unip.controller.Reiter;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class MainController extends Application {
	private int aktuellerReiter;
	private Node[] reiter;
	public ArrayList<Reiter> controller = new ArrayList<Reiter>();
	
	@FXML
	private HBox navigation;
	
	@FXML
	private AnchorPane root;
	
	@FXML
	private void initialize() {
		UniP.mainController = this;
		reiter = new Node[5];
		try {	
		reiter[0]= (Node) FXMLLoader.load(getClass().getResource("KalenderGUI.fxml"));
		reiter[1]= (Node) FXMLLoader.load(getClass().getResource("StundenplanGUI.fxml"));
		reiter[2]= (Node) FXMLLoader.load(getClass().getResource("ModulGUI.fxml"));
		reiter[3]= (Node) FXMLLoader.load(getClass().getResource("ToDoGUI.fxml"));
		reiter[4]= (Node) FXMLLoader.load(getClass().getResource("NotenGUI.fxml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerController(Reiter controller) {
		if(!this.controller.contains(controller)) {
			this.controller.add(controller);
		}
	}
	
	public static void run(String[] args) {
		launch(args);
	}
	
	public void navigationListener(ActionEvent event) {
		Button button = (Button) event.getSource();
		switch (button.getId()) {
		case "kalender":
			aktuellerReiter=0;
			break;
		case "stundenplan":
			aktuellerReiter=1;
			break;
		case "module":
			aktuellerReiter=2;
			break;
		case "todo":
			aktuellerReiter=3;
			break;
		case "noten":
			aktuellerReiter=4;
			break;
		}
		
		for(int i=0;i<controller.size();i++) {
			controller.get(i).update();
		}					
		UniP.datenmanager.saveAll();
		
		((BorderPane) button.getScene().getRoot()).setCenter(reiter[aktuellerReiter]);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {	

			BorderPane border = (BorderPane)FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
			HBox navigation = (HBox)FXMLLoader.load(getClass().getResource("Navigation.fxml"));
			border.setTop(navigation);
			Scene scene = new Scene(border,1280,720);
			scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());   
			primaryStage.setTitle("UniP");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent arg0) {
					ArrayList<Reiter> controller = UniP.mainController.controller;
					for(int i=0;i<controller.size();i++) {
						controller.get(i).update();
					}					
					UniP.datenmanager.saveAll();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void loadSplashScreen() 
	{
		try {
		StackPane pane = FXMLLoader.load(getClass().getResource(("SplashGUI.FXML")));
		root.getChildren().setAll(pane);
		
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);
		
		fadeIn.play();
		
		} catch (IOException ex) {
			Logger.getLogger(SplashGUIController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}



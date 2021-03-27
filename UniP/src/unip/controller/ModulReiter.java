package unip.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModulReiter extends Reiter {

	@FXML
	private HBox semester;
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		int anzSem = 1;
		for(int i=1; i<=anzSem;i++) {
			addSemesterListener(null);			
		}
		
	}
	
	public void addSemesterListener(ActionEvent event) {
		Separator separator = new Separator(Orientation.VERTICAL);
		separator.setPadding(new Insets(0, 3, 0, 0));			
		semester.getChildren().add(separator);
		
		BorderPane border = new BorderPane();
		semester.getChildren().add(border);
		
		VBox vbox = new VBox();
		setDropTarget(vbox);
		vbox.setPadding(new Insets(30,0,0,0));
		vbox.setSpacing(5);
		BorderPane.setAlignment(vbox, Pos.CENTER);
		border.setCenter(vbox);
		
		Text semCap = new Text();
		semCap.setText((semester.getChildrenUnmodifiable().size()/2) + ".Semester");
		BorderPane.setAlignment(semCap, Pos.CENTER);
		border.setTop(semCap);
		
		Button AddBtn = new Button("Modul\nhinzufügen");
		AddBtn.setTextAlignment(TextAlignment.CENTER);
		BorderPane.setAlignment(AddBtn, Pos.CENTER);
		AddBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {				
				try {
					VBox content = (VBox) FXMLLoader.load(new File("src/unip/view/ModulPopUp.fxml").toURI().toURL());
			        
			        final Stage dialog = new Stage();
			        dialog.setTitle("Modul hinzufügen");
			        dialog.initModality(Modality.APPLICATION_MODAL);
			        dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
			        
			        
			        
			        
			        
			        Button abbrechenBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(0);
			        Button speichernBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(1);
			        
			        abbrechenBtn.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							dialog.close();							
						}
					});
			        
			        speichernBtn.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg) {
							String kuerzel = ((TextField) content.getChildren().get(3)).getText();
							String name = ((TextField) content.getChildren().get(5)).getText();	
							String prüfungsform = ((TextField) content.getChildren().get(7)).getText();	
							String prüfungsvoraussetzung = ((TextField) content.getChildren().get(9)).getText();	
							String cpr = ((TextField) content.getChildren().get(11)).getText();	
							String note = ((TextField) content.getChildren().get(13)).getText();
							
							StackPane modul = new StackPane(); //Eigentliches Modul Anzeigen
							try {
								modul = (StackPane) FXMLLoader.load(new File("src/unip/view/ModulBlock.fxml").toURI().toURL());
								((Text) modul.getChildren().get(0)).setText(kuerzel);
							} catch (Exception e) {
								e.printStackTrace();
							}			
							setDragable(modul);
							
							modul.setOnMouseClicked(new EventHandler<MouseEvent>() {

								@Override
								public void handle(MouseEvent arg0) {
									try {
										VBox content = (VBox) FXMLLoader.load(new File("src/unip/view/ModulPopUp.fxml").toURI().toURL());
								        
								        final Stage dialog = new Stage();
								        dialog.setTitle("Modul ändern");
								        dialog.initModality(Modality.APPLICATION_MODAL);
								        dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
								        
										String kuerzel = ((Text) ((StackPane) arg0.getSource()).getChildren().get(0)).getText();								        
										((TextField) content.getChildren().get(3)).setText(kuerzel);
										((Text) content.getChildren().get(0)).setText("Modul ändern");
										
								        Button loeschenBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(0);
								        Button speichernBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(1);
								        
								        loeschenBtn.setText("Löschen");
								        loeschenBtn.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												StackPane source = (StackPane) arg0.getSource();
												((VBox) source.getParent()).getChildren().remove(source);
												dialog.close();							
											}
										});
								        
								        speichernBtn.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent arg) {
												String kuerzel = ((TextField) content.getChildren().get(3)).getText();
												String name = ((TextField) content.getChildren().get(5)).getText();	
												String prüfungsform = ((TextField) content.getChildren().get(7)).getText();	
												String prüfungsvoraussetzung = ((TextField) content.getChildren().get(9)).getText();	
												String cpr = ((TextField) content.getChildren().get(11)).getText();	
												String note = ((TextField) content.getChildren().get(13)).getText();
												
												((Text) ((StackPane) arg0.getSource()).getChildren().get(0)).setText(kuerzel);
												
												dialog.close();
											}
										});
								        
								        Scene dialogScene = new Scene(content);
								        dialog.setScene(dialogScene);
								        dialog.show();
									} catch(Exception e) {
										e.printStackTrace();
									}
									
								}
							});
							
							((VBox) ((BorderPane) ((Button) event.getSource()).getParent()).getCenter()).getChildren().add(modul);	
							
							dialog.close();
						}
					});
			        
			        Scene dialogScene = new Scene(content);
			        dialog.setScene(dialogScene);
			        dialog.show();
				} catch(Exception e) {
					e.printStackTrace();
				}							
			}
		});
		border.setBottom(AddBtn);
	}
	
	private void setDragable(StackPane modul) {
		modul.setOnDragDetected((MouseEvent event) -> {
            Dragboard db = modul.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString(((Text) modul.getChildrenUnmodifiable().get(0)).getText());
            db.setContent(content);
        });
	}
	
	private void setDropTarget(VBox sem) {
		sem.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if(event.getGestureSource() instanceof StackPane && event.getDragboard().hasString()) {
					VBox target = ((VBox) event.getSource());
					StackPane modul = ((StackPane) event.getGestureSource());
					
					((VBox) modul.getParent()).getChildren().remove(modul);
					target.getChildren().add(modul);
				}
				
				event.consume();
			}		
		});
		
		sem.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if(event.getGestureSource() instanceof StackPane && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}				
			}		
		});
		
		sem.setOnDragEntered(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if(event.getGestureSource() instanceof StackPane && event.getDragboard().hasString()) {
					((VBox) event.getSource()).setStyle("-fx-background-color: lightgreen;");
				}
			}		
		});
		
		sem.setOnDragExited(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				((VBox) event.getSource()).setStyle("");				
			}		
		});
	}

}

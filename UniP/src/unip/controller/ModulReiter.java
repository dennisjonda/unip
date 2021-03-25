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
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
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

public class ModulReiter extends Reiter {

	@FXML
	private HBox semester;
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		int anzSem = 5;
		for(int i=1; i<=anzSem;i++) {
			
			Separator separator = new Separator(Orientation.VERTICAL);
			separator.setPadding(new Insets(0, 3, 0, 0));			
			semester.getChildren().add(separator);

			BorderPane border = new BorderPane();
			semester.getChildren().add(border);
			
			VBox vbox = new VBox();
			setDropTarget(vbox);
			vbox.setPadding(new Insets(30,0,0,0));
			vbox.setSpacing(5);
			
			Text semCap = new Text();
			semCap.setText(i + ".Semester");
			BorderPane.setAlignment(semCap, Pos.CENTER);
			border.setTop(semCap);
			
			StackPane modul = new StackPane();
			try {
				modul = (StackPane) FXMLLoader.load(new File("src/unip/view/ModulBlock.fxml").toURI().toURL());
				((Text) modul.getChildren().get(0)).setText("Modul" + i);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			setDragable(modul);
			vbox.getChildren().add(modul);
			
			BorderPane.setAlignment(vbox, Pos.CENTER);
			border.setCenter(vbox);
			
			Button AddBtn = new Button("Modul\nhinzufügen");
			AddBtn.setTextAlignment(TextAlignment.CENTER);
			BorderPane.setAlignment(AddBtn, Pos.CENTER);
			AddBtn.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			border.setBottom(AddBtn);
			
			
		}
		
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

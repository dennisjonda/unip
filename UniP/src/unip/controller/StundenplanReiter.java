package unip.controller;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StundenplanReiter extends Reiter {

	@FXML
	private GridPane grid;
	
	public void loeschenListener(ActionEvent event) {
		try {
			BorderPane content = (BorderPane) FXMLLoader.load(new File("src/unip/view/LoeschenPopUp.fxml").toURI().toURL());
	        
	        final Stage dialog = new Stage();
	        dialog.setTitle("Löschen");
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
	        
	        Button loeschenBtn = (Button) ((HBox) content.getCenter()).getChildren().get(0);
	        Button abbrechenBtn = (Button) ((HBox) content.getCenter()).getChildren().get(1);
	        
	        loeschenBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					for(Node node : grid.getChildren()) {
						if(node instanceof StackPane) {
							((StackPane) node).getChildren().clear();
						}
					}
					dialog.close();
				}
	        });
	        
	        abbrechenBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
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

	public void stundenplanListener(MouseEvent event) {
		boolean neu = ((StackPane)event.getSource()).getChildrenUnmodifiable().isEmpty();
		final Stage dialog = new Stage();
		dialog.setTitle("Eintrag");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
        
        try {            	
            VBox content = (VBox) FXMLLoader.load(new File("src/unip/view/EintragPopUpGUI.fxml").toURI().toURL());
            
            ObservableList<Node> popUpItems = content.getChildren();
            
            if(!neu) {
        		((Text) popUpItems.get(0)).setText("Eintrag ändern:");
        		((TextField) popUpItems.get(5)).setText(((Text) ((StackPane) event.getSource()).getChildren().get(0)).getText());
        	}
            
            ChoiceBox<String> choiceBox = (ChoiceBox<String>) popUpItems.get(3);
            String[] module = {"Sonstiges", "Modul1", "Modul2"};
			choiceBox.setItems(FXCollections.observableArrayList(module));
			choiceBox.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					if(((ChoiceBox<String>) event.getSource()).getSelectionModel().getSelectedIndex()!=0) {
						TextField bezTxt = (TextField) popUpItems.get(5);
						bezTxt.setText(((ChoiceBox<String>) event.getSource()).getSelectionModel().getSelectedItem());
					}
				}
			});
			
			GridPane grid = (GridPane) ((Node) event.getSource()).getParent();
			int col = grid.getColumnIndex((Node) event.getSource());
			int row = grid.getRowIndex((Node) event.getSource());
			
            ChoiceBox<String> choiceBox2 = (ChoiceBox<String>) popUpItems.get(8);
            String[] tage = new String[5];
            for(int i=1;i<=5;i++) {
            	tage[i-1] = numberToDay(i);
            }
			choiceBox2.setItems(FXCollections.observableArrayList(tage));
			choiceBox2.getSelectionModel().select(col-1);
			
            ChoiceBox<String> choiceBox3 = (ChoiceBox<String>) popUpItems.get(10);
            String[] block = new String[grid.getRowCount()-1];
            for(int i=0; i<block.length;i++) {
            	block[i]=(i+1)+"";
            }
			choiceBox3.setItems(FXCollections.observableArrayList(block));
			choiceBox3.getSelectionModel().select(row-1);
			
			if(!neu) {
				choiceBox2.setDisable(true);
				choiceBox3.setDisable(true);
			}
			
            
			Button loeschenBtn = (Button)((HBox) popUpItems.get(popUpItems.size()-1)).getChildren().get(0);
			if(neu) {
				loeschenBtn.setText("Abbrechen");
			}
			loeschenBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if(neu) {
						dialog.close();
					} else {
						((StackPane)event.getSource()).getChildren().clear();
						dialog.close();
					}
				}				
			});
			
			Button speichernBtn = (Button)((HBox) popUpItems.get(popUpItems.size()-1)).getChildren().get(1);
			speichernBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if(((TextField) popUpItems.get(5)).getText().isEmpty()) {
						((TextField) popUpItems.get(5)).requestFocus();
						return;
					}
					if(neu) {
						for (Node node : grid.getChildren()) {
					        if(grid.getRowIndex(node) == choiceBox3.getSelectionModel().getSelectedIndex()+1 && grid.getColumnIndex(node) == choiceBox2.getSelectionModel().getSelectedIndex()+1) {
					            ((StackPane) node).getChildren().add(new Text(((TextField) popUpItems.get(5)).getText()));
					            break;
					        }
					    }
					} else {
						for (Node node : grid.getChildren()) {
					        if(grid.getRowIndex(node) == choiceBox3.getSelectionModel().getSelectedIndex()+1 && grid.getColumnIndex(node) == choiceBox2.getSelectionModel().getSelectedIndex()+1) {
					            ((Text) ((StackPane) node).getChildren().get(0)).setText(((TextField) popUpItems.get(5)).getText());
					            break;
					        }
					    }							
					}
					dialog.close();
				}
			});
            
            Scene dialogScene = new Scene(content, 200, 300);
            dialog.setScene(dialogScene);
            dialog.show();
        } catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	
	private String numberToDay(int tag) {
		switch (tag) {
		case 1:
			return "Montag";
		case 2:
			return "Dienstag";
		case 3:
			return "Mittwoch";
		case 4:
			return "Donnerstag";
		case 5:
			return "Freitag";

		default:
			return "";
		}
	}
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}

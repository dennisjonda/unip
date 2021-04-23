package unip.controller;

import java.io.File;
import java.util.ArrayList;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unip.UniP;
import unip.model.*;

public class StundenplanReiter extends Reiter {

	@FXML
	private GridPane grid;
	@FXML
	private VBox semester;
	
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
					UniP.datenmanager.getStundenplan().clear();
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
            int semNmb = 1;
            ObservableList<Node> children = semester.getChildren();
            for(int i=0;i<children.size();i++) {
            	if(children.get(i) instanceof RadioButton && ((RadioButton) children.get(i)).isSelected()) {
            		semNmb = Integer.parseInt(((RadioButton) children.get(i)).getId());
            	}
            }
            
            ObservableList<Node> popUpItems = content.getChildren();
            ArrayList<Modul> modulArray = UniP.datenmanager.getModule(false);
            
            ArrayList<Modul> modulArray2 = new ArrayList<Modul>();
            for(int i=0;i<modulArray.size();i++) {
            	if(modulArray.get(i).semester == semNmb) {
            		modulArray2.add(modulArray.get(i));
            	}
            }
            
            if(!neu) {
        		((Text) popUpItems.get(0)).setText("Eintrag ändern:");
        		((TextField) popUpItems.get(5)).setText(((Text) ((StackPane) event.getSource()).getChildren().get(0)).getText());
        	}
            
            ChoiceBox<String> choiceBox = (ChoiceBox<String>) popUpItems.get(3);
            String[] module = new String[modulArray2.size()];
            for(int i=0;i<module.length;i++) {
            	module[i] = modulArray2.get(i).modulbezeichnung;
            }
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
						UniP.datenmanager.removeStdEintrag(Integer.parseInt(((Text) ((StackPane)event.getSource()).getChildren().get(0)).getId()));
						((StackPane)event.getSource()).getChildren().clear();
						dialog.close();
					}
				}				
			});
			
			Button speichernBtn = (Button)((HBox) popUpItems.get(popUpItems.size()-1)).getChildren().get(1);
			speichernBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if(((TextField) popUpItems.get(5)).getText().isEmpty() || ((TextField) popUpItems.get(5)).getText().length()>30) {
						((TextField) popUpItems.get(5)).requestFocus();
						return;
					}
					if(neu) {
						for (Node node : grid.getChildren()) {
					        if(grid.getRowIndex(node) == choiceBox3.getSelectionModel().getSelectedIndex()+1 && grid.getColumnIndex(node) == choiceBox2.getSelectionModel().getSelectedIndex()+1) {
					            ((StackPane) node).getChildren().add(new Text(((TextField) popUpItems.get(5)).getText()));
					            Stundenplaneintrag eintrag = new Stundenplaneintrag(((TextField) popUpItems.get(5)).getText(), grid.getColumnIndex(node), grid.getRowIndex(node));
					            ((Text) ((StackPane) node).getChildren().get(0)).setId(eintrag.getID() + "");
					            UniP.datenmanager.addStdEintrag(eintrag);
					            break;
					        }
					    }
					} else {
						for (Node node : grid.getChildren()) {
					        if(grid.getRowIndex(node) == choiceBox3.getSelectionModel().getSelectedIndex()+1 && grid.getColumnIndex(node) == choiceBox2.getSelectionModel().getSelectedIndex()+1) {
					            ((Text) ((StackPane) node).getChildren().get(0)).setText(((TextField) popUpItems.get(5)).getText());
					            UniP.datenmanager.changeStdEintrag(new Stundenplaneintrag(((TextField) popUpItems.get(5)).getText(), grid.getColumnIndex(node), grid.getRowIndex(node)));
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
	public void update() {
		ArrayList<Modul> module = UniP.datenmanager.getModule(false);
		
		semester.getChildren().clear();
		
		int anzSem = 1;
		for(int i=0;i<module.size();i++) {
			if(anzSem<module.get(i).semester) {
				anzSem=module.get(i).semester;
			}
		}
		
		ToggleGroup group = new ToggleGroup();
		for(int i=1;i<=anzSem;i++) {
			RadioButton semesterRadio = new RadioButton(i + ".Semester");
			semesterRadio.setId(i + "");
			semesterRadio.setToggleGroup(group);
			if(i==1) {
				semesterRadio.setSelected(true);
			}
			semester.getChildren().add(semesterRadio);
		}
		
	}

	@Override
	public void initialize() {
		UniP.mainController.registerController(this);
		ArrayList<Modul> module = UniP.datenmanager.getModule(false);
		
		int anzSem = 1;
		for(int i=0;i<module.size();i++) {
			if(anzSem<module.get(i).semester) {
				anzSem=module.get(i).semester;
			}
		}
		
		ToggleGroup group = new ToggleGroup();
		for(int i=1;i<=anzSem;i++) {
			RadioButton semesterRadio = new RadioButton(i + ".Semester");
			semesterRadio.setId(i + "");
			semesterRadio.setToggleGroup(group);
			if(i==1) {
				semesterRadio.setSelected(true);
			}
			semester.getChildren().add(semesterRadio);
		}
		
		ArrayList<Stundenplaneintrag> eintraege = UniP.datenmanager.getStundenplan();
		
		for(int i=0;i<eintraege.size();i++) {
			for (Node node : grid.getChildren()) {
		        if(grid.getRowIndex(node) == eintraege.get(i).getBlock() && grid.getColumnIndex(node) == eintraege.get(i).getTag()) {
		            ((StackPane) node).getChildren().add(new Text(eintraege.get(i).bezeichnung));
		            ((Text) ((StackPane) node).getChildren().get(0)).setId(eintraege.get(i).getID() + "");
		            break;
		        }
		    }
		}
	}

}

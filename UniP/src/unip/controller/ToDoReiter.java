package unip.controller;

import java.io.File;
import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ToDoReiter extends Reiter {
	
	@FXML
	private VBox vbox;
	
	@FXML
	private HBox hbox;
	
	public void loeschenListener(ActionEvent event) {
		ObservableList<Node> children = vbox.getChildren();
		for(int i=0; i<children.size();i++) {
			HBox parent = (HBox) children.get(i);
			CheckBox check = (CheckBox) ((HBox) children.get(i)).getChildren().get(1);
			
			if(check.isSelected()) {
				vbox.getChildren().remove(parent);
				i--;
			}
		}
		if(children.isEmpty()) {
			try {
			HBox hbox = (HBox) FXMLLoader.load(new File("src/unip/view/ToDoZeile.fxml").toURI().toURL());
			TextField textfld = (TextField) hbox.getChildren().get(0);
			
			textfld.focusedProperty().addListener(new ChangeListener<Boolean>()
			{
			    @Override
			    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			    {
			        if (!newPropertyValue)
			        {
			            textFieldListener(null);
			        }
			    }
			});
			
			vbox.getChildren().add(hbox);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkBoxListener(ActionEvent event) {

	}
	
	public void textFieldListener(ActionEvent event) {
		ObservableList<Node> fields = vbox.getChildrenUnmodifiable();
		try {
			boolean full = true;
			for(int i=0; i<fields.size();i++) {
				if(((HBox) fields.get(i)).getChildrenUnmodifiable().get(0) instanceof TextField) {
					if(((TextField)((HBox) fields.get(i)).getChildrenUnmodifiable().get(0)).getText().isEmpty()) {
						full=false;
					}
				}
			}
			if(full) {
				HBox hbox = (HBox) FXMLLoader.load(new File("src/unip/view/ToDoZeile.fxml").toURI().toURL());
				TextField textfld = (TextField) hbox.getChildren().get(0);
				
				textfld.focusedProperty().addListener(new ChangeListener<Boolean>()
				{
				    @Override
				    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
				    {
				        if (!newPropertyValue)
				        {
				            textFieldListener(null);
				        }
				    }
				});
				
				vbox.getChildren().add(hbox);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		ObservableList<Node> children = vbox.getChildren();
		for(int i=0; i<children.size();i++) {
			TextField textfld = (TextField) ((HBox) children.get(i)).getChildren().get(0);
			
			textfld.focusedProperty().addListener(new ChangeListener<Boolean>()
			{
			    @Override
			    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			    {
			        if (!newPropertyValue)
			        {
			            textFieldListener(null);
			        }
			    }
			});
		}
	}
}

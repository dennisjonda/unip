package unip.controller;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import unip.UniP;
import unip.model.Modul;

public class NotenReiter extends Reiter {

	@FXML
	private ScrollPane scroll;
	@FXML
	private GridPane grid;
	
	@Override
	public void update() {
		ObservableList<Node> children = grid.getChildren();
		ArrayList<Modul> module = UniP.datenmanager.getModule(false);
		int modul = 0;
		
		if((scroll.getParent()!=null)) {
			for(int i=0;i<children.size();i++) {
				if(children.get(i) instanceof TextField) {
					try{
						 double note = Double.parseDouble(((TextField) children.get(i)).getText());
						 
						 if(note>=1d && note<=4d) {
							 note*=10d;
							 note=Math.round(note);
							 note/=10d;
						 } else {
							 note=0d;
						 }
						 
						 module.get(modul).note = note;
					} catch(NumberFormatException e) {
						module.get(modul).note = 0d;
					}
					modul++;
				}
			}
		}
		
		
		for(int i=0;i<children.size();i++) {
			if(!(children.get(i) instanceof Group) && GridPane.getRowIndex(children.get(i))!=0) {
				children.remove(i);
				i--;
			}
		}
		
		ObservableList<RowConstraints> constraints = grid.getRowConstraints();
		for(int i=2; i<constraints.size();) {
			constraints.remove(i);
		}
		
		initialize();
	}

	@Override
	public void initialize() {
		UniP.mainController.registerController(this);
		
		ArrayList<Modul> module = UniP.datenmanager.getModule(false);
		
		for(int i=0;i<module.size();i++) {
			ObservableList<Node> children = grid.getChildren();
			int gridrows = grid.getRowCount();
			for(int c=0; c<children.size();c++) {
				if(!(children.get(c) instanceof Group) && GridPane.getRowIndex(children.get(c)) == gridrows-1) {
					GridPane.setRowIndex(children.get(c), gridrows);
				}
			}
			Text modul = new Text(module.get(i).modulbezeichnung);
			GridPane.setHalignment(modul, HPos.CENTER);
			double grade = module.get(i).note;
			TextField note;
			if(grade != 0d) {
				note = new TextField(grade + "");
			} else {
				note = new TextField("");
			}
			GridPane.setHalignment(note, HPos.CENTER);
			
			note.focusedProperty().addListener(new ChangeListener<Boolean>()
			{
			    @Override
			    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			    {
			        if (!newPropertyValue)
			        {
			        	update();
			        }
			    }
			});
			
			Text crp = new Text(module.get(i).crp + "");
			GridPane.setHalignment(crp, HPos.CENTER);
			
			grid.addRow(gridrows-1, modul, note, crp);
			grid.getRowConstraints().add(new RowConstraints(30));
		}
		
		double sumnote = 0d;
		int gesCrp = 0;
		int count = 0;
		
		for(int i=0;i<module.size();i++) {
			if(module.get(i).note!=0d) {
				sumnote+=module.get(i).note;
				gesCrp+=module.get(i).crp;
				count++;
			}
		}
		
		
		double avgNote = 0d;
		if(count!=0) {
			avgNote = sumnote / (double) count;
			
			avgNote*=10d;
			avgNote=Math.round(avgNote);
			avgNote/=10d;
		}
		
		Text gesTxt = new Text("Gesamt");
		Text gesNoteTxt = new Text(avgNote + "");
		Text gesCrpTxt = new Text(gesCrp + "");
		
		grid.add(gesTxt , 0, grid.getRowCount()-1);
		grid.add(gesNoteTxt, 1, grid.getRowCount()-1);
		grid.add(gesCrpTxt, 2, grid.getRowCount()-1);
		
		GridPane.setHalignment(gesTxt, HPos.CENTER);
		GridPane.setHalignment(gesNoteTxt, HPos.CENTER);
		GridPane.setHalignment(gesCrpTxt, HPos.CENTER);
	}

}

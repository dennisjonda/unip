package unip.controller;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class NotenReiter extends Reiter {

	@FXML
	private GridPane grid;
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		ArrayList<String> testmodule = new ArrayList<String>();
		ArrayList<String> testnoten = new ArrayList<String>();
		ArrayList<String> testcrp = new ArrayList<String>();
		
		for(int i=0; i<8; i++) {
			testmodule.add("Modul " + i);
			testnoten.add((int)(Math.random()*4+1) + "");
			testcrp.add((int)(Math.random()*16+1) + "");
		}
		
		for(int i=0;i<testmodule.size();i++) {
			ObservableList<Node> children = grid.getChildren();
			int gridrows = grid.getRowCount();
			for(int c=0; c<children.size();c++) {
				if(!(children.get(c) instanceof Group) && GridPane.getRowIndex(children.get(c)) == gridrows-1) {
					GridPane.setRowIndex(children.get(c), gridrows);
				}
			}
			Text modul = new Text(testmodule.get(i));
			GridPane.setHalignment(modul, HPos.CENTER);
			TextField note = new TextField(testnoten.get(i));
			GridPane.setHalignment(note, HPos.CENTER);
			Text crp = new Text(testcrp.get(i));
			GridPane.setHalignment(crp, HPos.CENTER);
			
			grid.addRow(gridrows-1, modul, note, crp);
			grid.getRowConstraints().add(new RowConstraints(30));
		}
		double sumnote = 0;
		double gesCrp = 0;
		for(int i=0;i<testmodule.size();i++) {
			sumnote+=Double.parseDouble(testnoten.get(i));
			gesCrp+=Double.parseDouble(testcrp.get(i));
		}
		
		double avgNote = sumnote/ (double)testnoten.size();
		
		grid.add(new Text(avgNote + ""), 1, grid.getRowCount()-1);
		grid.add(new Text(gesCrp + ""), 2, grid.getRowCount()-1);
	}

}

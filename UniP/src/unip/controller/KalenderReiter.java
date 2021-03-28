package unip.controller;

import java.util.Calendar;
import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class KalenderReiter extends Reiter{
	@FXML
	private GridPane kalender;
	@FXML
	private VBox alarmBox;
	@FXML
	private CheckBox stundenplanCheck;
	
	private Calendar startDate;	
	private boolean woche = true; //True wenn Wochenansicht; false wenn Monatsansicht
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		
		startDate = Calendar.getInstance();
		startDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		drawKalender();		
	}
	
	public void modusListener(ActionEvent event) {
		if(((Button) event.getSource()).getId().equals("wocheBtn")) {
			if(!woche) {
				woche=true;
				startDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				drawKalender();
			}
		} else if(((Button) event.getSource()).getId().equals("monatBtn")) {
			if(woche) {
				woche=false;
				startDate.set(Calendar.DAY_OF_MONTH, 1);
				drawKalender();
			}
		}
	}
	
	public void dateSwitchListener(ActionEvent event) {
		int multiplier = 1;
		if(((Button) event.getSource()).getId().equals("previousBtn")) {
			multiplier=-1;
		}
		if(woche) {
			startDate.add(Calendar.WEEK_OF_YEAR, multiplier);
		} else {
			startDate.add(Calendar.MONTH, multiplier);
		}
		drawKalender();
	}
	
	private void drawKalender() {
		kalender.getChildren().clear();
		kalender.getColumnConstraints().clear();
		kalender.getRowConstraints().clear();
		kalender.setGridLinesVisible(false);
		
		Calendar cal = (Calendar) startDate.clone();
		
		if(woche) { //Wochenansicht
			for(int x=0; x<8;x++) {
				for(int y=0;y<25;y++) {
					if(y==0) { //erste Zeile
						if(x!=0) {
							kalender.getColumnConstraints().add(new ColumnConstraints(150));
							
							String date = "";
							date += cal.get(Calendar.DAY_OF_MONTH) + ".";
							date += (cal.get(Calendar.MONTH)+1) + ".";
							date += cal.get(Calendar.YEAR);
							cal.add(Calendar.DAY_OF_YEAR, 1);
							
							Text text = new Text(numberToDay(x) + "\n" +  date);
							text.setTextAlignment(TextAlignment.CENTER);
							text.setFont(new Font(25));
							kalender.add(text, x, y);
						} else {
							kalender.getColumnConstraints().add(new ColumnConstraints(80));
						}
					} else if(x==0) { //erste Spalte
						if(y!=0 && y<=10) {
							Text text = new Text("0" + (y-1) + ":00");
							text.setFont(new Font(25));
							kalender.add(text, x, y);
						} else if(y!=0 && y>10) {
							Text text = new Text((y-1) + ":00");
							text.setFont(new Font(25));
							kalender.add(text, x, y);
						}
					}
					
				}
			}
		} else { //Monatsansicht
			for(int x=0; x<8;x++) {
				for(int y=0;;y++) {
					int day;
					if(cal.get(Calendar.DAY_OF_WEEK)>1) {
						day=cal.get(Calendar.DAY_OF_WEEK)-1;
					} else {
						day=7;
					}
					
					if(y==0) { //erste Zeile
						if(x!=0) {
							kalender.getColumnConstraints().add(new ColumnConstraints(150));
							
							Text text = new Text(numberToDay(x));
							text.setFont(new Font(25));
							kalender.add(text, x, y);
						} else {
							Text text = new Text(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.GERMAN) + "\n" + cal.get(Calendar.YEAR));
							text.setTextAlignment(TextAlignment.CENTER);
							text.setFont(new Font(18));
							kalender.add(text, x, y);
							
							kalender.getColumnConstraints().add(new ColumnConstraints(120));
							kalender.getRowConstraints().add(new RowConstraints(50));
						}
					} else if(x==0) { //erste Spalte
						if(y!=0) {
							Text text = new Text("Woche" + y);
							text.setFont(new Font(25));
							kalender.add(text, x, y);
							kalender.getRowConstraints().add(new RowConstraints(150));
						}
					} else {
						HBox hbox = new HBox();
						kalender.add(hbox, x, y);
						
						int nmb = 7*(y-1)+x-(day-1);
						
						if(nmb>=1 && nmb<=cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
							Text text = new Text(nmb + "");
							hbox.getChildren().add(text);
						}
					}
					if(y>(cal.getActualMaximum(Calendar.DAY_OF_MONTH)+day-1)/7) {
						break;
					}
				}
			}
		}
		
		ObservableList<Node> children = kalender.getChildren();
		for(int i=0; i<children.size();i++) {
			if(!(children.get(i) instanceof Group)) {
				GridPane.setMargin(children.get(i), new Insets(10, 10, 10, 10));
				GridPane.setHalignment(children.get(i), HPos.CENTER);
			}
		}
		
		kalender.setGridLinesVisible(true);
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
		case 6:
			return "Samstag";
		case 7:
			return "Sonntag";

		default:
			return "Invalid Day";
		}
	}
}

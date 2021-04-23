package unip.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unip.UniP;
import unip.model.Termin;
import unip.model.Termin.Eventtype;

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
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize() {
		UniP.mainController.registerController(this);
		
		startDate = Calendar.getInstance();
		startDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		drawKalender();		
	}
	
	private void terminListener(Node source, Calendar date) {
		try {
			VBox content = (VBox) FXMLLoader.load(new File("src/unip/view/TerminPopUp.fxml").toURI().toURL());
			boolean neu;
			if(source instanceof VBox) {
				neu=true;
			} else {
				neu=((StackPane) source).getChildrenUnmodifiable().size()==0;
			}
	        
	        final Stage dialog = new Stage();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.initOwner(source.getScene().getWindow());
	        
	        dialog.setTitle(date.get(Calendar.DAY_OF_MONTH) + "." + (date.get(Calendar.MONTH)+1) + "." + date.get(Calendar.YEAR));
	        
	        Text popUpTitel = (Text) content.getChildren().get(0);
	        TextField titel = (TextField) content.getChildren().get(3);
	        ChoiceBox<Integer> von = (ChoiceBox<Integer>) ((HBox) content.getChildren().get(4)).getChildren().get(1);
	        ChoiceBox<Integer> bis = (ChoiceBox<Integer>) ((HBox) content.getChildren().get(5)).getChildren().get(1);
	        ToggleGroup kategorie = (ToggleGroup) ((RadioButton) content.getChildren().get(8)).getToggleGroup();
	        TextArea beschreibung = (TextArea) content.getChildren().get(14);
	        Button abbruchBtn =  (Button) ((HBox) content.getChildren().get(15)).getChildren().get(0);
	        Button speichernBtn =  (Button) ((HBox) content.getChildren().get(15)).getChildren().get(1);
	        
	        Integer[] hours = new Integer[24];
	        for(int i=0;i<=23;i++) {
	        	hours[i] = i;
	        }
	        
	        von.setItems(FXCollections.observableArrayList(hours));
	        bis.setItems(FXCollections.observableArrayList(hours));
	        
	        if(neu) { //neuer Termin
		        if(woche) { 
		        	von.getSelectionModel().select(GridPane.getRowIndex(source)-1);
		        	bis.getSelectionModel().select(GridPane.getRowIndex(source)-1);
		        } else {
		        	von.getSelectionModel().select(0);
		        	bis.getSelectionModel().select(1);
		        }
		        
		        abbruchBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						dialog.close();						
					}		        	
				});
	        } else { //Termin ändern
	        	Termin termin = UniP.datenmanager.getTermin(Integer.parseInt(source.getId()));
	        	popUpTitel.setText("Termin ändern");
	        	
	        	titel.setText(termin.titel);
	        	von.getSelectionModel().select(termin.von);
	        	bis.getSelectionModel().select(termin.bis);
	        	kategorie.selectToggle(kategorie.getToggles().get(termin.kategorie.ordinal()));
	        	beschreibung.setText(termin.beschreibung);
	        	
	        	abbruchBtn.setText("Löschen");
	        	abbruchBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						UniP.datenmanager.removeTermin(termin.getID());
						drawKalender();
						dialog.close();
					}
				});
	        }
	        
	        speichernBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					int id = 0;
					if(neu) {
						id = UniP.datenmanager.getTerminID();
					} else {
						id = Integer.parseInt(source.getId());
					}
					Termin termin = new Termin(id, titel.getText(), beschreibung.getText(), von.getValue(), bis.getValue(), date, Termin.stringToEventType(((RadioButton) kategorie.getSelectedToggle()).getText()));
					if(UniP.datenmanager.isOpen(termin)) {
						if(von.getSelectionModel().getSelectedIndex()<=bis.getSelectionModel().getSelectedIndex()) {
							if(neu) {
								UniP.datenmanager.addTermin(termin);
							} else {
								UniP.datenmanager.changeTermin(termin);
							}
							drawKalender();
							dialog.close();
						} else {
							popUpTitel.setText("\"Von\" muss kleiner \"Bis\"");
							von.requestFocus();
						}
					} else {
						popUpTitel.setText("Zeitraum belegt");
						von.requestFocus();
					}
				}
			});
	        
	        Scene dialogScene = new Scene(content);
	        dialog.setScene(dialogScene);
	        dialog.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
			ArrayList<Termin> termine = UniP.datenmanager.getWoche(startDate);
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
							kalender.add(text, x, y);
						} else {
							kalender.getColumnConstraints().add(new ColumnConstraints(80));
						}
					} else if(x==0) { //erste Spalte
						if(y!=0 && y<=10) {
							Text text = new Text("0" + (y-1) + ":00");
							kalender.add(text, x, y);
						} else if(y!=0 && y>10) {
							Text text = new Text((y-1) + ":00");
							kalender.add(text, x, y);
						}
					} else { //Wochenzellen
						StackPane termin = new StackPane();
						kalender.add(termin, x, y);
						Calendar date = (Calendar)cal.clone();
						date.add(Calendar.DAY_OF_YEAR, -1);
						termin.setOnMouseClicked(new EventHandler<Event>() {
							@Override
							public void handle(Event arg0) {
								terminListener((Node) arg0.getSource(), date);											
							}
						});
						for(int i=0;i<termine.size();i++) {
							if(date.get(Calendar.DAY_OF_YEAR)==termine.get(i).datum.get(Calendar.DAY_OF_YEAR) && date.get(Calendar.YEAR)==termine.get(i).datum.get(Calendar.YEAR)) {
								if((y-1) == termine.get(i).von) {
									termin.setId(termine.get(i).getID() + "");
									termin.setStyle(getCategoryColor(termine.get(i).kategorie) + " -fx-border-color: #4a5c66");
									Text terminname = new Text(termine.get(i).titel);
									terminname.setFill(Color.WHITE);
									termin.getChildren().add(terminname);
									
									int length = termine.get(i).bis + 1 - termine.get(i).von;
									
									GridPane.setRowSpan(termin, length);
								}
							}
						}
					}					
				}
			}
		} else { //Monatsansicht
			ArrayList<Termin> termine = UniP.datenmanager.getMonat(startDate);
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
							kalender.add(text, x, y);
						} else {
							Text text = new Text(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.GERMAN) + "\n" + cal.get(Calendar.YEAR));
							text.setTextAlignment(TextAlignment.CENTER);
							kalender.add(text, x, y);
							
							kalender.getColumnConstraints().add(new ColumnConstraints(120));
							kalender.getRowConstraints().add(new RowConstraints(50));
						}
					} else if(x==0) { //erste Spalte
						if(y!=0) {
							Text text = new Text("Woche" + y);
							kalender.add(text, x, y);
							kalender.getRowConstraints().add(new RowConstraints(150));
						}
					} else {
						VBox vbox = new VBox();
						kalender.add(vbox, x, y);
						
						int nmb = 7*(y-1)+x-(day-1);
						
						if(nmb>=1 && nmb<=cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
							Calendar currentday = (Calendar) cal.clone();
							currentday.set(Calendar.DAY_OF_MONTH, nmb);
							Text text = new Text(nmb + "");
							vbox.getChildren().add(text);
							vbox.setOnMouseClicked(new EventHandler<Event>() {
								@Override
								public void handle(Event arg0) {
									terminListener((Node) arg0.getSource(), currentday);											
								}
							});
							
							for(int i=0;i<termine.size();i++) {
								if(cal.get(Calendar.MONTH)==termine.get(i).datum.get(Calendar.MONTH) && cal.get(Calendar.YEAR)==termine.get(i).datum.get(Calendar.YEAR)) {
									if(nmb == termine.get(i).datum.get(Calendar.DAY_OF_MONTH)) {
										StackPane termin = new StackPane();
										termin.setId(termine.get(i).getID() + "");
										termin.setStyle(getCategoryColor(termine.get(i).kategorie) + " -fx-border-color: #4a5c66");
										Text terminname = new Text(termine.get(i).titel);
										terminname.setFill(Color.WHITE);
										termin.getChildren().add(terminname);
										termin.setOnMouseClicked(new EventHandler<Event>() {
											@Override
											public void handle(Event arg0) {
												terminListener((Node) arg0.getSource(), currentday);	
												arg0.consume();
											}
										});
										vbox.getChildren().add(termin);
									}
								}
							}
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
		drawTimer();
	}
	
	private void drawTimer() {
		//Ausrechnen der Timer Daten
		Calendar heute = Calendar.getInstance();
		ArrayList<Termin> termine = new ArrayList<Termin>();
		ArrayList<Integer> zeit = new ArrayList<Integer>();
		
		ArrayList<Termin> tests = UniP.datenmanager.getTerminTyp(Eventtype.KLAUSUR, true);
		tests.addAll(UniP.datenmanager.getTerminTyp(Eventtype.TESTAT, true));
		
		for(int i=0; i<tests.size();i++) {
			int abstandtage = 0;
			if(tests.get(i).datum.get(Calendar.YEAR)==heute.get(Calendar.YEAR)) { // gleiches jahr
				abstandtage=tests.get(i).datum.get(Calendar.DAY_OF_YEAR)-heute.get(Calendar.DAY_OF_YEAR);
			} else {
				abstandtage+=heute.getActualMaximum(Calendar.DAY_OF_YEAR) - heute.get(Calendar.DAY_OF_YEAR); //Tage bis Jahres ende
				abstandtage+=(tests.get(i).datum.get(Calendar.YEAR) - heute.get(Calendar.YEAR) - 1)*365; // Ganze Jahre zwischen Terminen
				abstandtage+=tests.get(i).datum.get(Calendar.DAY_OF_YEAR);
			}
			if(abstandtage>99) { //keine Termine über 99Tage im Timer
				continue;
			}
			
			if(termine.size()!=0) { //nicht der erste Termin
				for(int t = 0;t<=termine.size();t++) {
					if(t<termine.size()) {
						if(abstandtage<zeit.get(t) || (abstandtage==zeit.get(t) && tests.get(i).von<termine.get(t).von)) { //wenn früher
							termine.add(t, tests.get(i));
							zeit.add(t, abstandtage);
							break;
						}
					} else { //wenn neuer Termin später als alle bisherigen
						termine.add(tests.get(i));
						zeit.add(abstandtage);
						break;
					}
				}
			} else {
				termine.add(tests.get(i));
				zeit.add(abstandtage);
			}
		}
		
		//Anzeigen der Timer
		alarmBox.getChildren().clear();
		
		for(int i=0;i<termine.size();i++) {
			BorderPane alarm = new BorderPane();
			alarm.setStyle(getCategoryColor(termine.get(i).kategorie) + "-fx-border-color: #4a5c66");
			
			Text abstand = new Text("noch " + zeit.get(i) + " Tage");
			Text titel = new Text(termine.get(i).titel);
			Text datum = new Text(termine.get(i).datum.get(Calendar.DAY_OF_MONTH) + "." + (termine.get(i).datum.get(Calendar.MONTH)+1) + "." + termine.get(i).datum.get(Calendar.YEAR));
			
			alarm.setTop(abstand);
			alarm.setCenter(titel);
			alarm.setBottom(datum);
			
			BorderPane.setMargin(abstand, new Insets(5, 10, 5, 10));
			BorderPane.setMargin(titel, new Insets(5, 10, 5, 10));
			BorderPane.setMargin(datum, new Insets(5, 10, 5, 10));
			
			alarmBox.getChildren().add(alarm);			
		}
	}
	
	private String getCategoryColor(Eventtype type) {
		switch(type) {
		case KLAUSUR:
			return "-fx-background-color: #215FBF;"; 
		case TESTAT:
			return "-fx-background-color: #D61C89;";
		case UNI:
			return "-fx-background-color: #E3A730;";
		case FREIZEIT:
			return "-fx-background-color: #36B02E;";
		default:
			return "";
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
		case 6:
			return "Samstag";
		case 7:
			return "Sonntag";

		default:
			return "Invalid Day";
		}
	}
}

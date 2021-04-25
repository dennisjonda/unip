package unip.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unip.UniP;
import unip.model.Modul;

public class ModulReiter extends Reiter {

	@FXML
	private HBox semester;
	
	@Override
	public void update() {
		ObservableList<Node> sem = semester.getChildren();
		
		for(int s=0;s<sem.size();s++) {
			if(sem.get(s) instanceof BorderPane) {
				ObservableList<Node> module = ((VBox) ((BorderPane) sem.get(s)).getCenter()).getChildren(); 
				
				for(int m=0;m<module.size();m++) {
					Modul modul = UniP.datenmanager.getModul(Integer.parseInt(((StackPane) module.get(m)).getId()));
					modul.semester = Integer.parseInt(sem.get(s).getId());
					
					if(semester.getParent().getParent()==null) {
						StackPane modulbox = (StackPane) module.get(m);
						if(modul.note==0d) {
							modulbox.setStyle("");
							setDragable(modulbox);
							modulbox.setStyle("-fx-border-color: Black;");
						} else {
							modulbox.setOnDragDetected(null);
							modulbox.setStyle("-fx-background-color: #80ba24");
						}
					}
				}
			}
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
		
		for(int i=1; i<=anzSem;i++) {
			addSemesterListener(null);			
		}
		
		for(int i=0; i<module.size();i++) {
			
			
			StackPane modul = new StackPane(); //Eigentliches Modul Anzeigen
			try {
				modul = (StackPane) FXMLLoader.load(new File("src/unip/view/ModulBlock.fxml").toURI().toURL());
				((Text) modul.getChildren().get(0)).setText(module.get(i).modulbezeichnung);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(module.get(i).note==0d) {
				setDragable(modul);
			} else {
				modul.setStyle("-fx-background-color: #80ba24");
			}
			
			modul.setId(module.get(i).getID() + "");
			
			modul.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					try {
						ArrayList<Modul> module = UniP.datenmanager.getModule(false);
						VBox content = (VBox) FXMLLoader.load(new File("src/unip/view/ModulPopUp.fxml").toURI().toURL());
				        
				        final Stage dialog = new Stage();
				        dialog.setTitle("Modul ändern");
				        dialog.initModality(Modality.APPLICATION_MODAL);
				        dialog.initOwner(semester.getScene().getWindow());
				        
						String kuerzel = ((Text) ((StackPane) arg0.getSource()).getChildren().get(0)).getText();								        
						((TextField) content.getChildren().get(3)).setText(kuerzel);
						((Text) content.getChildren().get(0)).setText("Modul ändern");
						
						int modulid = Integer.parseInt(((StackPane) arg0.getSource()).getId());
						
						
						
						((TextField) content.getChildren().get(3)).setText(UniP.datenmanager.getModul(modulid).modulbezeichnung);
						((TextField) content.getChildren().get(5)).setText(UniP.datenmanager.getModul(modulid).modulname);
						((TextField) content.getChildren().get(7)).setText(UniP.datenmanager.getModul(modulid).prüfungsform);
						((TextField) content.getChildren().get(9)).setText(UniP.datenmanager.getModul(modulid).prüfungsvoraussetzung);
						((TextField) content.getChildren().get(11)).setText(UniP.datenmanager.getModul(modulid).crp + "");
						((TextField) content.getChildren().get(13)).setText(UniP.datenmanager.getModul(modulid).note + "");
						
				        Button loeschenBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(0);
				        Button speichernBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(1);
				        
				        loeschenBtn.setText("Löschen");
				        loeschenBtn.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								StackPane source = (StackPane) arg0.getSource();
								UniP.datenmanager.removeModul(Integer.parseInt(source.getId()));
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
								int crp = 0;
								try { crp = Integer.parseInt(((TextField) content.getChildren().get(11)).getText());} catch(NumberFormatException e) {System.out.println("Wrong crp");};
								if(crp<0) {
									crp=0;
								}
								
								double note = 0d;
								try {note = Double.parseDouble(((TextField) content.getChildren().get(13)).getText());} catch(NumberFormatException e) {System.out.println("Wrong Grade");};
								if(note<1d || note>4d) {
									note = 0d;
								}
								note*=10d;
								note=Math.round(note);
								note/=10d;
								
								Text modultxt = ((Text) ((StackPane) arg0.getSource()).getChildren().get(0));
								int modulid = Integer.parseInt(((StackPane) modultxt.getParent()).getId());
								int semester = Integer.parseInt(((BorderPane) ((VBox) ((StackPane) modultxt.getParent()).getParent()).getParent()).getId());
								
								if(!kuerzel.isBlank() && kuerzel.length()<=10 && !name.isBlank() && name.length()<=30) {
									modultxt.setText(kuerzel);
									UniP.datenmanager.changeModul(new Modul(modulid, kuerzel, name, prüfungsform, prüfungsvoraussetzung, note, crp, semester));
									StackPane modul = (StackPane) modultxt.getParent();
									if(note==0d) {
										modul.setStyle("");
										setDragable(modul);
										modul.setStyle("-fx-border-color: Black;");
									} else {
										modul.setOnDragDetected(null);
										modul.setStyle("-fx-background-color: #80ba24");
									}
									dialog.close();	
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
			});
			
			ObservableList<Node> semesterchildren = semester.getChildren();
			for(int s=0;s<semesterchildren.size();s++) {
				if(semesterchildren.get(s) instanceof BorderPane && Integer.parseInt(semesterchildren.get(s).getId()) == module.get(i).semester) {
					((VBox) ((BorderPane) semesterchildren.get(s)).getCenter()).getChildren().add(modul);
					break;
				}
			}
			
			
		}
		
	}
	
	public void addSemesterListener(ActionEvent event) {
		Separator separator = new Separator(Orientation.VERTICAL);
		separator.setPadding(new Insets(0, 3, 0, 0));			
		semester.getChildren().add(separator);
		
		BorderPane border = new BorderPane();
		semester.getChildren().add(border);
		border.idProperty().set(((semester.getChildrenUnmodifiable().size()/2) + ""));
		
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
		AddBtn.getStyleClass().add("btn");
		AddBtn.getStyleClass().add("btn-success");
		AddBtn.getStyleClass().add("btn-sm");
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
							int crp = 0;
							try { crp = Integer.parseInt(((TextField) content.getChildren().get(11)).getText());} catch(NumberFormatException e) {System.out.println("Wrong crp");};
							if(crp<0) {
								crp=0;
							}
							
							double note = 0d;
							try {note = Double.parseDouble(((TextField) content.getChildren().get(13)).getText());} catch(NumberFormatException e) {System.out.println("Wrong Grade");};
							if(note<1d || note>4d) {
								note = 0d;
							}
							note*=10d;
							note=Math.round(note);
							note/=10d;
							
							if(!kuerzel.isBlank() && kuerzel.length()<=10 && !name.isBlank() && name.length()<=30) {
								int semester = Integer.parseInt(((BorderPane) ((Button) event.getSource()).getParent()).getId());
								int modulid = UniP.datenmanager.getModulID();
								
								StackPane modul = new StackPane(); //Eigentliches Modul Anzeigen
								try {
									modul = (StackPane) FXMLLoader.load(new File("src/unip/view/ModulBlock.fxml").toURI().toURL());
									((Text) modul.getChildren().get(0)).setText(kuerzel);
								} catch (Exception e) {
									e.printStackTrace();
								}
								if(note==0d) {
									setDragable(modul);
								} else {
									modul.setStyle("-fx-background-color: #80ba24");
								}
								modul.setId(modulid + "");
								
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
											ArrayList<Modul> module = UniP.datenmanager.getModule(false);
											
											((TextField) content.getChildren().get(3)).setText(UniP.datenmanager.getModul(modulid).modulbezeichnung);
											((TextField) content.getChildren().get(5)).setText(UniP.datenmanager.getModul(modulid).modulname);
											((TextField) content.getChildren().get(7)).setText(UniP.datenmanager.getModul(modulid).prüfungsform);
											((TextField) content.getChildren().get(9)).setText(UniP.datenmanager.getModul(modulid).prüfungsvoraussetzung);
											((TextField) content.getChildren().get(11)).setText(UniP.datenmanager.getModul(modulid).crp + "");
											((TextField) content.getChildren().get(13)).setText(UniP.datenmanager.getModul(modulid).note + "");
											
									        Button loeschenBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(0);
									        Button speichernBtn = (Button) ((HBox) content.getChildren().get(14)).getChildren().get(1);
									        
									        loeschenBtn.setText("Löschen");
									        loeschenBtn.setOnAction(new EventHandler<ActionEvent>() {
	
												@Override
												public void handle(ActionEvent event) {
													StackPane source = (StackPane) arg0.getSource();
													UniP.datenmanager.removeModul(Integer.parseInt(source.getId()));
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
													
													int crp = 0;
													try { crp = Integer.parseInt(((TextField) content.getChildren().get(11)).getText());} catch(NumberFormatException e) {System.out.println("Wrong crp");};
													if(crp<0) {
														crp=0;
													}
													
													double note = 0d;
													try {note = Double.parseDouble(((TextField) content.getChildren().get(13)).getText());} catch(NumberFormatException e) {System.out.println("Wrong Grade");};
													if(note<1d || note>4d) {
														note = 0d;
													}
													note*=10d;
													note=Math.round(note);
													note/=10d;
													
													Text modultxt = ((Text) ((StackPane) arg0.getSource()).getChildren().get(0));
													int modulid = Integer.parseInt(((StackPane) modultxt.getParent()).getId());
													int semester = Integer.parseInt(((BorderPane) ((VBox) ((StackPane) modultxt.getParent()).getParent()).getParent()).getId());
													
													if(!kuerzel.isBlank() && kuerzel.length()<=10 && !name.isBlank() && name.length()<=30) {
														modultxt.setText(kuerzel);
														
														StackPane modul = (StackPane) modultxt.getParent();
														if(note==0d) {
															modul.setStyle("");
															setDragable(modul);
															modul.setStyle("-fx-border-color: Black;");
														} else {
															modul.setOnDragDetected(null);
															modul.setStyle("-fx-background-color: #80ba24");
														}
														
														UniP.datenmanager.changeModul(new Modul(modulid, kuerzel, name, prüfungsform, prüfungsvoraussetzung, note, crp, semester));
														dialog.close();
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
								});
								
								
								UniP.datenmanager.addModul(new Modul(modulid, kuerzel, name, prüfungsform, prüfungsvoraussetzung, note, crp, semester));
								((VBox) ((BorderPane) ((Button) event.getSource()).getParent()).getCenter()).getChildren().add(modul);							
								dialog.close();
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
					UniP.datenmanager.getModul(Integer.parseInt(modul.getId())).semester = Integer.parseInt(((BorderPane) target.getParent()).getId());
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
					((VBox) event.getSource()).setStyle("-fx-background-color: #80ba24;");
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

package unip;

import java.time.LocalTime;
import java.util.Calendar;

import unip.model.Datenmanager;
import unip.model.Termin;
import unip.model.Termin.Eventtype;
import unip.view.MainController;

public class UniP {
	
	public static MainController mainController;
	public static Datenmanager datenmanager; 

	public static void main(String[] args) {
		datenmanager = new Datenmanager();
		try {
			Thread thread = new Thread(new Runnable() {				
				@Override
				public void run() {
					MainController.run(args);					
				}
			});
			thread.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

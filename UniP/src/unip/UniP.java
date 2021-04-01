package unip;

import unip.model.Datenmanager;
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

package unip.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Datenmanager {

	private String terminPfad;
	private String modulePfad;
	private String stundenplanPfad;
	private ArrayList<Termin> termine = new ArrayList<Termin>();
	private ArrayList<Modul> module = new ArrayList<Modul>();
	private ArrayList<Stundenplaneintrag> stundenplan = new ArrayList<Stundenplaneintrag>();
	private ArrayList<ToDoEintrag> todoliste = new ArrayList<ToDoEintrag>();
	

	public ArrayList<Termin> getWoche(Calendar von){
		ArrayList<Termin> termin2 = new ArrayList<Termin>;
		Calendar calendar = Calendar.getInstance();
		von.set(Calendar.DAY_OF_WEEK, 2);
		Calendar bis = (Calendar) von.clone();
		bis.add(Calendar.DAY_OF_YEAR, +6); 
		
		for(int i= 0; i<termine.size(); i++) {
			if(termine.get(i).von => von && termine.get(i).von <= bis) {
				termin2.add(termine.get(i));
			}
			return termin2;
		}
	}
	
	public ArrayList<Termin> getMonat(Calendar von){
		ArrayList<Termin> termin2 = new ArrayList<Termin>;
		Calendar calendar = Calendar.getInstance();
		von.set(Calendar.DAY_OF_MONTH, 1);
		Calendar bis = (Calendar) von.clone();
		bis = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for(int i= 0; i<termine.size(); i++) {
			if(termine.get(i).von => von && termine.get(i).von <= bis) {
				termin2.add(termine.get(i));
			}
			return termin2;
		}
	}
	
	public ArrayList<Termin> getTerminTyp (Eventtype type, boolean zukunft){
		
	}
	
	public ArrayList<Modul> getModule (boolean abgeschlossen){
		for(int i = 0; i<module.size(); i++) {
			if(module.get(i).note != 0) {
				return module.get(i);
			}
		}
		
	}
	
	public ArrayList<Stundenplaneintrag> getStundenplan() {
		return stundenplan;
	}
	
	public ArrayList<ToDoEintrag> getToDo(){
		return todoliste;
	}
	
	public void addTermin (Termin termin) {
		termine.add(termin);
	}
	
	public void changeTermin(Termin termin) {
		removeTermin(termin.getID());
		addTermin(termin);
	}
	
	public void removeTermin(int id) {
		for(int i=0; i<termine.size(); i++) {
			if(termine.get(i).getID == id) {
				termine.remove(i);
				break;
			}
		}
	}
	
	public void removeModul (int id) {
		for(int i = 0; i<module.size(); i++) {
			if(module.get(i).getID() == id ) {
				module.remove(i);
				break;
			}
		}
	}
	
	public void addStdEintrag (Stundenplaneintrag eintrag) {
		stundenplan.add(eintrag);
	}
	
	public void changeStdEintrag (Stundenplaneintrag eintrag) {
		addStdEintrag(eintrag);
		removeStdEintrag(eintrag);
	}
	
	public void removeStdEintrag (Stundenplaneintrag eintrag) {
		for(int i=0; i<stundenplan.size(); i++) {
			if(stundenplan.get(i).getID() == eintrag.getID()) {
				stundenplan.remove(i);
				break;
			}
		}
	
	}
	
	public void addToDo(ToDoEintrag eintrag) {
		todoliste.add(eintrag);
	}
	
	public void changeToDo(ToDoEintrag eintrag) {
		addToDo(eintrag);
		removeAbgeschlossen();
	}
	
	public boolean removeAbgeschlossen() {
		for(int i = 0; i<todoliste.size(); i++) {
			if(todoliste.get(i).getID() == todoliste.getID()) {
				todoliste.remove(i);
				break;
			}
		}
	}
	
	private void saveTermin() {
		
	}
	
	private void saveModule() {
		
	}
	
	private void saveStundenplan() {
		
	}
	
	private void saveToDo() {
		
	}
	
	public void saveAll() {
		
	}
	
	private void writeToFile(String[] text, String path) {
		
	}
	
	private String[] lines readFile(String path) {
		
	}
}

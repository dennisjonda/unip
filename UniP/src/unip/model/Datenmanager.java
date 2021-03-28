package unip.model;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import unip.model.Termin.Eventtype;

public class Datenmanager {

	private String terminPfad;
	private String modulePfad;
	private String stundenplanPfad;
	private ArrayList<Termin> termine = new ArrayList<Termin>();
	private ArrayList<Modul> module = new ArrayList<Modul>();
	private ArrayList<Stundenplaneintrag> stundenplan = new ArrayList<Stundenplaneintrag>();
	private ArrayList<ToDoEintrag> todoliste = new ArrayList<ToDoEintrag>();
	
	
	public Datenmanager() {
		readAll();
	}

	public ArrayList<Termin> getWoche(Calendar von){
		ArrayList<Termin> termin2 = new ArrayList<Termin>();
		von.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Calendar bis = (Calendar) von.clone();
		bis.add(Calendar.DAY_OF_YEAR, +6); 
		
		for(int i= 0; i<termine.size(); i++) {
			if(termine.get(i).datum.compareTo(von)>=0 && termine.get(i).datum.compareTo(bis)<=0) {
				termin2.add(termine.get(i));
			}
		}
		return termin2;
	}
	
	public ArrayList<Termin> getMonat(Calendar von){
		ArrayList<Termin> termin2 = new ArrayList<Termin>();
		von.set(Calendar.DAY_OF_MONTH, 1);
		Calendar bis = (Calendar) von.clone();
		bis.set(Calendar.DAY_OF_MONTH, bis.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		for(int i= 0; i<termine.size(); i++) {
			if(termine.get(i).datum.compareTo(von)>=0 && termine.get(i).datum.compareTo(bis)<=0) {
				termin2.add(termine.get(i));
			}
		}
		return termin2;
	}
	
	public ArrayList<Termin> getTerminTyp (Eventtype type, boolean zukunft){
		ArrayList<Termin> ausgabe = new ArrayList<Termin>();
		Calendar heute = Calendar.getInstance();
		
		for(int i=0;i<termine.size();i++) {
			if(!zukunft || heute.compareTo(termine.get(i).datum)<=0) {
				if(termine.get(i).kategorie == type) {
					ausgabe.add(termine.get(i));
				}
			}
		}
		getModule(false);
		return ausgabe;
	}
	
	public ArrayList<Modul> getModule (boolean abgeschlossen){ //wenn true nur abeschlossene, Wenn false alle
		ArrayList<Modul> ausgabe = new ArrayList<Modul>();
		
		for(int i = 0; i<module.size(); i++) {
			if(!abgeschlossen || module.get(i).note != 0d) {
				ausgabe.add(module.get(i));
			}
		}
		
		return ausgabe;
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
			if(termine.get(i).getID() == id) {
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
		removeStdEintrag(eintrag.getID());
		addStdEintrag(eintrag);
	}
	
	public void removeStdEintrag (int id) {
		for(int i=0; i<stundenplan.size(); i++) {
			if(stundenplan.get(i).getID() == id) {
				stundenplan.remove(i);
				break;
			}
		}	
	}
	
	public void addToDo(ToDoEintrag eintrag) {
		todoliste.add(eintrag);
	}
	
	public void changeToDo(ToDoEintrag eintrag) {
		for(int i=0;i<todoliste.size();i++) {
			if(todoliste.get(i).notizPosition == eintrag.notizPosition) {
				todoliste.remove(i);
			}
		}
		addToDo(eintrag);
	}
	
	public boolean removeAbgeschlossen() {
		boolean output = false;
		for(int i = 0; i<todoliste.size(); i++) {
			if(todoliste.get(i).abgehakt) {
				todoliste.remove(i);
				output = true;
				i--;
			}
		}
		return output;
	}
	
	private void saveTermin() {
		Gson gson = new Gson();
		JsonArray json = gson.toJsonTree(termine).getAsJsonArray();
		writeToFile(json.toString(), "Termine.json");
	}
	
	private void readTermin() {
		Gson gson = new Gson();
		String json = readFile("Termine.json");
		if(json==null) {
			return;
		}
		JsonArray arr = gson.fromJson(json, JsonArray.class);
		for(JsonElement element : arr) {
			termine.add(gson.fromJson(element, Termin.class));
		}
	}
	
	private void saveModule() {
		Gson gson = new Gson();
		JsonArray json = gson.toJsonTree(module).getAsJsonArray();
		writeToFile(json.toString(), "Module.json");
	}
	
	private void readModule() {
		Gson gson = new Gson();
		String json = readFile("Module.json");
		if(json==null) {
			//Habe hunger auf copy pasta und auch hunger irl
		} else {
			JsonArray arr = gson.fromJson(json, JsonArray.class);
			for(JsonElement element : arr) {
				module.add(gson.fromJson(element, Modul.class));
			}
		}
	}
	
	private void saveStundenplan() {
		Gson gson = new Gson();
		JsonArray json = gson.toJsonTree(stundenplan).getAsJsonArray();
		writeToFile(json.toString(), "Stundenplan.json");
	}
	
	private void readStundenplan() {
		Gson gson = new Gson();
		String json = readFile("Stundenplan.json");
		if(json==null) {
			return;
		}
		JsonArray arr = gson.fromJson(json, JsonArray.class);
		for(JsonElement element : arr) {
			stundenplan.add(gson.fromJson(element, Stundenplaneintrag.class));
		}
	}
	
	private void saveToDo() {
		Gson gson = new Gson();
		JsonArray json = gson.toJsonTree(todoliste).getAsJsonArray();
		writeToFile(json.toString(), "ToDoListe.json");
	}
	
	private void readToDo() {
		Gson gson = new Gson();
		String json = readFile("ToDoListe.json");
		if(json==null) {
			return;
		}
		JsonArray arr = gson.fromJson(json, JsonArray.class);
		for(JsonElement element : arr) {
			todoliste.add(gson.fromJson(element, ToDoEintrag.class));
		}
	}
	
	public void saveAll() {
		saveTermin();
		saveModule();
		saveStundenplan();
		saveToDo();
	}
	
	private void readAll() {
		readTermin();
		readModule();
		readStundenplan();
		readToDo();
	}
	
	private void writeToFile(String text, String path) {
		try {
			File file = new File(path);
			
			file.createNewFile();
			
		    FileWriter myWriter = new FileWriter(file);
	
		    myWriter.write(text);
		      
		    myWriter.close();
		} catch (Exception e) {
		      e.printStackTrace();
		}
	}
	
	private String readFile(String path) {
		try {
			File file = new File(path);
			if(file.exists()) {
				Scanner myReader = new Scanner(file);
				String data = "";
				while (myReader.hasNextLine()) {
					data += myReader.nextLine();
				}
				myReader.close();
				return data;
			} else {
				return null;
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
}

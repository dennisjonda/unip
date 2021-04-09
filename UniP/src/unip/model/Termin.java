package unip.model;
import java.time.LocalTime;
import java.util.Calendar;

import unip.model.Termin.Eventtype;


public class Termin {
	public static enum Eventtype{KLAUSUR, TESTAT, UNI, FREIZEIT}
	private int terminID;
	public String titel;
	public String beschreibung;
	public int von;
	public int bis;
	public Calendar datum;
	public Eventtype kategorie;
	
	
	public Termin (int terminID, String titel, String beschreibung, int von, int bis, Calendar datum, Eventtype kategorie) {
		this.terminID = terminID;
		this.titel = titel;
		this.beschreibung = beschreibung; 
		this.von = von;
		this.bis = bis;
		this.datum = datum;
		this.kategorie = kategorie;
		
	}
	
	public static Eventtype stringToEventType(String input) {
		switch (input) {
		case "Freizeit":
			return Eventtype.FREIZEIT;
		case "Uni":
			return Eventtype.UNI;
		case "Klausur":
			return Eventtype.KLAUSUR;
		case "Testat":
			return Eventtype.TESTAT;
		default:
			return Eventtype.UNI;
		}
	}
	
	public int getID() {
		return terminID;
	}
}

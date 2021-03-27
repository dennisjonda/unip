package unip.model;
import java.time.LocalTime;
import java.util.Calendar;


public class Termin {
	public static enum Eventtype{Klausur, Testat, Uni, Freizeit}
	private int terminID;
	public String titel;
	public String beschreibung;
	public LocalTime von;
	public LocalTime bis;
	public Calendar datum;
	public Eventtype kategorie;
	
	
	public Termin (int terminID, String titel, String beschreibung, LocalTime von, LocalTime bis, Calendar datum, Eventtype kategorie) {
		this.terminID = terminID;
		this.titel = titel;
		this.beschreibung = beschreibung; 
		this.von = von;
		this.bis = bis;
		this.datum = datum;
		this.kategorie = kategorie;
		
	}
	
	public int getID() {
		return terminID;
	}
}

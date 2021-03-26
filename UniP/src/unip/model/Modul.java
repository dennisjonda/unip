package unip.model;

public class Modul {

	private int modulID; 
	public String modulbezeichnung;
	public String modulname;
	public int semester;
	public String prüfungsform;
	public String prüfungsvoraussetzung;
	public double note = 0d;
	public int crp;
	
	public Modul (int modulID, String modulbezeichnung, String modulname, String prüfungsform, String prüfungsvoraussetzung, double note, int crp) { 
		this.modulID = modulID;
		this.modulbezeichnung = modulbezeichnung;
		this.modulname = modulname;
		this.prüfungsform = prüfungsform;
		this.prüfungsvoraussetzung = prüfungsvoraussetzung;
		this.note = note;
		this.crp = crp;
	}
	
	public Modul (int modulID, String modulbezeichnung, String modulname, String prüfungsform, String prüfungsvoraussetzung, int crp) {
		this.modulID = modulID;
		this.modulbezeichnung = modulbezeichnung;
		this.modulname = modulname;
		this.prüfungsform = prüfungsform;
		this.prüfungsvoraussetzung = prüfungsvoraussetzung;
		this.crp = crp;
	}
	
	public int getID() {
		return modulID;
	}
}

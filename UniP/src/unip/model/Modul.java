package unip.model;

public class Modul {

	private int modulID; // Methode?
	public String modulbezeichnung;
	public String modulname;
	public int semester;
	public String prüfungsform;
	public String prüfungsvoraussetzung;
	public int note;
	public int crp;
	
	public Modul (int modulID, String modulbezeichnung, String modulname, String prüfungsform, String prüfungsvoraussetzung, int note, int crp) { // woher bekommt die methode die iD?
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

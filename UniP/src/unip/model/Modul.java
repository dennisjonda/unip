package unip.model;

public class Modul {

	private int modulID; 
	public String modulbezeichnung;
	public String modulname;
	public int semester;
	public String pr�fungsform;
	public String pr�fungsvoraussetzung;
	public double note = 0d;
	public int crp;
	
	public Modul (int modulID, String modulbezeichnung, String modulname, String pr�fungsform, String pr�fungsvoraussetzung, double note, int crp) { 
		this.modulID = modulID;
		this.modulbezeichnung = modulbezeichnung;
		this.modulname = modulname;
		this.pr�fungsform = pr�fungsform;
		this.pr�fungsvoraussetzung = pr�fungsvoraussetzung;
		this.note = note;
		this.crp = crp;
	}
	
	public Modul (int modulID, String modulbezeichnung, String modulname, String pr�fungsform, String pr�fungsvoraussetzung, int crp) {
		this.modulID = modulID;
		this.modulbezeichnung = modulbezeichnung;
		this.modulname = modulname;
		this.pr�fungsform = pr�fungsform;
		this.pr�fungsvoraussetzung = pr�fungsvoraussetzung;
		this.crp = crp;
	}
	
	public int getID() {
		return modulID;
	}
}

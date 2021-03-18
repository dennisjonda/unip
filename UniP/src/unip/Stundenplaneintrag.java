package unip;

public class Stundenplaneintrag {

	private int blockID; // setzt sich aus TagBlock zusammen
	public String bezeichnung;
	
	private Stundenplaneintrag(String bezeichnung, int tag, int block) { 
		this.bezeichnung = bezeichnung;
		this.blockID = tag + block;
	}
	
	public int getID() {
		return this.blockID;
	}
	
	public int getBlock() {
		return (this.blockID / 10) % 10;
	}
	
	public int getTag() {
		return (this.blockID %10);
	}
	
	public void setBlock(int tag, int block) { // Wieso keine setTag Methode?
		
	}
	
}

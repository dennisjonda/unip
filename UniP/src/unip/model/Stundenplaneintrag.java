package unip.model;

public class Stundenplaneintrag {

	private int blockID; // setzt sich aus TagBlock zusammen
	public String bezeichnung;
	
	private Stundenplaneintrag(String bezeichnung, int tag, int block) { 
		this.bezeichnung = bezeichnung;
		this.blockID = (tag*10) + block;
	}
	
	public int getID() {
		return blockID;
	}
	
	public int getBlock() {
		return blockID % 10;
	}
	
	public int getTag() {
		return blockID / 10;
	}
	
	public void setBlock(int tag, int block) {
		blockID = (tag*10) + block;
	}
	
}

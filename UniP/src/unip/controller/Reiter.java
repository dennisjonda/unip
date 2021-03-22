package unip.controller;

public abstract class Reiter {
	private boolean active = false;
	protected String gui;
	
	public void setActive(boolean active) {
		this.active=active;
		update();
	}
	
	protected abstract void update();
	
	public abstract void initialize();
	
}

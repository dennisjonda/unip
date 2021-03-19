package unip.controller;

public abstract class Reiter {
	private boolean active = false;
	
	public void setActive(boolean active) {
		this.active=active;
		update();
	}
	
	protected abstract void update();
	
}

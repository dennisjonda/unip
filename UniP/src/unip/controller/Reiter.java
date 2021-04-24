package unip.controller;

public abstract class Reiter {
	private boolean active = false;
	protected String gui;
	
	public abstract void update();
	
	public abstract void initialize();
	
}

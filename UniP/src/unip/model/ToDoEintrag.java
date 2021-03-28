package unip.model;

public class ToDoEintrag {

	public int notizPosition;
	public String notiz;
	public boolean abgehakt;
	
	public ToDoEintrag (int notizPosition, String notiz, boolean abgehakt) {
		this.notizPosition = notizPosition;
		this.notiz = notiz;
		this.abgehakt = abgehakt; 
	}
}

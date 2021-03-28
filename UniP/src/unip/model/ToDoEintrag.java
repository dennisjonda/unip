package unip.model;

public class ToDoEintrag {

	public int NotizPosition;
	public String Notiz;
	public boolean Abgehakt;
	
	public ToDoEintrag (int NotizPosition, String Notiz, boolean Abgehakt) {
		this.NotizPosition = NotizPosition;
		this.Notiz = Notiz;
		this.Abgehakt = Abgehakt; 
	}
}


public class Carte {
	
	private int valeur;
	private String couleur;
	private int trophee;
	private boolean visible;
	
	public Carte() {
		this.valeur = 0;
		this.couleur = "";
		this.visible = false;
	}
	
	public Carte(int valeur, String couleur) {
		this.valeur = valeur;
		this.couleur = couleur;
		this.visible = false;
	}
	
	public void montrer() {
		this.visible = true;
	}
	
	public void cacher() {
		this.visible = false;
	}
	
	public String toString(){
	      return  valeur +" de "+couleur;
	}
	
	

}

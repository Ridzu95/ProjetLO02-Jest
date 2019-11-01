
public class Carte {
	
	private int valeur;
	private String couleur;
	private String trophee;
	private boolean visible;
	
	public Carte() {
		this.valeur = 0;
		this.couleur = "";
		this.trophee = "";
		this.visible = false;
	}

	public Carte(int valeur, String couleur, String trophee) {
		this.valeur = valeur;
		this.couleur = couleur;
		this.trophee = trophee;
		this.visible = true;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getTrophee() {
		return trophee;
	}

	public void setTrophee(int trophee) {
		this.trophee = trophee;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
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

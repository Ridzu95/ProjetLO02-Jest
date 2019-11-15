package fr.utt.lo02.xfmv.jest.model.cartes;

public class Carte {
	
	private Valeurs valeur;
	private Couleurs couleur;
	private Trophees trophee;
	private boolean visible;
	
	public Carte() {

	}

	public Carte(Valeurs valeur, Couleurs couleur) {
		this.valeur = valeur;
		this.couleur = couleur;
	}

	public Couleurs getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleurs couleur) {
		this.couleur = couleur;
	}

	public void setTrophee(Trophees trophee) {
		this.trophee = trophee;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Valeurs getValeur() {
		return valeur;
	}

	public void setValeur(Valeurs valeur) {
		this.valeur = valeur;
	}

	public void montrer() {
		this.visible = true;
	}
	
	public void cacher() {
		this.visible = false;
	}
	
	public String toString(){
	      return  valeur + " de " + couleur;
	}
	
	

}

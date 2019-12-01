package fr.utt.lo02.xfmv.jest.model.cartes;

import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;

public class Carte implements Comparable<Carte>{
	
	private Valeurs valeur;
	private Couleurs couleur;
	private Trophees trophee;
	private boolean visible;
	
	public Carte() {}

	public Carte(Valeurs valeur, Couleurs couleur) {
		this.valeur = valeur;
		this.couleur = couleur;
		this.visible = true;
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

	public Trophees getTrophee() { return this.trophee; }

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Valeurs getValeur() {
		return this.valeur;
	}
	
	public boolean getVisible() {
		return this.visible;
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
	
	public int compareTo(Carte carte) {

		int sorter = carte.getValeur().getValeur() - carte.getValeur().getValeur();
		if (sorter == 0) {
			sorter = carte.getCouleur().getOrdre() - carte.getCouleur().getOrdre();
			return sorter;
		}
		else {
			return sorter;
		}

	}
	
	public String toString(){
		if (this.visible == true) {
			if (this.couleur == Couleurs.Joker) {
				return String.valueOf(this.couleur.getCouleur());
			} else {
				return valeur.getValeur() + couleur.getCouleur();
			}
		}
		else {
			return "[]";
		}
	}
	
	

}

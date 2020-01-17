package fr.utt.lo02.xfmv.jest.model.card;

/**
 * Class for the Card model in this Jest game.
 * Each card in the game has a value, a color, an associated trophy and can be visible or not.
 */

public class Card implements Comparable<Card> {
	
	private Values value;
	private Colors color;
	private Trophies trophy;
	private boolean visible;
	
	public Card() {}

	public Card(Values value, Colors couleur) {
		this.value = value;
		this.color = couleur;
		this.visible = true;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public void setTrophy(Trophies trophy) {
		this.trophy = trophy;
	}

	public Trophies getTrophy() { return this.trophy; }

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Values getValue() {
		return this.value;
	}
	
	public boolean getVisible() {
		return this.visible;
	}

	public void setValue(Values value) {
		this.value = value;
	}

	public void montrer() {
		this.visible = true;
	}
	
	public void cacher() {
		this.visible = false;
	}

	/**
	 * Compares the value of two cards and return an int for comparison results
	 * @param card
	 * @return int
	 */

	public int compareTo(Card card) {

		int sorter = card.getValue().getValeur() - this.getValue().getValeur();
		if (sorter == 0) {
			sorter = card.getColor().getOrdre() - this.getColor().getOrdre();
			return sorter;
		}
		else {
			return sorter;
		}

	}

	public String toString(){
		if (this.visible == true) {
			if (this.color == Colors.Joker) {
				return String.valueOf(this.color.getColor());
			} else {
				return value.getValeur() + color.getColor();
			}
		}
		else {
			return "[]";
		}
	}
	
	

}

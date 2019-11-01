import java.util.ArrayList;

public class Joueur {
	
	protected int id;
	protected ArrayList<Carte> main;
	protected ArrayList<Carte> jest;

	public Joueur() {
		this.id = 0;
	}

	public Joueur(int id) {
		this.id = id;
	}
	
	public void faireOffre() { //va appeller soit faireOffre de virtuel ou réel
		
	}
	
	public void prendreOffre() { //va appeller soit prendrOffre de virtuel ou réel
		
	}
	
}


public class Partie implements Variante {
	
	private int nbJoueurs;
	private Main[] pioche;
	private Variante variante; 
	private int tour;
	
	public Partie(Variante variante) {
		this.variante = variante;
		//this.nbJoueurs = variante.nbJoueurs(); // méthode qui va renvoyer le nb de joueurs selon la variante (3 ou 4)
		//on fait des actions en fonction de la variante
		this.tour = 0;
	}
	
	public Partie() { //partie créee par défaut
		this.tour = 0;
		//créer la pioche
	}
	
	public void debuterPartie() {
		//créer les joueurs
		//le reste se fait dans le main ?
	}
	
	public void distribuerCartes() { //est appellé en début de chaque tour
		
	}
	
	public void terminerTour() { 
		
	}
	
	public void declarerVainqueur() { //est appellé en fin de partie
		
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}
	
	
}

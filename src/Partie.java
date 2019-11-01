
public class Partie implements Variante {

	private int nbJoueurs;
	// private Variante variante;
	private int tour;
	
	public Partie(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
		// this.variante = variante;
		//this.nbJoueurs = variante.nbJoueurs(); // méthode qui va renvoyer le nb de joueurs selon la variante (3 ou 4)
		//on fait des actions en fonction de la variante
		this.tour = 1;
	}
	
	public Partie() { //partie créee par défaut
		this.tour = 1;
		//créer la pioche
	}
	
	public void debuterPartie() {
		System.out.println("Vous avez choisi une partie à " + this.nbJoueurs + " joueurs, il faut donc créer " + (this.nbJoueurs - 1) + " bots.");

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
		return this.tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public int getNbJoueurs() {
		return this.nbJoueurs;
	}
	
}

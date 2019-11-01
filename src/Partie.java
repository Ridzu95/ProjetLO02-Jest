
public class Partie implements Variante {

	private int nbJoueurs;
	// private Variante variante;
	private int tour;
	
	public Partie(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
		// this.variante = variante;
		//this.nbJoueurs = variante.nbJoueurs(); // m�thode qui va renvoyer le nb de joueurs selon la variante (3 ou 4)
		//on fait des actions en fonction de la variante
		this.tour = 1;
	}
	
	public Partie() { //partie cr�ee par d�faut
		this.tour = 1;
		//cr�er la pioche
	}
	
	public void debuterPartie() {
		System.out.println("Vous avez choisi une partie � " + this.nbJoueurs + " joueurs, il faut donc cr�er " + (this.nbJoueurs - 1) + " bots.");

		//cr�er les joueurs
		//le reste se fait dans le main ?
	}
	
	public void distribuerCartes() { //est appell� en d�but de chaque tour
		
	}
	
	public void terminerTour() { 
		
	}
	
	public void declarerVainqueur() { //est appell� en fin de partie
		
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

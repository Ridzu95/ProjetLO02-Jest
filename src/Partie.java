
public class Partie implements Variante {
	
	private int nbJoueurs;
	private Main[] pioche;
	private Variante variante; 
	private int tour;
	
	public Partie(Variante variante) {
		this.variante = variante;
		//this.nbJoueurs = variante.nbJoueurs(); // m�thode qui va renvoyer le nb de joueurs selon la variante (3 ou 4)
		//on fait des actions en fonction de la variante
		this.tour = 0;
	}
	
	public Partie() { //partie cr�ee par d�faut
		this.tour = 0;
		//cr�er la pioche
	}
	
	public void debuterPartie() {
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
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}
	
	
}

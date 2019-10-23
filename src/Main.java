
public class Main {

	public static void main(String[] args) {
		Partie partie = new Partie();
		
		partie.debuterPartie();
		
		while (partie.getTour() != 4) {//jspu combien y'a de tour au max mais j'ai mis 4
			
			partie.distribuerCartes();
			
			//les joueurs font leurs actions
			
			partie.terminerTour();
			
			partie.setTour(partie.getTour() + 1); //incrémentation peut aussi être mis dans terminerTour()
		}
		
		partie.declarerVainqueur();
	}
}


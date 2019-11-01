import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {

        System.out.println("--- Jeu de Jest inventé par Brett J. Gilbert ---");
        System.out.println("Pour l'instant, vous ne pouvez jouer qu'en solo contre des bots. \n");
        System.out.println("Choisissez le nombre de joueurs : \n 1 - Trois joueurs \n 2 - Quatre joueurs \n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        int nbJoueurs = 0;
        if (choice == 1) {
            nbJoueurs = 3;
        }
        else if (choice == 2) {
            nbJoueurs = 4;
        }
        else {
            System.out.println("erreur");
        }

        System.out.println("Création des joueurs");

        Partie partie = new Partie(nbJoueurs);
        partie.debuterPartie();

        System.out.println("Invocation des cartes");

        Carte joker = new Carte(0, "joker", "Best Jest");
        Carte asDeCoeur = new Carte(1, "Coeur", "Joker");
        Carte deuxDeCoeur = new Carte(2, "Coeur", "Joker");
        Carte troisDeCoeur = new Carte(3, "Coeur", "Joker");
        Carte quatreDeCoeur = new Carte(4, "Coeur", "Joker");
        Carte asDeCarreau = new Carte(1, "Carreau", "Majority 4");
        Carte deuxDeCarreau = new Carte(2, "Carreau", "Highest Carreau");
        Carte troisDeCarreau = new Carte(3, "Carreau", "Lowest Carreau");
        Carte quatreDeCarreau = new Carte(4, "Carreau", "Best Jest No Joker");
        Carte asDePique = new Carte(1, "Pique", "Highest Trefle");
        Carte deuxDePique = new Carte(2, "Pique", "Majority 3");
        Carte troisDePique = new Carte(3, "Pique", "Majority 2");
        Carte quatreDePique = new Carte(4, "Pique", "Lowest Trefle");
        Carte asDeTrefle = new Carte(1, "Trefle", "Highest Pique");
        Carte deuxDeTrefle = new Carte(2, "Trefle", "Lowest Coeur");
        Carte troisDeTrefle = new Carte(3, "Trefle", "Highest Coeur");
        Carte quatreDeTrefle = new Carte(4, "Trefle", "Lowest Pique");

        public enum Cartes {
            joker,
            asDeCoeur,
            deuxDeCoeur,
            troisDeCoeur,
            // à faire quoi
        }

        for (Cartes carte : Cartes.values()) {
            partie.addToPioche(carte);
        }
        partie.distribuerCartes();

        /* Partie partie = new Partie();

        partie.debuterPartie();

        while (partie.getTour() != 4) {//jspu combien y'a de tour au max mais j'ai mis 4

            partie.distribuerCartes();

            //les joueurs font leurs actions

            partie.terminerTour();

            partie.setTour(partie.getTour() + 1); //incrémentation peut aussi être mis dans terminerTour()
        }

        partie.declarerVainqueur(); */
    }
}

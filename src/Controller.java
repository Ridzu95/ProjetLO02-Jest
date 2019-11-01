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

        Partie partie = new Partie(nbJoueurs);
        partie.debuterPartie();

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

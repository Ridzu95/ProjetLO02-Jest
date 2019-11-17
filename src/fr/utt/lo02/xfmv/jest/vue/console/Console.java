package fr.utt.lo02.xfmv.jest.vue.console;

import java.util.Scanner;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurReel;

public abstract class Console {

    public static void welcomeMessage() {
        System.out.println("--- Jeu de Jest inventé par Brett J. Gilbert ---");
    }

    public static String playerUsernameChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez votre pseudo : ");
        String username = sc.nextLine();
        return username;
    }

    public static int cardChoice(JoueurReel joueur) {

        System.out.println("Choisissez la carte à cacher :");

        System.out.println("(1) --- " + joueur.getMain().get(0));
        System.out.println("(2) --- " + joueur.getMain().get(1));

        return joueur.faireOffre();

    }
    
    public static void showBoard(JoueurReel joueur) {
    	
    }

    public static void tellHiddenCard(JoueurReel joueur) {

        for(Carte carte : joueur.getMain()) {
            if (carte.isVisible() == false) {
                System.out.println("La carte " + carte.toString() + " est cachée !");
            }
        }

    }
}

package fr.utt.lo02.xfmv.jest.vue.console;

import java.util.ArrayList;
import java.util.Scanner;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;

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


    public static void tellHiddenCard(Joueur joueur) {
                System.out.println("La carte " + joueur.getCarteCachee().toString() + " est cachée !");
           

    }
    
    public static void showBoard(ArrayList<Joueur> joueurs) {
    	System.out.println("Voici les cartes de la main de chaque joueur");
    	System.out.println("");
    	for (Joueur joueur : joueurs) {
    		System.out.println("Le joueur " + joueur + " possèdes :");
    		System.out.println(joueur.getCarteVisible() + " et une carte face cachée");
    	}
    }
}

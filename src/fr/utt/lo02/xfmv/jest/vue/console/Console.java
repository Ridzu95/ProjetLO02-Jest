package fr.utt.lo02.xfmv.jest.vue.console;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;
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
    
    // permet de récupérer le choix du joueur pour la variante
    
    public static int demanderVariante() {

        Scanner sc = new Scanner(System.in);
    	int choice = 0;
    	
    	System.out.println("Choisissez une variante");
    	System.out.println("(1) -- Variante de base : les trophées sont assignés selon les règles classiques" );
    	System.out.println("(2) -- Variante 1 : les trophées sont assignés aléatoirement" );
    	System.out.println("(3) -- Variante 2 : les trophées sont assignés selon les règles classiques mais sont inconnus" );
    	System.out.println("");
    	
    	do {
            System.out.print("Votre choix : ");
            System.out.println("");
            choice = sc.nextInt();
        } while (choice !=1 && choice != 2 && choice !=3);
        
        
        return choice;
    }

    public static void cardChoice(Joueur joueur) {

        System.out.println("");
        System.out.println("Choisissez la carte à cacher :");

        System.out.println("(1) --- " + joueur.getMain().get(0));
        System.out.println("(2) --- " + joueur.getMain().get(1));

        return;
    }


    public static void displayPlayerCards(ArrayList<Joueur> joueurs) {

        for (Joueur joueur : joueurs) {
            System.out.print(joueur.toString() + " --- ");
            for (Carte carte : joueur.getMain()) {
                System.out.print(carte.toString() + " ");
            }
            System.out.println("");
        }

    }

    public static void displaySelectCards(ArrayList<Carte> selectCards) {

        System.out.println("Choisis la carte à mettre dans ton Jest :");
        for (int i = 0; i < selectCards.size(); i++ ) {
            System.out.println("(" + (i + 1) + ") --- " + selectCards.get(i).toString());
        }


    }

    /*
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
    */
}

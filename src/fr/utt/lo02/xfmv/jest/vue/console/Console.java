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

    public static void cardChoice(Joueur joueur) {

        showTrophies(Partie.getInstance().getTropheesPartie());
        System.out.println("");
        System.out.println("Choisissez la carte à cacher :");

        System.out.println("(1) --- " + joueur.getMain().get(0));
        System.out.println("(2) --- " + joueur.getMain().get(1));

        return;
    }

    public static void showTrophies(ArrayList<Carte> tropheesPartie) {
        System.out.println("Les deux trophées pour cette partie sont :");
        for(Carte carte : tropheesPartie) {
            System.out.println(carte.toString() + " --- " + carte.getTrophee().getDescription());
        }
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

    public static void showTurn() {
        System.out.println("TOUR : " + Partie.getInstance().getTour());
        System.out.println("");
    }

    /*
    public static void tellHiddenCard(Joueur joueur) {

        for(Carte carte : joueur.getMain()) {
            if (carte.isVisible() == false) {
                System.out.println("La carte " + carte.toString() + " est cachée !");
            }
        }

    }
    */
}

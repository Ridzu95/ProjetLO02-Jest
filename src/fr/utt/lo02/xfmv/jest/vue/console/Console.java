package fr.utt.lo02.xfmv.jest.vue.console;

import java.awt.*;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.*;
import java.util.concurrent.BlockingQueue;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;
import fr.utt.lo02.xfmv.jest.model.joueurs.JoueurReel;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;

public class Console implements Runnable {

    private final BlockingQueue<Integer> queue;
    private Scanner scan;
    private Partie partie;

    private Joueur joueur; //faire majAfficher() avec partie en passant un joueur pour pouvoir
    //prendre des infos sur lui et les afficher

    public Console(BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.partie = Partie.getInstance();
    }

    public String playerUsernameChoice(int id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le pseudo du joueur n° " + id);
        String username = sc.nextLine();
        return username;
    }


    public void displayPlayerCards(ArrayList<Joueur> joueurs) {

        for (Joueur joueur : joueurs) {
            System.out.print(joueur.toString() + " --- ");
            for (Carte carte : joueur.getMain()) {
                System.out.print(carte.toString() + " ");
            }
            System.out.println("");
        }

    }

    public void displaySelectCards(ArrayList<Carte> selectCards, Joueur joueur) {

        System.out.println(joueur + " choisis la carte à mettre dans ton Jest :");
        for (int i = 0; i < selectCards.size(); i++) {
            System.out.println("(" + (i + 1) + ") --- " + selectCards.get(i).toString());
        }


    }

    public void showTurn(int tour) {
        System.out.println("");
        System.out.println("*********");
        System.out.println(" TOUR " + tour);
        System.out.println("*********");
        System.out.println("");
    }

    public void showJests() {
        System.out.println("Révélez vos Jests !");
        for (Joueur joueur : Partie.getInstance().getJoueurs()) {
            System.out.print(joueur.toString() + ": ");
            for (Carte carte : joueur.getJest()) {
                carte.setVisible(true);
                System.out.print(carte.toString() + " ");
            }
            System.out.println("");
        }
    }

    public static void showScores() {
        System.out.println("");
        System.out.println("Voilà les scores !");
        for (Joueur joueur : Partie.getInstance().getJoueurs()) {
            System.out.println(joueur.toString() + " : " + joueur.getScore());
        }
        System.out.println("");
    }

    public static void showWinner(Joueur winner) {
        System.out.println("Le gagnant de la partie est " + winner.toString() + " !");
    }

    public void endOfGame() throws InterruptedException {
        System.out.println("(1) --- Retourner au menu\n(2) --- Quitter");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            try {
                System.out.print("Votre choix : ");
                choice = sc.nextInt();
                System.out.println("");
            } catch (InputMismatchException e) {
                System.out.println("Format invalide.");
            }
            sc.nextLine();
        } while (choice != 1 && choice != 2);
        switch (choice) {
            case 1:
                //this.showMenu();
                break;
            case 2:
                System.exit(0);
                break;
        }
    }


    //Partie producer

    @Override
    public void run() {
        try {
            this.process();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void majAffichage() throws InterruptedException {
        System.out.println("");
        if (partie.isStarted() == false) {
            System.out.println("(1) --- Jouer\n(2) --- Lire les règles\n(3) --- Quitter");
        }

        if(partie.isStarted()==true && partie.isSetup()==false && (partie.getPlayerCount() == -1 || partie.getRealPlayerCount() == -1 || partie.getVariante() == null) ) {
            if (partie.getPlayerCount() == -1) {
                System.out.println("Voulez-vous jouer à 3 ou 4 joueurs ?");

            } else if (partie.getRealPlayerCount() == -1) {
                System.out.println("Combien y a-t-il de joueurs réels dans la partie ?");

            } else if (partie.getVariante() == null) {
                System.out.println("Choisissez une variante pour la partie :\n" +
                        "(1) -- Variante de base : les trophées sont assignés selon les règles classiques\n" +
                        "(2) -- Variante 1 : les trophées sont assignés aléatoirement\n" +
                        "(3) -- Variante 2 : les trophées sont assignés selon les règles classiques mais sont inconnus");
            }
        }

        if (this.partie.getGamePhase() == "sélection de la carte à cacher" && Partie.getInstance().checkCardsStates() == false){
            System.out.println("## Mains des joueurs : \n"
                    + Partie.getInstance().getJoueurs().get(0) + " possède : " + Partie.getInstance().getJoueurs().get(0).getMain().get(0) + " --- " + Partie.getInstance().getJoueurs().get(0).getMain().get(1) + "\n"
                    + Partie.getInstance().getJoueurs().get(1) + " possède : " + Partie.getInstance().getJoueurs().get(1).getMain().get(0) + " --- " + Partie.getInstance().getJoueurs().get(1).getMain().get(1) + "\n"
                    + Partie.getInstance().getJoueurs().get(2) + " possède : " + Partie.getInstance().getJoueurs().get(2).getMain().get(0) + " --- " + Partie.getInstance().getJoueurs().get(2).getMain().get(1) + "\n"
            );
            if ( Partie.getInstance().getPlayerCount() == 4 ){
                System.out.println(Partie.getInstance().getJoueurs().get(3) + " possèdes : " + Partie.getInstance().getJoueurs().get(3).getMain().get(0) + " --- " + Partie.getInstance().getJoueurs().get(3).getMain().get(1)
                );

            }
            System.out.println("Choisir la carte à cacher pour le joueur " + Partie.getInstance().getCurrentPlaying());

        }
        if (Partie.getInstance().getGamePhase() == "sélection de la carte à cacher" && Partie.getInstance().getRealPlayerCount() == 0){
            System.out.println("Les bots ont automatiquement choisis leurs carte");
        }
        if (this.partie.getGamePhase() == "sélection de la carte à mettre dans le Jest"){

        }


        System.out.println("Votre choix : ");
    }

    public void process() throws InterruptedException {
        this.majAffichage();
        this.scan = new Scanner(System.in);
        int msg = -1;

        msg = this.scan.nextInt(); //le thread reste bloqué ici s'il n'y a pas d'input

        this.queue.put(msg); //on envoit le msg dès qu'il y a un input
        Thread.sleep(2000); //besoin d'attentre que la partie aie finit ses manips pour maj l'affichage sinon cv pas
        run();
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}

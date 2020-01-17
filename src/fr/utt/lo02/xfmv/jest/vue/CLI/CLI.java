package fr.utt.lo02.xfmv.jest.vue.CLI;

import java.util.*;
import java.util.concurrent.BlockingQueue;

import fr.utt.lo02.xfmv.jest.controller.Game;
import fr.utt.lo02.xfmv.jest.model.card.Card;
import fr.utt.lo02.xfmv.jest.model.player.Player;
import fr.utt.lo02.xfmv.jest.model.player.RealPlayer;

/**
 * Class for CLI display.
 * Can display information on the current phase of the game and wait for a user input.
 * Similar to the GUI, user input is an int message that is processed in the game's class message queue.
 * Runs in a separate thread, so we can do parallel processing between the game engine, the CLI and the GUI.
 * If the user input needed is done via GUI instead, CLI scanner interrupts and display what is should next.
 * This class also contains punctual information to display that don't need user input like turn, jest display and score display.
 */

public class CLI implements Runnable {

    private final BlockingQueue<Integer> queue;
    private Scanner scan;
    private Game game;

    private Player player;

    public CLI(BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.game = Game.getInstance();
    }

    /**
     * Simple print-out to display at the beginning of each turn.
     * @param tour : turn in the Jest game
     */

    public void showTurn(int tour) {
        System.out.println();
        System.out.println("*********");
        System.out.println(" TOUR " + tour);
        System.out.println("*********");
        System.out.println();
    }

    /**
     * Prints every player's Jest in the CLI, using cards custom toString() method.
     */

    public void showJests() {
        System.out.println("Révélez vos Jests !");
        for (Player player : Game.getInstance().getPlayers()) {
            System.out.print(player.toString() + ": ");
            for (Card card : player.getJest()) {
                card.setVisible(true);
                System.out.print(card.toString() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints score of each player in CLI.
     */

    public void showScores() {
        System.out.println();
        System.out.println("Voilà les scores !");
        for (Player player : Game.getInstance().getPlayers()) {
            System.out.println(player.toString() + " : " + player.getScore());
        }
        System.out.println();
    }

    public void showWinner(Player winner) {
        System.out.println("Le gagnant de la partie est " + winner.toString() + " !");
    }
    
    public static void showTrophyWinner(Player player, Card trophee) {
    	System.out.println(player + " a remporté le trophée : " + trophee.getTrophy().getDescription());
		System.out.println();
    }

    /**
     * Calls process() method.
     */

    public void run() {
        try {
            this.process();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void displayUpdate() throws InterruptedException {
        System.out.println();
        if (game.isStarted() == false) {
            System.out.println("(1) --- Jouer\n(2) --- Lire les règles\n(3) --- Quitter");
        }

        if(game.isStarted()==true && game.isSetup()==false && (game.getPlayerCount() == -1 || game.getRealPlayerCount() == -1 || game.getVariant() == null) ) {
            if (game.getPlayerCount() == -1) {
                System.out.println("Voulez-vous jouer à 3 ou 4 joueurs ?");

            } else if (game.getRealPlayerCount() == -1) {
                System.out.println("Combien y a-t-il de joueurs réels dans la partie ?");

            } else if (game.getVariant() == null) {
                System.out.println("Choisissez une variante pour la partie :\n" +
                        "(1) -- Variante de base : les trophées sont assignés selon les règles classiques\n" +
                        "(2) -- Variante 1 : les trophées sont assignés aléatoirement\n" +
                        "(3) -- Variante 2 : les trophées sont assignés selon les règles classiques mais sont inconnus");
            }
        }

        if (this.game.getGamePhase() == "hiding"){
            if (this.game.getCurrentPlaying() instanceof RealPlayer){
                System.out.println("Le joueur " + Game.getInstance().getCurrentPlaying() + " doit choisir une carte à cacher parmi "+
                        Game.getInstance().getCurrentPlaying().getMain().get(0) + " - " + Game.getInstance().getCurrentPlaying().getMain().get(1));
            }
        }

        if (this.game.getGamePhase() == "jesting"){
            if (Game.getInstance().getCurrentPlaying() instanceof RealPlayer){
                System.out.println("Le joueur " + Game.getInstance().getCurrentPlaying() + " dois choisir une carte à mettre dans son Jest parmi :");
                for (Card card : Game.getInstance().getSelectCards()){
                    System.out.println("-- " + card);
                }

            }
        }

        System.out.println("Votre choix : ");
    }

    /**
     * Calls displayUpdate() and waits for an int message from the user.
     * The consumer interrupts this process if it receives the information via GUI instead.
     * @throws InterruptedException
     */

    public void process() throws InterruptedException {
        this.displayUpdate();

        this.scan = new Scanner(System.in);
        int msg = -1;

        msg = this.scan.nextInt(); //le thread reste bloqué ici s'il n'y a pas d'input

        this.queue.put(msg); //on envoit le msg dès qu'il y a un input
        Thread.sleep(500); //besoin d'attentre que la partie aie finit ses manips pour maj l'affichage sinon cv pas
        run();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

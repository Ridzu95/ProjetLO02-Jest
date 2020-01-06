package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;
import fr.utt.lo02.xfmv.jest.vue.console.Console;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GUI;

import java.awt.*;
import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class PartieController implements Runnable{ //Consumer

    private Partie partie;
    private BlockingQueue<Integer> queue;
    private GUI gui;
    private Console console;

    public PartieController(Partie partie){
        this.partie = partie;
        this.queue =  new LinkedBlockingQueue<>(2);
    }

    public void run() {

        while (true){ //le thread tourne en boucle pour reçevoir des informations
            try {
                int msg = queue.take(); //fait rien sauf si un message est présent
                this.process(msg); //dans ce cas on envoie le message dans process pour le traiter
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void process(int msg) throws InterruptedException { //traitement du message

        if (this.partie.isStarted() == false){
            switch (msg) {
                case 1 :
                    this.partie.setStarted(true);
                    new Thread(gui).start();
                    break;
                case 2 :
                    try {
                        URI uri = new URI("https://puu.sh/EKl29/655216593a.png");
                        Desktop.getDesktop().browse(uri);
                    }
                    catch(Exception ex) {}
                    //this.showMenu();
                    break;
                case 3 :
                    System.exit(0);
                    break;
                default :
                    System.out.println("Format incorrecte");
            }
        }

        else if (this.partie.getPlayerCount() == -1){
            if (msg == 4 || msg == 3){
                this.partie.setPlayerCount(msg);
            } else {
                System.out.println("Format incorrect dd");
            }
        }

        else if (this.partie.getRealPlayerCount() == -1){
            if (msg > Partie.getInstance().getPlayerCount() || msg < 0){
                System.out.println("Format incorrect cc");
            } else {
                this.partie.setRealPlayerCount(msg);
            }
        }

        else if (this.partie.getVariante() == null){
            switch (msg){
                case 1:
                    this.partie.setVariante(new Variantebase());
                    break;
                case 2:
                    this.partie.setVariante(new Variante1());
                    break;
                case 3:
                    this.partie.setVariante(new Variante2());
                default:
                    System.out.println("Format incorrecte aa");
            }
        }

        if ( this.partie.getGamePhase() == "hiding"){
            if (msg == 1 || msg == 2){
                this.partie.setMessage(msg);
            } else {
                System.out.println("Format incorrect bb");
            }
        }

        if ( this.partie.getVariante() != null && this.partie.getRealPlayerCount() != -1 && this.partie.getPlayerCount() != -1 && this.partie.getGamePhase() == "init"){
            this.partie.setSetup(true);
            new Thread(Partie.getInstance()).start(); //lancement du moteur de jeu
            new Thread(this.gui).start();
        }

        if (Partie.getInstance().getGamePhase() == "hiding" && Partie.getInstance().checkCardsStates()) {
            Partie.getInstance().setHidingPhasePlayed(true); //passage à la phase de jest
        }

        if (Partie.getInstance().getGamePhase() == "jesting"){
            this.partie.setMessage(msg);
        }


        this.run(); // on retourne dans la boucle

    }






    public Partie getPartie() {
        return partie;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public BlockingQueue<Integer> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}

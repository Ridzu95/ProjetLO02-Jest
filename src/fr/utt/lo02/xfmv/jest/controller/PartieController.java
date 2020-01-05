package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;
import fr.utt.lo02.xfmv.jest.vue.Message;
import fr.utt.lo02.xfmv.jest.vue.console.Console;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GUI;

import java.awt.*;
import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class PartieController implements Runnable{

    private Partie partie;
    private BlockingQueue<Message> queue;
    private GUI gui;
    private Console console;

    public PartieController(Partie partie){
        this.partie = partie;
        this.queue =  new LinkedBlockingQueue<>(2);
    }

    public void run() {

        while (true){ //le thread tourne en boucle pour reçevoir des informations
            try {
                Message msg = queue.take();
                this.process(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void process(Message msg) throws InterruptedException { //traitement du message

        if (msg.getKey() == "menu"){
            switch ((int) msg.getValue()) {
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
            }
        }

        if (msg.getKey() == "nbplayer"){
            this.partie.setPlayerCount((int) msg.getValue());
        }

        if (msg.getKey() == "nbrealplayer"){
            this.partie.setRealPlayerCount((int) msg.getValue());
        }

        if (msg.getKey() == "variante"){
            switch ((int) msg.getValue()){
                case 1:
                    this.partie.setVariante(new Variantebase());
                    break;
                case 2:
                    this.partie.setVariante(new Variante1());
                    break;
                case 3:
                    this.partie.setVariante(new Variante2());
            }
        }

        if ( this.partie.getVariante() != null && this.partie.getRealPlayerCount() != -1 && this.partie.getPlayerCount() != -1){
            this.partie.setSetup(true);
            new Thread(this.gui).start();
            new Thread(this.partie);
            this.console.majAffichage();
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

    public BlockingQueue<Message> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}

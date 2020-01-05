package fr.utt.lo02.xfmv.jest;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.controller.PartieController;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.vue.console.Console;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GUI;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Menu;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Partie.getInstance();
        PartieController partieController = new PartieController(Partie.getInstance());


        GUI gui = new GUI(partieController.getQueue());
        partieController.setGui(gui);

        Partie.getInstance().setQueue(partieController.getQueue());

        Console console = new Console(partieController.getQueue());
        partieController.setConsole(console);


        Thread partieControllerThread = new Thread(partieController);
        partieControllerThread.start();

        //il faut lancer une première fois les thread de gui et console
        new Thread(partieController.getConsole()).start();
        new Thread(partieController.getGui()).start();

    }
}

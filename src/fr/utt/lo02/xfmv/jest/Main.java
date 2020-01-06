package fr.utt.lo02.xfmv.jest;

import com.formdev.flatlaf.FlatLightLaf;
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

        FlatLightLaf.install(); // installation du Look and Feel FlatLaf

        Partie.getInstance();
        PartieController partieController = new PartieController(Partie.getInstance());
        //on a besoin d'un controller sur la partie

        GUI gui = new GUI(partieController.getQueue());
        partieController.setGui(gui);

        Partie.getInstance().setQueue(partieController.getQueue());
        //la queue commune à tous les threads et les classes est créee dans PartieController et passée à Partie

        Console console = new Console(partieController.getQueue());
        partieController.setConsole(console);
        Partie.getInstance().setConsole(console);


        Thread partieControllerThread = new Thread(partieController);
        partieControllerThread.start();
        //le thread de controller c'est la boucle qui en permanence lit les message qu'elle reçoit

        //il faut lancer une première fois les thread de gui et console
        new Thread(partieController.getGui()).start();
        new Thread(partieController.getConsole()).start();

    }
}

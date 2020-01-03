package fr.utt.lo02.xfmv.jest;

import fr.utt.lo02.xfmv.jest.controller.Partie;
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


        GUI gui = new GUI(Partie.getInstance().getQueue());
        Partie.getInstance().setGUI(gui);

        Console console = new Console(Partie.getInstance().getQueue());
        Partie.getInstance().setCons(console);

        new Thread(gui).start();
        new Thread(console).start();

        Thread partieThread = new Thread(Partie.getInstance());
        partieThread.start();

    }
}

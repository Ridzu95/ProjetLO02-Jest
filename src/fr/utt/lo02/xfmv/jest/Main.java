package fr.utt.lo02.xfmv.jest;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.vue.console.Console;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Menu;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu menu = new Menu();
                    menu.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        /**
        Console.welcomeMessage();
        Console.showMenu();
        **/
    }
}

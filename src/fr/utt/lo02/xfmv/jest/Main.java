package fr.utt.lo02.xfmv.jest;

import fr.utt.lo02.xfmv.jest.controller.Partie;
import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.vue.console.Console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console.welcomeMessage();
        Partie.getInstance().initialiserPartie();

    }
}

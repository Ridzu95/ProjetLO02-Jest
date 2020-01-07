package fr.utt.lo02.xfmv.jest.controller;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Menu;

import javax.management.openmbean.TabularData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.concurrent.BlockingQueue;

public class MenuController {


    public MenuController(Menu menu) {

        menu.getPlayButton().addActionListener(new ActionListener() {



            public void actionPerformed(ActionEvent e) {
                try {
                    Partie.getInstance().getQueue().put(1);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    Partie.getInstance().getConsole().majAffichage();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        menu.getRulesButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    Partie.getInstance().getQueue().put(2);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        menu.getQuitButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    Partie.getInstance().getQueue().put(3);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

}

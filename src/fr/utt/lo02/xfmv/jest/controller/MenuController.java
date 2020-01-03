package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.vue.Message;
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
                    System.out.println("DEBUG MenuController:25 : Un message a été envoyé par le Menu");
                    Partie.getInstance().getQueue().put(new Message("menu", 1 ));

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        menu.getRulesButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://puu.sh/EKl29/655216593a.png");
                    Desktop.getDesktop().browse(uri);
                }
                catch(Exception ex) {}
            }
        });

        menu.getQuitButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

}

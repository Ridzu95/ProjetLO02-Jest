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

    private BlockingQueue<Integer> queue;
    private Integer integer;

    public BlockingQueue<Integer> getQueue() {
        return queue;
    }

    public MenuController(Menu menu, BlockingQueue<Integer> queue) {
        this.queue = queue;
        this.integer = -1;

        menu.getPlayButton().addActionListener(new ActionListener() {



            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("input envoyé par le menu");
                    Partie.getInstance().getQueue().put(1);

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

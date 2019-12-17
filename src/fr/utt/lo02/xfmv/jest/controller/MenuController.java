package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class MenuController {

    public MenuController(JButton playButton, JButton rulesButton, JButton quitButton) {

        playButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Partie.getInstance().initialiserPartie();
            }
        });

        rulesButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://puu.sh/EKl29/655216593a.png");
                    Desktop.getDesktop().browse(uri);
                }
                catch(Exception ex) {}
            }
        });

        quitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

}

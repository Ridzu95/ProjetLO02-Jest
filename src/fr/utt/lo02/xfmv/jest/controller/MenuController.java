package fr.utt.lo02.xfmv.jest.controller;
import fr.utt.lo02.xfmv.jest.vue.GUI.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the menu GUI.
 * Adds events listeners for the buttons in GUI.
 * Clicking on a button sends an integer-type message in the game message queue (from 1 to 3).
 * At this state, the game engine waits for input.
 * It was made this way to allow people to play in CLI and GUI at the same time.
 */

public class MenuController {


    public MenuController(Menu menu) {

        menu.getPlayButton().addActionListener(new ActionListener() {



            public void actionPerformed(ActionEvent e) {
                try {
                    Game.getInstance().getQueue().put(1);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                try {
                    Game.getInstance().getCLI().displayUpdate();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        menu.getRulesButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    Game.getInstance().getQueue().put(2);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        menu.getQuitButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    Game.getInstance().getQueue().put(3);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

}

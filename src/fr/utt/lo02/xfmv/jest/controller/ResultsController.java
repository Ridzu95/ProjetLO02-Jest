package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.vue.GUI.Results;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the results GUI.
 * Adds events listeners for the "back to menu" button in GUI.
 */

public class ResultsController {

    public ResultsController(Results results) {

        results.getMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Game.getInstance().getQueue().put(1);
                    Thread.sleep(500);
                    Game.getInstance().setGamePhase("init");

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

}

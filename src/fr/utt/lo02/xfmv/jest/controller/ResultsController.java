package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.vue.graphicInterface.Results;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsController {

    public ResultsController(Results results) {

        results.getMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Partie.getInstance().getQueue().put(1);
                    Thread.sleep(500);
                    Partie.getInstance().setGamePhase("init");

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

}

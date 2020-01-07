package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GameConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class GameConfigController {

    public GameConfigController(GameConfig gameConfig) {

        gameConfig.getButtonStartGame().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (((Integer) gameConfig.getSpinnerPlayers().getValue()).intValue() >= ((Integer) gameConfig.getSpinnerRealPlayers().getValue()).intValue()) {
                    try {
                        Partie.getInstance().getQueue().put(((Integer) gameConfig.getSpinnerPlayers().getValue()).intValue());
                        Partie.getInstance().getQueue().put(((Integer) gameConfig.getSpinnerRealPlayers().getValue()).intValue());
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }


                    switch ((String) gameConfig.getSpinnerVariante().getValue()) {
                        case "Normale":
                            try {
                                Partie.getInstance().getQueue().put(1);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            break;
                        case "Aléatoire":
                            try {
                                Partie.getInstance().getQueue().put(2);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            break;
                        case "Caché":
                            try {
                                Partie.getInstance().getQueue().put(3);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            break;
                    }
                }
            }
        });

    }

}

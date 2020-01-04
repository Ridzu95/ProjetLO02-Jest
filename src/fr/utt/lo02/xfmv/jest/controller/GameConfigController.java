package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.variantes.Variante1;
import fr.utt.lo02.xfmv.jest.model.variantes.Variante2;
import fr.utt.lo02.xfmv.jest.model.variantes.Variantebase;
import fr.utt.lo02.xfmv.jest.vue.Message;
import fr.utt.lo02.xfmv.jest.vue.graphicInterface.GameConfig;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class GameConfigController {

    public GameConfigController(GameConfig gameConfig) {

        gameConfig.getBeginGameButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    Partie.getInstance().getQueue().put(new Message("nbplayer", ((Integer) gameConfig.getPlayersCount().getValue()).intValue() ) );
                    Partie.getInstance().getQueue().put(new Message("nbrealplayer", ((Integer) gameConfig.getRealPlayersCount().getValue()).intValue() ));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }


                switch ((String) gameConfig.getVarianteSelect().getValue()) {
                    case "Normal":
                        try {
                            Partie.getInstance().getQueue().put(new Message("variante", 1));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Aléatoire":
                        try {
                            Partie.getInstance().getQueue().put(new Message("variante", 2));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        break;
                    case "Caché":
                        try {
                            Partie.getInstance().getQueue().put(new Message("variante", 3));
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        break;
                }

                //Partie.getInstance().setSetup(true);
                //à faire dans Partie
            }
        });

    }

}

package fr.utt.lo02.xfmv.jest.controller;

import fr.utt.lo02.xfmv.jest.model.cartes.Carte;
import fr.utt.lo02.xfmv.jest.model.cartes.Couleurs;
import fr.utt.lo02.xfmv.jest.model.cartes.Valeurs;
import fr.utt.lo02.xfmv.jest.model.joueurs.Joueur;

public class CompteurVarianteBase implements Compteur {

    @Override
    public void compterJests(Joueur joueur) {

        int score = 0;
        int coeurCounter = 0;
        int asCounter = 0;
        boolean hasJoker = false;

        for (Carte carteTest : joueur.getJest()) {
            if (carteTest.getCouleur() == Couleurs.Coeur) {
                coeurCounter++;
            }
            if (carteTest.getValeur() == Valeurs.Joker) {
                hasJoker = true;
            }
        }

        if (hasJoker == true && coeurCounter == 0) {
            score += 4;
        }

        for (Carte carte : joueur.getJest()) {

            int cardValue = 0;
            boolean onlyAceInThatSuit = true;

            if (carte.getValeur() == Valeurs.As) {
                for (Carte carteTestAs : joueur.getJest()) {
                    if (carteTestAs.getCouleur() == carte.getCouleur()) {
                        onlyAceInThatSuit = false;
                    }
                }
                if (onlyAceInThatSuit) {
                    cardValue = 5;
                }
                else {
                    cardValue = 1;
                }
            }
            else {
                cardValue = carte.getValeur().getValeur();
            }

            if (carte.getCouleur() == Couleurs.Trefle) {
                score += cardValue;
            }
            else if (carte.getCouleur() == Couleurs.Carreau) {
                score -= cardValue;
            }
            else if (carte.getCouleur() == Couleurs.Coeur) {
                if (hasJoker == true) {
                    if (coeurCounter == 4) {
                        score += cardValue;
                    }
                    else {
                        score -= cardValue;
                    }
                }
            }
            else if (carte.getCouleur() == Couleurs.Pique) {
                for (Carte carteTestPaire : joueur.getJest()) {
                    if (carteTestPaire.getCouleur() == Couleurs.Trefle && carteTestPaire.getValeur() == carte.getValeur()) {
                        score += 2;
                    }
                }
                score += cardValue;
            }

            joueur.setScore(score);

        }

    }

}

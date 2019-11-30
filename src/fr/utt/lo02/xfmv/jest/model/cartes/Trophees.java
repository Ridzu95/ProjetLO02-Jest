package fr.utt.lo02.xfmv.jest.model.cartes;

import java.util.Random;

public enum Trophees {

    HighestTrefle(1, "Ce trophée revient au joueur avec la meilleure carte en trèfle."),
    HighestCarreau(2, "Ce trophée revient au joueur avec la meilleure carte en carreau."),
    HighestCoeur(3, "Ce trophée revient au joueur avec la meilleure carte en coeur."),
    HighestPique(4, "Ce trophée revient au joueur avec la meilleure carte en pique."),
    LowestTrefle(5, "Ce trophée revient au joueur avec la moins bonne carte en trèfle."),
    LowestCarreau(6, "Ce trophée revient au joueur avec la moins bonne carte en carreau."),
    LowestCoeur(7, "Ce trophée revient au joueur avec la moins bonne carte en coeur."),
    LowestPique(8, "Ce trophée revient au joueur avec la moins bonne carte en pique."),
    MajorityDeux(9, "Ce trophée revient au joueur avec le plus de 2."),
    MajorityTrois(10, "Ce trophée revient au joueur avec le plus de 3."),
    MajorityQuatre(11, "Ce trophée revient au joueur avec le plus de 4."),
    BestJest(12, "Ce trophée revient au joueur avec le meilleur Jest."),
    BestJestNoJoker(13, "Ce trophée revient au joueur avec le meilleur Jest sans compter le Joker."),
    Joker(14, "Ce trophée revient au joueur qui détient le Joker.");

    private String description;
    private int id;

    Trophees(int id, String description) {
        this.id = id;
        this.description = description;
    }
    
    /*méthode qui va permettre de renvoyer un trophée aléatoire */
    
    public static Trophees getRandomTrophee() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    public String getDescription() { return description; }
    public int getId() { return id; }
}

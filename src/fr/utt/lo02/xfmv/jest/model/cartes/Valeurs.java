package fr.utt.lo02.xfmv.jest.model.cartes;

public enum Valeurs {
    Un(1),
    Deux(2),
    Trois(3),
    Quatre(4),
    Cinq(5);

    private int valeur;

    Valeurs(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() { return valeur;}
}